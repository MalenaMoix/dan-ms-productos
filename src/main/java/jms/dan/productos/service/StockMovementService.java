package jms.dan.productos.service;

import jms.dan.productos.exceptions.ApiException;
import jms.dan.productos.model.StockMovement;
import jms.dan.productos.repository.IStockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMovementService implements IStockMovementService {
    final IStockMovementRepository stockMovementRepository;

    @Autowired
    public StockMovementService(IStockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
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
}
