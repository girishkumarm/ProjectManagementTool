package com.tool.management.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "worklog" )
public class WorkLogged implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column( name = "id" )
	private Integer id;

	@Column( name = "org_name" )
	private String orgName;

	@Column( name = "project_name" )
	private String projectName;

	@Column( name = "zip_code" )
	private String zipCode;

	@Column( name = "sprint_name" )
	private String sprintName;

	@Column( name = "task_name" )
	private String taskName;

	@Column( name = "task_id" )
	private Integer taskId;

	@Column( name = "logged_hours" )
	private Integer loggedHours;

	@Column( name = "remaining_time" )
	private Integer remainingTime;

	@Column( name = "logged_time" )
	private Long loggedTime;

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
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

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode( String zipCode )
	{
		this.zipCode = zipCode;
	}

	public String getSprintName()
	{
		return sprintName;
	}

	public void setSprintName( String sprintName )
	{
		this.sprintName = sprintName;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName( String taskName )
	{
		this.taskName = taskName;
	}

	public Integer getTaskId()
	{
		return taskId;
	}

	public void setTaskId( Integer taskId )
	{
		this.taskId = taskId;
	}

	public Integer getLoggedHours()
	{
		return loggedHours;
	}

	public void setLoggedHours( Integer loggedHours )
	{
		this.loggedHours = loggedHours;
	}

	public Integer getRemainingTime()
	{
		return remainingTime;
	}

	public void setRemainingTime( Integer remainingTime )
	{
		this.remainingTime = remainingTime;
	}

	public Long getLoggedTime()
	{
		return loggedTime;
	}

	public void setLoggedTime( Long loggedTime )
	{
		this.loggedTime = loggedTime;
	}

}
