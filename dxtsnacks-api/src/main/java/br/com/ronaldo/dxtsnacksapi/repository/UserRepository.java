/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ronaldo.dxtsnacksapi.model.UserModel;

/**
 * @author Ronaldo L. Vieira
 *
 * 6 de dez de 2017
 */
public interface UserRepository extends JpaRepository<UserModel, Long> {

	/**
	 * @param email
	 * @return
	 */
	Optional<UserModel> findByEmail(String email);

}
