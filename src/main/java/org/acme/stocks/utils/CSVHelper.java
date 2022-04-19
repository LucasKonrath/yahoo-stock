package org.acme.stocks.utils;

import org.acme.stocks.entity.StockEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";

    public static List<StockEntity> csvToStocks(final InputStream is) {
        try (final BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             final CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withDelimiter(';'))) {

            final List<StockEntity> stocks = new ArrayList<>();

            final Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (final CSVRecord csvRecord : csvRecords) {
                final StockEntity stock = new StockEntity();
                stock.setId(csvRecord.getRecordNumber());
                stock.setCode(csvRecord.get(0));
                stock.setStock(csvRecord.get(1));
                stock.setTheoricQuantity(Long.parseLong(csvRecord.get(2)));
                stock.setShare(new BigDecimal(csvRecord.get(3)));
                stocks.add(stock);
            }

            return stocks;
        } catch (final IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}