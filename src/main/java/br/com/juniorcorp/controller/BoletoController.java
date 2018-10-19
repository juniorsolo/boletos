package br.com.juniorcorp.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.juniorcorp.model.Boleto;
import br.com.juniorcorp.service.BoletoService;
import br.com.juniorcorp.service.ParcelaService;


@Controller
public class BoletoController {
	
	private static final Logger LOG = LogManager.getLogger(BoletoController.class);
	
	private static int currentPage = 1;
	private static int pageSize = 3;
	
	private Boleto boletoSelecionado;
	
	@Autowired
	private BoletoService serviceBoleto;
	
	@Autowired
	private ParcelaService serviceParcela;
	
	@GetMapping("/login")
	public ModelAndView login() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/login/login");
	    return mav;
   }	
	
	@RequestMapping("/")
	public String home() {
		serviceParcela.atualizaStatusParcelaAtrasada();
		return "home";
	}
	
	@GetMapping("emAtraso")
	public ModelAndView ematraso(@RequestParam("page") Optional<Integer> page, 
			  @RequestParam("size") Optional<Integer> size) {
		ModelAndView mv = new ModelAndView("/user/emAtraso"); 
		 page.ifPresent(p -> currentPage = p);
	     size.ifPresent(s -> pageSize = s);
	     Page<Boleto> boletoPage = serviceBoleto.boletosEmAtrasoPaginated(PageRequest.of(currentPage - 1, pageSize));
		 mv.addObject("boletoPage", boletoPage);
		 int totalPages = boletoPage.getTotalPages();
		 if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                .boxed()
	                .collect(Collectors.toList());
	            mv.addObject("pageNumbers", pageNumbers);	         
	        }
	
		return mv;
	}
	
	@PostMapping("/parcelasematraso")
	public ModelAndView verParcelasEmAtraso(@RequestParam(name = "id") Integer id) {
		LOG.info("id boleto: " + id);
		ModelAndView mv;
		if(id != null) {
			boletoSelecionado = serviceBoleto.findOne(id);
			boletoSelecionado.getParcelas();
			mv = new ModelAndView("/user/verparcelasEmAtraso");
			mv.addObject("boletoSelecionado", boletoSelecionado);
		}else {
			mv = new ModelAndView("/user/emAtraso");	
		}
		return mv;
	}
	
	@RequestMapping("sucesso")
	public String sucesso() {
		return "/user/sucesso";
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastro(Boleto boleto) {
		ModelAndView mv = new ModelAndView("/user/cadastro");
		mv.addObject("boleto", boleto);
		return mv;
	}
	
	@GetMapping("/consulta")
	public ModelAndView consulta( @RequestParam("page") Optional<Integer> page, 
		      					  @RequestParam("size") Optional<Integer> size) {
		ModelAndView mv = new ModelAndView("/user/consulta"); 
		 page.ifPresent(p -> currentPage = p);
	     size.ifPresent(s -> pageSize = s);
	     Page<Boleto> boletoPage = serviceBoleto.findPaginated(PageRequest.of(currentPage - 1, pageSize));
		 mv.addObject("boletoPage", boletoPage);
		 int totalPages = boletoPage.getTotalPages();
		 if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                .boxed()
	                .collect(Collectors.toList());
	            mv.addObject("pageNumbers", pageNumbers);	         
	        }
	
		return mv;
	}
	@PostMapping("/verparcelas")
	public ModelAndView verParcelas(@RequestParam(name = "id") Integer id) {
		LOG.info("id boleto: " + id);
		ModelAndView mv;
		if(id != null) {
			boletoSelecionado = serviceBoleto.findOne(id);
			//boletoSelecionado.getParcelas();
			mv = new ModelAndView("/user/verparcelas");
			mv.addObject("boletoSelecionado", boletoSelecionado);
		}else {
			mv = new ModelAndView("/user/consulta");	
		}
		return mv;
	}
	
	@PostMapping("/salvarBoleto")
	public String salvarBoleto(@Valid Boleto boleto, BindingResult bindingResult) {
		try {
	        if (bindingResult.hasErrors()) {
	            return "/user/cadastro";
	        }
	        serviceBoleto.save(boleto);
	       
	       return "redirect:/user/sucesso";
		}catch (Exception e) {
			
			return "/user/cadastro";
		}
	}
	
	@PostMapping("/deletarBoleto")
	public String deletarBoleto(@RequestParam(name="idBoleto") Integer idBoleto) {
		try {
			LOG.info("id Boleto para deletar: " + idBoleto);
	        if (idBoleto == null) {
	            return "/";
	        }
	        serviceBoleto.delete(idBoleto);
	       
	       return "redirect:/user/sucesso";
		}catch (Exception e) {
			
			return "/";
		}
	}
	
	@PostMapping("/pagarParcela")
	public ModelAndView pagarParcela(@RequestParam(name="idParcela") Long idParcela,
			                   @RequestParam(name="idBoleto") Integer idBoleto) {
		try {
			LOG.info("id parcela: " + idParcela);
			LOG.info("id boleto: " + idBoleto);
			serviceParcela.pagarParcela(idParcela);
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		
		return this.verParcelas(idBoleto);
	}
	
	@PostMapping("/cancelarPagamento")
	public ModelAndView cancelarParcela(@RequestParam(name="idParcela") Long idParcela,
										@RequestParam(name="idBoleto") Integer idBoleto) {
		try {
			LOG.info("id parcela: " + idParcela);
			LOG.info("id boleto: " + idBoleto);
			serviceParcela.cancelarPagamento(idParcela);
			
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		
		return this.verParcelas(idBoleto);
	}
	
	@GetMapping("/acessonegado")
	public ModelAndView error() {
	    ModelAndView mav = new ModelAndView("/login/acessoNegado");
	    String errorMessage= "Você não está autorizado";
	    mav.addObject("errorMsg", errorMessage);
	    mav.setViewName("403");
	    return mav;
        }	
}
