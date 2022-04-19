package org.acme.stocks.service;

import org.acme.stocks.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SaveStocksServiceTest {

    @InjectMocks
    private SaveStocksService saveStocksService;

    @Mock
    private StockRepository stockRepository;

    @Test
    public void saveStocks() throws IOException {

        final MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "ABEV3;AMBEV S/A ON;4355174839;3.096".getBytes());

        saveStocksService.saveStocks(mockMultipartFile);

        verify(stockRepository).saveAll(any());

    }

}
