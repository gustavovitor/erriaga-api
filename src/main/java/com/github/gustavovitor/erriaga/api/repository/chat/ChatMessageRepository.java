package com.github.gustavovitor.erriaga.api.repository.chat;

import com.github.gustavovitor.erriaga.api.domain.chat.ChatMessage;
import com.github.gustavovitor.maker.repository.RepositoryMaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatMessageRepository extends RepositoryMaker<ChatMessage, Long> {
    Page<ChatMessage> findAllByOrderByMessageDatetimeDesc(Pageable pageable);
}
