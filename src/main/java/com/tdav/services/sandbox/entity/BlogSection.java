package com.tdav.services.sandbox.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BLOG_SECTIONS", schema = "SANDBOX")
public class BlogSection {

  @Id
  @UuidGenerator(style = UuidGenerator.Style.RANDOM)
  private UUID id;

  private int index;

  private boolean isImage;

  private boolean isCode;

  private String codeLanguage;

  private String imgUrl;

  private String content;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @JsonProperty("isImage")
  public boolean isImage() {
    return isImage;
  }

  @JsonProperty("isImage")
  public void setImage(boolean isImage) {
    this.isImage = isImage;
  }

  @JsonProperty("isCode")
  public boolean isCode() {
    return isCode;
  }

  @JsonProperty("isCode")
  public void setCode(boolean isCode) {
    this.isCode = isCode;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public String getCodeLanguage() {
    return codeLanguage;
  }

  public void setCodeLanguage(String codeLanguage) {
    this.codeLanguage = codeLanguage;
  }
  
}
