package com.github.gustavovitor.erriaga.api.service.user;

import com.github.gustavovitor.erriaga.api.domain.user.AppUser;
import com.github.gustavovitor.erriaga.api.repository.user.AppUserRepository;
import com.github.gustavovitor.erriaga.config.domain.APIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private APIConfig apiConfig;

    public AppUser findById(Long id) {
        return appUserRepository.findById(id).orElse(null);
    }

    public AppUser findByEmail(String email) {
        return appUserRepository.findByEmail(email).orElse(null);
    }

    public AppUser register(AppUser appUserToRegistry) {
        appUserToRegistry.setPassword(bCryptPasswordEncoder.encode(appUserToRegistry.getPassword()));
        return appUserRepository.save(appUserToRegistry);
    }

    public void revoke(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(apiConfig.getSecurity().isEnableHttps());
        cookie.setPath(request.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }

}
