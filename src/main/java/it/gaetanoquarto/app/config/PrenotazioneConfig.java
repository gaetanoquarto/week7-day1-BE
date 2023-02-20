package it.gaetanoquarto.app.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import it.gaetanoquarto.app.entity.Postazione;
import it.gaetanoquarto.app.entity.Prenotazione;
import it.gaetanoquarto.app.entity.Utente;

@Configuration
@PropertySource("classpath:application.properties")
public class PrenotazioneConfig {
	
	@Bean
    public Prenotazione p() {
        Prenotazione p = new Prenotazione();
        return p;
    }

}
