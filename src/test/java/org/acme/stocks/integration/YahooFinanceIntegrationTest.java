package org.acme.stocks.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.acme.stocks.fixture.Fixture;
import org.acme.stocks.integration.response.GetAutoCompleteResponse;
import org.acme.stocks.integration.response.GetQuotesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class YahooFinanceIntegrationTest {

    @InjectMocks
    private YahooFinanceIntegration integration;

    @Spy
    private RestTemplate restTemplate;

    @Spy
    private ObjectMapper objectMapper;

    private MockWebServer mockWebServer;
    private String baseUrl;

    @BeforeEach
    public void setup() throws IOException {

        mockWebServer = new MockWebServer();
        mockWebServer.start();
        final String url = "/api/%s";
        baseUrl = mockWebServer.url(url).toString();

        ReflectionTestUtils.setField(integration, "url", baseUrl);
    }

    @Test
    public void ok_getAutoComplete() throws InterruptedException, IOException {

        final GetAutoCompleteResponse expected = Fixture.make(GetAutoCompleteResponse.class);
        objectMapper = new ObjectMapper();

        final String expectedString = objectMapper.writeValueAsString(expected);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setBody(expectedString)
                .addHeader("Content-Type", "application/json"));

        final String shareCode = Fixture.make(String.class);

        final GetAutoCompleteResponse result = integration.getAutoComplete(shareCode);

        assertEquals(expectedString, objectMapper.writeValueAsString(result));

        final RecordedRequest request1 = mockWebServer.takeRequest();
        assertTrue(request1.getPath().contains(String.format("auto-complete?q=%s&region=BR", shareCode)));

    }


    @Test
    public void ok_getQuotes() throws InterruptedException, IOException {

        final GetQuotesResponse expected = Fixture.make(GetQuotesResponse.class);
        objectMapper = new ObjectMapper();

        final String expectedString = objectMapper.writeValueAsString(expected);

        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setBody(expectedString)
                .addHeader("Content-Type", "application/json"));

        final String shareCode = Fixture.make(String.class);
        final List<String> shareCodes = asList(shareCode);

        final GetQuotesResponse result = integration.getQuotes(shareCodes);

        assertEquals(expectedString, objectMapper.writeValueAsString(result));

        final RecordedRequest request1 = mockWebServer.takeRequest();
        assertTrue(request1.getPath().contains(String.format("market/v2/get-quotes?region=BR&symbols=%s",
                String.join(",", shareCodes))));

    }
}
