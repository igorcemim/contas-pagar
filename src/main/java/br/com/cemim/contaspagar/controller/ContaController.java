package br.com.cemim.contaspagar.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cemim.contaspagar.entity.Conta;
import br.com.cemim.contaspagar.service.ContaService;

@RestController
public class ContaController {

    private ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping("/contas")
    public Page<Conta> get(@RequestParam(name="page", defaultValue = "0") int page) {
        return contaService.findAll(page);
    }

    @PostMapping("/contas")
    public Conta create(@RequestBody Conta conta) {
        return contaService.create(conta);
    }

}
