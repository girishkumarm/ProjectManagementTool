package com.tool.management.entities;

import java.util.Map;

public class HolidayInfoRequest {

	private String organisationName;
	private String zipcode;
	private Integer year;
	private Integer annual;
	private Integer sick;
	private Integer unPaid;
	private Map<String, String> publicHolidays;

	public String getOrganisationName()
	{
		return organisationName;
	}

	public void setOrganisationName( String organisationName )
	{
		this.organisationName = organisationName;
	}

	public String getZipcode()
	{
		return zipcode;
	}

	public void setZipcode( String zipcode )
	{
		this.zipcode = zipcode;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear( Integer year )
	{
		this.year = year;
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

	public Integer getUnPaid()
	{
		return unPaid;
	}

	public void setUnPaid( Integer unPaid )
	{
		this.unPaid = unPaid;
	}

	public Map<String, String> getPublicHolidays()
	{
		return publicHolidays;
	}

	public void setPublicHolidays( Map<String, String> publicHolidays )
	{
		this.publicHolidays = publicHolidays;
	}

}
