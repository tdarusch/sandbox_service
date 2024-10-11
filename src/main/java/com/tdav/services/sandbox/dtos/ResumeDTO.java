package com.tdav.services.sandbox.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tdav.services.sandbox.entities.Resume;

public class ResumeDTO {

  private UUID id;
  private String nickname;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private LocalDate lastUpdatedDate;

  public ResumeDTO(Resume resume) {
    this.id = resume.getId();
    this.nickname = resume.getResumeNickname();
    this.lastUpdatedDate = resume.getLastUpdatedDate();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public LocalDate getLastUpdatedDate() {
    return lastUpdatedDate;
  }

  public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
    this.lastUpdatedDate = lastUpdatedDate;
  }

}
