package com.sda.spring.chatservicespring.config;

import antlr.collections.impl.IntRange;
import com.sda.spring.chatservicespring.dao.AuthorRepository;
import com.sda.spring.chatservicespring.dao.MessageRepository;
import com.sda.spring.chatservicespring.model.Author;
import com.sda.spring.chatservicespring.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

import static java.lang.String.format;

@Component
public class DataSetup implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private MessageRepository messageRepository;

    @Autowired
    public DataSetup(AuthorRepository authorRepository, MessageRepository messageRepository) {
        this.authorRepository = authorRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        IntStream.range(0,4).forEach((i) -> {
            Author author = new Author();
            author.setName(format("author%d", i));
            authorRepository.save(author);
        });

        authorRepository.findAll().forEach(author -> {
            IntStream.range(0, 3).forEach(i -> {
                Message message = new Message();
                message.setAuthor(author);
                message.setText(format("message %d from %s", i, author.getName()));
                messageRepository.save(message);
            });
        });

    }
}
