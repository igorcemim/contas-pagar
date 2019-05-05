package br.com.cemim.contaspagar.entity;

import java.time.LocalDate;

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

    private double valorOriginal;

    private double valorCorrigido;

    private int quantidadeDiasAtraso;

    private LocalDate dataPagamento;

    private LocalDate dataVencimento;

    private double percentualMulta;

    private double percentualJurosDia;
}
