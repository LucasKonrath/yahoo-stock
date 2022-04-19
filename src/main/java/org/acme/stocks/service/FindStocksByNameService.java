package org.acme.stocks.service;

import org.acme.stocks.entity.StockEntity;
import org.acme.stocks.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindStocksByNameService {

    @Autowired
    private StockRepository repository;

    public List<StockEntity> find(final String name) {
        return repository.findAllByStockContains(name);
    }

}
