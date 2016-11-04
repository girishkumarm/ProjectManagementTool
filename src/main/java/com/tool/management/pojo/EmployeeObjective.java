package com.tool.management.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "empObjective" )
public class EmployeeObjective implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column( name = "id" )
	private Integer id;

	@Column( name = "employee_id" )
	private Integer employeeId;

	@Column( name = "employee_name" )
	private Integer employeeName;

	@Column( name = "email_id" )
	private Integer emailId;

	@Column( name = "objective" )
	private String objective;

	@Column( name = "team_lead" )
	private Integer teamLead;

	@Column( name = "type" )
	private String type;

	@Column( name = "status" )
	private String status;

	@Column( name = "createdAt" )
	private Long createdAt;

	public Integer getId()
	{
		return id;
	}

	public Integer getEmployeeName()
	{
		return employeeName;
	}

	public void setEmployeeName( Integer employeeName )
	{
		this.employeeName = employeeName;
	}

	public Integer getEmailId()
	{
		return emailId;
	}

	public void setEmailId( Integer emailId )
	{
		this.emailId = emailId;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public Integer getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId( Integer employeeId )
	{
		this.employeeId = employeeId;
	}

	public String getObjective()
	{
		return objective;
	}

	public void setObjective( String objective )
	{
		this.objective = objective;
	}

	public Integer getTeamLead()
	{
		return teamLead;
	}

	public void setTeamLead( Integer teamLead )
	{
		this.teamLead = teamLead;
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus( String status )
	{
		this.status = status;
	}

	public Long getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt( Long createdAt )
	{
		this.createdAt = createdAt;
	}

}
