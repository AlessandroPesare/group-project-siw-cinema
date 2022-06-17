package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.SpettacoloService;

@Controller
public class SpettacoloController {
	@Autowired
	private SpettacoloService spettacoloService;
	@Autowired
	private CredentialsService credentialsService;
	
	@GetMapping("/spettacolo/all")
	public String getTuttiSpettacoli(Model model) {
		model.addAttribute("allSpettacoli", spettacoloService.findAllSpettacoli());
		credentialsService.adattaAdUtente(model);
		return "spettacoli.html";
	}
}
