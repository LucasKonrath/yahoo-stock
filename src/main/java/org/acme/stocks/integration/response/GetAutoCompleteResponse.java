package org.acme.stocks.integration.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.acme.stocks.integration.domain.AutoCompleteQuote;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAutoCompleteResponse implements Serializable {

    private static final long serialVersionUID = 958748994860513897L;
    private List<AutoCompleteQuote> quotes;

}
