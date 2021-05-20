package jms.dan.productos.service;

import jms.dan.productos.domain.Product;
import jms.dan.productos.exceptions.ApiException;
import jms.dan.productos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(Product newProduct) {
        productRepository.createProduct(newProduct);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public Product getProductById(Integer id) {
        Product product = productRepository.getProductById(id);
        if (product == null) {
            throw new ApiException(HttpStatus.NOT_FOUND.toString(), "Product not found", HttpStatus.NOT_FOUND.value());
        }
        return product;
    }
}
