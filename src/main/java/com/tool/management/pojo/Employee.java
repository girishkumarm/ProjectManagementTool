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
@Table( name = "employee" )
@NamedQueries( { @NamedQuery( name = "Employee.byId", query = "SELECT e FROM Employee e WHERE e.id =:key" ) } )
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column( name = "id" )
	private Integer id;

	@Column( name = "employee_id" )
	private String key;

	@Column( name = "employee_name" )
	private String name;

	@Column( name = "projects_under" )
	private String projectUnder;

	@Column( name = "image_url" )
	private String source;

	@Column( name = "title" )
	private String title;

	@Column( name = "department" )
	private String department;

	@Column( name = "email_id" )
	private String emailId;

	@Column( name = "emp_password" )
	private String password;

	@Column( name = "company_name" )
	private String organisation;

	@Column( name = "zip_code" )
	private String zipCode;

	@Column( name = "employee_type" )
	private String employeeType;

	@Column( name = "project_name" )
	private String projectName;

	@Column( name = "team_lead" )
	private Long teamLead;

	@Column( name = "date_of_birth" )
	private String dateOfBirth;

	public String getProjectUnder()
	{
		return projectUnder;
	}

	public void setProjectUnder( String projectUnder )
	{
		this.projectUnder = projectUnder;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode( String zipCode )
	{
		this.zipCode = zipCode;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment( String department )
	{
		this.department = department;
	}

	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth( String dateOfBirth )
	{
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey( String key )
	{
		this.key = key;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource( String source )
	{
		this.source = source;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId( String emailId )
	{
		this.emailId = emailId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public String getOrganisation()
	{
		return organisation;
	}

	public void setOrganisation( String organisation )
	{
		this.organisation = organisation;
	}

	public String getEmployeeType()
	{
		return employeeType;
	}

	public void setEmployeeType( String employeeType )
	{
		this.employeeType = employeeType;
	}

	public String getProjectName()
	{
		return projectName;
	}

	public void setProjectName( String projectName )
	{
		this.projectName = projectName;
	}

	public Long getTeamLead()
	{
		return teamLead;
	}

	public void setTeamLead( Long team_lead )
	{
		this.teamLead = team_lead;
	}
}
