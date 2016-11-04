package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.EmployeeHolidayInfo;

public class EmployeeHolidayInfoDao extends GenericRepositoryImpl<EmployeeHolidayInfo, Serializable> {

	public EmployeeHolidayInfoDao( Class<EmployeeHolidayInfo> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public EmployeeHolidayInfoDao( EntityManager em )
	{
		super(EmployeeHolidayInfo.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public EmployeeHolidayInfo fetchById( Integer id ) throws Exception
	{
		List<EmployeeHolidayInfo> e = null;
		try
		{
			e = (List<EmployeeHolidayInfo>) this.getEm()
					.createQuery("Select e from EmployeeHolidayInfo e where e.id=:id").setParameter("id", id)
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
	public List<EmployeeHolidayInfo> fetchByEmployeeId( Integer employeeId ) throws Exception
	{
		List<EmployeeHolidayInfo> e = null;
		try
		{
			e = (List<EmployeeHolidayInfo>) this.getEm()
					.createQuery("Select e from EmployeeHolidayInfo e where e.employeeId=:employeeId")
					.setParameter("employeeId", employeeId).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

	@SuppressWarnings( "unchecked" )
	public List<EmployeeHolidayInfo> fetchByEmailId( String emailId ) throws Exception
	{
		List<EmployeeHolidayInfo> e = null;
		try
		{
			e = (List<EmployeeHolidayInfo>) this.getEm()
					.createQuery("Select e from EmployeeHolidayInfo e where e.emailId=:emailId")
					.setParameter("emailId", emailId).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

	@SuppressWarnings( "unchecked" )
	public EmployeeHolidayInfo fetchByEmailIdAndYear( String emailId, Integer year ) throws Exception
	{
		List<EmployeeHolidayInfo> e = null;
		try
		{
			e = (List<EmployeeHolidayInfo>) this.getEm()
					.createQuery("Select e from EmployeeHolidayInfo e where e.emailId=:emailId and e.year=:year")
					.setParameter("emailId", emailId).setParameter("year", year).getResultList();

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

}