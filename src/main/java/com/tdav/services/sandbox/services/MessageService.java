package com.tdav.services.sandbox.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tdav.services.sandbox.entities.Message;
import com.tdav.services.sandbox.handlers.InvalidDataException;
import com.tdav.services.sandbox.handlers.ResourceNotFoundException;
import com.tdav.services.sandbox.repositories.MessageRepository;

@Service
public class MessageService {

  private final MessageRepository messageRepo;

  public MessageService(MessageRepository messageRepo) {
    this.messageRepo = messageRepo;
  }

  public Message getMessage(UUID id) {
    return messageRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Message with ID: " + id + " not found."));
  }

  public List<Message> getAllMessages(Boolean archived) {
    return messageRepo.findByIsArchivedOrderByIsFlaggedDesc(archived);
  }

  public Message saveMessage(Message message) {
    if (message.getId() != null) {
      throw new InvalidDataException("Cannot create message with ID. Use the update method.");
    }
    return messageRepo.save(message);
  }

  public Message updateMessage(Message message, UUID id) {
    if (message.getId() == null) {
      throw new InvalidDataException("Cannot update message without an ID. Use the save method.");
    } else if (!messageRepo.existsById(id)) {
      throw new ResourceNotFoundException("Message with ID: " + id + " not found.");
    } else if (!message.getId().equals(id)) {
      throw new InvalidDataException("ID in request and body do not match.");
    }
    return messageRepo.save(message);
  }

  public void deleteMessage(Message message, UUID id) {
    if (!messageRepo.existsById(id)) {
      throw new ResourceNotFoundException("Message with ID: " + id + " not found.");
    } else if (!message.getId().equals(id)) {
      throw new InvalidDataException("ID in request and body do not match.");
    }
    messageRepo.delete(message);
  }

}
