package br.com.pagseguro.testeenginvestimentos.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.pagseguro.testeenginvestimentos.entity.AcaoEntity;
import br.com.pagseguro.testeenginvestimentos.repository.AcaoRepository;
import br.com.pagseguro.testeenginvestimentos.utils.CSVHelper;

@Service
public class UploadAcoesService {

    @Autowired
    private AcaoRepository repository;

    public void salvarAcoes(MultipartFile file) throws IOException {
        List<AcaoEntity> acoes = CSVHelper.csvToAcoes(file.getInputStream());
        repository.saveAll(acoes);
    }

}
