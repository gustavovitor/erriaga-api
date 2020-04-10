package com.github.gustavovitor.erriaga.api.repository.user;

import com.github.gustavovitor.erriaga.api.domain.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
