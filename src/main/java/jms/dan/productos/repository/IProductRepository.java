package jms.dan.productos.repository;

import jms.dan.productos.domain.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getProducts();

    Product getProductById(Integer id);

    void createProduct(Product newProduct);
}
