package com.aistocks.investing.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.aistocks.investing.exception.ResourceNotFoundException;

@Service
@CacheConfig(cacheNames={"users"})
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> list() {
        return stockRepository.findAll();
    }

    @Cacheable
    public Stock get(long id) throws ResourceNotFoundException {
        return findById(id);
    }

    @CachePut
    public Stock create(Stock stock) {
        return stockRepository.save(stock);
    }

    @CachePut
    public Stock update(long id, Stock stock) throws ResourceNotFoundException {

        final Stock s = findById(id);

        s.setName(stock.getName());
        s.setTicker(stock.getTicker());

        return stockRepository.save(s);
    }

    @CacheEvict(key="#id")
    public Stock delete(long id) throws ResourceNotFoundException {

        final Stock s = findById(id);

        stockRepository.delete(s);

        return s;
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
