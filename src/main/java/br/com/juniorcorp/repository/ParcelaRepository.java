package br.com.juniorcorp.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import br.com.juniorcorp.model.Parcela;
import br.com.juniorcorp.model.StatusParcela;

public interface ParcelaRepository extends CrudRepository<Parcela, Long> {
	

	Iterable<Parcela> findByDataVencimentoLessThanAndStatus(Date data, StatusParcela status);
	
	
}
