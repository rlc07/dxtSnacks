/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.ronaldo.dxtsnacksapi.model.Ingredient;
import br.com.ronaldo.dxtsnacksapi.service.IngredientService;

/**
 * @author Ronaldo L. Vieira
 *
 * 9 de dez de 2017
 */

@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {
	
	@Autowired
	private IngredientService service;
	
	/**
	 * LISTA TODOS INGREDIENTs - RETORNA 200 OK
	 */
	@GetMapping
	public ResponseEntity<List<Ingredient>> findAll(){
		List<Ingredient> ingredients = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(ingredients);
	}
	
	
	/**
	 * SALVA INGREDIENT - RETORNA 201 CRIADO COM SUCESSO
	 */
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody Ingredient ingredientVM){
		service.save(ingredientVM);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	/**
	 * ATUALIZA INGREDIENTE - RETORNA 200 OK COM DADOS ATUALIZADOS
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Ingredient> update(@RequestBody Ingredient ingredientVM, @PathVariable("id") Long id){
		Ingredient ingredient = service.update(ingredientVM, id);
		return ResponseEntity.status(HttpStatus.OK).body(ingredient);
	}

	/**
	 * DELETA INGREDIENTE - RETORNA 204 NENHUM CONTEUDO ENCONTRADO
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	/**
	 * PROCURA INGREDIENTE POR ID - RETORNA 200 COM DADOS DO USUARIO
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Ingredient> findById(@PathVariable("id") Long id){
		Ingredient ingredient = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ingredient);
	}
}
