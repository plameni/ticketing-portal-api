package com.ticketing.portal.services;

import com.ticketing.portal.models.Project;
import com.ticketing.portal.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository _projectRepository) {
        this.projectRepository = _projectRepository;
    }

    public List<Project> getProjects() {
        return this.projectRepository.getProjects();
    }

}
