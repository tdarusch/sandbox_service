package com.tdav.services.sandbox.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.dtos.ResumeDTO;
import com.tdav.services.sandbox.entities.Resume;
import com.tdav.services.sandbox.services.ResumeService;

@RestController
@RequestMapping("/resumes")
public class ResumeController {
  
  private final ResumeService resumeService;

  public ResumeController(ResumeService resumeService) {
    this.resumeService = resumeService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ResumeDTO> getAllResumes() {
    return resumeService.getAllResumes();
  }

  @GetMapping(value = "/primary", produces = MediaType.APPLICATION_JSON_VALUE)
  public Resume getPrimaryResume() {
    return resumeService.getPrimaryResume();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Resume getResumeById(@PathVariable UUID id) {
    return resumeService.getResumeById(id);
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Resume saveResume(@RequestBody Resume resume) {
    return resumeService.saveResume(resume);
  }

  @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Resume updateResume(@PathVariable UUID id, @RequestBody Resume resume) {
    return resumeService.updateResume(resume, id);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void deleteResume(@PathVariable UUID id, @RequestBody Resume resume) {
    resumeService.deleteResume(resume, id);
  }

}
