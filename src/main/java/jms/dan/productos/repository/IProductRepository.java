package jms.dan.productos.repository;

import jms.dan.productos.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    //List<Product> getProducts();

    //Product getProductById(Integer id);

    //void save(Product newProduct);
}
