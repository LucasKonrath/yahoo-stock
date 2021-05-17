package br.com.pagseguro.testeenginvestimentos.integration.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.pagseguro.testeenginvestimentos.integration.domain.AutoCompleteQuote;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAutoCompleteResponse implements Serializable {

    private List<AutoCompleteQuote> quotes;

}
