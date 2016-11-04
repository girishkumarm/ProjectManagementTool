package com.tool.management.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tool.management.dao.WorkLoggedDao;
import com.tool.management.generic.Constants;
import com.tool.management.interfaces.WorkLoggedServices;
import com.tool.management.pojo.WorkLogged;

@Service
public class WorkLoggedImpl extends WorkLoggedDao implements WorkLoggedServices {

	public WorkLoggedImpl( Class<WorkLogged> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public WorkLoggedImpl( EntityManager em )
	{
		super(WorkLogged.class, em);
	}

	public WorkLogged logWork( WorkLogged workLogged ) throws Exception
	{
		try
		{
			if( workLogged == null || workLogged.getOrgName() == null || workLogged.getOrgName().isEmpty()
					|| workLogged.getProjectName() == null || workLogged.getProjectName().isEmpty()
					|| workLogged.getZipCode() == null || workLogged.getZipCode().isEmpty()
					|| workLogged.getTaskName() == null || workLogged.getTaskName().isEmpty()
					|| workLogged.getLoggedHours() == null || workLogged.getRemainingTime() == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return save(workLogged);

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}

	public List<WorkLogged> fetchLogWork( String orgName, String projName, String zipcode, String sprintName )
			throws Exception
	{
		try
		{
			if( orgName == null || orgName.isEmpty() || projName == null || projName.isEmpty() || sprintName == null
					|| sprintName.isEmpty() || zipcode == null || zipcode.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return fetchByStringName(orgName, projName, zipcode, sprintName);

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}

}
