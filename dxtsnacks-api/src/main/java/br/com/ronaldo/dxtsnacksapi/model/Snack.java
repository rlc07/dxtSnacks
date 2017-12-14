/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
@Table(name = "snacks")
@SQLDelete(sql="UPDATE snacks SET delete_date = CURRENT_DATE() WHERE snack_id=?")
@Where(clause ="delete_date IS NULL")
public class Snack extends ApplicationModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long snack_id;
	
	@NotNull(message = "O campo nome deve ser preenchido")
	private String name;
	
	@NotNull(message = "O campo descrição deve ser preenchido")
	private String description;
	
	private BigDecimal amount;

	
	/*@NotNull(message = "Informe os ingredientes desejado")
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "snack_ingredients" , joinColumns = @JoinColumn(name = "snack_id") 
	   , inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	@SQLInsert(sql="insert into snack_ingredients (snack_id, ingredient_id, quantity) "
			+ "values (?, ?,?)")
	private List<Ingredient> ingredient;*/
	
	@Transient
	private List<Ingredient> ingredient;
	
	public Long getSnack_id() {
		return snack_id;
	}

	public void setSnack_id(Long snack_id) {
		this.snack_id = snack_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<Ingredient> getIngredient() {
		return ingredient;
	}

	public void setIngredient(List<Ingredient> ingredient) {
		this.ingredient = ingredient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((snack_id == null) ? 0 : snack_id.hashCode());
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
		Snack other = (Snack) obj;
		if (snack_id == null) {
			if (other.snack_id != null)
				return false;
		} else if (!snack_id.equals(other.snack_id))
			return false;
		return true;
	}
	
	

}
