/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * @author Ronaldo L. Vieira
 *
 * 9 de dez de 2017
 */
@Entity
@Table(name = "ingredients")
@SQLDelete(sql="UPDATE ingredients SET delete_date = CURRENT_DATE() WHERE ingredient_id=?")
@Where(clause ="delete_date IS NULL")
public class Ingredient extends ApplicationModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingredient_id;
	
	@NotNull(message = "O campo nome deve ser preenchido")
	private String name;
	
	@NotNull(message = "O campo valor deve ser preenchido")
	private BigDecimal amount;

	@Transient
	private int quantity;

	public Long getIngredient_id() {
		return ingredient_id;
	}

	public void setIngredient_id(Long ingredient_id) {
		this.ingredient_id = ingredient_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
		result = prime * result + ((ingredient_id == null) ? 0 : ingredient_id.hashCode());
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
		Ingredient other = (Ingredient) obj;
		if (ingredient_id == null) {
			if (other.ingredient_id != null)
				return false;
		} else if (!ingredient_id.equals(other.ingredient_id))
			return false;
		return true;
	}
	
	

}
