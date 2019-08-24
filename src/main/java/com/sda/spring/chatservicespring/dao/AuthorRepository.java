package com.sda.spring.chatservicespring.dao;

import com.sda.spring.chatservicespring.model.Author;
import com.sda.spring.chatservicespring.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
