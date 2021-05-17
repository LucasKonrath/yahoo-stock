package br.com.pagseguro.testeenginvestimentos.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.MediaSize.NA;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACOES")
@Data
@NoArgsConstructor
public class AcaoEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column
    private String codigo;

    @Column
    private String acao;

    @Column
    private Long quantidadeTeorica;

    @Column
    private BigDecimal particicao;

}
