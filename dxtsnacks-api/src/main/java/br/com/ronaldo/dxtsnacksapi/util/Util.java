
package br.com.ronaldo.dxtsnacksapi.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @author Ronaldo L. Vieira
 *
 * 6 de dez de 2017
 */
public class Util {

	public static String removeSymbolsPhone(String phone) {
		return phone.replace("(", "").replace(")", "").replace("-","");
	}
	
	public static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}
