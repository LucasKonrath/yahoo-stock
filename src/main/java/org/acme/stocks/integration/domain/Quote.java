package org.acme.stocks.integration.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote implements Serializable {

    private static final long serialVersionUID = -8804808864413792775L;
    
    @JsonProperty("fiftyDayAverage")
    private BigDecimal average50Days;

    @JsonProperty("twoHundredDayAverage")
    private BigDecimal average200Days;

    @JsonProperty("symbol")
    private String code;

}
