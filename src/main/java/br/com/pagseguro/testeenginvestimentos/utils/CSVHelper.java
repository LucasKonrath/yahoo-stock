package br.com.pagseguro.testeenginvestimentos.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import br.com.pagseguro.testeenginvestimentos.entity.AcaoEntity;

public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "CODIGO", "ACAO", "QUANTIDADE_TEORICA", "PARTICICAO" };

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<AcaoEntity> csvToAcoes(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withDelimiter(';'))) {

      List<AcaoEntity> acoes = new ArrayList<AcaoEntity>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        AcaoEntity acao = new AcaoEntity();
        acao.setId(csvRecord.getRecordNumber());
        acao.setCodigo(csvRecord.get(0));
        acao.setAcao(csvRecord.get(1));
        acao.setQuantidadeTeorica(Long.parseLong(csvRecord.get(2)));
        acao.setParticicao(new BigDecimal(csvRecord.get(3)));
        acoes.add(acao);
      }

      return acoes;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

}