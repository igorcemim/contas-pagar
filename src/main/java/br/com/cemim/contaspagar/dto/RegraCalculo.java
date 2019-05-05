package br.com.cemim.contaspagar.dto;

import lombok.Data;

@Data
public class RegraCalculo {

    private double percentualMulta;

    private double percentualJurosDia;

    public RegraCalculo() {
    }

    public RegraCalculo(double percentualMulta, double percentualJurosDia) {
        this.percentualMulta = percentualMulta;
        this.percentualJurosDia = percentualJurosDia;
    }
}
