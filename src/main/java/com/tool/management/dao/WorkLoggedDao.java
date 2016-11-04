package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.WorkLogged;

@Service
public class WorkLoggedDao extends GenericRepositoryImpl<WorkLogged, Serializable> {

	public WorkLoggedDao( Class<WorkLogged> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public WorkLoggedDao( EntityManager em )
	{
		super(WorkLogged.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public WorkLogged fetchById( Integer id ) throws Exception
	{
		List<WorkLogged> log = null;
		try
		{
			log = (List<WorkLogged>) this.getEm().createQuery("Select o from WorkLogged o where o.id=:id")
					.setParameter("id", id).getResultList();

			if( log != null && !log.isEmpty() )
			{
				return log.get(0);
			}

		}
		catch( Exception e )
		{
			throw e;
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public List<WorkLogged> fetchByStringName( String orgName, String projectName, String zipCode, String sprintName )
			throws Exception
	{
		List<WorkLogged> log = null;
		try
		{
			log = (List<WorkLogged>) this.getEm()
					.createQuery(
							"Select o from WorkLogged o where o.orgName=:orgName and o.projectName=:projectName and o.zipCode=:zipCode and o.sprintName=:sprintName order by o.loggedTime")
					.setParameter("orgName", orgName).setParameter("projectName", projectName)
					.setParameter("zipCode", zipCode).setParameter("sprintName", sprintName).getResultList();

		}
		catch( Exception e )
		{
			throw e;
		}
		return log;
	}
}
