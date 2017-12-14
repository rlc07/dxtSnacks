/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.ronaldo.dxtsnacksapi.model.UserModel;


/**
 * @author Ronaldo L. Vieira
 *
 * 7 de dez de 2017
 */
public class UserSystem extends User {

	private static final long serialVersionUID = 1L;
	
	private UserModel user;
	
	public UserSystem(UserModel user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		this.user = user;
	}

	public UserModel getUser() {
		return user;
	}

}