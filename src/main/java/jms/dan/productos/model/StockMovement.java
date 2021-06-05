package jms.dan.productos.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer inputAmount;
    private Integer outputAmount;
    private Instant date;
    @ManyToOne
    private Product product;
    @OneToOne
    private ProvisionDetail provisionDetail;

    public StockMovement() {
    }

    public StockMovement(Integer id, Integer inputAmount, Integer outputAmount, Instant date, Product product, ProvisionDetail provisionDetail) {
        this.id = id;
        this.inputAmount = inputAmount;
        this.outputAmount = outputAmount;
        this.date = date;
        this.product = product;
        this.provisionDetail = provisionDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(Integer inputAmount) {
        this.inputAmount = inputAmount;
    }

    public Integer getOutputAmount() {
        return outputAmount;
    }

    public void setOutputAmount(Integer outputAmount) {
        this.outputAmount = outputAmount;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProvisionDetail getProvisionDetail() {
        return provisionDetail;
    }

    public void setProvisionDetail(ProvisionDetail provisionDetail) {
        this.provisionDetail = provisionDetail;
    }
}
