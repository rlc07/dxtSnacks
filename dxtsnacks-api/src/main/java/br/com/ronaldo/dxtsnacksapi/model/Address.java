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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Ronaldo L. Vieira
 *
 * 6 de dez de 2017
 */
@Entity
@Table(name = "address")
@SQLDelete(sql="UPDATE address SET delete_date = CURRENT_DATE() WHERE address_id=?")
@Where(clause ="delete_date IS NULL")
public class Address extends ApplicationModel implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long address_id;
	
	@NotNull(message = "O campo rua deve ser preenchido")
	@NotEmpty(message = "O campo rua deve ser preenchido")
	private String street;
	
	private int number;
	
	@NotNull(message = "O campo bairro deve ser preenchido")
	@NotEmpty(message = "O campo bairro deve ser preenchido")
	private String district;
	
	@NotNull(message = "O campo cidade deve ser preenchido")
	@NotEmpty(message = "O campo cidade deve ser preenchido")
	private String city;
	
	@NotNull(message = "O campo estado deve ser preenchido")
	@NotEmpty(message = "O campo estado deve ser preenchido")
	private String state;
	
	private String complement;

	@NotNull(message = "O campo cep deve ser preenchido")
	@NotEmpty(message = "O campo cep deve ser preenchido")
	private String zipcode;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "user_address")
	private UserModel user;	

	public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address_id == null) ? 0 : address_id.hashCode());
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
		Address other = (Address) obj;
		if (address_id == null) {
			if (other.address_id != null)
				return false;
		} else if (!address_id.equals(other.address_id))
			return false;
		return true;
	}
	
	
	
	
	
	

}
