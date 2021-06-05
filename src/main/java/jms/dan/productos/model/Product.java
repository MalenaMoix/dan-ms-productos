package jms.dan.productos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer actualStock;
    private Integer minimumStock;

    public Product() {

    }

    public Product(Integer id, String name, String description, Double price, Integer actualStock, Integer minimumStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.actualStock = actualStock;
        this.minimumStock = minimumStock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getActualStock() {
        return actualStock;
    }

    public void setActualStock(Integer actualStock) {
        this.actualStock = actualStock;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Integer minimumStock) {
        this.minimumStock = minimumStock;
    }
}
