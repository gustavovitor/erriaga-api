package com.github.gustavovitor.erriaga.api.domain.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "persons")
@ApiModel("Pessoa")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O nome é obrigatório.")
    @Size(max = 255, message = "O nome não pode ter mais de 255 caracteres.")
    @ApiModelProperty("Nome")
    private String name;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty("Gênero")
    private Gender gender;

    @Email(message = "Email inválido.")
    @ApiModelProperty("Email Válido")
    private String email;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @ApiModelProperty("Data de Nascimento")
    private LocalDate birthDate;

    @Size(max = 255, message = "A naturalidade não pode ter mais de 255 caracteres.")
    @ApiModelProperty("Naturalidade")
    private String birthPlace;

    @Size(max = 255, message = "A nacionalidade não pode ter mais de 255 caracteres.")
    @ApiModelProperty("Nacionalidade")
    private String nationality;

    @CPF(message = "CPF inválido.")
    @NotNull(message = "O CPF é obrigatório.")
    @Column(unique = true)
    @ApiModelProperty("CPF Válido")
    private String cpf;
}
