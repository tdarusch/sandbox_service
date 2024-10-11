package com.tdav.services.sandbox.entities;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "BLOG_POSTS", schema = "SANDBOX")
public class BlogPost {
  
  @Id
  @UuidGenerator(style = UuidGenerator.Style.RANDOM)
  private UUID id;

  @DateTimeFormat(pattern = "MM/dd/yyyy")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private LocalDate createdDate;

  @DateTimeFormat(pattern = "MM/dd/yyyy")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private LocalDate revisedDate;

  @Column(length = 255)
  private String category;

  private String title;

  private String description;
  
  @Column(length = 255)
  private String author;
  
  private boolean archived;

  private boolean featured;

  private long viewCount;

  private String slug;

  @Enumerated(EnumType.STRING)
  private BlogStatusEnum status;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "blog_section_id")
  private List<BlogSection> sections;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDate getRevisedDate() {
    return revisedDate;
  }

  public void setRevisedDate(LocalDate revisedDate) {
    this.revisedDate = revisedDate;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
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

  public boolean isArchived() {
    return archived;
  }

  public void setArchived(boolean archived) {
    this.archived = archived;
  }

  public boolean isFeatured() {
    return featured;
  }

  public void setFeatured(boolean featured) {
    this.featured = featured;
  }

  public long getViewCount() {
    return viewCount;
  }

  public void setViewCount(long viewCount) {
    this.viewCount = viewCount;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public BlogStatusEnum getStatus() {
    return status;
  }

  public void setStatus(BlogStatusEnum status) {
    this.status = status;
  }

  public List<BlogSection> getSections() {
    return sections;
  }

  public void setSections(List<BlogSection> sections) {
    this.sections = sections;
  }

  @PrePersist
  protected void onCreate() {
    createdDate = LocalDate.now();
    slug = getSlug(title);
  }

  @PreUpdate
  protected void onUpdate() {
    revisedDate = LocalDate.now();
    slug = getSlug(title);
  }

  private String getSlug(String title) {
    String titleSlug = title;
    if (titleSlug != null) {
      titleSlug = titleSlug.toLowerCase();
      titleSlug = Normalizer.normalize(titleSlug, Normalizer.Form.NFD);
      titleSlug = titleSlug.replaceAll("[^a-z0-9\\s]", "").replaceAll("\\s+", "-");
      titleSlug = titleSlug.replaceAll("^-|-$", "");
    }
    return titleSlug;
  }

}
