package it.uniroma3.siw.service;

import javax.transaction.Transactional;

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
	
	public void iniziaInserimentoFilm() {
		
	}
	
	@Transactional
	public void addFilm(Film film) {
		repo.save(film);
	}
}
