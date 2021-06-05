package jms.dan.productos.controller;

import jms.dan.productos.exceptions.ApiError;
import jms.dan.productos.exceptions.ApiException;
import jms.dan.productos.model.StockMovement;
import jms.dan.productos.service.StockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stock-movements")
public class StockMovementController {
    final StockMovementService stockMovementService;

    @Autowired
    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @PostMapping
    public ResponseEntity<?> createStockMovement(@RequestBody StockMovement stockMovement) {
        try {
            stockMovementService.createStockMovement(stockMovement);
            return ResponseEntity.status(HttpStatus.CREATED).body("Stock Movement created successfully");
        } catch (ApiException e) {
            return new ResponseEntity<>(
                    new ApiError(e.getCode(), e.getDescription(), e.getStatusCode()),
                    HttpStatus.valueOf(e.getStatusCode()));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStockMovementById(@PathVariable Integer id) {
        try {
            StockMovement stockMovement = stockMovementService.getStockMovementById(id);
            return new ResponseEntity<>(stockMovement, HttpStatus.OK);
        } catch (ApiException e) {
            return new ResponseEntity<>(
                    new ApiError(e.getCode(), e.getDescription(), e.getStatusCode()),
                    HttpStatus.valueOf(e.getStatusCode()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getStockMovements() {
        List<StockMovement> stockMovements = stockMovementService.getStockMovements();
        return ResponseEntity.ok(stockMovements);
    }
}
