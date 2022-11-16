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
    private StockRepository stockRepository;

    @GetMapping
    public List<Stock> list() {
        return stockRepository.findAll();
    }

    @GetMapping("{id}")
    public Stock get(@PathVariable long id) throws ResourceNotFoundException {

        return findById(id);
    }

    @PostMapping
    public ResponseEntity<Stock> create(@Valid @RequestBody Stock stock) {
        return ResponseEntity.ok(stockRepository.save(stock));
    }

    @PutMapping("{id}")
    public ResponseEntity<Stock> update(@PathVariable long id, @Valid @RequestBody Stock stock)
            throws ResourceNotFoundException {

        final Stock s = findById(id);

        s.setName(stock.getName());
        s.setTicker(stock.getTicker());

        return ResponseEntity.ok(stockRepository.save(s));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Stock> delete(@PathVariable long id) throws ResourceNotFoundException {

        final Stock s = findById(id);

        stockRepository.delete(s);

        return ResponseEntity.ok(s);
    }

    /**
     * Finds a stock by its ID
     * 
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    private Stock findById(final long id) throws ResourceNotFoundException {
        return stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with id = " + id));
    }

}
