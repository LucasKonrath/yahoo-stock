package org.acme.stocks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.stocks.entity.StockEntity;
import org.acme.stocks.fixture.Fixture;
import org.acme.stocks.integration.domain.Quote;
import org.acme.stocks.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StocksControllerTest {

    @MockBean
    private SaveStocksService saveStocksService;

    @MockBean
    private AddStockService addStockService;

    @MockBean
    private DeleteStocksService deleteStocksService;

    @MockBean
    private ListStocksService listStocksService;

    @MockBean
    private FindStocksByCodeService findStocksByCodeService;

    @MockBean
    private FindStocksByNameService findStocksByNameService;

    @MockBean
    private FindStocksAverageService findStocksAverageService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /stocks/upload")
    public void testUpload() throws Exception {

        final MockMultipartFile mockFile = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());


        mockMvc.perform(multipart("/stocks/upload")
                        .file(mockFile))
                .andExpect(status().isOk());

        verify(saveStocksService).saveStocks(mockFile);
    }

    @Test
    @DisplayName("POST /stocks")
    public void testSaveStock() throws Exception {
        final StockEntity mockStock = Fixture.make(StockEntity.class);

        mockMvc.perform(post("/stocks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockStock))
        ).andExpect(status().isOk());

        verify(addStockService).add(mockStock);
    }

    @Test
    @DisplayName("DELETE /stocks/{id}")
    public void deleteStock() throws Exception {

        final Long id = Fixture.make(Long.class);

        mockMvc.perform(delete("/stocks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(deleteStocksService).delete(id);
    }

    @Test
    @DisplayName("GET /stocks")
    public void listStocks() throws Exception {

        final StockEntity firstStock = Fixture.make(StockEntity.class);
        final List<StockEntity> mockStocks = asList(firstStock);

        doReturn(mockStocks).when(listStocksService).listStocks();

        mockMvc.perform(get("/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(firstStock.getId())))
                .andExpect(jsonPath("$[0].code", is(firstStock.getCode())))
                .andExpect(jsonPath("$[0].stock", is(firstStock.getStock())))
                .andExpect(jsonPath("$[0].theoricQuantity", is(firstStock.getTheoricQuantity())))
                .andExpect(jsonPath("$[0].share", is(firstStock.getShare())));


        verify(listStocksService).listStocks();
    }

    @Test
    @DisplayName("GET /stocks/code/{code}")
    public void getStockByCode() throws Exception {

        final StockEntity firstStock = Fixture.make(StockEntity.class);
        final List<StockEntity> mockStocks = asList(firstStock);

        doReturn(mockStocks).when(findStocksByCodeService).find(firstStock.getCode());

        mockMvc.perform(get("/stocks/code/{code}", firstStock.getCode())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(firstStock.getId())))
                .andExpect(jsonPath("$[0].code", is(firstStock.getCode())))
                .andExpect(jsonPath("$[0].stock", is(firstStock.getStock())))
                .andExpect(jsonPath("$[0].theoricQuantity", is(firstStock.getTheoricQuantity())))
                .andExpect(jsonPath("$[0].share", is(firstStock.getShare())));


        verify(findStocksByCodeService).find(firstStock.getCode());
    }

    @Test
    @DisplayName("GET /stocks/name/{name}")
    public void getStockByName() throws Exception {

        final StockEntity firstStock = Fixture.make(StockEntity.class);
        final List<StockEntity> mockStocks = asList(firstStock);

        doReturn(mockStocks).when(findStocksByNameService).find(firstStock.getStock());

        mockMvc.perform(get("/stocks/name/{name}", firstStock.getStock())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(firstStock.getId())))
                .andExpect(jsonPath("$[0].code", is(firstStock.getCode())))
                .andExpect(jsonPath("$[0].stock", is(firstStock.getStock())))
                .andExpect(jsonPath("$[0].theoricQuantity", is(firstStock.getTheoricQuantity())))
                .andExpect(jsonPath("$[0].share", is(firstStock.getShare())));


        verify(findStocksByNameService).find(firstStock.getStock());
    }

    @Test
    @DisplayName("GET /stocks/average")
    public void getStocksAverage() throws Exception {

        final Quote firstQuote = Fixture.make(Quote.class);
        final List<Quote> mockQuotes = asList(firstQuote);

        doReturn(mockQuotes).when(findStocksAverageService).find();

        mockMvc.perform(get("/stocks/average")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].fiftyDayAverage", is(firstQuote.getAverage50Days())))
                .andExpect(jsonPath("$[0].twoHundredDayAverage", is(firstQuote.getAverage200Days())))
                .andExpect(jsonPath("$[0].symbol", is(firstQuote.getCode())));


        verify(findStocksAverageService).find();
    }
}
