package com.tdav.services.sandbox.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tdav.services.sandbox.entities.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, UUID>, JpaSpecificationExecutor<BlogPost> {
  Optional<BlogPost> findOneBySlug(String slug);
}
