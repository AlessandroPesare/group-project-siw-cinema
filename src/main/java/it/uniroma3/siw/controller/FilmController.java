package it.uniroma3.siw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Film;
import it.uniroma3.siw.service.FilmService;

@Controller
public class FilmController {
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
		return "films.html";
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
}
