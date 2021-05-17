package br.com.pagseguro.testeenginvestimentos.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.pagseguro.testeenginvestimentos.integration.response.GetAutoCompleteResponse;
import br.com.pagseguro.testeenginvestimentos.integration.response.GetQuotesResponse;
import lombok.extern.slf4j.Slf4j;

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

    public GetAutoCompleteResponse getAutoComplete(final String codigoAcao){
        final String operationUrl = String.format("auto-complete?q=%s&region=BR", codigoAcao);
        return get(String.format(url, operationUrl), GetAutoCompleteResponse.class);
    }

    public GetQuotesResponse getQuotes(final List<String> codigosAcoes){
        final String operationUrl = String.format("market/v2/get-quotes?region=BR&symbols=%s", String.join(",", codigosAcoes));
        return get(String.format(url, operationUrl), GetQuotesResponse.class);
    }

}
