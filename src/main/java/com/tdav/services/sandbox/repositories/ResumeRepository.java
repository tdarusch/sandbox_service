package com.tdav.services.sandbox.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdav.services.sandbox.entities.Resume;

public interface ResumeRepository extends JpaRepository<Resume, UUID> {
  Optional<Resume> findTopByOrderByLastUpdatedDateDesc();
  Optional<List<Resume>> findAllByIsPrimaryTrue();
  Optional<Resume> findOneByIsPrimaryTrue();
}
