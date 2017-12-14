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

import br.com.ronaldo.dxtsnacksapi.model.UserModel;
import br.com.ronaldo.dxtsnacksapi.service.UserService;

/**
 * @author Ronaldo L. Vieira
 *
 * 6 de dez de 2017
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	/**
	 * LISTA TODOS USUARIOs - RETORNA 200 OK
	 */
	@GetMapping
	public ResponseEntity<List<UserModel>> findAll(){
		List<UserModel> users = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	
	/**
	 * SALVA USUARIO - RETORNA 201 CRIADO COM SUCESSO
	 */
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody UserModel userModelVM){
		service.save(userModelVM);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	/**
	 * ATUALIZA USUARIO - RETORNA 200 OK COM DADOS ATUALIZADOS
	 */
	@PutMapping("/{id}")
	public ResponseEntity<UserModel> update(@RequestBody UserModel userModelVM, @PathVariable("id") Long id){
		UserModel userModel = service.update(userModelVM, id);
		return ResponseEntity.status(HttpStatus.OK).body(userModel);
	}

	/**
	 * DELETA USUARIO - RETORNA 204 NENHUM CONTEUDO ENCONTRADO
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	/**
	 * PROCURA USUARIO POR ID - RETORNA 200 COM DADOS DO USUARIO
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UserModel> findById(@PathVariable("id") Long id){
		UserModel user = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
}
