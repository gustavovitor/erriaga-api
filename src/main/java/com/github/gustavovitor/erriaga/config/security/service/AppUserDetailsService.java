package com.github.gustavovitor.erriaga.config.security.service;

import com.github.gustavovitor.erriaga.api.service.user.AppUserService;
import com.github.gustavovitor.erriaga.config.security.domain.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new SystemUser(appUserService.findByEmail(email));
    }
}
