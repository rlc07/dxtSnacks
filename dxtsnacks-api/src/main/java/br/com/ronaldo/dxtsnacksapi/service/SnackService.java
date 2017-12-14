/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.ronaldo.dxtsnacksapi.model.Ingredient;
import br.com.ronaldo.dxtsnacksapi.model.Snack;
import br.com.ronaldo.dxtsnacksapi.model.SnackIngredient;
import br.com.ronaldo.dxtsnacksapi.repository.SnackIngredientRepository;
import br.com.ronaldo.dxtsnacksapi.repository.SnackRepository;
import br.com.ronaldo.dxtsnacksapi.util.Constant;

/**
 * @author Ronaldo L. Vieira
 *
 * 9 de dez de 2017
 */
@Service
public class SnackService {

	@Autowired
	private SnackRepository repository;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private SnackIngredientRepository snackIngredientRepository;

	/*LISTA TODOS LANCHES*/
	public List<Snack> findAll(){
		return repository.findAll();
	}

	/*SALVA LANCHE*/
	@Transactional(rollbackOn = Exception.class)
	public void save(Snack snackVM) {

		BigDecimal totalTemp = new BigDecimal(1);
		snackVM.setAmount(totalTemp);
		Snack snack = repository.save(snackVM);

		BigDecimal total = calculateAmountSnack(snackVM, snack);

		snack.setAmount(total);
		repository.save(snack);

	}

	/*ATUALIZA LANCHE*/
	@Transactional(rollbackOn = Exception.class)
	public Snack update(Snack snackVM, Long id) {
		Snack snack = findSnackById(id);
		BeanUtils.copyProperties(snackVM, snack, "snack_id", "create_date", "ingredient", "amount");
		snack.setEdit_date(LocalDate.now());
		return repository.save(snack);
	}

	/*DELETA LANCHE*/
	public void delete(Long id) {				
		Snack snack = findSnackById(id);
		repository.delete(snack);
	}

	/*ENCONTRA LANCHE POR ID*/
	public Snack findById(Long id) {				
		Snack snack = findSnackById(id);
		return snack;
	}

	/*************************************** METODOS PRIVADOS *****************************************/

	/*
	 * ENCONTRAR E VALIDAR LANCHE POR ID
	 */
	private Snack findSnackById(Long id) {
		Snack snack = repository.findOne(id);

		if(snack == null || snack.getDelete_date() != null) {
			throw new EmptyResultDataAccessException(1);
		}
		return snack;		
	}

	/*
	 * CALCULA VALOR DO LANCHE
	 */
	private BigDecimal calculateAmountSnack(Snack snackVM, Snack snack) {

		BigDecimal total = new BigDecimal(0);	
		List<Ingredient> list = new ArrayList<>();

		for(Ingredient i : snackVM.getIngredient()) {

			int amountTopay = i.getQuantity();
			
			/*CRIA UM OBJETO DE INGREDIENTES E LANCHES*/
			SnackIngredient snackIngredient = new SnackIngredient();
			snackIngredient.setSnack(snack);

			/*RECUPERA INGREDIENTE POR ID - INFORMADO PELO USUÁRIO*/
			Ingredient ingredient = ingredientService.findById(i.getIngredient_id());
			snackIngredient.setIngredient(ingredient);    		
			snackIngredient.setQuantity(i.getQuantity());
			
			if(ingredient.getName().equals(Constant.MUCH_BURGER) && i.getQuantity() >= Constant.VALUE_PROMOTION) {
				amountTopay = 0;
				amountTopay = checkDiscount(i.getQuantity());				
			}
			
			if(ingredient.getName().equals(Constant.MUCH_CHEESE) && i.getQuantity() >= Constant.VALUE_PROMOTION) {
				amountTopay = 0;
				amountTopay = checkDiscount(i.getQuantity());				
			}

			/*FAZ CALCULO DO LANCHE DE ACORDO COM INGREDIENTES INFORMADO PELO USUÁRIO*/
			total = total.add(ingredient.getAmount().multiply(new BigDecimal(amountTopay)));

			/*ADICIONA INGREDIENTES EM UMA LISTA*/
			list.add(ingredient);

			/*SALVA INGREDIENTE E LANCHE - (PERSONALIZADO)*/
			snackIngredientRepository.save(snackIngredient);
		}
		
		boolean isLight = checkPromotion(list);
		
		if(isLight) {
			BigDecimal discount = total.multiply(new BigDecimal(Constant.DISCOUNT_PERCENTAGE));
			total = total.subtract(discount);
		}
		return total;
	}

	
	private boolean checkPromotion(List<Ingredient> list) {
		boolean isPromotion = false;
		
		for(Ingredient i : list) {
			
			if(i.getName().equals(Constant.LETTUCE)) {
				isPromotion = true;
			}
			
			if(i.getName().equals(Constant.BACON)) {
				isPromotion = false;
			}
		}
		
		return isPromotion;
	}

	private int checkDiscount(int value) {

		int cont = 0;
		int lastValue = 0;
		int total = value - Constant.VALUE_PROMOTION;

		if(total == 0) {
			cont = cont + Constant.INCREMENT_PROMOTION;
		}else {

			do {
				value = value - Constant.VALUE_PROMOTION;

				if(value >= 1) {		

					cont = cont + Constant.INCREMENT_PROMOTION;
					lastValue = value;

				}else if(value == 0){

					cont = cont + lastValue -1;
				}else {
					cont = cont + lastValue;
				}

			}while(value > 0);
		}
		
		return (cont == 0 ? value : cont);
	}

}
