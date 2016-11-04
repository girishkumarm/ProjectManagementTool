package com.tool.management.generic;

public class EmpChart {

	private Integer key;
	private Integer id;
	private String name;
	private String source;
	private String title;
	private String parent;
	private String department;
	private String emailId;

	
	public String getEmailId()
	{
		return emailId;
	}

	
	public void setEmailId( String emailId )
	{
		this.emailId = emailId;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment( String department )
	{
		this.department = department;
	}

	public Integer getKey()
	{
		return key;
	}

	public void setKey( Integer key )
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

	public String getParent()
	{
		return parent;
	}

	public void setParent( String parent )
	{
		this.parent = parent;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

}
