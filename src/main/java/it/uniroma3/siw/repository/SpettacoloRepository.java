package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Spettacolo;

public interface SpettacoloRepository extends CrudRepository<Spettacolo, Long> {
	
	public List<Spettacolo> findByFilmId(Long id);
	
	
}
