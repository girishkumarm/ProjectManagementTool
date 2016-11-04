package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.ActivityStream;

public class ActivityStreamDao extends GenericRepositoryImpl<ActivityStream, Serializable> {

	public ActivityStreamDao( Class<ActivityStream> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public ActivityStreamDao( EntityManager em )
	{
		super(ActivityStream.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public ActivityStream fetchById( Integer id ) throws Exception
	{
		List<ActivityStream> e = null;
		try
		{
			e = (List<ActivityStream>) this.getEm().createQuery("Select e from ActivityStream e where e.id=:id")
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
	public List<ActivityStream> fetchByType( ActivityStream activity ) throws Exception
	{
		List<ActivityStream> e = null;
		try
		{
			if( activity.getActivityType().equals("all"))
			{
				e = (List<ActivityStream>) this.getEm()
						.createQuery(
								"Select e from ActivityStream e where e.orgName=:orgName and e.zipCode=:zipCode")
						.setParameter("orgName", activity.getOrgName())
						.setParameter("zipCode", activity.getZipCode()).getResultList();

			}
			else
			{
				e = (List<ActivityStream>) this.getEm()
						.createQuery(
								"Select e from ActivityStream e where e.taskId=:taskId and e.projectName=:projectName and e.orgName=:orgName and e.zipCode=:zipCode and e.activityType=:activityType")
						.setParameter("taskId", activity.getTaskId()).setParameter("orgName", activity.getOrgName())
						.setParameter("projectName", activity.getProjectName())
						.setParameter("zipCode", activity.getZipCode())
						.setParameter("activityType", activity.getActivityType()).getResultList();
			}

		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

}
