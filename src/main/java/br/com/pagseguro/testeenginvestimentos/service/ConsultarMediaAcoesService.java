package br.com.pagseguro.testeenginvestimentos.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import br.com.pagseguro.testeenginvestimentos.entity.AcaoEntity;
import br.com.pagseguro.testeenginvestimentos.integration.YahooFinanceIntegration;
import br.com.pagseguro.testeenginvestimentos.integration.domain.AutoCompleteQuote;
import br.com.pagseguro.testeenginvestimentos.integration.domain.Cotacao;
import br.com.pagseguro.testeenginvestimentos.integration.response.GetAutoCompleteResponse;
import br.com.pagseguro.testeenginvestimentos.repository.AcaoRepository;

@Service
public class ConsultarMediaAcoesService {

    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private YahooFinanceIntegration yahooFinanceIntegration;

    public List<Cotacao> consultar() {
        Page<AcaoEntity> acoes = acaoRepository.findAll(PageRequest.of(0, 10, Direction.DESC, "quantidadeTeorica"));

        List<GetAutoCompleteResponse> autoCompleteResponses =
            acoes.stream()
            .map(AcaoEntity::getCodigo)
            .map(codigo -> yahooFinanceIntegration.getAutoComplete(codigo))
            .collect(Collectors.toList());

        List<String> codigos = autoCompleteResponses.stream()
            .map(auto -> auto.getQuotes())
            .map(lista -> lista.stream().map(cotacao -> cotacao.getCodigo()).findFirst().orElse(null))
            .collect(Collectors.toList());

        return yahooFinanceIntegration.getQuotes(codigos)
            .getQuoteResponse()
            .getResult();
    }
}
