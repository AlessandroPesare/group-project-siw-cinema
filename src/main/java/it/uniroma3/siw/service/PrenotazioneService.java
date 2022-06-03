package it.uniroma3.siw.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {

	@Autowired
	PrenotazioneRepository pr;
	
	public Prenotazione findById(Long id){
		return pr.findById(id).get();
	}
	
	
	@Transactional
	public void addPrenotazione(Prenotazione prenotazione) {
		pr.save(prenotazione);
	}
	
	@Transactional
	public void deletePrenotazione(Prenotazione prenotazione) {
		pr.delete(prenotazione);
	}
}
