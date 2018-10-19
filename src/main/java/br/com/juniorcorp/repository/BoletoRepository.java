package br.com.juniorcorp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.juniorcorp.model.Boleto;
import br.com.juniorcorp.model.StatusParcela;

public interface  BoletoRepository extends CrudRepository<Boleto, Integer> {
	
	
	@Query("select distinct b from boleto b join b.parcelas p where p.status = :status")
	List<Boleto> findBoletosPerStatus(@Param("status") StatusParcela status);
	
}
