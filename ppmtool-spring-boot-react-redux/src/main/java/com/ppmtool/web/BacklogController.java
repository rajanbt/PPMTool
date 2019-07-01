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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ppmtool.domain.ProjectTask;
import com.ppmtool.services.MapValidationErrorService;
import com.ppmtool.services.ProjectTaskService;

@CrossOrigin
@RestController
@RequestMapping("/api/backlogs")
public class BacklogController {

	@Autowired
	private ProjectTaskService projectTaskService;
//	@Autowired
//	private BacklogService backlogService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("/{backlog_id}")
	public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
			@PathVariable("backlog_id") String backlogId) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
		if (errorMap != null) {
			return errorMap;
		}
		ProjectTask projectTask1 = projectTaskService.addProjectTask(backlogId, projectTask);
		return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
	}

	@GetMapping("/{backlog_id}")
	public ResponseEntity<?> getProjectBacklog(@PathVariable("backlog_id") String backlogId) {
		return new ResponseEntity<List<ProjectTask>>(projectTaskService.findBacklogById(backlogId), HttpStatus.OK);
	}

	@GetMapping("/{backlog_id}/{project_sequence_id}")
	public ResponseEntity<?> getProjectTask(@PathVariable("backlog_id") String backlogId,
			@PathVariable("project_sequence_id") String projectSequenceId) {
		ProjectTask projectTask = projectTaskService.findProjectTaskBySequence(backlogId, projectSequenceId);
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}

	@PatchMapping("/{backlog_id}/{project_sequence_id}")
	public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
			@PathVariable("backlog_id") String backlogId, @PathVariable("project_sequence_id") String projectSequenceId) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
		if (errorMap != null) {
			return errorMap;
		}
		ProjectTask updatedProjectTask = projectTaskService.updateProjectTaskBySequence(projectTask, backlogId, projectSequenceId);
		return new ResponseEntity<ProjectTask>(updatedProjectTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{backlog_id}/{project_sequence_id}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable("backlog_id") String backlogId,
			@PathVariable("project_sequence_id") String projectSequenceId) {
		projectTaskService.deleteProjectTaskBySequence(backlogId, projectSequenceId);
		return new ResponseEntity<String>("Project Task with '"+projectSequenceId+"' deleted successfully", HttpStatus.OK);
	}
}
