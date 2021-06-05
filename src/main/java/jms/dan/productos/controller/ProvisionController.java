package jms.dan.productos.controller;

import jms.dan.productos.exceptions.ApiError;
import jms.dan.productos.exceptions.ApiException;
import jms.dan.productos.model.Provision;
import jms.dan.productos.service.ProvisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/provisions")
public class ProvisionController {
    final ProvisionService provisionService;

    @Autowired
    public ProvisionController(ProvisionService provisionService) {
        this.provisionService = provisionService;
    }

    @PostMapping
    public ResponseEntity<?> createProvision(@RequestBody Provision newProvision) {
        try {
            provisionService.createProvision(newProvision);
            return ResponseEntity.status(HttpStatus.CREATED).body("Provision created successfully");
        } catch (ApiException e) {
            return new ResponseEntity<>(
                    new ApiError(e.getCode(), e.getDescription(), e.getStatusCode()),
                    HttpStatus.valueOf(e.getStatusCode()));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProvisionById(@PathVariable Integer id) {
        try {
            Provision provision = provisionService.getProvisionById(id);
            return new ResponseEntity<>(provision, HttpStatus.OK);
        } catch (ApiException e) {
            return new ResponseEntity<>(
                    new ApiError(e.getCode(), e.getDescription(), e.getStatusCode()),
                    HttpStatus.valueOf(e.getStatusCode()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getProvisions() {
        List<Provision> provisions = provisionService.getProvisions();
        return ResponseEntity.ok(provisions);
    }
}
