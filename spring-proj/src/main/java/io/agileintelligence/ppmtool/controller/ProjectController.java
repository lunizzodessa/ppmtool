package io.agileintelligence.ppmtool.controller;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.service.MapValidationErrorService;
import io.agileintelligence.ppmtool.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.validateErrors(result);
        if (errorMap!= null) {
            return errorMap;
        }

        Project projectAdded = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(projectAdded, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findByProjectIdentifier(projectId);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllprojects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<String> ("Project '" + projectId + "' was deleted", HttpStatus.OK);

    }

}
