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

    private float valorOriginal;

    private float valorCorrigido;

    private float quantidadeDiasAtraso;

    private LocalDate dataPagamento;

    private LocalDate dataVencimento;

    private float percentualMulta;

    private float percentualJurosDia;
}
