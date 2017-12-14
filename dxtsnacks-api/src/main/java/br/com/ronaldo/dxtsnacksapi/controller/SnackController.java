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

import br.com.ronaldo.dxtsnacksapi.model.Snack;
import br.com.ronaldo.dxtsnacksapi.service.SnackService;

/**
 * @author Ronaldo L. Vieira
 *
 * 9 de dez de 2017
 */

@RestController
@RequestMapping("/api/snack")
public class SnackController {
	
	@Autowired
	private SnackService service;
	
	/**
	 * LISTA TODOS LANCHES - RETORNA 200 OK
	 */
	@GetMapping
	public ResponseEntity<List<Snack>> findAll(){
		List<Snack> snack = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(snack);
	}
	
	
	/**
	 * SALVA LANCHE - RETORNA 201 CRIADO COM SUCESSO
	 */
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody Snack snackVM){
		service.save(snackVM);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	/**
	 * ATUALIZA LANCHE - RETORNA 200 OK COM DADOS ATUALIZADOS
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Snack> update(@RequestBody Snack snackVM, @PathVariable("id") Long id){
		Snack snack = service.update(snackVM, id);
		return ResponseEntity.status(HttpStatus.OK).body(snack);
	}

	/**
	 * DELETA LANCHE - RETORNA 204 NENHUM CONTEUDO ENCONTRADO
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	/**
	 * PROCURA LANCHE POR ID - RETORNA 200 COM DADOS DO USUARIO
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Snack> findById(@PathVariable("id") Long id){
		Snack snack = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(snack);
	}
}
