package jms.dan.productos.service;

import jms.dan.productos.model.Product;
import jms.dan.productos.exceptions.ApiException;
import jms.dan.productos.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    final IProductRepository productRepository;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product newProduct) {
        Product product = getProductById(id);
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());
        product.setActualStock(newProduct.getActualStock());
        product.setMinimumStock(newProduct.getMinimumStock());
        return productRepository.save(product);
    }

    public List<Product> getProducts(Integer minStock, Integer maxStock, Double price) {
        if (minStock == null && maxStock == null && price == null)
            return productRepository.findAll();
        if (price == null)
            return productRepository.findByStock(minStock, maxStock);
        if (minStock == null)
            return productRepository.findByPriceAndMaxStock(maxStock, price);
        if (maxStock == null)
            return productRepository.findByPriceAndMinStock(minStock, price);

        return productRepository.findByStockAndPrice(minStock, maxStock, price);
    }

    public Product getProductByName(String name) {
        Product product = productRepository.findProductByName(name);
        if (product == null) {
            throw new ApiException(HttpStatus.NOT_FOUND.toString(), "Product not found", HttpStatus.NOT_FOUND.value());
        }
        return product;
    }

    @Override
    public void deleteProduct(Integer id) {
        getProductById(id);
        productRepository.deleteById(id);
    }

    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new ApiException(HttpStatus.NOT_FOUND.toString(), "Product not found", HttpStatus.NOT_FOUND.value());
        }
        return product;
    }
}
