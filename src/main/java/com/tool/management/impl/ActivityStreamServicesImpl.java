package com.tool.management.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.tool.management.dao.ActivityStreamDao;
import com.tool.management.generic.Constants;
import com.tool.management.interfaces.ActivityStreamServices;
import com.tool.management.pojo.ActivityStream;

@Service
public class ActivityStreamServicesImpl extends ActivityStreamDao implements ActivityStreamServices {

	public ActivityStreamServicesImpl( Class<ActivityStream> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public ActivityStreamServicesImpl( EntityManager em )
	{
		super(ActivityStream.class, em);
	}

	@Override
	public ActivityStream addActivity( ActivityStream activity ) throws Exception
	{
		try
		{
			System.out.println(new Gson().toJson(activity));
			if( activity == null || activity.getActivityType() == null || activity.getActivityType().isEmpty()
					|| activity.getDescription() == null || activity.getDescription().isEmpty()
					|| activity.getOrgName() == null || activity.getOrgName().isEmpty()
					|| activity.getProjectName() == null || activity.getProjectName().isEmpty()
					|| activity.getZipCode() == null || activity.getZipCode().isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return save(activity);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	@Override
	public List<ActivityStream> fetchActivityList( ActivityStream activity ) throws Exception
	{
		try
		{
			if( activity == null || activity.getActivityType() == null || activity.getActivityType().isEmpty()
					|| activity.getOrgName() == null || activity.getOrgName().isEmpty()
					|| activity.getZipCode() == null || activity.getZipCode().isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return fetchByType(activity);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	@Override
	public ActivityStream fetchActivity( Integer id ) throws Exception
	{
		try
		{
			if( id == null || id <= 0 )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return fetchActivity(id);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

}
