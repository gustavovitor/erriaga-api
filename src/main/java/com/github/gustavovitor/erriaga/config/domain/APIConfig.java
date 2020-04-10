package com.github.gustavovitor.erriaga.config.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "apiconfig")
public class APIConfig {
    private final Security security = new Security();

    @Getter
    @Setter
    public static class Security {
        private boolean enableHttps;
        private String authServerClient;
        private String authServerSecret;

        private Integer tokenValiditySeconds;
        private Integer refreshTokenValiditySeconds;

        private String jwtAccessTokenSigningKey;
    }

}
