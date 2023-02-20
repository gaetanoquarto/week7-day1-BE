package it.gaetanoquarto.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import it.gaetanoquarto.app.entity.Postazione;
import it.gaetanoquarto.app.entity.TipoPostazione;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Integer>{

	@Query(
			nativeQuery = true,
			value = "SELECT * FROM postazioni WHERE postazioni.is_libero = true"
		)List<Postazione> getAll();
	
	@Query(
			nativeQuery = true,
			value = "SELECT postazioni.* FROM postazioni WHERE postazioni.tipo = :tipo")
	List<Postazione> findByTipoPostazione(@Param("tipo")String tipo);
	@Query(
			nativeQuery = true,
			value = "SELECT postazioni.* FROM postazioni JOIN edifici ON postazioni.edificio_id = edifici.edificio_id WHERE edifici.citta = :citta")
	List<Postazione> findByCittaPostazione(@Param("citta")String citta);
	 @Query(
			 nativeQuery = true,
			 value = "SELECT postazioni.* FROM postazioni JOIN edifici ON postazioni.edificio_id = edifici.edificio_id WHERE postazioni.tipo = :tipo AND edifici.citta = :citta")
	    List<Postazione> findByTipoPostazioneAndCittà(@Param("tipo")String tipo, @Param("citta")String citta);
}
