package org.acme.stocks.service;

import org.acme.stocks.entity.StockEntity;
import org.acme.stocks.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddStockService {

    @Autowired
    private StockRepository repository;

    public void add(final StockEntity stock) {
        repository.save(stock);
    }
}
