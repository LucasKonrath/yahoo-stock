package org.acme.stocks.integration.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class GetQuotesResponse implements Serializable {

    private static final long serialVersionUID = 8788725964022636284L;
    private QuoteResponse quoteResponse;
}
