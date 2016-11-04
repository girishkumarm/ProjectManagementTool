package com.tool.management.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( name = "tasks" )
@NamedQueries( { @NamedQuery( name = "Tasks.byId", query = "SELECT e FROM Tasks e WHERE e.id =:key" ) } )
public class Tasks implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column( name = "id" )
	private Integer id;

	@Column( name = "task_id" )
	private String taskId;

	@Column( name = "org_name" )
	private String orgName;

	@Column( name = "project_name" )
	private String projectName;

	@Column( name = "assignee" )
	private String assignee;

	@Column( name = "zip_code" )
	private String zipCode;

	@Column( name = "title" )
	private String title;

	@Column( name = "description" )
	private String description;

	@Column( name = "type" )
	private String type;

	@Column( name = "estimated_time" )
	private Integer estimatedTime;

	@Column( name = "remaining_time" )
	private Integer remainingTime;

	@Column( name = "total_time_taken" )
	private Integer totalTimeTaken;

	@Column( name = "status" )
	private String status;

	@Column( name = "sprint_name" )
	private String sprintName;

	@Column( name = "summary" )
	private String summary;

	@Column( name = "created_at" )
	private Long createdAt;

	@Column( name = "created_by" )
	private String createdBy;

	@Column( name = "updated_at" )
	private Long updatedAt;

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy( String createdBy )
	{
		this.createdBy = createdBy;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getTaskId()
	{
		return taskId;
	}

	public void setTaskId( String taskId )
	{
		this.taskId = taskId;
	}

	public String getOrgName()
	{
		return orgName;
	}

	public void setOrgName( String orgName )
	{
		this.orgName = orgName;
	}

	public String getProjectName()
	{
		return projectName;
	}

	public void setProjectName( String projectName )
	{
		this.projectName = projectName;
	}

	public String getAssignee()
	{
		return assignee;
	}

	public void setAssignee( String assignee )
	{
		this.assignee = assignee;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode( String zipCode )
	{
		this.zipCode = zipCode;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public Integer getEstimatedTime()
	{
		return estimatedTime;
	}

	public void setEstimatedTime( Integer estimatedTime )
	{
		this.estimatedTime = estimatedTime;
	}

	public Integer getRemainingTime()
	{
		return remainingTime;
	}

	public void setRemainingTime( Integer remainingTime )
	{
		this.remainingTime = remainingTime;
	}

	public Integer getTotalTimeTaken()
	{
		return totalTimeTaken;
	}

	public void setTotalTimeTaken( Integer totalTimeTaken )
	{
		this.totalTimeTaken = totalTimeTaken;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus( String status )
	{
		this.status = status;
	}

	public String getSprintName()
	{
		return sprintName;
	}

	public void setSprintName( String sprintName )
	{
		this.sprintName = sprintName;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary( String summary )
	{
		this.summary = summary;
	}

	public Long getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt( Long createdAt )
	{
		this.createdAt = createdAt;
	}

	public Long getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt( Long updatedAt )
	{
		this.updatedAt = updatedAt;
	}

}
