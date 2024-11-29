package com.tdav.services.sandbox.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.dtos.BlogPostDTO;
import com.tdav.services.sandbox.entities.BlogPost;
import com.tdav.services.sandbox.services.BlogPostService;

@RestController
@RequestMapping("/blog-posts")
public class BlogPostController {

  private final BlogPostService blogPostService;

  public BlogPostController(BlogPostService blogPostService) {
    this.blogPostService = blogPostService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<BlogPostDTO> getAllBlogPosts() {
    return blogPostService.getAllBlogPosts();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public BlogPost getBlogPost(@PathVariable UUID id) {
    return blogPostService.getBlogPostById(id);
  }

}
