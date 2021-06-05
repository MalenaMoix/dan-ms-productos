package jms.dan.productos.service;

import jms.dan.productos.model.StockMovement;

import java.util.List;

public interface IStockMovementService {
    void createStockMovement(StockMovement stockMovement);

    StockMovement getStockMovementById(Integer id);

    List<StockMovement> getStockMovements();
}
