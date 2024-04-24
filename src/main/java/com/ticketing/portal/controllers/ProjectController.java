package com.ticketing.portal.controllers;

import com.ticketing.portal.DBUtil;
import com.ticketing.portal.models.Project;
import com.ticketing.portal.response.DBOperationResponse;
import com.ticketing.portal.services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ticketing-portal-api/project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService _projectService){
        this.projectService = _projectService;
    }

    // CRUD - 5 osnovnih operacija
    // GET - prikazi sve rekorde nekog entiteta
    // GET (id) - prikazi projekat specifiran sa ovim PKem
    // POST - unesi projekat
    // PUT {id} - update vec postojeceg projekta
    // DELETE {id} - brisanje vec postojeceg projekta

    @CrossOrigin
    @GetMapping
    public List<Project> getProjects() { return this.projectService.getProjects(); }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public Project getProjectById(@PathVariable("id") int projectId) { return this.projectService.getProjectById(projectId); }

    @CrossOrigin
    @PostMapping
    public DBOperationResponse addProject(@RequestBody Project p) { return this.projectService.addProject(p); }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public DBOperationResponse editProject(@PathVariable("id") int projectId, @RequestBody Project p) {
        return this.projectService.editProject(projectId, p);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public DBOperationResponse deleteProject(@PathVariable("id") int projectId){
        return this.projectService.deleteProject(projectId);
    }
}
