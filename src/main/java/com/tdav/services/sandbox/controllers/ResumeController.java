package com.tdav.services.sandbox.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.entities.Resume;
import com.tdav.services.sandbox.services.ResumeService;

@RestController
@RequestMapping("/resumes")
public class ResumeController {

  private final ResumeService resumeService;

  public ResumeController(ResumeService resumeService) {
    this.resumeService = resumeService;
  }

  @GetMapping(value = "/primary", produces = MediaType.APPLICATION_JSON_VALUE)
  public Resume getPrimaryResume() {
    return resumeService.getPrimaryResume();
  }

}
