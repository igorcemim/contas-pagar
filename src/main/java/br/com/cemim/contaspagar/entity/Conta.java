package br.com.cemim.contaspagar.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String nome;

    @Column(precision=15, scale=2)
    private BigDecimal valorOriginal;

    @Column(precision=15, scale=2)
    private BigDecimal valorCorrigido;

    private int quantidadeDiasAtraso;

    private LocalDate dataPagamento;

    private LocalDate dataVencimento;

    @Column(precision=15, scale=2)
    private BigDecimal percentualMulta;

    @Column(precision=15, scale=2)
    private BigDecimal percentualJurosDia;
}
