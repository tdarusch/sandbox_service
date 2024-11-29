package com.tdav.services.sandbox.controllers;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.entities.BlogPost;
import com.tdav.services.sandbox.services.BlogPostService;

@RestController
@RequestMapping("/admin/blog-posts")
public class BlogPostAdminController {

  private final BlogPostService blogPostService;

  public BlogPostAdminController(BlogPostService blogPostService) {
    this.blogPostService = blogPostService;
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public BlogPost saveBlogPost(@RequestBody BlogPost blogPost) {
    return blogPostService.saveBlogPost(blogPost);
  }

  @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public BlogPost updateBlogPost(@PathVariable UUID id, @RequestBody BlogPost blogPost) {
    return blogPostService.updateBlogPost(blogPost, id);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void deleteBlogPost(@PathVariable UUID id, @RequestBody BlogPost blogPost) {
    blogPostService.deleteBlogPost(blogPost, id);
  }
  
}
