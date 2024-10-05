package com.tdav.services.sandbox.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.entities.Project;
import com.tdav.services.sandbox.services.ProjectService;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/projects")
public class ProjectController {
  
  private ProjectService projectService;

  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Project> getAllProjects() {
      return projectService.getAllProjects();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Project getProject(@PathVariable UUID id) {
      return projectService.getProjectById(id);
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
