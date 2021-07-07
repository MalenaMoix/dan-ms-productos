package jms.dan.productos.service;

import jms.dan.productos.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts(Integer minStock, Integer maxStock, Double price);

    Product getProductById(Integer id);

    void createProduct(Product newProduct);

    Product getProductByName(String name);

    void deleteProduct(Integer id);
}
