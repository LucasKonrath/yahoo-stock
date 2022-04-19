package org.acme.stocks.service;

import org.acme.stocks.entity.StockEntity;
import org.acme.stocks.integration.YahooFinanceIntegration;
import org.acme.stocks.integration.domain.Quote;
import org.acme.stocks.integration.response.GetAutoCompleteResponse;
import org.acme.stocks.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindStocksAverageService {

    @Autowired
    private StockRepository stocksRepository;

    @Autowired
    private YahooFinanceIntegration yahooFinanceIntegration;

    public List<Quote> find() {

        final Page<StockEntity> stocks = stocksRepository.findAll(PageRequest.of(0, 10, Direction.DESC, "theoricQuantity"));

        final List<GetAutoCompleteResponse> autoCompleteResponses =
                stocks.stream()
                        .map(StockEntity::getCode)
                        .map(code -> yahooFinanceIntegration.getAutoComplete(code))
                        .collect(Collectors.toList());

        final List<String> codes = autoCompleteResponses.stream()
                .map(auto -> auto.getQuotes())
                .map(list -> list.stream().map(quote -> quote.getCode()).findFirst().orElse(null))
                .collect(Collectors.toList());

        return yahooFinanceIntegration.getQuotes(codes)
                .getQuoteResponse()
                .getResult();
    }
}
