package com.github.gustavovitor.erriaga.config.security.domain;

import com.github.gustavovitor.erriaga.api.domain.user.AppUser;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class SystemUser extends User {

    @Getter
    private AppUser appUser;

    public SystemUser(AppUser user) {
        super(user.getEmail(), user.getPassword(), Collections.emptyList());
        this.appUser = user;
    }

}
