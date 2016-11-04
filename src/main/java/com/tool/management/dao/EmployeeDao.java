package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.Employee;

public class EmployeeDao extends GenericRepositoryImpl<Employee, Serializable> {

	public EmployeeDao( Class<Employee> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public EmployeeDao( EntityManager em )
	{
		super(Employee.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public Employee fetchById( Integer id ) throws Exception
	{
		List<Employee> e = null;
		try
		{
			e = (List<Employee>) this.getEm().createQuery("Select e from Employee e where e.id=:id")
					.setParameter("id", id).getResultList();

			if( e != null && !e.isEmpty() )
			{
				return e.get(0);
			}

		}
		catch( Exception ee )
		{
			throw ee;
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public Employee fetchByEmailId( String emailId ) throws Exception
	{
		List<Employee> e = null;
		try
		{
			e = (List<Employee>) this.getEm().createQuery("Select e from Employee e where e.emailId=:emailId")
					.setParameter("emailId", emailId).getResultList();

			if( e != null && !e.isEmpty() )
			{
				return e.get(0);
			}

		}
		catch( Exception ee )
		{
			throw ee;
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public List<Employee> fetchAllEmployee( String orgName, String zipCode ) throws Exception
	{
		List<Employee> e = null;
		try
		{
			e = (List<Employee>) this.getEm()
					.createQuery("Select e from Employee e where e.organisation=:organisation and e.zipCode=:zipCode")
					.setParameter("organisation", orgName).setParameter("zipCode", zipCode).getResultList();

			if( e != null && !e.isEmpty() )
			{
				return e;
			}

		}
		catch( Exception ee )
		{
			throw ee;
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public List<Employee> fetchByTeamLead( Long teamLead ) throws Exception
	{
		List<Employee> e = null;
		try
		{
			e = (List<Employee>) this.getEm().createQuery("Select e from Employee e where e.teamLead=:teamLead")
					.setParameter("teamLead", teamLead).getResultList();

			if( e != null && !e.isEmpty() )
			{
				return e;
			}

		}
		catch( Exception ee )
		{
			throw ee;
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public List<Employee> fetchEmpOfProject( String orgName, String zipCode, String projectName ) throws Exception
	{
		List<Employee> e = null;
		try
		{
			e = (List<Employee>) this.getEm()
					.createQuery(
							"Select e from Employee e where e.projectName=:projectName and e.zipCode=:zipCode and e.organisation=:organisation")
					.setParameter("projectName", projectName).setParameter("zipCode", zipCode)
					.setParameter("organisation", orgName).getResultList();

			if( e != null && !e.isEmpty() )
			{
				return e;
			}

		}
		catch( Exception ee )
		{
			throw ee;
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public Employee fetchEmployeeInfo( String orgName, String zipCode, String emailId ) throws Exception
	{
		List<Employee> e = null;
		try
		{
			e = (List<Employee>) this.getEm()
					.createQuery(
							"Select e from Employee e where e.emailId=:emailId and e.zipCode=:zipCode and e.organisation=:organisation")
					.setParameter("emailId", emailId).setParameter("zipCode", zipCode)
					.setParameter("organisation", orgName).getResultList();

			if( e != null && !e.isEmpty() )
			{
				return e.get(0);
			}

		}
		catch( Exception ee )
		{
			throw ee;
		}
		return null;
	}

}
