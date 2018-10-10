package br.com.juniorcorp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.juniorcorp.model.Parcela;

public interface ParcelaRepository extends CrudRepository<Parcela, Long> {
	
	@Query("SELECT e FROM parcela p WHERE p.dtvencimento < CURRENT_DATE")
	Iterable<Parcela> buscaParcelasVencidas();
}
