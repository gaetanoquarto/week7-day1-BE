package it.gaetanoquarto.app.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "postazioni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Builder
public class Postazione {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postazione_id;
	
	@Enumerated(EnumType.STRING)
	private TipoPostazione tipo;
	
	private int nMaxOccupanti;
	
	@ManyToOne
	@JoinColumn(name = "edificio_id")
	@JsonIgnoreProperties("postazioni")
	private Edificio edificio;
	
	@OneToMany(mappedBy = "postazione")
	@JsonIgnoreProperties("postazione")
	private Set<Prenotazione> prenotazioni;


	@Override
	public String toString() {
		return "ID POSTAZIONE: - " + postazione_id + " " + " | Nome edificio: " + edificio.getNome() +" | Città: " + edificio.getCitta() + " | Indirizzo: " + edificio.getIndirizzo();
	}
	
	
	
}
