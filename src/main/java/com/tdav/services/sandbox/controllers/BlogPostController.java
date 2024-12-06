package com.tdav.services.sandbox.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.dtos.BlogPostDTO;
import com.tdav.services.sandbox.entities.BlogPost;
import com.tdav.services.sandbox.services.BlogPostService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/blog-posts")
public class BlogPostController {

  private final BlogPostService blogPostService;

  public BlogPostController(BlogPostService blogPostService) {
    this.blogPostService = blogPostService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<BlogPostDTO> getBlogPosts(
    @ModelAttribute BlogPost model,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "createdDate") String sortBy,
    @RequestParam(defaultValue = "ASC") String sortDir) {
      return blogPostService.getBlogPostPage(model, page, size, sortBy, sortDir);
  }
  

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public BlogPost getBlogPost(@PathVariable UUID id) {
    return blogPostService.getBlogPostById(id);
  }

  @GetMapping(value = "/slug/{slug}", produces = MediaType.APPLICATION_JSON_VALUE)
  public BlogPost getBlogPostBySlug(@PathVariable String slug) {
      return blogPostService.getBlogPostBySlug(slug);
  }

  @PostMapping(value = "/slug/{slug}/view")
  public Long incrementViewCountBySlug(@PathVariable String slug) {
    return blogPostService.incrementViewCountBySlug(slug);
  }

  @PostMapping(value = "/{id}/view")
  public Long incrementViewCountBySlug(@PathVariable UUID id) {
    return blogPostService.incrementViewCountById(id);
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
