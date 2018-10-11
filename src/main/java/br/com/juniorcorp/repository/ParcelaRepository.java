package br.com.juniorcorp.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import br.com.juniorcorp.model.Parcela;

public interface ParcelaRepository extends CrudRepository<Parcela, Long> {
	

	Iterable<Parcela> findByDataVencimentoLessThan(Date data );
	
}
