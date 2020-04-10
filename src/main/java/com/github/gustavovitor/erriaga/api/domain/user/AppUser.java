package com.github.gustavovitor.erriaga.api.domain.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Isso não parece um email válido.")
    @NotNull(message = "O email não pode ser vazia.")
    @NotEmpty(message = "O email não pode ser vazio.")
    @Size(max = 255, message = "O email não pode ser maior que 255 caracteres.")
    private String email;

    @NotNull(message = "A senha não pode ser vazia.")
    @NotEmpty(message = "A senha não pode ser vazia.")
    private String password;

    @NotNull(message = "O nome não pode ser vazio.")
    @NotEmpty(message = "O nome não pode ser vazio.")
    @Size(max = 64, message = "O nome não pode ser maior que 64 caracteres.")
    private String name;

}
