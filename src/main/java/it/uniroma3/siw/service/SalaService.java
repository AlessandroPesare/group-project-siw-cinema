package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Sala;
import it.uniroma3.siw.repository.SalaRepository;

@Service
public class SalaService {
	
	@Autowired
	SalaRepository salaRepo;

	public List<Sala> findAll(){
		List<Sala> sale = new ArrayList<Sala>();
		for(Sala sala: salaRepo.findAll()) {
			sale.add(sala);
		}
		return sale;
	}
}
