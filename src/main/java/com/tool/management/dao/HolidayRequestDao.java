package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.HolidayRequest;

public class HolidayRequestDao extends GenericRepositoryImpl<HolidayRequest, Serializable> {

	public HolidayRequestDao( Class<HolidayRequest> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public HolidayRequestDao( EntityManager em )
	{
		super(HolidayRequest.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public HolidayRequest fetchById( Integer id ) throws Exception
	{
		List<HolidayRequest> e = null;
		try
		{
			e = (List<HolidayRequest>) this.getEm().createQuery("Select e from HolidayRequest e where e.id=:id")
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
	public List<HolidayRequest> fetchByEmployeeId( Integer employeeId ) throws Exception
	{
		List<HolidayRequest> e = null;
		try
		{
			e = (List<HolidayRequest>) this.getEm()
					.createQuery("Select e from HolidayRequest e where e.employeeId=:employeeId")
					.setParameter("employeeId", employeeId).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

	@SuppressWarnings( "unchecked" )
	public List<HolidayRequest> fetchByEmailId( String emailId ) throws Exception
	{
		List<HolidayRequest> e = null;
		try
		{
			e = (List<HolidayRequest>) this.getEm()
					.createQuery("Select e from HolidayRequest e where e.emailId=:emailId")
					.setParameter("emailId", emailId).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

	@SuppressWarnings( "unchecked" )
	public List<HolidayRequest> fetchByEmailIdAndYear( String emailId, String createdAt ) throws Exception
	{
		List<HolidayRequest> e = null;
		try
		{
			e = (List<HolidayRequest>) this.getEm()
					.createQuery("Select e from HolidayRequest e where e.emailId=:emailId and e.createdAt=:createdAt")
					.setParameter("emailId", emailId).setParameter("createdAt", createdAt).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

}
