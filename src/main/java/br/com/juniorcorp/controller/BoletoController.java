package br.com.juniorcorp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.juniorcorp.model.Boleto;
import br.com.juniorcorp.service.BoletoService;


@Controller
public class BoletoController {
	
	@Autowired
	private BoletoService service;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/tasks")
	public String tasks() {
		return "tasks";
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastro(Boleto boleto) {
		ModelAndView mv = new ModelAndView("/cadastro");
		mv.addObject("boleto", boleto);
		return mv;
	}
	@PostMapping(value = "/salvarBoleto")
	public ModelAndView salvaCandidato( Boleto boleto) {
		
			service.save(boleto);
						
		return this.cadastro(new Boleto());
	}
}
