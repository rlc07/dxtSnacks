/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author Ronaldo L. Vieira
 *
 * 6 de dez de 2017
 */

@Entity
@Table(name = "user")
@SQLDelete(sql="UPDATE user SET delete_date = CURRENT_DATE() WHERE user_id=?")
@Where(clause ="delete_date IS NULL")
public class UserModel extends ApplicationModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	
	@NotNull(message = "O campo nome deve ser preenchido")
	@Size(max = 60, min = 3)
	private String name;
	
	@NotNull(message = "O campo senha deve ser preenchido")
	private String password;
	
	@NotNull(message = "O campo email deve ser preenchido")
	@Column(unique = true)
	private String email;
	
	@NotNull(message = "O campo endereço deve ser preenchido")
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "user_address")
	private Address address;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	@JsonManagedReference(value = "user_phone")
	private List<Phone> phone;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_permission", joinColumns = @JoinColumn(name = "user_id")
	   ,inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private List<Permission> permission;
	
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public List<Permission> getPermission() {
		return permission;
	}
	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	public List<Phone> getPhone() {
		return phone;
	}
	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
		UserModel other = (UserModel) obj;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	
	

}
