package br.com.pagseguro.testeenginvestimentos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import br.com.pagseguro.testeenginvestimentos.entity.AcaoEntity;

public interface AcaoRepository extends JpaRepository<AcaoEntity, Long> {

    List<AcaoEntity> findAllByCodigoContains(String codigo);

    List<AcaoEntity> findAllByAcaoContains(String nome);
}