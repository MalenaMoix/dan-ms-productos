package jms.dan.productos.service;

import jms.dan.productos.domain.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product getProductById(Integer id);

    void createProduct(Product newProduct);
}
