package com.tool.management.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tool.management.dao.NotificationDao;
import com.tool.management.generic.Constants;
import com.tool.management.interfaces.NotificationServices;
import com.tool.management.pojo.Notification;

@Service
public class NotificationServicesImpl extends NotificationDao implements NotificationServices {

	public NotificationServicesImpl( Class<Notification> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public NotificationServicesImpl( EntityManager em )
	{
		super(Notification.class, em);
	}

	@Override
	public Notification addNotification( Notification Notification ) throws Exception
	{
		try
		{
			if( Notification == null || Notification.getDescription() == null || Notification.getDescription().isEmpty()
					|| Notification.getEmployeeId() == null || Notification.getType() == null
					|| Notification.getType().isEmpty() || Notification.getTypeId() == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return save(Notification);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	@Override
	public List<Notification> fetchNotificationListOfEmployee( String id ) throws Exception
	{
		try
		{
			if( id == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return fetchByEmployeeId(Integer.parseInt(id));
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	@Override
	public Notification fetchNotification( Integer id ) throws Exception
	{
		try
		{
			if( id == null || id <= 0 )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return fetchById(id);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

}
