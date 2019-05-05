package br.com.cemim.contaspagar.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.cemim.contaspagar.entity.Conta;

@Repository
public interface ContaRepository extends PagingAndSortingRepository<Conta, Long> {

}
