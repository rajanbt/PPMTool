package com.ppmtool.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ppmtool.domain.Project;
import com.ppmtool.services.MapValidationErrorService;
import com.ppmtool.services.ProjectService;

@CrossOrigin
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
		if(errorMap != null) {
			return errorMap;
		}
		Project project1 = projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable("projectId") String projectId) {
		Project project1 = projectService.findProjectById(projectId);
		return new ResponseEntity<Project>(project1, HttpStatus.OK);
	}
	
//	@GetMapping("")
//	public List<Project> getAllProjects() {
//		List<Project> projects = projectService.findAllProjects();
//		return projects;
//	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllProjects() {
		List<Project> projects = projectService.findAllProjects();
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProjectById(@PathVariable("projectId") String projectId) {
		projectService.deleteProjectById(projectId);
		return new ResponseEntity<String>("Project with ID '"+projectId+"' was deleted", HttpStatus.OK);
	}
}
