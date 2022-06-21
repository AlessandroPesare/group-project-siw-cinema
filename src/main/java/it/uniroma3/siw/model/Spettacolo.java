package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Spettacolo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDateTime dataConOra;
	
	private int numeroPosti;
	
	@ManyToOne
	private Film film;
	
	@ManyToOne
	private Sala sala;
	
	@OneToMany(mappedBy="spettacolo", cascade= CascadeType.REMOVE)
	private List<Prenotazione> prenotazioni;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataConOra() {
		return dataConOra;
	}

	/*public void setDataConOra(LocalDateTime dataConOra) {
		this.dataConOra = dataConOra;
	}*/
	
	public void setDataConOra(String data) {
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm");
		this.dataConOra = LocalDateTime.parse(data, DateTimeFormatter.ISO_DATE_TIME);
		
	}

	public int getNumeroPosti() {
		return numeroPosti;
	}

	public void setNumeroPosti(int numeroPosti) {
		this.numeroPosti = numeroPosti;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
		this.numeroPosti = sala.getNumeroPosti();
	}
	
	
}
