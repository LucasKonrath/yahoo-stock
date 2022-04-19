package org.acme.stocks.integration.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoCompleteQuote implements Serializable {

    private static final long serialVersionUID = -7993687570944394855L;

    @JsonProperty("symbol")
    private String code;

}
