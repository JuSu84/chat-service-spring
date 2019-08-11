package com.sda.spring.chatservicespring.api;

import com.sda.spring.chatservicespring.dto.MessageDTO;
import com.sda.spring.chatservicespring.model.Message;
import com.sda.spring.chatservicespring.services.MessageService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/posts")
    public List<Message> getAllPosts(@RequestParam(name = "author", required = false) String author) {
        if (!StringUtils.isEmpty(author)) {
            return messageService.findMessageByAuthor(author);
        }
        return messageService.getAllMessages();
    }

    @GetMapping("/posts/{id}")
    public Message getMessageById(@PathVariable("id") Long id) {
        return messageService.getOneMessageById(id);
    }

    @PostMapping("/posts")
    public Message addNewMessage(@RequestBody Message message) {

//        Message message = new Message();
//        message.setAuthor(messageDTO.getAuthor());
//        message.setDate(LocalDateTime.now());
//        message.setText(messageDTO.getText());

        Message savedMessage = messageService.addNewMessage(message);
        return savedMessage;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity handleObjectNotFoundException(ObjectNotFoundException onfe) {
        return ResponseEntity.notFound().build();
    }

}
