package com.github.gustavovitor.erriaga.api.resource.user;

import com.github.gustavovitor.erriaga.api.domain.user.AppUser;
import com.github.gustavovitor.erriaga.api.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/public/user")
public class AppUserResource {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody @Valid AppUser appUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserService.register(appUser));
    }

}
