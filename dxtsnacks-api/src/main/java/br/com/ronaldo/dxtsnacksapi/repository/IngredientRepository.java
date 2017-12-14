/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ronaldo.dxtsnacksapi.model.Ingredient;

/**
 * @author Ronaldo L. Vieira
 *
 * 9 de dez de 2017
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

}
