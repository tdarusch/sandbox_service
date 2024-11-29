package com.tdav.services.sandbox.controllers;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.entities.Project;
import com.tdav.services.sandbox.services.ProjectService;

@RestController
@RequestMapping("/admin/projects")
public class ProjectAdminController {

  private final ProjectService projectService;

  public ProjectAdminController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Project saveProject(@RequestBody Project project) {
    return projectService.saveProject(project);
  }

  @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Project updateProject(@PathVariable UUID id, @RequestBody Project project) {
    return projectService.updateProject(project, id);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void deleteProject(@PathVariable UUID id, @RequestBody Project project) {
    projectService.deleteProject(project, id);
  }
  
}
