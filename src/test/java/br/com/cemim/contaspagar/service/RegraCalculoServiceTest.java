package br.com.cemim.contaspagar.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cemim.contaspagar.dto.RegraCalculo;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegraCalculoServiceTest {

    @Test
    public void faixasParaRegrasDeCalculo() {
        RegraCalculoService regraCalculoService = new RegraCalculoService();

        RegraCalculo regra1 = regraCalculoService.getRegra(3);
        double ate3diasEsperado = 2;
        assertEquals("até 3 dias", ate3diasEsperado, regra1.getPercentualMulta(), 0.01);

        RegraCalculo regra2 = regraCalculoService.getRegra(5);
        double superior3diasEsperado = 3;
        assertEquals("superior a 3 dias", superior3diasEsperado, regra2.getPercentualMulta(), 0.01);

        RegraCalculo regra3 = regraCalculoService.getRegra(10);
        double superior5diasEsperado = 5;
        assertEquals("superior a 5 dias", superior5diasEsperado, regra3.getPercentualMulta(), 0.01);
    }

}
