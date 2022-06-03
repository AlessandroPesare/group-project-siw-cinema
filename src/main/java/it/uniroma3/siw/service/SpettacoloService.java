package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.model.Spettacolo;
import it.uniroma3.siw.repository.SpettacoloRepository;

public class SpettacoloService {
	@Autowired
	private SpettacoloRepository repo;

	public List<Spettacolo> findAllSpettacoliPerFilm(Long idFilm) {
		return repo.findByFilmId(idFilm);
	}
}
