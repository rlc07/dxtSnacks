/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.model;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Ronaldo L. Vieira
 *
 * 6 de dez de 2017
 */
@MappedSuperclass
@Where(clause ="active = 1 AND delete_date IS NULL")
public class ApplicationModel {

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate create_date;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate edit_date;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonInclude(value=Include.NON_EMPTY)
	private LocalDate delete_date;
	
	public ApplicationModel() {
		create_date = LocalDate.now();
	}

	public LocalDate getCreate_date() {
		return create_date;
	}

	public void setCreate_date(LocalDate create_date) {
		this.create_date = create_date;
	}

	public LocalDate getEdit_date() {
		return edit_date;
	}

	public void setEdit_date(LocalDate edit_date) {
		this.edit_date = edit_date;
	}

	public LocalDate getDelete_date() {
		return delete_date;
	}

	public void setDelete_date(LocalDate delete_date) {
		this.delete_date = delete_date;
	}
	
	


}
