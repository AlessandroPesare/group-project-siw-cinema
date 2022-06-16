package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Film;

public interface FilmRepository extends CrudRepository<Film, Long> {
	public Optional<Film> findByTitolo(String titolo);

}
