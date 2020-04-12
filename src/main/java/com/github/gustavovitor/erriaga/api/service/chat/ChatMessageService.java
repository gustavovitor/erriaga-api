package com.github.gustavovitor.erriaga.api.service.chat;

import com.github.gustavovitor.erriaga.api.domain.chat.ChatMessage;
import com.github.gustavovitor.erriaga.api.domain.user.AppUser;
import com.github.gustavovitor.erriaga.api.repository.chat.ChatMessageRepository;
import com.github.gustavovitor.erriaga.api.repository.chat.spec.ChatMessageSpecification;
import com.github.gustavovitor.erriaga.api.repository.chat.spec.filter.ChatMessageFilter;
import com.github.gustavovitor.erriaga.api.service.user.AppUserService;
import com.github.gustavovitor.erriaga.utils.TokenUtils;
import com.github.gustavovitor.maker.service.ServiceMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Service
public class ChatMessageService extends ServiceMaker<ChatMessageRepository, ChatMessage, Long, ChatMessageFilter, ChatMessageSpecification> {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AppUserService appUserService;

    public Page<ChatMessage> findAllOrdered(Pageable pageable) {
        return getRepository().findAllByOrderByMessageDatetimeDesc(pageable);
    }

    @Override
    public void beforeInsert(ChatMessage object) {
        AppUser appUser = appUserService.findById(Long.parseLong(tokenUtils.getAdditionalInfo().get("appUserId").toString()));
        if (isNull(appUser)) {
            throw new EntityNotFoundException();
        }
        object.setOwner(appUser);
        object.setMessageDatetime(LocalDateTime.now());
    }
}
