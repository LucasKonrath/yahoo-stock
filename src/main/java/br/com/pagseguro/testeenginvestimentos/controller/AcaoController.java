package br.com.pagseguro.testeenginvestimentos.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.pagseguro.testeenginvestimentos.entity.AcaoEntity;
import br.com.pagseguro.testeenginvestimentos.integration.domain.Cotacao;
import br.com.pagseguro.testeenginvestimentos.service.AdicionarAcaoService;
import br.com.pagseguro.testeenginvestimentos.service.ConsultarAcaoPorCodigoService;
import br.com.pagseguro.testeenginvestimentos.service.ConsultarAcaoPorNomeService;
import br.com.pagseguro.testeenginvestimentos.service.ConsultarMediaAcoesService;
import br.com.pagseguro.testeenginvestimentos.service.DeletarAcaoService;
import br.com.pagseguro.testeenginvestimentos.service.ListarAcoesService;
import br.com.pagseguro.testeenginvestimentos.service.UploadAcoesService;

@RestController
@RequestMapping("/api/acao")
public class AcaoController {

    @Autowired
    private UploadAcoesService uploadAcoesService;

    @Autowired
    private AdicionarAcaoService adicionarAcaoService;

    @Autowired
    private DeletarAcaoService deletarAcaoService;

    @Autowired
    private ListarAcoesService listarAcoesService;

    @Autowired
    private ConsultarAcaoPorCodigoService consultarAcaoPorCodigoService;

    @Autowired
    private ConsultarAcaoPorNomeService consultarAcaoPorNomeService;

    @Autowired
    private ConsultarMediaAcoesService consultarMediaAcoesService;

    @PostMapping("/upload")
    public void adicionarAcoes(@RequestParam("file") MultipartFile file) throws IOException {
        uploadAcoesService.salvarAcoes(file);
    }

    @PostMapping
    public void adicionarAcao(@RequestBody AcaoEntity acao){
        adicionarAcaoService.adicionar(acao);
    }

    @DeleteMapping("/{id}")
    public void deletarAcao(@PathVariable Long id){
        deletarAcaoService.deletar(id);
    }

    @GetMapping
    public List<AcaoEntity> listarAcoes(){
        return listarAcoesService.listarAcoes();
    }

    @GetMapping("/por-codigo/{codigo}")
    public List<AcaoEntity> consultarPorCodigo(@PathVariable String codigo){
        return consultarAcaoPorCodigoService.consultar(codigo);
    }

    @GetMapping("/por-nome/{nome}")
    public List<AcaoEntity> consultarPorNome(@PathVariable String nome){
        return consultarAcaoPorNomeService.consultar(nome);
    }

    @GetMapping("/media")
    public List<Cotacao> consultarMediaHistorica(){
        return consultarMediaAcoesService.consultar();
    }

}
