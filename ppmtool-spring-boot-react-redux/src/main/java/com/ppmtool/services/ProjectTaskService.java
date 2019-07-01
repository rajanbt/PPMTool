package com.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppmtool.domain.Backlog;
import com.ppmtool.domain.Project;
import com.ppmtool.domain.ProjectTask;
import com.ppmtool.exceptions.ProjectNotFoundException;
import com.ppmtool.repositories.BacklogRepository;
import com.ppmtool.repositories.ProjectRepository;
import com.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private BacklogRepository backlogRepository;
	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

		try {
			// Exceptions: Project not found

			// PTs to be added to a specific project, project != null, BL exists
			Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

			// set the bl to pt
			projectTask.setBacklog(backlog);
			// we want our project sequence to be like this: IDPRO-1 IDPRO-2 ...100 101
			Integer backlogSequence = backlog.getProjectTaskSequence();
			// Update the BL SEQUENCE
			backlogSequence++;
			backlog.setProjectTaskSequence(backlogSequence);
			// Add Sequence to Project Task
			projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);
			// INITIAL priority when priority null
			if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
				projectTask.setPriority(3);
			}
			// INITIAL status when status is null
			if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
				projectTask.setStatus("TO_DO");
			}

			return projectTaskRepository.save(projectTask);
		} catch (Exception e) {
			throw new ProjectNotFoundException("Project with id '" + projectIdentifier + "' does not exist");
		}
	}

	public List<ProjectTask> findBacklogById(String backlogId) {
		Project project = projectRepository.findByProjectIdentifier(backlogId);
		if (project == null) {
			throw new ProjectNotFoundException("Project with id '" + backlogId + "' does not exist");
		}
		List<ProjectTask> projectTasks = projectTaskRepository.findByProjectIdentifierOrderByPriority(backlogId);
		return projectTasks;
	}

	public ProjectTask findProjectTaskBySequence(String backlogId, String projectSequenceId) {
		Backlog backlog = backlogRepository.findByProjectIdentifier(backlogId);
		if (backlog == null) {
			throw new ProjectNotFoundException("Project with id '" + backlogId + "' does not exist");
		}
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequenceId);
		if (projectTask == null) {
			throw new ProjectNotFoundException("Project task with id '" + projectSequenceId + "' does not exist");
		}
		if (!projectTask.getProjectIdentifier().equals(backlogId)) {
			throw new ProjectNotFoundException(
					"Project task with id '" + projectSequenceId + "' does not exist in project '" + backlogId + "'");
		}
		return projectTask;
	}

	public ProjectTask updateProjectTaskBySequence(ProjectTask updatedTask, String backlogId,
			String projectSequenceId) {
//		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequenceId);
		ProjectTask projectTask = findProjectTaskBySequence(backlogId, projectSequenceId);
		projectTask = updatedTask;
		return projectTaskRepository.save(projectTask);
	}
	
	public void deleteProjectTaskBySequence(String backlogId, String projectSequenceId) {
		ProjectTask projectTask = findProjectTaskBySequence(backlogId, projectSequenceId);
//		Backlog backlog = projectTask.getBacklog();
//		List<ProjectTask> pts = backlog.getProjectTasks();
//		pts.remove(projectTask);
//		backlog.setProjectTasks(pts);
//		backlogRepository.save(backlog);
		projectTaskRepository.delete(projectTask);
	}

}
