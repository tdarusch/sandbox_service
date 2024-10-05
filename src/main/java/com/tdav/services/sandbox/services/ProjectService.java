package com.tdav.services.sandbox.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tdav.services.sandbox.entities.Project;
import com.tdav.services.sandbox.handlers.InvalidDataException;
import com.tdav.services.sandbox.handlers.ResourceNotFoundException;
import com.tdav.services.sandbox.repositories.ProjectRepository;

@Service
public class ProjectService {
  
  private ProjectRepository projectRepo;

  public ProjectService(ProjectRepository projectRepo) {
    this.projectRepo = projectRepo;
  }

  public List<Project> getAllProjects() {
    return projectRepo.findAll();
  }

  public Project getProjectById(UUID id) {
    return projectRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project with ID: " + id.toString() + " not found."));
  }

  public Project saveProject(Project project) {
    if (project.getId() != null) {
      throw new InvalidDataException("Cannot persist a new project with an existing ID. Use the update method instead.");
    }
    return projectRepo.save(project);
  }

  public Project updateProject(Project project, UUID id) {
    if (!projectRepo.existsById(id)) {
      throw new ResourceNotFoundException("Project with ID: " + id.toString() + " not found.");
    } else if (!project.getId().equals(id)) {
      throw new InvalidDataException("Project ID in request and body do not match.");
    }
    return projectRepo.save(project);
  }

  public void deleteProject(Project project, UUID id) {
    if (!projectRepo.existsById(id)) {
      throw new ResourceNotFoundException("Project with ID: " + id.toString() + " not found.");
    } else if (!project.getId().equals(id)) {
      throw new InvalidDataException("Project ID in request and body do not match.");
    }
    projectRepo.deleteById(id);
  }

}
