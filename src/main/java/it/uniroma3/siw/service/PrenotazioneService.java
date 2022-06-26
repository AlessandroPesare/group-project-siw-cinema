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
		if((pr.findBySpettacoloIdAndUtenteId(prenotazione.getSpettacolo().getId(), prenotazione.getUtente().getId()) != null)||(prenotazione.getSpettacolo().getNumeroPosti()<=0))
			return false;
		pr.save(prenotazione);
		return true;
	}
	
	@Transactional
	public void deletePrenotazione(Prenotazione prenotazione) {
		pr.delete(prenotazione);
	}
	
	@Transactional
	public void deletePrenotazione(Long id) {
		pr.deleteById(id);
	}
}
