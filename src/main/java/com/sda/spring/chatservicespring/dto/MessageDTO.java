package com.sda.spring.chatservicespring.dto;

import com.sda.spring.chatservicespring.model.Message;

import java.time.format.DateTimeFormatter;

public class MessageDTO {

    private Long id;
    private String author;
    private String date;
    private String text;
    private String thread;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public static MessageDTO fromMessage(Message message) {
        MessageDTO result = new MessageDTO();
        result.id = message.getId();
        result.author = message.getAuthor().getName();
        result.date = message.getDate().format(DateTimeFormatter.ISO_TIME);
        return result;
    }
}
