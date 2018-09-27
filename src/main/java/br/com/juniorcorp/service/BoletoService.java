package br.com.juniorcorp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.juniorcorp.model.Boleto;
import br.com.juniorcorp.repository.BoletoRepository;

@Service
public class BoletoService {
	
	private static final Logger LOG = LogManager.getLogger(BoletoService.class);
	
	@Autowired
	private BoletoRepository boletoRepo;
	
	public void save(Boleto boleto) {
		try {
			boletoRepo.save(boleto);
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	public void delete(Integer id) {
		boletoRepo.deleteById(id);
	}
}
