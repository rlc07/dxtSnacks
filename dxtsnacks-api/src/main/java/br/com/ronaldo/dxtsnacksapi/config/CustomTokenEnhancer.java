/**
 * 
 */
package br.com.ronaldo.dxtsnacksapi.config;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import br.com.ronaldo.dxtsnacksapi.security.UserSystem;

/**
 * @author Ronaldo L. Vieira
 *
 * 11 de dez de 2017
 */
public class CustomTokenEnhancer implements TokenEnhancer{

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

         UserSystem userSystem = (UserSystem) authentication.getPrincipal();
         
         
         Map<String, Object> addInfo = new HashMap<>();
         addInfo.put("name", userSystem.getUser().getName());
         addInfo.put("user_id", userSystem.getUser().getUser_id());
         addInfo.put("email", userSystem.getUser().getEmail());


         ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
         return accessToken;
	}
}
	