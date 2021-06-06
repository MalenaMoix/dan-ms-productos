package jms.dan.productos.service;

import jms.dan.productos.dto.OrderDetailDTO;
import jms.dan.productos.exceptions.ApiException;
import jms.dan.productos.model.Product;
import jms.dan.productos.model.Provision;
import jms.dan.productos.model.ProvisionDetail;
import jms.dan.productos.model.StockMovement;
import jms.dan.productos.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockMovementService implements IStockMovementService {
    private static final Logger logger = LoggerFactory.getLogger(StockMovementService.class);
    final IStockMovementRepository stockMovementRepository;
    final IOrderDetailRepository orderDetailRepository;
    final IProductService productService;
    final IProductRepository productRepository;
    final IProvisionDetailRepository provisionDetailRepository;
    final IProvisionRepository provisionRepository;

    @Autowired
    public StockMovementService(
            IStockMovementRepository stockMovementRepository,
            IOrderDetailRepository orderDetailRepository,
            IProductService productService,
            IProductRepository productRepository,
            IProvisionDetailRepository provisionDetailRepository,
            IProvisionRepository provisionRepository
    ) {
        this.stockMovementRepository = stockMovementRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productService = productService;
        this.productRepository = productRepository;
        this.provisionDetailRepository = provisionDetailRepository;
        this.provisionRepository = provisionRepository;
    }

    @Override
    public void createStockMovement(StockMovement stockMovement) {
        stockMovementRepository.save(stockMovement);
    }

    @Override
    public StockMovement getStockMovementById(Integer id) {
        StockMovement stockMovement = stockMovementRepository.findById(id).orElse(null);
        if (stockMovement == null) {
            throw new ApiException(HttpStatus.NOT_FOUND.toString(), "Stock Movement not found", HttpStatus.NOT_FOUND.value());
        }
        return stockMovement;
    }

    @Override
    public List<StockMovement> getStockMovements() {
        return stockMovementRepository.findAll();
    }

    // TODO probar
    @JmsListener(destination = "COLA_PEDIDOS")
    public void handle(List<Integer> orderDetailIds) throws JmsException {
        logger.info("orderDetailIds size: " + orderDetailIds.size());

        for (Integer id : orderDetailIds) {
            OrderDetailDTO detailDTO = orderDetailRepository.getOrderDetailById(id);
            if (detailDTO == null) {
                throw new ApiException(HttpStatus.NOT_FOUND.toString(), "Order Detail not found", HttpStatus.NOT_FOUND.value());
            }
            registerStockMovement(detailDTO);
        }
    }

    private void registerStockMovement(OrderDetailDTO detailDTO) {
        Product product = productService.getProductById(detailDTO.getProductId());
        product.setActualStock(product.getActualStock() - detailDTO.getQuantity());
        Product newProduct = productRepository.save(product);

        StockMovement stockMovement = new StockMovement();
        stockMovement.setDate(Instant.now());
        stockMovement.setProduct(newProduct);
        stockMovement.setOutputAmount(detailDTO.getQuantity());

        if (newProduct.getActualStock() < newProduct.getMinimumStock()) {
            Provision provision = new Provision();
            provision.setProvisionDate(Instant.now());
            ProvisionDetail provisionDetail = new ProvisionDetail();
            provisionDetail.setProduct(newProduct);
            provisionDetail.setQuantity(newProduct.getMinimumStock());
            ProvisionDetail savedProvisionDetail = provisionDetailRepository.save(provisionDetail);

            List<ProvisionDetail> details = new ArrayList<>();
            details.add(savedProvisionDetail);
            provision.setDetails(details);

            provisionRepository.save(provision);

            stockMovement.setProvisionDetail(savedProvisionDetail);
        }

        createStockMovement(stockMovement);
    }
}
