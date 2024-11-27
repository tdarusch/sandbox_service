package com.tdav.services.sandbox.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdav.services.sandbox.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByGitHubId(String gitHubId);
  Optional<User> findByEmail(String email);
}