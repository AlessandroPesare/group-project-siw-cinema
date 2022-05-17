package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Film;

@Controller
public class FilmController {
	
	@GetMapping("/filmForm")
	public String getFilmForm(Model model) {
		model.addAttribute("film", new Film());
		return "filmForm.html";
	}
	
	public String addFilm(@Valid Film film, BindingResult bindingResult) {
		return null;
	}
}
