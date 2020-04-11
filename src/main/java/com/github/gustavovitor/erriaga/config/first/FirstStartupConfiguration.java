package com.github.gustavovitor.erriaga.config.first;

import com.github.gustavovitor.erriaga.api.domain.user.AppUser;
import com.github.gustavovitor.erriaga.api.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
public class FirstStartupConfiguration {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    private void firstStartup() {
        if (appUserService.findByEmail("admin@admin.com") == null) {
            AppUser defaultUser = new AppUser();
            defaultUser.setPassword("1");
            defaultUser.setEmail("admin@admin.com");
            defaultUser.setName("Administrador");
            appUserService.register(defaultUser);
        }
    }
}
