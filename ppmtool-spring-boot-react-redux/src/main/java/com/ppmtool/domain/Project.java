package com.ppmtool.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotBlank(message="Project is required")
	private String projectName;
	@NotBlank(message="Project Identifier is required")
	@Size(min=4, max=5, message="Please use 4 to 5 characters")
	@Column(updatable=false, unique=true)
	private String projectIdentifier;
	@NotBlank(message="Project Description is required")
	private String description;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date start_date;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date last_date;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(updatable=false)
	private Date created_at;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date updated_at;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="project")
	@JsonIgnore
	private Backlog backlog;
	
	public Project() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getLast_date() {
		return last_date;
	}

	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	@PrePersist
	protected void onCreate() {
		this.created_at = new Date();
	}
	
	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	@PreUpdate
	protected void onupdate() {
		this.updated_at = new Date();
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + ", projectIdentifier=" + projectIdentifier
				+ ", description=" + description + ", start_date=" + start_date + ", last_date=" + last_date
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + ", backlog=" + backlog + "]";
	}
	
}
