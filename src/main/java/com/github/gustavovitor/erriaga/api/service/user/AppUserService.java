package com.github.gustavovitor.erriaga.api.service.user;

import com.github.gustavovitor.erriaga.api.domain.user.AppUser;
import com.github.gustavovitor.erriaga.api.repository.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUser findByEmail(String email) {
        return appUserRepository.findByEmail(email).orElse(null);
    }

    public AppUser register(AppUser appUserToRegistry) {
        appUserToRegistry.setPassword(bCryptPasswordEncoder.encode(appUserToRegistry.getPassword()));
        return appUserRepository.save(appUserToRegistry);
    }

}
