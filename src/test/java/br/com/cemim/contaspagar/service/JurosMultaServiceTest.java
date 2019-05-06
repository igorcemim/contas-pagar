package br.com.cemim.contaspagar.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cemim.contaspagar.dto.JurosMulta;
import br.com.cemim.contaspagar.dto.RegraCalculo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JurosMultaServiceTest {

    @Mock
    private RegraCalculoService regraCalculoService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void pagamentoSemAtraso() {
        int diasEmAtrasoEsperado = 0;
        double jurosAoDiaEsperado = 0;
        double multaEsperada = 0;
        double valorCorrigidoEsperado = 0;

        float valorOriginal = 100;
        LocalDate dataPagamento = LocalDate.of(2019, 1, 1);
        LocalDate dataVencimento = LocalDate.of(2019, 1, 1);

        JurosMultaService jurosMultaService = new JurosMultaService(regraCalculoService);
        JurosMulta jurosMulta = jurosMultaService.calcular(valorOriginal, dataPagamento, dataVencimento);

        assertEquals("Quantidade de dias em atraso", diasEmAtrasoEsperado, jurosMulta.getQuantidadeDiasAtraso());
        assertEquals("Percentual de multa", multaEsperada, jurosMulta.getPercentualMulta(), 0.01);
        assertEquals("Percentual de juros ao dia", jurosAoDiaEsperado, jurosMulta.getPercentualJurosDia(), 0.01);
        assertEquals("Valor corrigido", valorCorrigidoEsperado, jurosMulta.getValorCorrigido(), 0.01);
    }

    @Test
    public void pagamentoComAtraso() {
        int diasEmAtrasoEsperado = 1;
        double jurosAoDiaEsperado = 0.1;
        double multaEsperada = 2;
        double valorCorrigidoEsperado = 135.36;

        double valorOriginal = 132.58;
        LocalDate dataVencimento = LocalDate.of(2019, 10, 1);
        LocalDate dataPagamento = LocalDate.of(2019, 10, 2);

        when(regraCalculoService.getRegra(1)).thenReturn(
            new RegraCalculo(multaEsperada, jurosAoDiaEsperado)
        );

        JurosMultaService jurosMultaService = new JurosMultaService(regraCalculoService);
        JurosMulta jurosMulta = jurosMultaService.calcular(valorOriginal, dataPagamento, dataVencimento);

        assertEquals("Quantidade de dias em atraso", diasEmAtrasoEsperado, jurosMulta.getQuantidadeDiasAtraso());
        assertEquals("Percentual de multa", multaEsperada, jurosMulta.getPercentualMulta(), 0.01);
        assertEquals("Percentual de juros ao dia", jurosAoDiaEsperado, jurosMulta.getPercentualJurosDia(), 0.01);
        assertEquals("Valor corrigido", valorCorrigidoEsperado, jurosMulta.getValorCorrigido(), 0.01);
    }


}
