package jms.dan.productos.repository;

import jms.dan.productos.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByName(String name);

    @Query("SELECT p FROM Product p WHERE (p.actualStock > :minStock AND p.actualStock < :maxStock AND p.price = :price) OR (p.actualStock > :minStock AND p.actualStock < :maxStock AND :price IS NULL) OR (p.actualStock > :minStock AND :maxStock IS NULL AND :price = p.price) OR (p.actualStock > :minStock AND :maxStock IS NULL AND :price IS NULL) OR (:minStock IS NULL AND p.actualStock < :maxStock AND :price IS NULL) OR (:minStock IS NULL AND p.actualStock < :maxStock AND :price = p.price) OR (:minStock IS NULL AND :maxStock IS NULL AND p.price = :price)")
    List<Product> findByStockAndPrice(@Param("minStock") Integer minStock, @Param("maxStock") Integer maxStock, @Param("price") Double price);

    @Query("SELECT p FROM Product p WHERE (p.actualStock > :minStock AND p.actualStock < :maxStock) OR (p.actualStock > :minStock AND :maxStock IS NULL) OR (p.actualStock < :maxStock AND :minStock IS NULL)")
    List<Product> findByStock(@Param("minStock") Integer minStock, @Param("maxStock") Integer maxStock);

    @Query("SELECT p FROM Product p WHERE (p.actualStock < :maxStock AND p.price = :price) OR (p.actualStock < :maxStock AND :price IS NULL) OR (:maxStock IS NULL AND p.price = :price)")
    List<Product> findByPriceAndMaxStock(@Param("maxStock") Integer maxStock, @Param("price") Double price);

    @Query("SELECT p FROM Product p WHERE (p.actualStock > :minStock AND p.price = :price) OR (p.actualStock > :minStock AND :price IS NULL) OR (:minStock IS NULL AND p.price = :price)")
    List<Product> findByPriceAndMinStock(@Param("minStock") Integer minStock, @Param("price") Double price);

}
