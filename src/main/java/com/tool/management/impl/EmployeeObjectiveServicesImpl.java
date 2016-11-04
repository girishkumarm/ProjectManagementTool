package com.tool.management.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tool.management.dao.EmployeeObjectiveDao;
import com.tool.management.generic.Constants;
import com.tool.management.interfaces.EmployeeObjectiveServices;
import com.tool.management.interfaces.NotificationServices;
import com.tool.management.pojo.EmployeeObjective;
import com.tool.management.pojo.Notification;

@Service
public class EmployeeObjectiveServicesImpl extends EmployeeObjectiveDao implements EmployeeObjectiveServices {

	@Autowired
	private NotificationServices notificationServices;

	public EmployeeObjectiveServicesImpl( Class<EmployeeObjective> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public EmployeeObjectiveServicesImpl( EntityManager em )
	{
		super(EmployeeObjective.class, em);
	}

	@Override
	public EmployeeObjective addEmployeeObjective( EmployeeObjective employeeObjective ) throws Exception
	{
		EmployeeObjective empOb;
		try
		{
			if( employeeObjective == null || employeeObjective.getEmployeeId() == null
					|| employeeObjective.getObjective() == null || employeeObjective.getObjective().isEmpty()
					|| employeeObjective.getStatus() == null || employeeObjective.getStatus().isEmpty()
					|| employeeObjective.getTeamLead() == null || employeeObjective.getType() == null
					|| employeeObjective.getType().isEmpty() || employeeObjective.getCreatedAt() == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			empOb = save(employeeObjective);

			Notification notification = new Notification();
			notification.setEmployeeId(empOb.getEmployeeId());
			notification.setType("EmployeeObjective");
			notification.setTypeId(empOb.getId());
			notification.setDescription("You have new objective set by your teamLead");
			notificationServices.addNotification(notification);

			return save(empOb);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	@Override
	public EmployeeObjective updateEmployeeObjective( EmployeeObjective employeeObjective ) throws Exception
	{
		EmployeeObjective empOb;
		try
		{
			if( employeeObjective == null || employeeObjective.getEmployeeId() == null
					|| employeeObjective.getObjective() == null || employeeObjective.getObjective().isEmpty()
					|| employeeObjective.getStatus() == null || employeeObjective.getStatus().isEmpty()
					|| employeeObjective.getTeamLead() == null || employeeObjective.getType() == null
					|| employeeObjective.getType().isEmpty() || employeeObjective.getCreatedAt() == null
					|| employeeObjective.getId() == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			empOb = save(employeeObjective);

			Notification notification = new Notification();
			notification.setEmployeeId(empOb.getEmployeeId());
			notification.setType("EmployeeObjective");
			notification.setTypeId(empOb.getId());
			notification.setDescription(
					employeeObjective.getEmployeeName() + " has accepted the objectives, that you have been set.");
			notificationServices.addNotification(notification);

			return save(empOb);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	@Override
	public List<EmployeeObjective> fetchListOfEmployeeObjective( Integer employeeId ) throws Exception
	{
		try
		{
			if( employeeId == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return fetchByEmployeeId(employeeId);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	@Override
	public EmployeeObjective fetchEmployeeObjective( Integer id ) throws Exception
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
