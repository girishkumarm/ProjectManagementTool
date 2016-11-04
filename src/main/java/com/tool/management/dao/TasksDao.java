package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.Tasks;

public class TasksDao extends GenericRepositoryImpl<Tasks, Serializable> {

	public TasksDao( Class<Tasks> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public TasksDao( EntityManager em )
	{
		super(Tasks.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public Tasks fetchById( Integer id ) throws Exception
	{
		List<Tasks> e = null;
		try
		{
			e = (List<Tasks>) this.getEm().createQuery("Select e from Tasks e where e.id=:id").setParameter("id", id)
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
	public Tasks fetchByTaskId( String taskId ) throws Exception
	{
		List<Tasks> e = null;
		try
		{
			e = (List<Tasks>) this.getEm().createQuery("Select e from Tasks e where e.taskId=:taskId")
					.setParameter("taskId", taskId).getResultList();

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
	public List<Tasks> fetchTaskOfSprint( String orgName, String projectName, String sprintName, String zipCode )
			throws Exception
	{
		List<Tasks> e = null;
		try
		{
			e = (List<Tasks>) this.getEm()
					.createQuery(
							"Select e from Tasks e where e.orgName=:orgName and e.projectName=:projectName and e.sprintName=:sprintName and e.zipCode=:zipCode")
					.setParameter("zipCode", zipCode).setParameter("orgName", orgName)
					.setParameter("projectName", projectName).setParameter("sprintName", sprintName).getResultList();

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
	public List<Tasks> fetchAllTaskOfProject( String orgName, String projectName, String zipCode ) throws Exception
	{
		List<Tasks> e = null;
		try
		{
			e = (List<Tasks>) this.getEm()
					.createQuery(
							"Select e from Tasks e where e.orgName=:orgName and e.projectName=:projectName and e.zipCode=:zipCode")
					.setParameter("orgName", orgName).setParameter("projectName", projectName)
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

	public Tasks fetchTaskByTaskId( String taskId ) throws Exception
	{
		Tasks e = null;
		System.out.println(taskId);
		Integer id = Integer.parseInt(taskId);
		try
		{
			e = (Tasks) this.getEm().createQuery("Select e from Tasks e where e.id=:id")
					.setParameter("id", id).getSingleResult();

			if( e != null )
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

	public int fetchTaskCountInAProject( String orgName, String projectName ) throws Exception
	{
		int e = 1;
		try
		{

			e = Integer.parseInt((new Gson().toJson(this.getEm()
					.createQuery("Select count(*) from Tasks e where e.orgName=:orgName and e.projectName=:projectName")
					.setParameter("orgName", orgName).setParameter("projectName", projectName).getSingleResult())));

			if( e != 0 )
			{
				return e + 1;
			}

		}
		catch( Exception ee )
		{
			throw ee;
		}
		return 1;
	}

	@SuppressWarnings( "unchecked" )
	public List<Tasks> fetchTaskOfUserInAProject( String assignee ) throws Exception
	{
		List<Tasks> e = null;
		try
		{
			e = (List<Tasks>) this.getEm().createQuery("Select e from Tasks e where  e.assignee=:assignee")
					.setParameter("assignee", assignee).getResultList();

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
	public List<Tasks> fetchTaskOfUserInASprint( String orgName, String projectName, String sprintName,
			String assignee ) throws Exception
	{
		List<Tasks> e = null;
		try
		{
			e = (List<Tasks>) this.getEm()
					.createQuery(
							"Select e from Tasks e where e.orgName=:orgName and e.projectName=:projectName and e.assignee=:assignee and e.sprintName=:sprintName")
					.setParameter("orgName", orgName).setParameter("projectName", projectName)
					.setParameter("assignee", assignee).setParameter("sprintName", sprintName).getResultList();

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
