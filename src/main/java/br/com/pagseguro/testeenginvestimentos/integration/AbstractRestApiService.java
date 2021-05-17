package br.com.pagseguro.testeenginvestimentos.integration;

import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerErrorException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractRestApiService {

    @Autowired
    private RestTemplate restTemplate;

    abstract HttpHeaders getHttpHeaders();

    protected  <T> T get(final String url, final Class<T> responseClass) {

        final HttpEntity<?> httpEntity = new HttpEntity<>(null, getHttpHeaders());

        try {
            final ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseClass);

            return ofNullable(response)
                .map(HttpEntity::getBody)
                .orElseThrow(() -> {
                    log.warn("Response nula na chamada.");
                    return new ServerErrorException("Falha interna");
                });

        } catch (final HttpStatusCodeException ex) {

            final String responseBody = ex.getResponseBodyAsString();
            log.error(
                "Falha na API para url {} e request {}: [httpStatusCode:{}] [responseBody:{}]",
                url, ex.getRawStatusCode(), responseBody);

            throw ex;
        }
    }
}
