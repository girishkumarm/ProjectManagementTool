package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.Project;

public class ProjectDao extends GenericRepositoryImpl<Project, Serializable> {

	public ProjectDao( Class<Project> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public ProjectDao( EntityManager em )
	{
		super(Project.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public Project fetchById( Integer id ) throws Exception
	{
		List<Project> e = null;
		try
		{
			e = (List<Project>) this.getEm().createQuery("Select e from Project e where e.id=:id")
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
	public Project fetchByOrgNameAndProjectName( String orgName, String projectName, String zipCode ) throws Exception
	{
		List<Project> e = null;
		try
		{
			e = (List<Project>) this.getEm()
					.createQuery(
							"Select e from Project e where e.projectName=:projectName and e.orgName=:orgName and e.zipCode=:zipCode")
					.setParameter("projectName", projectName).setParameter("orgName", orgName)
					.setParameter("zipCode", zipCode).getResultList();

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
	public List<Project> fetchByOrgName( String orgName, String zipCode ) throws Exception
	{
		List<Project> e = null;
		try
		{
			e = (List<Project>) this.getEm()
					.createQuery("Select e from Project e where e.orgName=:orgName and e.zipCode=:zipCode")
					.setParameter("orgName", orgName).setParameter("zipCode", zipCode).getResultList();

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
}
