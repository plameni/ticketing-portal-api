package com.ticketing.portal.controllers;

import com.ticketing.portal.models.Project;
import com.ticketing.portal.services.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/ticketing-portal-api/project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService _projectService){
        this.projectService = _projectService;
    }

    @GetMapping
    public List<Project> getProjects() {
        return this.projectService.getProjects(); 
    }
}
