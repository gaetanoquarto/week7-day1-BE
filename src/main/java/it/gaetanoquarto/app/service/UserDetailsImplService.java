package it.gaetanoquarto.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.gaetanoquarto.app.config.UserDetailsImpl;
import it.gaetanoquarto.app.entity.Utente;



@Service
public class UserDetailsImplService implements UserDetailsService {
	
	@Autowired
	private UtenteService us;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utente> user = us.getUserByUsername(username);
		
		if( user.isPresent() ) {
			return UserDetailsImpl.build( user.get() );
		}
		else {
			throw new UsernameNotFoundException(username);
		}
		
		//return null;
	}
	
}