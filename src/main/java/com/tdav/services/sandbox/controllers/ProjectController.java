package com.tdav.services.sandbox.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.dtos.ProjectDTO;
import com.tdav.services.sandbox.entities.Project;
import com.tdav.services.sandbox.services.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

  private final ProjectService projectService;

  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ProjectDTO> getAllProjects() {
    return projectService.getAllProjects();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Project getProject(@PathVariable UUID id) {
    return projectService.getProjectById(id);
  }

}
