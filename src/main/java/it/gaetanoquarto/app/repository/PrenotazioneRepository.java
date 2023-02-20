package it.gaetanoquarto.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.gaetanoquarto.app.entity.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer>{

	@Query(
			nativeQuery = true,
			value = "SELECT COUNT(*) FROM prenotazioni WHERE data_prenotazione = :data AND postazione_id = :postazioneId")
			long getCountPrenotazioni(@Param("data")LocalDate data, @Param("postazioneId")int postazioneId);
	
	@Query(
			nativeQuery = true,
			value = "SELECT COUNT(*) FROM prenotazioni WHERE data_prenotazione = :data AND utente_id = :utenteId")
	long getCountPrenotazioniUtente(@Param("data")LocalDate data, @Param("utenteId")int utenteId);
	

}
