package com.tdav.services.sandbox.controllers;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.entities.Contact;
import com.tdav.services.sandbox.services.ContactService;

@RestController
@RequestMapping("/admin/contact")
public class ContactAdminController {

  private final ContactService contactService;

  public ContactAdminController(ContactService contactService) {
    this.contactService = contactService;
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Contact saveContact(@RequestBody Contact contact) {
    return contactService.saveContact(contact);
  }

  @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Contact updateContact(@PathVariable UUID id, @RequestBody Contact contact) {
    return contactService.updateContact(contact, id);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void deleteContact(@PathVariable UUID id, @RequestBody Contact contact) {
    contactService.deleteContact(contact, id);
  }

}
