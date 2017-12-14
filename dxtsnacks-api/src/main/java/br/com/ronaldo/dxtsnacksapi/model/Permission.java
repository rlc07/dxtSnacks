/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ronaldo L. Vieira
 *
 * 11 de dez de 2017
 */
@Entity
@Table(name = "permission")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long permission_id;
	private String name;
	private String description;
	public Long getPermission_id() {
		return permission_id;
	}
	public void setPermission_id(Long permission_id) {
		this.permission_id = permission_id;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permission_id == null) ? 0 : permission_id.hashCode());
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
		Permission other = (Permission) obj;
		if (permission_id == null) {
			if (other.permission_id != null)
				return false;
		} else if (!permission_id.equals(other.permission_id))
			return false;
		return true;
	}
	
	

}
