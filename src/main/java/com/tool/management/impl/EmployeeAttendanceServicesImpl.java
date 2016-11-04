package com.tool.management.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.management.dao.EmployeeAttendanceDao;
import com.tool.management.generic.Constants;
import com.tool.management.interfaces.EmployeeAttendanceServices;
import com.tool.management.pojo.EmployeeAttendance;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Period;

@Service
public class EmployeeAttendanceServicesImpl extends EmployeeAttendanceDao implements EmployeeAttendanceServices {

	public EmployeeAttendanceServicesImpl( Class<EmployeeAttendance> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public EmployeeAttendanceServicesImpl( EntityManager em )
	{
		super(EmployeeAttendance.class, em);
	}

	@Override
	@Transactional( readOnly = false )
	public EmployeeAttendance takeEmployeeAttendance( EmployeeAttendance employeeAttendance ) throws Exception
	{
		EmployeeAttendance emp = null;
		try
		{
			if( employeeAttendance == null || employeeAttendance.getMonth() == null
					|| employeeAttendance.getMonth().isEmpty() || employeeAttendance.getEmailId() == null
					|| employeeAttendance.getEmailId() == null || employeeAttendance.getEmployeeId() == null
					|| employeeAttendance.getLoginTime() == null || employeeAttendance.getName() == null
					|| employeeAttendance.getName().isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			List<EmployeeAttendance> employee = fetchEmployeeAttendance(employeeAttendance.getEmailId(),
					employeeAttendance.getDay(), employeeAttendance.getMonth(), employeeAttendance.getYear());

			if( employee == null || employee.isEmpty() )
			{
				emp = save(employeeAttendance);
			}
			else
			{
				emp = employee.get(0);
			}
		}
		catch( Exception e )
		{
			throw e;
		}
		return emp;
	}

	@Override
	@Transactional( readOnly = false )
	public EmployeeAttendance updateEmployeeAttendance( EmployeeAttendance employeeAttendance ) throws Exception
	{
		EmployeeAttendance emp = null;
		try
		{
			if( employeeAttendance == null || employeeAttendance.getMonth() == null
					|| employeeAttendance.getEmailId() == null || employeeAttendance.getEmailId() == null
					|| employeeAttendance.getEmployeeId() == null || employeeAttendance.getName() == null
					|| employeeAttendance.getName().isEmpty() || employeeAttendance.getLogoutTime() == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			List<EmployeeAttendance> employee = fetchEmployeeAttendance(employeeAttendance.getEmailId(),
					employeeAttendance.getDay(), employeeAttendance.getMonth(), employeeAttendance.getYear());

			if( employee != null && !employee.isEmpty() && employee.get(0).getStatus() == 1 )
			{
				employeeAttendance.setId(employee.get(0).getId());
				if( employeeAttendance.getLogoutTime() == -1 )
				{
					employeeAttendance.setLoginTime(employee.get(0).getLoginTime());
				}
				else
				{
					Period p = new Period(new DateTime(employee.get(0).getLoginTime()),
							new DateTime(System.currentTimeMillis()));
					int hours = p.getDuration().getHours();
					int minutes = p.getDuration().getMinutes();
					employeeAttendance.setNumOfHoursWorked(hours + "hr " + minutes + "m");
					employeeAttendance.setLoginTime(employee.get(0).getLoginTime());
				}
				emp = save(employeeAttendance);
			}
			else
			{
				emp = employee.get(0);
			}
		}
		catch( Exception e )
		{
			throw e;
		}
		return emp;
	}

	@Override
	public List<EmployeeAttendance> fetchEmployeeAttendance( String emailId ) throws Exception
	{
		List<EmployeeAttendance> res = null;
		try
		{
			if( emailId == null || emailId.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}
			System.out.println(emailId);
			res = fetchByEmailId(emailId);
		}
		catch( Exception e )
		{
			throw e;
		}
		return res;
	}

	@Override
	public List<EmployeeAttendance> fetchEmployeeAttendance( EmployeeAttendance employeeAttendance ) throws Exception
	{
		if( employeeAttendance == null || employeeAttendance.getMonth() == null
				|| employeeAttendance.getMonth().isEmpty() || employeeAttendance.getEmailId() == null
				|| employeeAttendance.getEmailId() == null || employeeAttendance.getYear() == null )
		{
			throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
					+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
		}
		return fetchEmployeeAttendance(employeeAttendance.getEmailId(), employeeAttendance.getDay(),
				employeeAttendance.getMonth(), employeeAttendance.getYear());
	}

}
