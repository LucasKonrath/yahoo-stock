package br.com.pagseguro.testeenginvestimentos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pagseguro.testeenginvestimentos.entity.AcaoEntity;
import br.com.pagseguro.testeenginvestimentos.repository.AcaoRepository;

@Service
public class AdicionarAcaoService {

    @Autowired
    private AcaoRepository repository;

    public void adicionar(AcaoEntity acao){
        repository.save(acao);
    }
}
