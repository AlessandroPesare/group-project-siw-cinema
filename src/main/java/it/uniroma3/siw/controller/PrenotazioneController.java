package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.service.PrenotazioneService;

@Controller
public class PrenotazioneController {

	@Autowired
	PrenotazioneService ps;
	
	@PostMapping("/prenotazione")
	public String addPrenotazione(@Valid Prenotazione prenotazione, BindingResult br, Model model) {
		if(!br.hasErrors()) {
			ps.addPrenotazione(prenotazione);
			model.addAttribute("prenotazione", prenotazione);
			return "prenotazione.html";
		} else
		return "prenotazioneForm.html";
	}
	
	@GetMapping("/prenotazione")
	public String getPrenotazione(Model model) {
		model.addAttribute("prenotazione", new Prenotazione());
		return "prenotazioneForm.html";
	}
	
	@PostMapping("/deletePrenotazione")
	public String deletePrenotazione(@PathVariable("id") Long id, Model model) {
		Prenotazione prenotazione = ps.findById(id);
		ps.deletePrenotazione(prenotazione);
		return "index.html";
	}
}
