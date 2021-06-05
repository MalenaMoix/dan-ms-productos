package jms.dan.productos.repository;

import jms.dan.productos.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStockMovementRepository extends JpaRepository<StockMovement, Integer> {
}
