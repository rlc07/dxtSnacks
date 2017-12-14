/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



/**
 * @author Ronaldo L. Vieira
 *
 * 7 de dez de 2017
 */
@ControllerAdvice
public class DxtSnacksExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){		
		List<Error> errors = createListErrors(ex.getBindingResult());		
		return  handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleMySQLIntegrityConstraintViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String message = messageSource.getMessage("resource.duplicate", null, LocaleContextHolder.getLocale());
		List<Error> erros = Arrays.asList(new Error(message));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	/*TRATA EX - DADOS NAO ENCONTRADOS*/
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String message = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		List<Error> erros = Arrays.asList(new Error(message));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	private List<Error> createListErrors(BindingResult bindingResult) {
        List<Error> errors = new ArrayList<>();
        
        for(FieldError fieldError : bindingResult.getFieldErrors()) {
        	String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
        	errors.add(new Error(message));
        }
		return errors;
	}
	
	public static class Error {

		private String message;

		public Error(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

	}

}
