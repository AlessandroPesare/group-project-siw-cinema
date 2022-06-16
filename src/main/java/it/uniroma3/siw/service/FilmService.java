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
	private FilmRepository filmRepo;
	
	public Film findByTitolo(String titolo) {
		return filmRepo.findByTitolo(titolo).orElse(null);
	}
	
	public List<Film> findAll(){
		List<Film> films = new ArrayList<Film>();
		for(Film film: filmRepo.findAll()) {
			films.add(film);
		}
		return films;
	}
	public Film findById(Long id) {
		return this.filmRepo.findById(id).get();	
	}
	
	@Transactional
	public void addFilm(Film film) {
		filmRepo.save(film);
	}

	public void deleteById(Long id) {
		this.filmRepo.deleteById(id);
	}
	
}
