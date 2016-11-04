package com.tool.management.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "project" )
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column( name = "id" )
	private Integer id;

	@Column( name = "project_name" )
	private String projectName;

	@Column( name = "created_by" )
	private String createdBy;

	@Column( name = "created_at" )
	private long createdAt;

	@Column( name = "org_name" )
	private String orgName;

	@Column( name = "zip_code" )
	private String zipCode;

	@Column( name = "team_lead" )
	private String teamLead;

	@Column( name = "employees" )
	private String employees;

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode( String zipCode )
	{
		this.zipCode = zipCode;
	}

	public String getEmployees()
	{
		return employees;
	}

	public String getTeamLead()
	{
		return teamLead;
	}

	public void setTeamLead( String teamLead )
	{
		this.teamLead = teamLead;
	}

	public void setEmployees( String employees )
	{
		this.employees = employees;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getProjectName()
	{
		return projectName;
	}

	public void setProjectName( String projectName )
	{
		this.projectName = projectName;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy( String createdBy )
	{
		this.createdBy = createdBy;
	}

	public long getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt( long createdAt )
	{
		this.createdAt = createdAt;
	}

	public String getOrgName()
	{
		return orgName;
	}

	public void setOrgName( String orgName )
	{
		this.orgName = orgName;
	}

}
