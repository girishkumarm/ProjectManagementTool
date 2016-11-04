package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.EmpGrievance;
import com.tool.management.pojo.EmployeeAttendance;

public class EmployeeAttendanceDao extends GenericRepositoryImpl<EmployeeAttendance, Serializable> {

	public EmployeeAttendanceDao( Class<EmployeeAttendance> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public EmployeeAttendanceDao( EntityManager em )
	{
		super(EmployeeAttendance.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public EmpGrievance fetchById( Integer id ) throws Exception
	{
		List<EmpGrievance> e = null;
		try
		{
			e = (List<EmpGrievance>) this.getEm().createQuery("Select e from EmployeeAttendance e where e.id=:id")
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
	public List<EmpGrievance> fetchByEmployeeId( Integer employeeId ) throws Exception
	{
		List<EmpGrievance> e = null;
		try
		{
			e = (List<EmpGrievance>) this.getEm()
					.createQuery("Select e from EmployeeAttendance e where e.employeeId=:employeeId")
					.setParameter("employeeId", employeeId).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

	@SuppressWarnings( "unchecked" )
	public List<EmployeeAttendance> fetchByEmailId( String emailId ) throws Exception
	{
		List<EmployeeAttendance> e = null;
		try
		{
			e = (List<EmployeeAttendance>) this.getEm()
					.createQuery("Select e from EmployeeAttendance e where e.emailId=:emailId")
					.setParameter("emailId", emailId).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

	@SuppressWarnings( "unchecked" )
	public List<EmployeeAttendance> fetchEmployeeAttendance( String emailId, Integer day, String month, Integer year )
			throws Exception
	{
		List<EmployeeAttendance> e = null;
		try
		{
			if( day == null || day <= 0 )
			{
				e = (List<EmployeeAttendance>) this.getEm()
						.createQuery(
								"Select e from EmployeeAttendance e where e.emailId=:emailId and e.month=:month and e.year=:year")
						.setParameter("emailId", emailId).setParameter("month", month).setParameter("year", year)
						.getResultList();
			}
			else
			{
				e = (List<EmployeeAttendance>) this.getEm()
						.createQuery(
								"Select e from EmployeeAttendance e where e.emailId=:emailId and e.day=:day and e.month=:month and e.year=:year")
						.setParameter("emailId", emailId).setParameter("day", day).setParameter("month", month)
						.setParameter("year", year).getResultList();
			}
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}
}
