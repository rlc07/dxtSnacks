/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.ronaldo.dxtsnacksapi.model.UserModel;
import br.com.ronaldo.dxtsnacksapi.repository.UserRepository;


/**
 * @author Ronaldo L. Vieira
 *
 * 7 de dez de 2017
 */
@Service
@CrossOrigin
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserModel> userOptional = repository.findByEmail(email);
		UserModel user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		

		return new UserSystem(user, getPermission(user));
	}

	private Collection<? extends GrantedAuthority> getPermission(UserModel userModel) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		userModel.getPermission().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getName().toUpperCase())));
		return authorities;
	}

}
