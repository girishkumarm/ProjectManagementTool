package com.tool.management.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "empGrievance" )
public class EmpGrievance implements Serializable {

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

	@Column( name = "email_id" )
	private String emailId;

	@Column( name = "description" )
	private String description;

	@Column( name = "created_at" )
	private String createdAt;

	@Column( name = "closed_at" )
	private String closedAt;

	@Column( name = "type" )
	private String type;

	@Column( name = "status" )
	private String status;

	public Integer getId()
	{
		return id;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId( String emailId )
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public String getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt( String createdAt )
	{
		this.createdAt = createdAt;
	}

	public String getClosedAt()
	{
		return closedAt;
	}

	public void setClosedAt( String closedAt )
	{
		this.closedAt = closedAt;
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

}
