package br.com.juniorcorp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoletoController {

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/tasks")
	public String tasks() {
		return "tasks";
	}
	
	@RequestMapping("/cadastro")
	public String cadastro() {
		return "cadastro";
	}
}
