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

import br.com.ronaldo.dxtsnacksapi.model.Phone;
import br.com.ronaldo.dxtsnacksapi.model.UserModel;
import br.com.ronaldo.dxtsnacksapi.repository.UserRepository;
import br.com.ronaldo.dxtsnacksapi.util.Util;

/**
 * @author Ronaldo L. Vieira
 *
 * 6 de dez de 2017
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	/*LISTA TODOS USUARIOS*/
	public List<UserModel> findAll(){
		return repository.findAll();
	}
	
	/*SALVA USUARIO*/
	public void save(UserModel userModelVM) {				
		getPhoneFormat(userModelVM);
		String password = Util.encryptPassword(userModelVM.getPassword());
		userModelVM.setPassword(password);
		repository.save(userModelVM);
	}
	
	/*ATUALIZA USUARIO*/
	public UserModel update(UserModel userModelVM, Long user_id) {
		UserModel user = findUserById(user_id);
		BeanUtils.copyProperties(userModelVM, user, "user_id", "create_date", "password", "email", "phone", "address");
		user.setEdit_date(LocalDate.now());
		return repository.save(user);
	}
	
	/*DELETA USUARIO*/
	public void delete(Long user_id) {				
		UserModel user = findUserById(user_id);
		repository.delete(user);
	}
	
	/*ENCONTRA USUARIO POR ID*/
	public UserModel findById(Long user_id) {				
		UserModel user = findUserById(user_id);
		return user;
	}

	/*************************************** METODOS PRIVADOS *****************************************/
	/**
	 * @param userModelVM
	 */
	private void getPhoneFormat(UserModel userModelVM) {
		if(userModelVM.getPhone() != null) {
    		for(Phone phone : userModelVM.getPhone()) {
    			phone.setNumber((Util.removeSymbolsPhone(phone.getNumber())));
    		}
    	}
	}
	
	 /*
   	 * ENCONTRAR E VALIDAR USUARIO POR ID
   	 */
    private UserModel findUserById(Long id) {
		UserModel user = repository.findOne(id);
		
		if(user == null || user.getDelete_date() != null) {
			throw new EmptyResultDataAccessException(1);
		}
		return user;		
	}


}
