package it.gaetanoquarto.app.config;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.gaetanoquarto.app.entity.Ruolo;
import it.gaetanoquarto.app.entity.Utente;
import it.gaetanoquarto.app.service.UserDetailsImplService;
import it.gaetanoquarto.app.service.UtenteService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests()
		.antMatchers("/auth_update_user_pw", "/edifici")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and().formLogin()
		.successForwardUrl("/login_success")
		.and()
		.logout()
		.and()
		.csrf()
		.disable();
	}

	
//	@Override
//	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//		Optional<Utente> authUserObj = us.getById(1);
//		Utente authUser = authUserObj.get();
//		
//		String role = "USER";
//		
//		Set<Ruolo> roles = authUser.getRuoli();
//		
//		for(Ruolo r : roles) {
//			if(r.getRuolo().toString().contains(role)) {
//				role = "ADMIN";
//				break;
//			}
//		}
//		
//		auth.inMemoryAuthentication()
//			.withUser(authUser.getUsername())
//			.password(passwordEncoder().encode(authUser.getPassword()))
//			.roles(role);
//	}
	
	@Autowired
	private UserDetailsImplService us;
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(us)
			.passwordEncoder( passwordEncoder() );
	}
	
//	metodo per criptare la password
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
