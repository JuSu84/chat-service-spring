package com.sda.spring.chatservicespring.api;

import com.sda.spring.chatservicespring.dto.MessageForm;
import com.sda.spring.chatservicespring.model.Message;
import com.sda.spring.chatservicespring.services.MessageService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getAllPosts(@RequestParam(name = "author", required = false) String author) {
        if (!StringUtils.isEmpty(author)) {
            return messageService.findMessageByAuthor(author);
        }
        return messageService.getAllMessages();
    }

    @GetMapping("{id}")
    public Message getMessageById(@PathVariable("id") Long id) {
        return messageService.getOneMessageById(id);
    }

    @PostMapping
    public Message addNewMessage(@RequestBody MessageForm message) {
        Message savedMessage = messageService.addNewMessage(message);
        return savedMessage;
    }

    @PutMapping("{id}")
    public Message updateMessage(@PathVariable("id") Long id, @RequestBody MessageForm messageForm) {
        return messageService.updateMessage(id, messageForm);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") Long id) {
        messageService.deleteMessage(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity handleObjectNotFoundException(ObjectNotFoundException onfe) {
        return ResponseEntity.notFound().build();
    }

}
