package com.tdav.services.sandbox.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdav.services.sandbox.entities.Message;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByIsArchivedOrderByIsFlaggedDesc(Boolean isArchived);
}