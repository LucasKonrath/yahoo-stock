package org.acme.stocks.service;

import org.acme.stocks.entity.StockEntity;
import org.acme.stocks.fixture.Fixture;
import org.acme.stocks.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddStockServiceTest {

    @InjectMocks
    private AddStockService addStockService;

    @Mock
    private StockRepository stockRepository;

    @Test
    public void testSave() {
        final StockEntity stock = Fixture.make(StockEntity.class);

        addStockService.add(stock);

        verify(stockRepository).save(stock);
    }
}
