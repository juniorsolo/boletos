package br.com.juniorcorp.controller;

import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	
	@PostMapping(value = "/salvarBoleto2")
	public ModelAndView salvarBoleto( Boleto boleto) {
		//boleto.getCpf().replace(".", "");
		//boleto.getCpf().replace("-", "");
		
		//service.save(boleto);
		
		return this.cadastro(new Boleto());
	}

	 @PostMapping("/salvarBoleto")
	    public String checkPersonInfo(@Valid Boleto boleto, BindingResult bindingResult) {

	        if (bindingResult.hasErrors()) {
	            return "/cadastro";
	        }
	        service.save(boleto);
	        System.out.println(boleto.getCpf());
	        return "redirect:/cadastro";
	    }
}
