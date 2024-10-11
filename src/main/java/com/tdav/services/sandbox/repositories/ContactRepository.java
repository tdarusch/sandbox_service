package com.tdav.services.sandbox.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdav.services.sandbox.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
  
}
