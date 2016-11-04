package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.Sprint;

public class SprintDao extends GenericRepositoryImpl<Sprint, Serializable> {

	public SprintDao( Class<Sprint> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public SprintDao( EntityManager em )
	{
		super(Sprint.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public Sprint fetchById( Integer id ) throws Exception
	{
		List<Sprint> e = null;
		try
		{
			e = (List<Sprint>) this.getEm().createQuery("Select e from Sprint e where e.id=:id").setParameter("id", id)
					.getResultList();

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
	public List<Sprint> fetchByProjectNameOrgName( String projectName, String orgName, String zipCode ) throws Exception
	{
		List<Sprint> e = null;
		try
		{
			e = (List<Sprint>) this.getEm()
					.createQuery(
							"Select e from Sprint e where e.projName=:projName and e.orgName=:orgName  and e.zipCode=:zipCode and e.active=:active")
					.setParameter("projName", projectName).setParameter("orgName", orgName).setParameter("active", true)
					.setParameter("zipCode", zipCode).getResultList();

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
	public int makeAllSprintInActiveInAProject( String projectName, String orgName, String zipCode ) throws Exception
	{
		List<Sprint> e = null;
		try
		{
			e = (List<Sprint>) this.getEm()
					.createQuery(
							"Select e from Sprint e where e.projName=:projName and e.orgName=:orgName  and e.zipCode=:zipCode")
					.setParameter("projName", projectName).setParameter("orgName", orgName)
					.setParameter("zipCode", zipCode).getResultList();

			/*if( e != null && !e.isEmpty() )
			{
				for( Sprint sprint : e )
				{
					if( sprint.isActive() )
					{
						sprint.setActive(false);
						save(sprint);
					}
				}
			}*/

		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e.size();
	}

	@SuppressWarnings( "unchecked" )
	public Sprint fetchByProjectNameOrgNameSprintName( String orgName, String projectName, String sprintName,
			String zipCode ) throws Exception
	{
		List<Sprint> e = null;
		try
		{
			e = (List<Sprint>) this.getEm()
					.createQuery(
							"Select e from Sprint e where e.projName=:projName and e.orgName=:orgName and e.sprintName=:sprintName and e.zipCode=:zipCode")
					.setParameter("projName", projectName).setParameter("orgName", orgName)
					.setParameter("sprintName", sprintName).setParameter("zipCode", zipCode).getResultList();

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

	public void deleteBySprintName( String orgName, String projectName, String sprintName ) throws Exception
	{
		try
		{
			this.getEm().createNamedQuery("Delete Sprint e where e.orgName=`" + orgName + "` and e.projName=`"
					+ projectName + "` e.sprintName=`" + sprintName + "`");

		}
		catch( Exception ee )
		{
			throw ee;
		}
	}
}
