package com.tdav.services.sandbox.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "MESSAGES", schema = "SANDBOX")
public class Message {

  @Id
  @UuidGenerator(style = UuidGenerator.Style.RANDOM)
  private UUID id;

  @DateTimeFormat(pattern = "MM/dd/yyyy")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private LocalDate sentDate;

  private String email;

  private String message;

  private Boolean isArchived = false;

  private Boolean isFlagged = false;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LocalDate getSentDate() {
    return sentDate;
  }

  public void setSentDate(LocalDate sentDate) {
    this.sentDate = sentDate;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @JsonProperty("isArchived")
  public Boolean getArchived() {
    return isArchived;
  }

  @JsonProperty("isArchived")
  public void setArchived(Boolean isArchived) {
    this.isArchived = isArchived;
  }

  @JsonProperty("isFlagged")
  public Boolean getFlagged() {
    return isFlagged;
  }

  @JsonProperty("isFlagged")
  public void setFlagged(Boolean isFlagged) {
    this.isFlagged = isFlagged;
  }

  @PrePersist
  protected void onCreate() {
    sentDate = LocalDate.now();
  }

}
