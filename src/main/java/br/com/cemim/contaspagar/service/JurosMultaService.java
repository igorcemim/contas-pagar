package br.com.cemim.contaspagar.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import br.com.cemim.contaspagar.dto.JurosMulta;
import br.com.cemim.contaspagar.dto.RegraCalculo;

@Service
public class JurosMultaService {

    private RegraCalculoService regraCalculoService;

    public JurosMultaService(RegraCalculoService regraCalculoService) {
        this.regraCalculoService = regraCalculoService;
    }

    private int diasAtraso(LocalDate dataPagamento, LocalDate dataVencimento) {
        Long diasAtraso = ChronoUnit.DAYS.between(dataVencimento, dataPagamento);
        /**
         * Pagamento efetuado antes do vencimento ocasiona quantidade de dias negativa.
         */
        if (diasAtraso < 0) {
            return 0;
        }
        return diasAtraso.intValue();
    }

    public JurosMulta calcular(double valorOriginal, LocalDate dataPagamento, LocalDate dataVencimento) {
        JurosMulta resultado = new JurosMulta();

        int diasAtraso = diasAtraso(dataPagamento, dataVencimento);
        /**
         * Efetua correção somente em caso de atraso.
         */
        if (diasAtraso > 0) {
            RegraCalculo regraCalculo = regraCalculoService.getRegra(diasAtraso);

            double valorMulta = valorOriginal * (regraCalculo.getPercentualMulta() / 100);
            double valorJurosPorDia = valorOriginal * (regraCalculo.getPercentualJurosDia() / 100);
            double valorJurosDiasAtraso = valorJurosPorDia * diasAtraso;
            double valorCorrigido = valorOriginal + valorJurosDiasAtraso + valorMulta;

            resultado.setPercentualJurosDia(regraCalculo.getPercentualJurosDia());
            resultado.setPercentualMulta(regraCalculo.getPercentualMulta());
            resultado.setQuantidadeDiasAtraso(diasAtraso);
            resultado.setValorCorrigido(valorCorrigido);
        }

        return resultado;
    }

}
