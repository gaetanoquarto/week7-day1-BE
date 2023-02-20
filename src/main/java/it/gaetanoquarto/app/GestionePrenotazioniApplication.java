package it.gaetanoquarto.app;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.gaetanoquarto.app.config.EdificioConfig;
import it.gaetanoquarto.app.config.PostazioneConfig;
import it.gaetanoquarto.app.config.PrenotazioneConfig;
import it.gaetanoquarto.app.config.RuoloConfig;
import it.gaetanoquarto.app.config.UtenteConfig;
import it.gaetanoquarto.app.entity.Edificio;
import it.gaetanoquarto.app.entity.Postazione;
import it.gaetanoquarto.app.entity.Prenotazione;
import it.gaetanoquarto.app.entity.Ruolo;
import it.gaetanoquarto.app.entity.TipoPostazione;
import it.gaetanoquarto.app.entity.TipoRuolo;
import it.gaetanoquarto.app.entity.Utente;
import it.gaetanoquarto.app.service.EdificioService;
import it.gaetanoquarto.app.service.PostazioneService;
import it.gaetanoquarto.app.service.PrenotazioneService;
import it.gaetanoquarto.app.service.RuoloService;
import it.gaetanoquarto.app.service.UtenteService;



@SpringBootApplication
public class GestionePrenotazioniApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GestionePrenotazioniApplication.class, args);

	}
	
	

	@Autowired
	EdificioService es;

	@Autowired
	PostazioneService ps;

	@Autowired
	UtenteService us;

	@Autowired
	PrenotazioneService prens;
	
	@Autowired
	RuoloService ruo;

	ApplicationContext ctxE = new AnnotationConfigApplicationContext(EdificioConfig.class);
	ApplicationContext ctxP = new AnnotationConfigApplicationContext(PostazioneConfig.class);
	ApplicationContext ctxU = new AnnotationConfigApplicationContext(UtenteConfig.class);
	ApplicationContext ctxPren = new AnnotationConfigApplicationContext(PrenotazioneConfig.class);
	ApplicationContext ctxR = new AnnotationConfigApplicationContext(RuoloConfig.class);

	Scanner in = new Scanner(System.in);

	@Override
	public void run(String... args) throws Exception {
	//	popolaDB();
//		gestore();
//		((AnnotationConfigApplicationContext) ctxE).close();
//		((AnnotationConfigApplicationContext) ctxP).close();
//		((AnnotationConfigApplicationContext) ctxU).close();
//		((AnnotationConfigApplicationContext) ctxR).close();
//		((AnnotationConfigApplicationContext) ctxPren).close();
	}

	public void popolaDB() {
		
//		Ruolo r1 = (Ruolo) ctxR.getBean("r");
//		r1.setRuolo(TipoRuolo.ROLE_USER);
//		ruo.insertRuolo(r1);
//
//		
//		Utente u1 = (Utente) ctxU.getBean("u");
//		u1.setNome("gaetano");
//		u1.setEmail("gaetano@mail.com");
//		u1.setUsername("gaetano");
//		u1.setAttivo(true);
//		u1.setPassword("ciao");
//		u1.setRuoli( new HashSet<>() {{
//			add(r1);
//		}});
//		us.save(u1);
		
		Edificio e1 = (Edificio) ctxE.getBean("e");
		e1.setCitta("Bari");
		e1.setCodice("a5do21v9");
		e1.setIndirizzo("Via Sparano, 15");
		e1.setNome("fiera");
		
		es.save(e1);
		
		System.out.println("DB POPOLATO");
		
		
	}
	
	public void gestore() {
		try {
			System.out.println("Benvenuto nel gestore di prenotazioni");
			System.out.println("-------------------------------------");
			System.out.println("1 - area amministrativa");
			System.out.println("2 - crea utente");
			System.out.println("3 - prenota");
			System.out.println("4 - ricerca");
			System.out.println("Seleziona un numero");
			int selezione = in.nextInt();
			switch (selezione) {
			case (1):
				selezioneAmministrazione();
				break;
			case (2):
				creaUtente();
				break;
			case (3):
				creaPrenotazione();
				break;
			case (4):
				ricerca();
			break;
			default:
				System.out.println("Inserisci un valore corretto!");
			}
			
		}catch (Exception e) {
			System.out.println("Errore!");
		}
		

	}

	static String tipoSelect;
	public void ricerca() {
		System.out.println("Benvenuto nella ricerca!");
		System.out.println("1 - Ricerca per Tipo");
		System.out.println("2 - Ricerca per città");
		System.out.println("3 - Ricerca per tipo e città");
		System.out.println("Inserisci un numero");
		int selezione = in.nextInt();
		switch(selezione) {
		case(1):
			ricercaPerTipo();
		break;
		case(2):
			ricercaPerCitta();
		break;
		case(3):
			ricercaPerTipoAndCitta();
		break;
		default:
			System.out.println("Inserisci un valore esatto!");
		break;
		}
		
	}
	public void ricercaPerTipo() {
		System.out.println("1 - PRIVATO");
		System.out.println("2 - OPENSPACE");
		System.out.println("3 - SALA_RIUNIONI");
		System.out.println("Inserisci un numero");
		int selezioneTipo = in.nextInt();
		
		if (selezioneTipo == 1) {
			tipoSelect = "PRIVATO";
		} else if (selezioneTipo == 2) {
			tipoSelect = "OPENSPACE";
		} else if (selezioneTipo == 3) {
			tipoSelect= "SALA_RIUNIONI";
		} else {
			System.out.println("inserisci un valore esatto!");
		}	
		
		findFromTipo(tipoSelect);
	}
	public void ricercaPerCitta() {
		System.out.println("Inserisci nome città: ");
		String citta = in.next();
		findFromCitta(citta);
	}
	
	public void ricercaPerTipoAndCitta() {
		System.out.println("1 - PRIVATO");
		System.out.println("2 - OPENSPACE");
		System.out.println("3 - SALA_RIUNIONI");
		System.out.println("Inserisci un numero");
		int selezioneTipo = in.nextInt();
		
		if (selezioneTipo == 1) {
			tipoSelect = "PRIVATO";
		} else if (selezioneTipo == 2) {
			tipoSelect = "OPENSPACE";
		} else if (selezioneTipo == 3) {
			tipoSelect= "SALA_RIUNIONI";
		} else {
			System.out.println("inserisci un valore esatto!");
		}	
		in.nextLine();
		System.out.println("Inserisci nome città: ");
		String citta = in.nextLine();
		findFromTipoAndCitta(tipoSelect, citta);
	}
	
	public void creaPrenotazione() {
		in.nextLine();
		System.out.println("Inserisci quando vuoi prenotare(AAA-MM-DD)");
		String dataPren = in.nextLine();
		LocalDate dataPrenotazione = LocalDate.parse(dataPren);
		LocalDate dataScad = LocalDate.parse(dataPren).plusDays(1);
		LocalDate dataCheck = dataPrenotazione.minusDays(2);
		//CHECK
				if (LocalDate.now().isAfter(dataCheck)) {
			System.out.println("Puoi prenotare SOLO a partire da due giorni prima della data indicata!");
			System.exit(0);
		} else if(LocalDate.now().isEqual(dataCheck) || LocalDate.now().isBefore(dataCheck)) {
			System.out.println("inserisci ID della postazione da prenotare");
			int idPos = in.nextInt();
			Postazione postazione = getPostazione(idPos);
			System.out.println("inserisci ID utente");
			int idUser = in.nextInt();
			Utente utente = getUtente(idUser);
			long countElementi = prens.getCountPrenotazioni(dataPrenotazione, idPos);
			System.out.println(countElementi);
			int nMaxOccupanti = postazione.getNMaxOccupanti();
			if(countElementi == nMaxOccupanti) {
				System.out.println("La postazione " + idPos + " è completa per la data scelta! Non puoi prenotare!");
				System.exit(0);
			} else if(countElementi < nMaxOccupanti){
				long contaElementi = prens.getCountPrenotazioniUtente(dataPrenotazione, idUser);
				if(contaElementi >= 1) {
					System.out.println("Hai già una prenotazione per questa data. Non puoi prenotare altre postazioni!");
					System.exit(0);
				} else {
					insertPrenotazione(dataPrenotazione, dataScad, postazione, utente);

				}
			}
		}

	}

	private Utente getUtente(int idUser) {

		Optional<Utente> utenteOpt = us.getById(idUser);
		Utente utente = utenteOpt.get();
		return utente;
	}

	public void findAllPostazioni() {
		String sep = "-----------------------------------";
		System.out.println("postazioni nella tabella 'postazioni':");
		List<Postazione> postazione = ps.getAllPostazioni();
		postazione.stream().forEach(pos -> {
			System.out.println(pos);
		});
	}
	public void findFromTipoAndCitta(String tipo, String citta) {
		String sep = "-----------------------------------";
		List<Postazione> postazione = ps.getFromTipoAndCitta(tipo, citta);
		postazione.stream().forEach(pos -> {
			System.out.println(pos);
		});
	}
	public void findFromCitta(String citta) {
		String sep = "-----------------------------------";
		List<Postazione> postazione = ps.getByCitta(citta);
		postazione.stream().forEach(pos -> {
			System.out.println(pos);
		});
	}
	public void findFromTipo(String tipo) {
		String sep = "-----------------------------------";
		List<Postazione> postazione = ps.getByTipo(tipo);
		postazione.stream().forEach(pos -> {
			System.out.println(pos);
		});
	}
	
	public void check(String data, int id) {
		
	}

	// -----METODI PER GESTORE------
	public void selezioneAmministrazione() {
		System.out.println("1 - crea edificio");
		System.out.println("2 - crea postazione");
		System.out.println("Seleziona un numero");
		int selezione = in.nextInt();
		switch (selezione) {
		case (1):
			creaEdificio();
			break;
		case (2):
			creaPostazione();
		default:
			System.out.println("Inserisci un valore corretto!");
			break;
		}

	}

	public void creaEdificio() {
		in.nextLine();
		System.out.println("Inserisci nome");
		String nome = in.nextLine();
		System.out.println("Inserisci città");
		String citta = in.nextLine();
		System.out.println("Inserisci Indirizzo");
		String indirizzo = in.nextLine();
		insertEdificio(citta, indirizzo, nome);
	}

	static TipoPostazione tipoPostazione;

	public void creaPostazione() {
		System.out.println("1 - Privato");
		System.out.println("2 - Openspace");
		System.out.println("3 - Sala riunioni");
		System.out.println("Inserisci il tipo");
		int selezioneTipo = in.nextInt();
		if (selezioneTipo == 1) {
			tipoPostazione = TipoPostazione.PRIVATO;
		} else if (selezioneTipo == 2) {
			tipoPostazione = TipoPostazione.OPENSPACE;
		} else if (selezioneTipo == 3) {
			tipoPostazione = TipoPostazione.SALA_RIUNIONI;
		} else {
			System.out.println("inserisci un valore esatto!");
		}
		System.out.println("inserisci il numero massimo di occupanti");
		int nMaxOccupanti = in.nextInt();
		System.out.println("Inserisci ID edificio");
		int idEdificio = in.nextInt();
		Edificio edificio = getEdificio(idEdificio);
		insertPostazione(nMaxOccupanti, tipoPostazione, edificio);
	}

	public void creaUtente() {
		in.nextLine();
		System.out.println("Inserisci username");
		String username = in.nextLine();
		System.out.println("Inserisci nome e cognome");
		String nome = in.nextLine();
		System.out.println("Inserisci email");
		String email = in.nextLine();
		insertUtente(nome, email, username);
	}

	// --------METODI INSTANZIAMENTO----------
	public Edificio insertEdificio(String citta, String indirizzo, String nome) {
		Edificio e = (Edificio) ctxE.getBean("e");
		e.setCitta(citta);
		e.setIndirizzo(indirizzo);
		e.setNome(nome);
		es.insertEdificio(e);
		return e;
	}

	public Edificio getEdificio(int id) {
		Optional<Edificio> edificioOpt = es.getById(id);
		Edificio edificio = edificioOpt.get();
		return edificio;
	}

	public Postazione getPostazione(int id) {
		Optional<Postazione> postazioneOpt = ps.getById(id);
		Postazione postazione = postazioneOpt.get();
		return postazione;
	}

	public Postazione insertPostazione(int nMaxOccupanti, TipoPostazione tipo, Edificio edificio) {
		Postazione pos = (Postazione) ctxP.getBean("pos");
		pos.setEdificio(edificio);
		pos.setNMaxOccupanti(nMaxOccupanti);
		pos.setTipo(tipo);
		ps.insertPostazione(pos);
		edificio.setPostazioni(new HashSet<Postazione>() {
			{
				add(pos);
			}
		});
		return pos;
	}

	public Utente insertUtente(String nome, String email, String username) {
		Utente u = (Utente) ctxU.getBean("u");
		u.setNome(nome);
		u.setEmail(email);
		u.setUsername(username);
		us.insertUtente(u);
		return u;
	}

	public Prenotazione insertPrenotazione(LocalDate dataPren, LocalDate dataScad, Postazione postazione,
			Utente utente) {
		Prenotazione pre = (Prenotazione) ctxPren.getBean("p");
		pre.setDataPrenotazione(dataPren);
		pre.setDataScadenza(dataScad);
		pre.setPostazione(postazione);
		pre.setUtente(utente);
		prens.insertPrenotazione(pre);
		return pre;
	}

}
