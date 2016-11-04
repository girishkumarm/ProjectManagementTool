package com.tool.management.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "holiday_info" )
public class HolidayInfo implements Serializable {

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

	@Column( name = "annual" )
	private Integer annual;

	@Column( name = "sick" )
	private Integer sick;

	@Column( name = "others" )
	private Integer others;

	@Column( name = "un_paid_leaves" )
	private Integer unPaidLeaves;

	@Column( name = "annual_leaves_taken" )
	private Integer annualLeavesTaken;

	@Column( name = "sick_leaves_taken" )
	private Integer sickLeavesTaken;

	@Column( name = "year" )
	private Integer year;

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

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId( String emailId )
	{
		this.emailId = emailId;
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

	public Integer getUnPaidLeaves()
	{
		return unPaidLeaves;
	}

	public void setUnPaidLeaves( Integer unPaidLeaves )
	{
		this.unPaidLeaves = unPaidLeaves;
	}

	public Integer getAnnualLeavesTaken()
	{
		return annualLeavesTaken;
	}

	public void setAnnualLeavesTaken( Integer annualLeavesTaken )
	{
		this.annualLeavesTaken = annualLeavesTaken;
	}

	public Integer getSickLeavesTaken()
	{
		return sickLeavesTaken;
	}

	public void setSickLeavesTaken( Integer sickLeavesTaken )
	{
		this.sickLeavesTaken = sickLeavesTaken;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear( Integer year )
	{
		this.year = year;
	}

}
