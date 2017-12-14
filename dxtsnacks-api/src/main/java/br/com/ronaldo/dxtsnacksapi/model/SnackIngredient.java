/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * @author Ronaldo L. Vieira
 *
 * 9 de dez de 2017
 */
@Entity
@Table(name = "snack_ingredients")
@SQLDelete(sql="UPDATE snack_ingredients SET delete_date = CURRENT_DATE() WHERE snack_ingredient_id=?")
@Where(clause ="delete_date IS NULL")
public class SnackIngredient extends ApplicationModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long snack_ingredient_id;
	
	@ManyToOne
	@JoinColumn(name = "snack_id")
	private Snack snack;
	
	@ManyToOne
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;

	private int quantity;
	
	public Long getSnack_ingredient_id() {
		return snack_ingredient_id;
	}

	public void setSnack_ingredient_id(Long snack_ingredient_id) {
		this.snack_ingredient_id = snack_ingredient_id;
	}

	public Snack getSnack() {
		return snack;
	}

	public void setSnack(Snack snack) {
		this.snack = snack;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((snack_ingredient_id == null) ? 0 : snack_ingredient_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SnackIngredient other = (SnackIngredient) obj;
		if (snack_ingredient_id == null) {
			if (other.snack_ingredient_id != null)
				return false;
		} else if (!snack_ingredient_id.equals(other.snack_ingredient_id))
			return false;
		return true;
	}
	

}
