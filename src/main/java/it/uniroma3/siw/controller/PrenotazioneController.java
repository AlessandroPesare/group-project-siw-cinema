package it.uniroma3.siw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Film;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Spettacolo;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.FilmService;
import it.uniroma3.siw.service.PrenotazioneService;
import it.uniroma3.siw.service.SpettacoloService;

@Controller
public class PrenotazioneController {
	@Autowired
	private FilmService filmService;
	@Autowired
	private PrenotazioneService prenService;
	@Autowired 
	private SpettacoloService spettService;
	@Autowired
	private CredentialsService credService;
	
	@PostMapping("/prenotazione")	// non usiamo?
	public String addPrenotazione(@Valid @ModelAttribute("prenotazione") Prenotazione prenotazione, BindingResult br, Model model) {
		if(!br.hasErrors()) {
			prenService.savePrenotazione(prenotazione);
			spettService.aggiornaPostiDisponibili(prenotazione.getSpettacolo());
			model.addAttribute("prenotazione", prenotazione);
			return "prenotazione.html";
		} else
		return "index.html";
	}
	
	@GetMapping("/user/prenotazione/add/{id}")
	public String aggiungiPrenotazione(@PathVariable("id") Long spettacoloId, Model model) {
		Prenotazione prenotazione = new Prenotazione();	
		prenotazione.setSpettacolo(spettService.findById(spettacoloId));
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credService.getCredentials(userDetails.getUsername());
    	User user = credentials.getUser();
    	prenotazione.setUtente(user);
//    	user.addPrenotazione(prenotazione);	non serve
    	if(prenService.savePrenotazione(prenotazione)) {
    		model.addAttribute("prenotazioneRiuscita", true);
    	}
    	else {
    		model.addAttribute("prenotazioneFallita", true);
    	}
    	model.addAttribute("allSpettacoli", spettService.findAllSpettacoli());
		credService.adattaAdUtente(model);
		return "spettacoli.html";	
	}
	
	@GetMapping("/user/prenota")
	public String getPrenotazioni(Model model){
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credService.getCredentials(userDetails.getUsername());
    	User user = credentials.getUser();
		model.addAttribute("user",user);
		credService.adattaAdUtente(model);
		return "prenotazioniPerUtente.html";
	}
	
	@GetMapping("/user/prenotazione/film/{id}")
	public String getPrenotazionePerFilm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("allSpettacoli", spettService.findAllSpettacoliPerFilm(id));
		Film film = filmService.findById(id);
		film.getTitolo().toUpperCase();
		model.addAttribute("film", film);
		credService.adattaAdUtente(model);
		return "spettacoli.html";
	}
	
	@PostMapping("/user/prenotazione/{id}")		// non usiamo?
	public String getPrenotazione(@PathVariable("id") Long id, Model model) {
		List<Spettacolo> spettacoli = spettService.findAllSpettacoliPerFilm(id);
		model.addAttribute("spettacoli", spettacoli);
		model.addAttribute("prenotazione", new Prenotazione());
		return "prenotazioneForm.html";
	}
	
	@GetMapping("/user/prenotazione/delete/{id}")
	public String deletePrenotazione(@PathVariable("id") Long id, Model model) {
		prenService.deletePrenotazione(id);
		return "redirect:/user/prenota";
	}
}
