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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Ronaldo L. Vieira
 *
 * 6 de dez de 2017
 */
@Entity
@Table(name = "phone")
@SQLDelete(sql="UPDATE phone SET delete_date = CURRENT_DATE() WHERE phone_id=?")
@Where(clause ="delete_date IS NOT NULL")
public class Phone extends ApplicationModel implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long phone_id;
	
	@NotNull(message = "O campo tipo de telefone deve ser preenchido")
	private String phone_type;
	
	@NotNull(message = "O campo número de telefone deve ser preenchido")
	private String number;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "user_phone")
	private UserModel user;

	public Long getPhone_id() {
		return phone_id;
	}

	public void setPhone_id(Long phone_id) {
		this.phone_id = phone_id;
	}

	public String getPhone_type() {
		return phone_type;
	}

	public void setPhone_type(String phone_type) {
		this.phone_type = phone_type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phone_id == null) ? 0 : phone_id.hashCode());
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
		Phone other = (Phone) obj;
		if (phone_id == null) {
			if (other.phone_id != null)
				return false;
		} else if (!phone_id.equals(other.phone_id))
			return false;
		return true;
	}
	
	

}
