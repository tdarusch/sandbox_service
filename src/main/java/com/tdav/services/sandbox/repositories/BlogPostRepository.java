package com.tdav.services.sandbox.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdav.services.sandbox.entities.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, UUID> {

}
