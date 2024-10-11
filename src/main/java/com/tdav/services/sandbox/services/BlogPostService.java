package com.tdav.services.sandbox.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tdav.services.sandbox.dtos.BlogPostDTO;
import com.tdav.services.sandbox.entities.BlogPost;
import com.tdav.services.sandbox.handlers.InvalidDataException;
import com.tdav.services.sandbox.handlers.ResourceNotFoundException;
import com.tdav.services.sandbox.repositories.BlogPostRepository;

@Service
public class BlogPostService {

  private BlogPostRepository blogPostRepo;

  public BlogPostService(BlogPostRepository blogPostRepo) {
    this.blogPostRepo = blogPostRepo;
  }

  public List<BlogPostDTO> getAllBlogPosts() {
    return blogPostRepo.findAll()
        .stream()
        .map(BlogPostDTO::new)
        .collect(Collectors.toList());
  }

  public BlogPost getBlogPostById(UUID id) {
    return blogPostRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Blog post with ID: " + id.toString() + " not found."));
  }

  public BlogPost saveBlogPost(BlogPost blogPost) {
    if (blogPost.getId() != null) {
      throw new InvalidDataException(
          "Cannot persist a new blog post with an existing ID. Use the update method instead.");
    }

    return blogPostRepo.save(blogPost);
  }

  public BlogPost updateBlogPost(BlogPost blogPost, UUID id) {
    if (!blogPostRepo.existsById(id)) {
      throw new ResourceNotFoundException("Project with ID: " + id.toString() + " not found.");
    } else if (!blogPost.getId().equals(id)) {
      throw new InvalidDataException("Project ID in request and body do not match.");
    }

    return blogPostRepo.save(blogPost);
  }

  public void deleteBlogPost(BlogPost blogPost, UUID id) {
    if (!blogPostRepo.existsById(id)) {
      throw new ResourceNotFoundException("Project with ID: " + id.toString() + " not found.");
    } else if (!blogPost.getId().equals(id)) {
      throw new InvalidDataException("Project ID in request and body do not match.");
    }
    blogPostRepo.delete(blogPost);
  }

}
