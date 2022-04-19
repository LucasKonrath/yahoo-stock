package org.acme.stocks.integration.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.acme.stocks.integration.domain.Quote;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class QuoteResponse implements Serializable {

    private static final long serialVersionUID = -8706917735378841411L;
    private List<Quote> result;

}
