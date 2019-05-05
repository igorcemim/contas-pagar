package br.com.cemim.contaspagar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.cemim.contaspagar.entity.Conta;
import br.com.cemim.contaspagar.repository.ContaRepository;

@Service
public class ContaService {

    private ContaRepository contaRepository;

    private final int pageSize = 10;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta create(Conta conta) {
        return contaRepository.save(conta);
    }

    public Page<Conta> findAll(int page) {
        return contaRepository.findAll(PageRequest.of(page, pageSize));
	}

}
