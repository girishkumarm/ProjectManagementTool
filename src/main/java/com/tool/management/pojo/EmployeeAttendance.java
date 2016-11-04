package com.tool.management.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "emp_attendance" )
public class EmployeeAttendance implements Serializable {

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
	private String name;

	@Column( name = "email_id" )
	private String emailId;

	@Column( name = "day" )
	private Integer day;

	@Column( name = "month" )
	private String month;

	@Column( name = "year" )
	private Integer year;

	@Column( name = "num_of_hours_worked" )
	private String numOfHoursWorked;

	@Column( name = "login_time" )
	private Long loginTime;

	@Column( name = "logout_time" )
	private Long logoutTime;

	@Column( name = "status" )
	private Integer status;

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus( Integer status )
	{
		this.status = status;
	}

	public Integer getDay()
	{
		return day;
	}

	public void setDay( Integer day )
	{
		this.day = day;
	}

	public String getMonth()
	{
		return month;
	}

	public void setMonth( String month )
	{
		this.month = month;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear( Integer year )
	{
		this.year = year;
	}

	public Long getLogoutTime()
	{
		return logoutTime;
	}

	public void setLogoutTime( Long logoutTime )
	{
		this.logoutTime = logoutTime;
	}

	public Integer getId()
	{
		return id;
	}

	public Integer getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId( Integer employeeId )
	{
		this.employeeId = employeeId;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId( String emailId )
	{
		this.emailId = emailId;
	}

	public String getNumOfHoursWorked()
	{
		return numOfHoursWorked;
	}

	public void setNumOfHoursWorked( String numOfHoursWorked )
	{
		this.numOfHoursWorked = numOfHoursWorked;
	}

	public Long getLoginTime()
	{
		return loginTime;
	}

	public void setLoginTime( Long loginTime )
	{
		this.loginTime = loginTime;
	}

}
