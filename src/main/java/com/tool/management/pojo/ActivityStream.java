package com.tool.management.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "activity" )
public class ActivityStream implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column( name = "id" )
	private Integer id;

	@Column( name = "task_id" )
	private String taskId;

	@Column( name = "task_name" )
	private String taskName;

	@Column( name = "org_name" )
	private String orgName;

	@Column( name = "project_name" )
	private String projectName;

	@Column( name = "zip_code" )
	private String zipCode;

	@Column( name = "type" )
	private String activityType;
	
	@Column( name = "created_at" )
	private String createdAt;

	@Column( name = "description" )
	private String description;

	
	public String getCreatedAt()
	{
		return createdAt;
	}

	
	public void setCreatedAt( String createdAt )
	{
		this.createdAt = createdAt;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName( String taskName )
	{
		this.taskName = taskName;
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

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode( String zipCode )
	{
		this.zipCode = zipCode;
	}

	public String getActivityType()
	{
		return activityType;
	}

	public void setActivityType( String activityType )
	{
		this.activityType = activityType;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

}
