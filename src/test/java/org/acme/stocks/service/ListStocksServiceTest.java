package org.acme.stocks.service;

import org.acme.stocks.entity.StockEntity;
import org.acme.stocks.fixture.Fixture;
import org.acme.stocks.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListStocksServiceTest {

    @InjectMocks
    private ListStocksService listStocksService;

    @Mock
    private StockRepository stockRepository;

    @Test
    public void testFindAll() {

        final List<StockEntity> stocks = Fixture.make(ArrayList.class);

        when(stockRepository.findAll()).thenReturn(stocks);

        final List<StockEntity> actual = listStocksService.listStocks();

        verify(stockRepository).findAll();
        assertEquals(stocks, actual);
    }
}
