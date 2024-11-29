package com.tdav.services.sandbox.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.entities.Contact;
import com.tdav.services.sandbox.services.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {

  private final ContactService contactService;

  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Contact getContact() {
    return contactService.getContact();
  }

}
