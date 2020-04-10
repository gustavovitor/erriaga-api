package com.github.gustavovitor.erriaga.config.security.token;

import com.github.gustavovitor.erriaga.config.security.domain.SystemUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/** {@link CustomTokenEnhancer} é responsável por customizar o token do JWT.  * */
public class CustomTokenEnhancer implements TokenEnhancer {

    /** enhance é responsável por adicionar um campo personalizado no JWT.
     * */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        SystemUser user = (SystemUser) oAuth2Authentication.getPrincipal();

        Map<String, Object> addInfo = new HashMap<>();
        addInfo.put("username", user.getUsername());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(addInfo);
        return oAuth2AccessToken;
    }
}
