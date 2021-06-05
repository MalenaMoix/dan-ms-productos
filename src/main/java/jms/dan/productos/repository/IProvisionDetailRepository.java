package jms.dan.productos.repository;

import jms.dan.productos.model.ProvisionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProvisionDetailRepository extends JpaRepository<ProvisionDetail, Integer> {
}
