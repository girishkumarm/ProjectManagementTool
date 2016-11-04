package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.Notification;

public class NotificationDao extends GenericRepositoryImpl<Notification, Serializable> {

	public NotificationDao( Class<Notification> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public NotificationDao( EntityManager em )
	{
		super(Notification.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public Notification fetchById( Integer id ) throws Exception
	{
		List<Notification> e = null;
		try
		{
			e = (List<Notification>) this.getEm().createQuery("Select e from Notification e where e.id=:id")
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
	public List<Notification> fetchByEmployeeId( Integer employeeId ) throws Exception
	{
		List<Notification> e = null;
		try
		{
			e = (List<Notification>) this.getEm()
					.createQuery("Select e from Notification e where e.employeeId=:employeeId")
					.setParameter("employeeId", employeeId).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}
	
	@SuppressWarnings( "unchecked" )
	public List<Notification> fetchByEmailId( String emailId ) throws Exception
	{
		List<Notification> e = null;
		try
		{
			e = (List<Notification>) this.getEm()
					.createQuery("Select e from Notification e where e.emailId=:emailId")
					.setParameter("emailId", emailId).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

}
