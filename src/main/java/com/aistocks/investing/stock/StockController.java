package com.aistocks.investing.stock;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aistocks.investing.exception.ResourceNotFoundException;

@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    private StockService service;

    @GetMapping
    public List<Stock> list() {
        return service.list();
    }

    @GetMapping("{id}")
    public Stock get(@PathVariable long id) throws ResourceNotFoundException {
        return service.get(id);
    }

    @PostMapping
    public ResponseEntity<Stock> create(@Valid @RequestBody Stock stock) {
        return ResponseEntity.ok(service.create(stock));
    }

    @PutMapping("{id}")
    public ResponseEntity<Stock> update(@PathVariable long id, @Valid @RequestBody Stock stock)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(service.update(id, stock));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Stock> delete(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.delete(id));
    }

}
