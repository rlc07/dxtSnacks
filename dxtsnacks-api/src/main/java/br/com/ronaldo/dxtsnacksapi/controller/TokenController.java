/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Ronaldo L. Vieira
 *
 * 12 de dez de 2017
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(false); // TODO: Em producao sera true
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);
		
		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}
	
	@GetMapping("/validToken")
	public boolean validToken(HttpServletRequest req, HttpServletResponse resp) {
		return true;
	}
}
