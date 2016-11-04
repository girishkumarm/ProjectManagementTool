package com.tool.management.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "sprint" )
public class Sprint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column( name = "id" )
	private Integer id;

	@Column( name = "sprint_name" )
	private String sprintName;

	@Column( name = "project_name" )
	private String projName;

	@Column( name = "org_name" )
	private String orgName;

	@Column( name = "zip_code" )
	private String zipCode;

	@Column( name = "total_hours" )
	private Long totalHours;

	@Column( name = "active" )
	private boolean active;

	@Column( name = "working_days" )
	private Integer workingDays;

	@Column( name = "created_at" )
	private long createdAt;

	@Column( name = "created_by" )
	private String createdBy;

	@Column( name = "start_date" )
	private Long startDate;

	@Column( name = "end_date" )
	private Long endDate;

	@Column( name = "working_dates" )
	private String workingDates;

	@Column( name = "holidays" )
	private String holidays;

	public String getWorkingDates()
	{
		return workingDates;
	}

	public void setWorkingDates( String workingDates )
	{
		this.workingDates = workingDates;
	}

	public String getHolidays()
	{
		return holidays;
	}

	public void setHolidays( String holidays )
	{
		this.holidays = holidays;
	}

	public Integer getWorkingDays()
	{
		return workingDays;
	}

	public void setWorkingDays( Integer workingDays )
	{
		this.workingDays = workingDays;
	}

	public Long getStartDate()
	{
		return startDate;
	}

	public Long getTotalHours()
	{
		return totalHours;
	}

	public void setTotalHours( Long totalHours )
	{
		this.totalHours = totalHours;
	}

	public void setStartDate( Long startDate )
	{
		this.startDate = startDate;
	}

	public Long getEndDate()
	{
		return endDate;
	}

	public void setEndDate( Long endDate )
	{
		this.endDate = endDate;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode( String zipCode )
	{
		this.zipCode = zipCode;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getSprintName()
	{
		return sprintName;
	}

	public void setSprintName( String sprintName )
	{
		this.sprintName = sprintName;
	}

	public String getProjName()
	{
		return projName;
	}

	public void setProjName( String projName )
	{
		this.projName = projName;
	}

	public String getOrgName()
	{
		return orgName;
	}

	public void setOrgName( String orgName )
	{
		this.orgName = orgName;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive( boolean active )
	{
		this.active = active;
	}

	public long getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt( long createdAt )
	{
		this.createdAt = createdAt;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy( String createdBy )
	{
		this.createdBy = createdBy;
	}

}
