package it.gaetanoquarto.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.gaetanoquarto.app.entity.Ruolo;
import it.gaetanoquarto.app.repository.RuoloRepository;

@Service
public class RuoloService {

	@Autowired
	RuoloRepository rr;
	
	public Ruolo insertRuolo(Ruolo r) {
        return rr.save(r);
    }
	

}
