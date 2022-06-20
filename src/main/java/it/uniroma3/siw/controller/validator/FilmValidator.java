package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Film;
import it.uniroma3.siw.service.FilmService;

@Component
public class FilmValidator implements Validator {
	@Autowired
	private FilmService service;

	@Override
	public boolean supports(Class<?> clazz) {
		return Film.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Film film = (Film) target;
		if(service.esisteFilm(film)) {
			errors.reject("duplicato");
		}
	}

}
