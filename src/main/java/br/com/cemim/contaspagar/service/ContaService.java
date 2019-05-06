package br.com.cemim.contaspagar.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.cemim.contaspagar.dto.JurosMulta;
import br.com.cemim.contaspagar.entity.Conta;
import br.com.cemim.contaspagar.repository.ContaRepository;

@Service
public class ContaService {

    private ContaRepository contaRepository;

    private JurosMultaService jurosMultaService;

    private final int pageSize = 10;

    public ContaService(ContaRepository contaRepository, JurosMultaService jurosMultaService) {
        this.contaRepository = contaRepository;
        this.jurosMultaService = jurosMultaService;
    }

    public Conta create(Conta conta) {
        conta.setPercentualJurosDia(new BigDecimal(0));
        conta.setPercentualMulta(new BigDecimal(0));
        conta.setValorCorrigido(new BigDecimal(0));
        conta.setQuantidadeDiasAtraso(0);

        JurosMulta jurosMulta = jurosMultaService.calcular(
            conta.getValorOriginal().doubleValue(),
            conta.getDataPagamento(),
            conta.getDataVencimento()
        );

        if (jurosMulta.getQuantidadeDiasAtraso() > 0) {
            conta.setPercentualJurosDia(
                new BigDecimal(jurosMulta.getPercentualJurosDia()).setScale(2, RoundingMode.HALF_EVEN)
            );
            conta.setPercentualMulta(
                new BigDecimal(jurosMulta.getPercentualMulta()).setScale(2, RoundingMode.HALF_EVEN)
            );
            conta.setValorCorrigido(
                new BigDecimal(jurosMulta.getValorCorrigido()).setScale(2, RoundingMode.HALF_EVEN)
            );
            conta.setQuantidadeDiasAtraso(jurosMulta.getQuantidadeDiasAtraso());
        }

        return contaRepository.save(conta);
    }

    public Page<Conta> findAll(int page) {
        return contaRepository.findAll(PageRequest.of(page, pageSize));
	}

}
