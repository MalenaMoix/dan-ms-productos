package jms.dan.productos.service;

import jms.dan.productos.domain.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getProducts(Integer minStock, Integer maxStock, Double price);

    Product getProductById(Integer id);

    void createProduct(Product newProduct);

    public Product getProductByName(String name);
}
