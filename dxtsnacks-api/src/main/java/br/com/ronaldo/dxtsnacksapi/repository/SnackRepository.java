/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ronaldo.dxtsnacksapi.model.Snack;

/**
 * @author Ronaldo L. Vieira
 *
 * 9 de dez de 2017
 */
public interface SnackRepository extends JpaRepository<Snack, Long> {

}
