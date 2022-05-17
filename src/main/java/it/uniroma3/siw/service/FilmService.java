package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Film> findAll(){
		List<Film> films = new ArrayList<Film>();
		for(Film film: repo.findAll()) {
			films.add(film);
		}
		return films;
	}
	
	@Transactional
	public void addFilm(Film film) {
		repo.save(film);
	}
}
