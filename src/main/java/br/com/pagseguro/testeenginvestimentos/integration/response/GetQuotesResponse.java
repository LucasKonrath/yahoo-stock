package br.com.pagseguro.testeenginvestimentos.integration.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class GetQuotesResponse implements Serializable {

    private QuoteResponse quoteResponse;
}
