package com.tdav.services.sandbox.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tdav.services.sandbox.entities.BlogPost;

public class BlogPostDTO {

  private UUID id;
  private String title;
  private String description;
  private String author;
  private String slug;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private LocalDate createdDate;

  public BlogPostDTO(BlogPost blogPost) {
    this.id = blogPost.getId();
    this.title = blogPost.getTitle();
    this.description = blogPost.getDescription();
    this.author = blogPost.getAuthor();
    this.createdDate = blogPost.getCreatedDate();
    this.slug = blogPost.getSlug();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

}
