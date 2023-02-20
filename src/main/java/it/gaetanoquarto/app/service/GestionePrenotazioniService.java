package it.gaetanoquarto.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class GestionePrenotazioniService {
	
	@Value("${gestioneprenotazioniservice.it}")
	private String it;
	@Value("${gestioneprenotazioniservice.en}")
	private String en;
	@Value("${gestioneprenotazioniservice.error}")
	private String error;

	
	public String informativa(String lingua) {
		if (lingua.equalsIgnoreCase("IT")) {
			return it;
		}
		else if (lingua.equalsIgnoreCase("EN")) {
			return en;
		} else {
			return error;
		}
	}

}
