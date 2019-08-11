package com.sda.spring.chatservicespring.dao;

import com.sda.spring.chatservicespring.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByAuthor(String author);
}
