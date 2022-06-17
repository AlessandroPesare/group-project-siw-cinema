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
	public boolean savePrenotazione(Prenotazione prenotazione) {
		if(pr.findBySpettacoloIdAndUtenteId(prenotazione.getSpettacolo().getId(), prenotazione.getUtente().getId()) != null)
			return false;
		pr.save(prenotazione);
		return true;
	}
	
	@Transactional
	public void deletePrenotazione(Prenotazione prenotazione) {
		pr.delete(prenotazione);
	}
}
