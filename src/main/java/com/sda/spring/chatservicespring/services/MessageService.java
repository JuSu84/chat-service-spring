package com.sda.spring.chatservicespring.services;

import com.sda.spring.chatservicespring.dao.MessageRepository;
import com.sda.spring.chatservicespring.model.Message;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message addNewMessage(Message message) {
        message.setDate(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> findMessageByAuthor(String author) {
        return messageRepository.findByAuthor(author);
    }

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(m -> messages.add(m));
        return messages;
    }

    public Message getOneMessageById(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Message.class.getName()));
    }
}
