package it.gaetanoquarto.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.gaetanoquarto.app.entity.Prenotazione;
import it.gaetanoquarto.app.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {
	
	@Autowired
    private PrenotazioneRepository prenotazioneRepo;
	

    public void insertPrenotazione(Prenotazione pre) {
        prenotazioneRepo.save(pre);
        System.out.println("Prenotazione inserita correttamente!");
    }
    public Prenotazione save(Prenotazione pre) {
    	return prenotazioneRepo.save(pre);
    }
    public long getCountPrenotazioni(LocalDate data, int id) {
    	return prenotazioneRepo.getCountPrenotazioni(data,id);
    }
    public long getCountPrenotazioniUtente(LocalDate data, int id) {
    	return prenotazioneRepo.getCountPrenotazioniUtente(data,id);
    }
    
    public List<Prenotazione> getAll() {
  		return prenotazioneRepo.findAll();
  	}
    
    public Optional<Prenotazione> getById(int id) {
  		return prenotazioneRepo.findById(id);
  	}
    
    public Page<Prenotazione> getAll_page(Pageable pageable) {
		return prenotazioneRepo.findAll(pageable);
	}
	
	public void delete(Prenotazione p) {
		prenotazioneRepo.delete(p);
	}

}
