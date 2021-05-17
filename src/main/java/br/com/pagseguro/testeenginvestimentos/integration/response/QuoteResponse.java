package br.com.pagseguro.testeenginvestimentos.integration.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.pagseguro.testeenginvestimentos.integration.domain.Cotacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class QuoteResponse implements Serializable {

    private List<Cotacao> result;

}
