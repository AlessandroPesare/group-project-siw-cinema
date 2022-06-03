package it.uniroma3.siw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Spettacolo;
import it.uniroma3.siw.service.PrenotazioneService;
import it.uniroma3.siw.service.SpettacoloService;

@Controller
public class PrenotazioneController {

	@Autowired
	PrenotazioneService ps;
	@Autowired 
	SpettacoloService ss;
	
	@PostMapping("/prenotazione")
	public String addPrenotazione(@Valid @ModelAttribute("prenotazione") Prenotazione prenotazione, BindingResult br, Model model) {
		if(!br.hasErrors()) {
			ps.addPrenotazione(prenotazione);
			ss.aggiornaPostiDisponibili(prenotazione.getSpettacolo());
			model.addAttribute("prenotazione", prenotazione);
			return "prenotazione.html";
		} else
		return "index.html";
	}
	
	@GetMapping("/prenotazione/{id}")
	public String getPrenotazione(@PathVariable("id") Long id, Model model) {
		List<Spettacolo> spettacoli = ss.findAllSpettacoliPerFilm(id);
		model.addAttribute("spettacoli", spettacoli);
		model.addAttribute("prenotazione", new Prenotazione());
		return "prenotazioneForm.html";
	}
	
	@PostMapping("/deletePrenotazione/{id}")
	public String deletePrenotazione(@PathVariable("id") Long id, Model model) {
		Prenotazione prenotazione = ps.findById(id);
		ps.deletePrenotazione(prenotazione);
		return "index.html";
	}
}
