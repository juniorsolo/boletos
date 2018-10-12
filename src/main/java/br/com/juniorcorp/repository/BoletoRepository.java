package br.com.juniorcorp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.juniorcorp.model.Boleto;
import br.com.juniorcorp.model.StatusParcela;

public interface  BoletoRepository extends CrudRepository<Boleto, Integer> {
	
	
//	@Query("select distinct b.id, b.celular, b.cpf, b.data_criacao, b.data_primeira_parcela, " + 
//			" b.descricao,b.email,b.nome, b.tel_residencial, b.total_parcelas " + 
//			" from boletos.boleto b, boletos.parcela p where p.status = :status")
//	List<Boleto> findBoletosPerStatus(@Param("status") String status);
	
}
