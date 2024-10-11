package com.tdav.services.sandbox.services;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.tdav.services.sandbox.entities.Contact;
import com.tdav.services.sandbox.handlers.InvalidDataException;
import com.tdav.services.sandbox.handlers.ResourceNotFoundException;
import com.tdav.services.sandbox.repositories.ContactRepository;

@Service
public class ContactService {

  private final ContactRepository contactRepo;

  public ContactService(ContactRepository contactRepo) {
    this.contactRepo = contactRepo;
  }

  public Contact getContact() {
    List<Contact> contactList = contactRepo.findAll();
    if (CollectionUtils.isEmpty(contactList)) {
      return new Contact();
    }
    return contactList.getFirst();
  }

  public Contact saveContact(Contact contact) {
    if (contact.getId() != null) {
      throw new InvalidDataException("Cannot create contact info with ID. Use the update method.");
    } else if (contactRepo.count() > 0) {
      throw new InvalidDataException("Contact info has already been created. Use the update method.");
    }
    return contactRepo.save(contact);
  }

  public Contact updateContact(Contact contact, UUID id) {
    if (!contactRepo.existsById(id)) {
      throw new ResourceNotFoundException("Contact info with ID: " + id.toString() + " not found.");
    } else if (!contact.getId().equals(id)) {
      throw new InvalidDataException("ID in request and body do not match.");
    }
    return contactRepo.save(contact);
  }

  public void deleteContact(Contact contact, UUID id) {
    if (!contactRepo.existsById(id)) {
      throw new ResourceNotFoundException("Contact info with ID: " + id.toString() + " not found.");
    } else if (!contact.getId().equals(id)) {
      throw new InvalidDataException("ID in request and body do not match.");
    }
    contactRepo.delete(contact);
  }

}
