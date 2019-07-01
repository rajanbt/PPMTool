package com.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppmtool.domain.Backlog;
import com.ppmtool.domain.Project;
import com.ppmtool.exceptions.ProjectIdException;
import com.ppmtool.repositories.BacklogRepository;
import com.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private BacklogRepository backlogRepository;
	
	public Project saveOrUpdate(Project project) {
		String projectIdentifier = project.getProjectIdentifier().toUpperCase();
		try{
            project.setProjectIdentifier(projectIdentifier);
            if(project.getId() == null) {
            	Backlog backlog = new Backlog();
            	backlog.setProjectIdentifier(projectIdentifier);
            	project.setBacklog(backlog);
            	backlog.setProject(project);
            } else {
            	project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
            }
//            System.out.println("=============================================================");
//            System.out.println(project);
//            System.out.println("=============================================================");
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project Identifier '"+projectIdentifier+"' already exists");
        }
	}
	
	public Project findProjectById(String projectIdentifier) {
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Project does not exist with this Identifier '"+projectIdentifier+"'");
		}
		return project;
	}
	
	public List<Project> findAllProjects() {
		List<Project> projects = (List<Project>) projectRepository.findAll();
		return projects;
	}

	public void deleteProjectById(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Project ID '"+projectId+"' does not exist");
		}
		projectRepository.delete(project);
	}
}
