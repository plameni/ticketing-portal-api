package com.ticketing.portal.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ticketing-portal-api/project")
public class ProjectController {

    @GetMapping
    public int  getProjects() {
        return 1;
    }
}
