package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Film;
import it.uniroma3.siw.repository.FilmRepository;

@Service
public class FilmService {
	@Autowired
	private FilmRepository repo;
	
	public Film getFilm(String titolo) {
		return repo.findByTitolo(titolo);
	}
}
