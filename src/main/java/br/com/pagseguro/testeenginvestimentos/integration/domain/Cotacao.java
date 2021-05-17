package br.com.pagseguro.testeenginvestimentos.integration.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cotacao implements Serializable {

    @JsonProperty("fiftyDayAverage")
    private BigDecimal precoMedio50dias;

    @JsonProperty("twoHundredDayAverage")
    private BigDecimal precoMedio200dias;

    @JsonProperty("symbol")
    private String codigo;

}
