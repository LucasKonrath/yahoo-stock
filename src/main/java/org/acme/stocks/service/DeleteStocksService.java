package org.acme.stocks.service;

import org.acme.stocks.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteStocksService {

    @Autowired
    private StockRepository repository;

    public void delete(final Long id) {
        repository.deleteById(id);
    }
}
