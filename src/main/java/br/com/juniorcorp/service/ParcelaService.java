package br.com.juniorcorp.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.juniorcorp.model.Parcela;
import br.com.juniorcorp.model.StatusParcela;
import br.com.juniorcorp.repository.ParcelaRepository;
import br.com.juniorcorp.util.ConvertDate;

@Service
public class ParcelaService {
	
	private static final Logger LOG = LogManager.getLogger(ParcelaService.class);

	@Autowired
	private ParcelaRepository parcelaRepo;
	
	public Parcela findOne(Long id) {
		try {
			 Optional<Parcela> parcela = parcelaRepo.findById(id);
			 return parcela.isPresent() ? parcela.get() : null;
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	
	public Iterable<Parcela> buscaParcelasAtrasadas(){
		Iterable<Parcela> parcelas = parcelaRepo.findByDataVencimentoLessThan(new Date());
		return parcelas;
	}
	
	public void delete(Long id) {
		try {			
			parcelaRepo.deleteById(id);
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	public void save(Parcela parcela) throws ServiceException {
		try {
			if(this.validaPrenchimentoParcela(parcela)) {			
				parcelaRepo.save(parcela);
			}
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage());
		}
	}

	public void pagarParcela(Long idParcela) throws ServiceException {
		try {
			if(idParcela == null) {
				throw new ServiceException("idParcela nula.");
			}
			Parcela parcela = this.findOne(idParcela);
				parcela.setStatus(StatusParcela.PAGA);	
				parcelaRepo.save(parcela);
			
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void cancelarPagamento(Long idParcela) throws ServiceException {
		try {
			if(idParcela == null ) {
				throw new ServiceException("idParcela nulo");
			}
			Parcela parcela = this.findOne(idParcela);
			LocalDate dataAtual = ConvertDate.convertDateInLocalDate(new Date());
			LocalDate vencimento = ConvertDate.convertDateInLocalDate(parcela.getDataVencimento());
			
			if(vencimento.isBefore(dataAtual)) {
				parcela.setStatus(StatusParcela.EM_ATRASO);
			}else {
				parcela.setStatus(StatusParcela.AGUARDANDO_PAGAMENTO);
			}
			parcelaRepo.save(parcela);
			
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage());
		}
	}
	private boolean validaPrenchimentoParcela(Parcela parcela) {
		if(parcela == null) {
			return false;
		}
		if(parcela.getDataVencimento() == null || parcela.getStatus() == null || 
		   parcela.getNumeroParcela() == null || parcela.getValor() == null) {
			return false;
		}
		return true;
	}
}
