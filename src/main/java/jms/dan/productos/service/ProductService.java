package jms.dan.productos.service;

import jms.dan.productos.domain.Product;
import jms.dan.productos.exceptions.ApiException;
import jms.dan.productos.repository.IProductRepository;
import jms.dan.productos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Service
public class ProductService implements IProductService {
    final IProductRepository productRepository;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setActualStock(product.getActualStock());
        productRepository.save(newProduct);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {

        if (productRepository.findById(id).isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND.toString(), "Product not found", HttpStatus.NOT_FOUND.value());
        }

        return productRepository.findById(id).get();
    }
}
