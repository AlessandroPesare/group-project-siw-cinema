package it.uniroma3.siw.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames= {"spettacolo_id", "utente_id"}))
public class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@ManyToOne
	Spettacolo spettacolo;
	
	@ManyToOne
	User utente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Spettacolo getSpettacolo() {
		return spettacolo;
	}

	public void setSpettacolo(Spettacolo spettacolo) {
		this.spettacolo = spettacolo;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}

}
