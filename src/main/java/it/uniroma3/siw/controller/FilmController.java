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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Film;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.FilmService;

@Controller
public class FilmController {
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private FilmService filmService;
	
	@GetMapping("/filmForm")
	public String getFilmForm(Model model) {
		model.addAttribute("film", new Film());
		return "filmForm.html";
	}
	
	@GetMapping("/films")
	public String getFilms(Model model) {
		List<Film> films = filmService.findAll();
		model.addAttribute("films", films);
		try {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
    		model.addAttribute("amministratoreLoggato",true);
        }
    	else if(credentials.getRole().equals(Credentials.DEFAULT_ROLE)) {
    		model.addAttribute("userLoggato",true);
    	}
		return "films.html"; }
		catch(Exception e)
		{
			return "films.html";
		}
	}
	
	@GetMapping("/film/nome")
	public String getFilmByNome(@RequestParam(name = "nome") String nome, Model model) {
		if(!(nome == null || nome.isBlank())) {
			Film film = filmService.findByTitolo(nome);
			if(film != null) {
				model.addAttribute("film", film);
				return "film.html";
			}
			else {
				// il film non esiste
				model.addAttribute("isFilmInesistente", true);				
			}
		}
		return "index.html";
	}
	
	@PostMapping("/film")
	public String addFilm(@Valid Film film, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			filmService.addFilm(film);
			model.addAttribute("film", film);
			return "film.html";
		}
		else return "filmForm.html";
	}
	
	@GetMapping("/toDeleteFilm/{id}")
	public String toDeleteFilm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("film", filmService.findById(id));
		return "toDeleteFilm.html";
	}
	
	@GetMapping("/deleteFilm/{id}")
	public String deleteFilm(@PathVariable("id") Long id, Model model) {
		filmService.deleteById(id);
		model.addAttribute("films", filmService.findAll());
		return "films.html";
	}
	
	@GetMapping("/film/{id}")
	public String getFilm(@PathVariable("id") Long id, Model model){
		Film film = filmService.findById(id);
		model.addAttribute("film", film);
		return "film.html";	
	}
}
