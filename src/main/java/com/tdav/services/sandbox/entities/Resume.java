package com.tdav.services.sandbox.entities;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "RESUMES", schema = "SANDBOX")
public class Resume {

  @Id
  @UuidGenerator(style = UuidGenerator.Style.RANDOM)
  private UUID id;

  @Column(length = 255)
  private String name;

  private String title;

  private String blurb;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "resume_job_id")
  private List<ResumeJob> jobs;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "resume_education_id")
  private List<ResumeEducation> education;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "resume_skill_id")
  private List<ResumeSkill> skills;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "resume_project_id")
  private List<ResumeProject> projects;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBlurb() {
    return blurb;
  }

  public void setBlurb(String blurb) {
    this.blurb = blurb;
  }

  public List<ResumeJob> getJobs() {
    return jobs;
  }

  public void setJobs(List<ResumeJob> jobs) {
    this.jobs = jobs;
  }

  public List<ResumeEducation> getEducation() {
    return education;
  }

  public void setEducation(List<ResumeEducation> education) {
    this.education = education;
  }

  public List<ResumeSkill> getSkills() {
    return skills;
  }

  public void setSkills(List<ResumeSkill> skills) {
    this.skills = skills;
  }

  public List<ResumeProject> getProjects() {
    return projects;
  }

  public void setProjects(List<ResumeProject> projects) {
    this.projects = projects;
  }
  
}
