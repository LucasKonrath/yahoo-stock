package org.acme.stocks.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerErrorException;

import static java.util.Optional.ofNullable;

@Slf4j
public abstract class AbstractRestApiService {

    @Autowired
    private RestTemplate restTemplate;

    abstract HttpHeaders getHttpHeaders();

    protected <T> T get(final String url, final Class<T> responseClass) {

        final HttpEntity<?> httpEntity = new HttpEntity<>(null, getHttpHeaders());

        try {
            final ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseClass);

            return ofNullable(response)
                    .map(HttpEntity::getBody)
                    .orElseThrow(() -> {
                        log.warn("Null response.");
                        return new ServerErrorException("Internal server error");
                    });

        } catch (final HttpStatusCodeException ex) {

            final String responseBody = ex.getResponseBodyAsString();
            log.error(
                    "Exception on url {} and request {}: [httpStatusCode:{}] [responseBody:{}]",
                    url, ex.getRawStatusCode(), responseBody);

            throw ex;
        }
    }
}
