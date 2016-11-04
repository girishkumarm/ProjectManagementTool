package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.HolidayInfo;

public class HolidayInfoDao extends GenericRepositoryImpl<HolidayInfo, Serializable> {

	public HolidayInfoDao( Class<HolidayInfo> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public HolidayInfoDao( EntityManager em )
	{
		super(HolidayInfo.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public HolidayInfo fetchById( Integer id ) throws Exception
	{
		List<HolidayInfo> e = null;
		try
		{
			e = (List<HolidayInfo>) this.getEm().createQuery("Select e from HolidayInfo e where e.id=:id")
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
	public List<HolidayInfo> fetchByEmployeeId( Integer employeeId ) throws Exception
	{
		List<HolidayInfo> e = null;
		try
		{
			e = (List<HolidayInfo>) this.getEm()
					.createQuery("Select e from HolidayInfo e where e.employeeId=:employeeId")
					.setParameter("employeeId", employeeId).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

	@SuppressWarnings( "unchecked" )
	public List<HolidayInfo> fetchByEmailId( String emailId ) throws Exception
	{
		List<HolidayInfo> e = null;
		try
		{
			e = (List<HolidayInfo>) this.getEm().createQuery("Select e from HolidayInfo e where e.emailId=:emailId")
					.setParameter("emailId", emailId).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

	@SuppressWarnings( "unchecked" )
	public HolidayInfo fetchByEmailIdAndYear( String emailId, Integer year ) throws Exception
	{
		List<HolidayInfo> e = null;
		try
		{
			e = (List<HolidayInfo>) this.getEm()
					.createQuery("Select e from HolidayInfo e where e.emailId=:emailId and e.year=:year")
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