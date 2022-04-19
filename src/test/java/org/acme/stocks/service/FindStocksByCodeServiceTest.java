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
public class FindStocksByCodeServiceTest {

    @InjectMocks
    private FindStocksByCodeService findStocksByCodeService;

    @Mock
    private StockRepository stockRepository;

    @Test
    public void testFindByCode() {
        final String code = Fixture.make(String.class);

        findStocksByCodeService.find(code);

        verify(stockRepository).findAllByCodeContains(code);
    }
}
