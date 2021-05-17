package br.com.pagseguro.testeenginvestimentos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pagseguro.testeenginvestimentos.entity.AcaoEntity;
import br.com.pagseguro.testeenginvestimentos.repository.AcaoRepository;

@Service
public class ConsultarAcaoPorNomeService {

    @Autowired
    private AcaoRepository repository;

    public List<AcaoEntity> consultar(final String nome){
        return repository.findAllByAcaoContains(nome);
    }

}
