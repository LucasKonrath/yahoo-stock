package org.acme.stocks.service;

import org.acme.stocks.fixture.Fixture;
import org.acme.stocks.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FindStocksByNameServiceTest {

    @InjectMocks
    private FindStocksByNameService findStocksByNameService;

    @Mock
    private StockRepository stockRepository;

    @Test
    public void testFindByName() {
        final String name = Fixture.make(String.class);

        findStocksByNameService.find(name);

        verify(stockRepository).findAllByStockContains(name);
    }
}
