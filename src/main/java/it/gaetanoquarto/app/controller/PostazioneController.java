package it.gaetanoquarto.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.gaetanoquarto.app.entity.Postazione;
import it.gaetanoquarto.app.service.PostazioneService;

@RestController
@RequestMapping("/")
public class PostazioneController {

	@Autowired
	private PostazioneService ps;
	
	@PostMapping("postazioni")
	public ResponseEntity<Object> postEdificio(@RequestBody Postazione p) {
		Postazione postazione = ps.save(p);
		return new ResponseEntity<>(postazione, HttpStatus.CREATED);
	}
	
	@GetMapping("postazioni")
	public ResponseEntity<List<Postazione>> getAll() {
		List<Postazione> postazioni = ps.getAll();
		
		if(postazioni.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(postazioni, HttpStatus.OK);
	}
	
	@GetMapping("postazioni/{id}")
	public ResponseEntity<Object> getById(@PathVariable Integer id) {
		
		Optional<Postazione> postazioneObj = ps.getById(id);
		
		ResponseEntity<Object> check = checkExists(postazioneObj);
		if(check != null) return check;
		
		return new ResponseEntity<>(postazioneObj.get(), HttpStatus.OK);
	}
	
	@GetMapping("postazioni_page")
	public ResponseEntity<Object> getAll_page(Pageable pageable) {
		Page<Postazione> postazioni = ps.getAll_page(pageable);
		
		if(postazioni.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(postazioni, HttpStatus.OK);
	}
	
	@PutMapping("postazioni/{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Postazione _postazione) {
		
		Optional<Postazione> postazioneObj = ps.getById(id);
		
		ResponseEntity<Object> check = checkExists(postazioneObj);
		if(check != null) return check;
		
		Postazione postazione = postazioneObj.get();
		postazione.setTipo( _postazione.getTipo() );
		ps.save(postazione);
		
		
		return new ResponseEntity<Object>(postazione, HttpStatus.CREATED);
	}
	
	@DeleteMapping("postazioni/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		
		Optional<Postazione> postazioneObj = ps.getById(id);
		ResponseEntity<Object> check = checkExists(postazioneObj);
		if(check != null) return check;
		
		ps.delete(postazioneObj.get());
		return new ResponseEntity<>(
			String.format("Location with id %d deleted!", id), HttpStatus.OK	
		);
	}
	
	private ResponseEntity<Object> checkExists(Optional<Postazione> obj) {
		if( !obj.isPresent() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return null;
	}
	
}