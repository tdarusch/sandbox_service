package com.tdav.services.sandbox.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.entities.Message;
import com.tdav.services.sandbox.services.MessageService;



@RestController
@RequestMapping("/messages")
public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Message saveMessage(@RequestBody Message message) {
    return messageService.saveMessage(message);
  }

}
