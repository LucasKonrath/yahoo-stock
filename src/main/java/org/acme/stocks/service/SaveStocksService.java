package org.acme.stocks.service;

import org.acme.stocks.entity.StockEntity;
import org.acme.stocks.repository.StockRepository;
import org.acme.stocks.utils.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SaveStocksService {

    @Autowired
    private StockRepository repository;

    public void saveStocks(final MultipartFile file) throws IOException {
        final List<StockEntity> stocks = CSVHelper.csvToStocks(file.getInputStream());
        repository.saveAll(stocks);
    }

}
