package br.com.cemim.contaspagar.dto;

import lombok.Data;

@Data
public class JurosMulta {

    private double valorCorrigido;

    private int quantidadeDiasAtraso;

    private double percentualMulta;

    private double percentualJurosDia;

}
