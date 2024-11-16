package com.tdav.services.sandbox.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.entities.Message;
import com.tdav.services.sandbox.services.MessageService;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/messages")
public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Message> getMessages(@RequestParam(defaultValue = "false") Boolean archived) {
    return messageService.getAllMessages(archived);
  }
  
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Message getMessage(@PathVariable UUID id) {
    return messageService.getMessage(id);
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Message saveMessage(@RequestBody Message message) {
    return messageService.saveMessage(message);
  }

  @PutMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Message updateMessage(@RequestBody Message message, @PathVariable UUID id) {
    return messageService.updateMessage(message, id);
  }

  @DeleteMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public void deleteMessage(@RequestBody Message message, @PathVariable UUID id) {
    messageService.deleteMessage(message, id);
  }

}
