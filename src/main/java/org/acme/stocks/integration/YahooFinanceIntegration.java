package org.acme.stocks.integration;

import lombok.extern.slf4j.Slf4j;
import org.acme.stocks.integration.response.GetAutoCompleteResponse;
import org.acme.stocks.integration.response.GetQuotesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class YahooFinanceIntegration extends AbstractRestApiService {

    @Value("${yahoo-finance.api-key}")
    private String apiKey;

    @Value("${yahoo-finance.base-url}/%s")
    private String url;

    @Override
    protected HttpHeaders getHttpHeaders() {

        final HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", apiKey);

        return headers;
    }

    public GetAutoCompleteResponse getAutoComplete(final String shareCode) {
        final String operationUrl = String.format("auto-complete?q=%s&region=BR", shareCode);
        return get(String.format(url, operationUrl), GetAutoCompleteResponse.class);
    }

    public GetQuotesResponse getQuotes(final List<String> shareCodes) {
        final String operationUrl = String.format("market/v2/get-quotes?region=BR&symbols=%s", String.join(",", shareCodes));
        return get(String.format(url, operationUrl), GetQuotesResponse.class);
    }

}
