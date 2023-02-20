package it.gaetanoquarto.app.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import it.gaetanoquarto.app.entity.Edificio;
import it.gaetanoquarto.app.entity.Postazione;
import it.gaetanoquarto.app.entity.Prenotazione;
import it.gaetanoquarto.app.entity.Utente;

@Configuration
@PropertySource("classpath:application.properties")
public class EdificioConfig {
	
	
	@Bean
    public Edificio e() {
        Edificio e = new Edificio();
        return e;
    }

}
