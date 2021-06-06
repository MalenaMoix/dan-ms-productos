package jms.dan.productos.repository;

import jms.dan.productos.dto.OrderDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OrderDetailDTO, Integer> {
}
