package com.ticketing.portal.services;

import com.ticketing.portal.models.Project;
import com.ticketing.portal.repositories.ProjectRepository;
import com.ticketing.portal.response.DBOperationResponse;
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

    public Project getProjectById(int projectId) { return this.projectRepository.getProjectById(projectId); }

    public DBOperationResponse addProject(Project p) { return this.projectRepository.addProject(p); }

    public DBOperationResponse editProject(int projectId, Project p) { return this.projectRepository.editProject(projectId, p); }

    public DBOperationResponse deleteProject(int projectId) { return  this.projectRepository.deleteProject(projectId); }
}
