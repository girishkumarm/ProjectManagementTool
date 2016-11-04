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
@Table( name = "Organisation" )
@NamedQueries( { @NamedQuery( name = "Organisation.byId", query = "SELECT e FROM Organisation e WHERE e.id =:key" ) } )
public class Organisation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column( name = "id" )
	private Integer id;
	
	@Column( name = "user_name" )
	private String userName;

	@Column( name = "organisation_name" )
	private String organisationName;

	@Column( name = "registration_number" )
	private String registrationNumber;
	
	@Column( name = "address" )
	private String address;
	
	@Column( name = "activated" )
	private boolean activated;

	@Column( name = "email_id" )
	private String emailId;

	@Column( name = "org_password" )
	private String password;
	
	
	public boolean isActivated()
	{
		return activated;
	}

	
	public void setActivated( boolean activated )
	{
		this.activated = activated;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName( String userName )
	{
		this.userName = userName;
	}


	public String getRegistrationNumber()
	{
		return registrationNumber;
	}

	
	public void setRegistrationNumber( String registrationNumber )
	{
		this.registrationNumber = registrationNumber;
	}

	
	public String getAddress()
	{
		return address;
	}

	
	public void setAddress( String address )
	{
		this.address = address;
	}
	
	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getOrganisationName()
	{
		return organisationName;
	}

	public void setOrganisationName( String organisationName )
	{
		this.organisationName = organisationName;
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

}
