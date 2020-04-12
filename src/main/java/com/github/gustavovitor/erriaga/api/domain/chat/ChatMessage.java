package com.github.gustavovitor.erriaga.api.domain.chat;

import com.github.gustavovitor.erriaga.api.domain.user.AppUser;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A mensagem não pode ser vazia.")
    @NotEmpty(message = "A mensagem não pode ser vazia.")
    @Size(max = 255, message = "A mensagem não pode ser maior que 255 caracteres.")
    private String message;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "appUserId", updatable = false)
    private AppUser owner;

    @Column(updatable = false)
    private LocalDateTime messageDatetime;

}
