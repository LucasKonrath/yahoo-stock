package org.acme.stocks.controller;

import org.acme.stocks.entity.StockEntity;
import org.acme.stocks.integration.domain.Quote;
import org.acme.stocks.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StocksController {

    @Autowired
    private SaveStocksService saveStocksService;

    @Autowired
    private AddStockService addStockService;

    @Autowired
    private DeleteStocksService deleteStocksService;

    @Autowired
    private ListStocksService listStocksService;

    @Autowired
    private FindStocksByCodeService findStocksByCodeService;

    @Autowired
    private FindStocksByNameService findStocksByNameService;

    @Autowired
    private FindStocksAverageService findStocksAverageService;

    @PostMapping("/upload")
    public void addStocks(@RequestParam("file") final MultipartFile file) throws IOException {
        saveStocksService.saveStocks(file);
    }

    @PostMapping
    public void addStock(@RequestBody final StockEntity stock) {
        addStockService.add(stock);
    }

    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable final Long id) {
        deleteStocksService.delete(id);
    }

    @GetMapping
    public List<StockEntity> listStocks() {
        return listStocksService.listStocks();
    }

    @GetMapping("/code/{code}")
    public List<StockEntity> findByCode(@PathVariable final String code) {
        return findStocksByCodeService.find(code);
    }

    @GetMapping("/name/{name}")
    public List<StockEntity> findByName(@PathVariable final String name) {
        return findStocksByNameService.find(name);
    }

    @GetMapping("/average")
    public List<Quote> findStocksAverage() {
        return findStocksAverageService.find();
    }

}
