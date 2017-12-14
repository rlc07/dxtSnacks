/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.ronaldo.dxtsnacksapi.model.Ingredient;
import br.com.ronaldo.dxtsnacksapi.repository.IngredientRepository;

/**
 * @author Ronaldo L. Vieira
 *
 * 9 de dez de 2017
 */
@Service
public class IngredientService {
	
	@Autowired
	private IngredientRepository repository;
	
	/*LISTA TODOS INGREDIENTES*/
	public List<Ingredient> findAll(){
		return repository.findAll();
	}
	
	/*SALVA INGREDIENTE*/
	public void save(Ingredient ingredientVM) {				
		repository.save(ingredientVM);
	}
	
	/*ATUALIZA INGREDIENTE*/
	public Ingredient update(Ingredient ingredientVM, Long id) {
		Ingredient ingredient = findIngredientById(id);
		BeanUtils.copyProperties(ingredientVM, ingredient, "ingredient_id", "create_date");
		ingredient.setEdit_date(LocalDate.now());
		return repository.save(ingredient);
	}
	
	/*DELETA INGREDIENTE*/
	public void delete(Long id) {				
		Ingredient ingredient = findIngredientById(id);
		repository.delete(ingredient);
	}
	
	/*ENCONTRA INGREDIENTE POR ID*/
	public Ingredient findById(Long id) {				
		Ingredient ingredient = findIngredientById(id);
		return ingredient;
	}

	/*************************************** METODOS PRIVADOS *****************************************/
	
	 /*
   	 * ENCONTRAR E VALIDAR INGREDIENTE POR ID
   	 */
    private Ingredient findIngredientById(Long id) {
    	Ingredient ingredient = repository.findOne(id);
		
		if(ingredient == null || ingredient.getDelete_date() != null) {
			throw new EmptyResultDataAccessException(1);
		}
		return ingredient;		
	}


}
