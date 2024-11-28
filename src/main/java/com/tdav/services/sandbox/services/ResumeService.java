package com.tdav.services.sandbox.services;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tdav.services.sandbox.dtos.ResumeDTO;
import com.tdav.services.sandbox.entities.Resume;
import com.tdav.services.sandbox.entities.ResumeEducation;
import com.tdav.services.sandbox.entities.ResumeJob;
import com.tdav.services.sandbox.entities.ResumeProject;
import com.tdav.services.sandbox.handlers.InvalidDataException;
import com.tdav.services.sandbox.handlers.ResourceNotFoundException;
import com.tdav.services.sandbox.repositories.ResumeRepository;

@Service
public class ResumeService {

  private final ResumeRepository resumeRepo;

  public ResumeService(ResumeRepository resumeRepo) {
    this.resumeRepo = resumeRepo;
  }

  public List<ResumeDTO> getAllResumes() {
    return resumeRepo.findAll().stream().map(ResumeDTO::new).collect(Collectors.toList());
  }

  public Resume getResumeById(UUID id) {
    Resume resume = resumeRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Resume with ID: " + id.toString() + " not found."));
    return sortChildEntities(resume);
  }

  public Resume getPrimaryResume() {
    Resume primaryResume = resumeRepo.findOneByIsPrimaryTrue()
        .orElseThrow(() -> new ResourceNotFoundException("Could not find primary resume."));
    return sortChildEntities(primaryResume);
  }

  public Resume saveResume(Resume resume) {
    if (resume.getId() != null) {
      throw new InvalidDataException(
          "Cannot persist a new project with an existing ID. Use the update method instead.");
    }
    if (resume.isPrimary()) {
      clearPrimaryResumes();
    }
    return sortChildEntities(resumeRepo.save(resume));
  }

  public Resume updateResume(Resume resume, UUID id) {
    if (!resumeRepo.existsById(id)) {
      throw new ResourceNotFoundException("Resume with ID: " + id.toString() + " not found.");
    } else if (!resume.getId().equals(id)) {
      throw new InvalidDataException("Resume ID in request and body do not match.");
    }
    if (resume.isPrimary()) {
      clearPrimaryResumes();
    }
    return sortChildEntities(resumeRepo.save(resume));
  }

  public void deleteResume(Resume resume, UUID id) {
    if (!resumeRepo.existsById(id)) {
      throw new ResourceNotFoundException("Resume with ID: " + id.toString() + " not found.");
    } else if (!resume.getId().equals(id)) {
      throw new InvalidDataException("Resume ID in request and body do not match.");
    }
    resumeRepo.delete(resume);
  }

  private void clearPrimaryResumes() {
    resumeRepo.findAllByIsPrimaryTrue().ifPresent(primaryResumes -> {
      primaryResumes.stream().forEach(resume -> {
        resume.setPrimary(false);
        resumeRepo.save(resume);
      });
    });
  }

  private Resume sortChildEntities(Resume resume) {
    resume.setProjects(
        resume.getProjects().stream().sorted(Comparator.comparing(ResumeProject::getStartDate).reversed()).toList());
    resume.setEducation(
        resume.getEducation().stream().sorted(Comparator.comparing(ResumeEducation::getStartDate).reversed()).toList());
    resume.setJobs(
        resume.getJobs().stream().sorted(Comparator.comparing(ResumeJob::getStartDate).reversed()).toList());
    return resume;
  }

}
