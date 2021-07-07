package jms.dan.productos.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
public class Provision {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Instant provisionDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProvisionDetail> details;

    public Provision() {
    }

    public Provision(Integer id, Instant provisionDate, List<ProvisionDetail> details) {
        this.id = id;
        this.provisionDate = provisionDate;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getProvisionDate() {
        return provisionDate;
    }

    public void setProvisionDate(Instant provisionDate) {
        this.provisionDate = provisionDate;
    }

    public List<ProvisionDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ProvisionDetail> details) {
        this.details = details;
    }
}
