package br.com.cemim.contaspagar.service;

import org.springframework.stereotype.Service;

import br.com.cemim.contaspagar.dto.RegraCalculo;

@Service
public class RegraCalculoService {

    public RegraCalculo getRegra(int diasAtraso) {
        if (diasAtraso <= 3) {
            return new RegraCalculo(2, 0.1);
        }
        if (diasAtraso <= 5) {
            return new RegraCalculo(3, 0.2);
        }
        return new RegraCalculo(5, 0.3);
    }

}
