package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Spettacolo;
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

	@GetMapping("/spettacolo")
	public String getSpettacoloForm(Model model) {
		Spettacolo s = new Spettacolo();
		model.addAttribute("spettacolo", s);
		credentialsService.adattaAdUtente(model);
		return "spettacoloForm.html";
	}

	@PostMapping("/spettacolo")
	public String addSpettacolo(@Valid Spettacolo spettacolo, BindingResult bindingResult, Model model) {
		credentialsService.adattaAdUtente(model);
		if(!bindingResult.hasErrors()) {
			spettacoloService.addSpettacolo(spettacolo);
			model.addAttribute("spettacolo",spettacolo);
			return "spettacolo.html";
		}
		else return "spettacoloForm.html";
	}
	
	@PostMapping("spettacolo/delete/{id}")
	public String deleteSpettacolo(@PathVariable("id") Long id, Model model) {
		spettacoloService.deleteById(id);
		model.addAttribute("spettacoli", spettacoloService.findAllSpettacoli());
		credentialsService.adattaAdUtente(model);
		return "spettacoli.html";
	}
}

