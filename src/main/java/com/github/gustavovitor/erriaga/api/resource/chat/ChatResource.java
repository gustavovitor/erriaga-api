package com.github.gustavovitor.erriaga.api.resource.chat;

import com.github.gustavovitor.erriaga.api.domain.chat.ChatMessage;
import com.github.gustavovitor.erriaga.api.service.chat.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/chat")
public class ChatResource {

    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping
    public ResponseEntity<Page<ChatMessage>> findAllMessages(Pageable pageable) {
        return ResponseEntity.ok(chatMessageService.findAllOrdered(pageable));
    }

    @PostMapping
    public ResponseEntity<ChatMessage> insert(@RequestBody @Valid ChatMessage chatMessage) {
        return ResponseEntity.ok(chatMessageService.insert(chatMessage));
    }

}
