package com.tool.management.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "holiday_request" )
public class HolidayRequest implements Serializable {

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
	private String employeeName;

	@Column( name = "email_id" )
	private String emailId;

	@Column( name = "status" )
	private String status;

	@Column( name = "team_lead" )
	private Integer teamLead;

	@Column( name = "description" )
	private String description;

	@Column( name = "dates" )
	private String dates;

	@Column( name = "total_days" )
	private Integer totalDays;

	@Column( name = "annual" )
	private Integer annual;

	@Column( name = "sick" )
	private Integer sick;

	@Column( name = "others" )
	private Integer others;

	@Column( name = "un_paid" )
	private Integer unPaid;

	@Column( name = "created_at" )
	private String createdAt;

	@Column( name = "team_lead_comment" )
	private String teamLeadComments;

	public String getTeamLeadComments()
	{
		return teamLeadComments;
	}

	public void setTeamLeadComments( String teamLeadComments )
	{
		this.teamLeadComments = teamLeadComments;
	}

	public Integer getId()
	{
		return id;
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

	public String getEmployeeName()
	{
		return employeeName;
	}

	public void setEmployeeName( String employeeName )
	{
		this.employeeName = employeeName;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId( String emailId )
	{
		this.emailId = emailId;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus( String status )
	{
		this.status = status;
	}

	public Integer getTeamLead()
	{
		return teamLead;
	}

	public void setTeamLead( Integer teamLead )
	{
		this.teamLead = teamLead;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public String getDates()
	{
		return dates;
	}

	public void setDates( String dates )
	{
		this.dates = dates;
	}

	public Integer getTotalDays()
	{
		return totalDays;
	}

	public void setTotalDays( Integer totalDays )
	{
		this.totalDays = totalDays;
	}

	public Integer getAnnual()
	{
		return annual;
	}

	public void setAnnual( Integer annual )
	{
		this.annual = annual;
	}

	public Integer getSick()
	{
		return sick;
	}

	public void setSick( Integer sick )
	{
		this.sick = sick;
	}

	public Integer getOthers()
	{
		return others;
	}

	public void setOthers( Integer others )
	{
		this.others = others;
	}

	public Integer getUnPaid()
	{
		return unPaid;
	}

	public void setUnPaid( Integer unPaid )
	{
		this.unPaid = unPaid;
	}

	public String getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt( String createdAt )
	{
		this.createdAt = createdAt;
	}

}
