package com.sda.spring.chatservicespring.services;

import com.google.common.collect.Lists;
import com.sda.spring.chatservicespring.dao.AuthorRepository;
import com.sda.spring.chatservicespring.dao.MessageRepository;
import com.sda.spring.chatservicespring.dto.MessageForm;
import com.sda.spring.chatservicespring.model.Author;
import com.sda.spring.chatservicespring.model.Message;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AuthorRepository authorRepository;

    public MessageService(MessageRepository messageRepository, AuthorRepository authorRepository) {
        this.messageRepository = messageRepository;
        this.authorRepository = authorRepository;
    }

    public Message addNewMessage(MessageForm messageForm) {
        return messageRepository.save(createNewMessage(messageForm));
    }

    public List<Message> findMessageByAuthor(String author) {
        return messageRepository.findByAuthor(author);
    }

    public List<Message> getAllMessages() {
        return Lists.newArrayList(messageRepository.findAll());
    }

    public Message getOneMessageById(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Message.class.getName()));
    }

    public Message updateMessage(Long id, MessageForm form) {
        Message foundMessage = messageRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Message.class.getName()));

        foundMessage.setText(form.getText());
        foundMessage.setThread(form.getThread());

        return messageRepository.save(foundMessage);
    }

    public void deleteMessage(Long id) {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id, Message.class.getName());
        }
    }

    private Message createNewMessage(MessageForm form) {
        Author author = authorRepository.findByName(form.getAuthor())
                .orElseThrow(() -> new ObjectNotFoundException(form.getAuthor(), Author.class.getName()));

        Message result = new Message();
        result.setAuthor(author);
        result.setText(form.getText());
        result.setThread(form.getThread());

        return result;
    }
}
