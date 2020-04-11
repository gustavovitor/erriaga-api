package com.github.gustavovitor.erriaga.config.locale;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
public class LocaleConfig {

    @PostConstruct
    public void setDefaultLocale(){
        Locale.setDefault(new Locale("pt", "BR"));
    }

}
