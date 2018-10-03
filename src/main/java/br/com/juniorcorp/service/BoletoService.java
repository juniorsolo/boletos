package br.com.juniorcorp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	public Iterable<Boleto> findAll() throws ServiceException{
		try {
			Iterable<Boleto> lista =  boletoRepo.findAll();
			return lista;
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Boleto findOne(Integer id) {
		try {
			 Optional<Boleto> boleto = boletoRepo.findById(id);
			 return boleto.isPresent() ? boleto.get() : null;
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	
	public Page<Boleto> findPaginated(Pageable pageable) {
			List<Boleto> boletos = (List<Boleto>) boletoRepo.findAll();
	        int pageSize = pageable.getPageSize();
	        int currentPage = pageable.getPageNumber();
	        int startItem = currentPage * pageSize;
	        List<Boleto> list;
	 
	        if (boletos.size() < startItem) {
	            list = Collections.emptyList();
	        } else {
	            int toIndex = Math.min(startItem + pageSize, boletos.size());
	            list = boletos.subList(startItem, toIndex);
	        }
	 
	        Page<Boleto> boletoPage = new PageImpl<Boleto>(list, PageRequest.of(currentPage, pageSize), boletos.size());
	 
	        return boletoPage;
	    }
	public void delete(Integer id) {
		boletoRepo.deleteById(id);
	}
	
	public void save(Boleto boleto) throws ServiceException {
		try {
			if(this.validaPrenchimentoBoleto(boleto)) {
				this.preencheParcelas(boleto);
				boletoRepo.save(boleto);
			}
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	private void preencheParcelas(Boleto boleto) {
		Parcela parcela;
		boleto.setParcelas(new ArrayList<Parcela>());
		for(int i = 1; i <= boleto.getTotalParcelas(); i++) {
			parcela = new Parcela();
			parcela.setNumeroParcela(i);
			parcela.setValor(boleto.getValor());
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
		@SuppressWarnings("deprecation")
		Date dataConvertida = new Date(ld.getYear() - 1900, ld.getMonthValue() - 1, ld.getDayOfMonth());
		return dataConvertida;
	}
	private LocalDate convertDateInLocalDate(Date data ) {
		@SuppressWarnings("deprecation")
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
		if(boleto.getTotalParcelas() == null) {
			return false;
		}
		if(boleto.getNome() == null) {
			return false;
		}
		
		return true;
	}
	

}
