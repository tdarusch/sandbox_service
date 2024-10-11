package com.tdav.services.sandbox.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tdav.services.sandbox.entities.Project;

public class ProjectDTO {

  private UUID id;
  private String name;
  private String blurb;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private LocalDate startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private LocalDate endDate;

  public ProjectDTO(Project project) {
    this.id = project.getId();
    this.startDate = project.getStartDate();
    this.endDate = project.getEndDate();
    this.name = project.getName();
    this.blurb = project.getBlurb();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBlurb() {
    return blurb;
  }

  public void setBlurb(String blurb) {
    this.blurb = blurb;
  }

}
