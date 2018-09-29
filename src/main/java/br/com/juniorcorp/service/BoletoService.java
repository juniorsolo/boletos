package br.com.juniorcorp.service;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.juniorcorp.model.Boleto;
import br.com.juniorcorp.model.Parcela;
import br.com.juniorcorp.model.StatusParcela;
import br.com.juniorcorp.repository.BoletoRepository;

@Service
public class BoletoService {
	
	private static final Logger LOG = LogManager.getLogger(BoletoService.class);
	
	@Autowired
	private BoletoRepository boletoRepo;
	
	public void save(Boleto boleto) {
		try {
			if(this.validaPrenchimentoBoleto(boleto)) {
				this.preencheParcelas(boleto);
				boletoRepo.save(boleto);
			}
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	private void preencheParcelas(Boleto boleto) {
		Parcela parcela;
		boleto.setParcelas(new ArrayList<Parcela>());
		for(int i = 1; i <= boleto.getNumParcelas(); i++) {
			parcela = new Parcela();
			parcela.setNumeroParcela(i);
			if(i == 1) {
				parcela.setDataVencimento(boleto.getDataPrimeiraParcela());
			}else {
				LocalDate novaDataVencimento = this.convertDateInLocalDate(boleto.getDataPrimeiraParcela());
				novaDataVencimento = novaDataVencimento.plusMonths(i -1);
				parcela.setDataVencimento(convertLocalDateInDate(novaDataVencimento));
			}
			parcela.setStatus(StatusParcela.AGUARDANDO_PAGAMENTO);
			boleto.getParcelas().add(parcela);
		}
	}
	private Date convertLocalDateInDate(LocalDate ld) {
		Date dataConvertida = new Date(ld.getYear() - 1900, ld.getMonthValue() - 1, ld.getDayOfMonth());
		return dataConvertida;
	}
	private LocalDate convertDateInLocalDate(Date data ) {
		LocalDate dataConvertida = LocalDate.of( data.getYear()+1900, data.getMonth() + 1, data.getDate());
		return dataConvertida;
	}
	private boolean validaPrenchimentoBoleto(Boleto boleto) {
		if(boleto == null) {
			return false;
		}
		if(boleto.getCpf() == null) {
			return false;
		}
		if(boleto.getDataPrimeiraParcela() == null) {
			return false;
		}
		if(boleto.getNumParcelas() == null) {
			return false;
		}
		if(boleto.getNome() == null) {
			return false;
		}
		
		return true;
	}
	
	public void delete(Integer id) {
		boletoRepo.deleteById(id);
	}
}
