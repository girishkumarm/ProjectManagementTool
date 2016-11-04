package com.tool.management.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.management.dao.HolidayInfoDao;
import com.tool.management.entities.HolidayInfoRequest;
import com.tool.management.generic.Constants;
import com.tool.management.interfaces.EmployeeServices;
import com.tool.management.interfaces.HolidayInfoServices;
import com.tool.management.interfaces.NotificationServices;
import com.tool.management.pojo.Employee;
import com.tool.management.pojo.HolidayInfo;
import com.tool.management.pojo.Notification;

@Service
public class HolidayInfoServicesImpl extends HolidayInfoDao implements HolidayInfoServices {

	@Autowired
	private NotificationServices notificationServices;

	@Autowired
	private EmployeeServices employeeServices;

	public HolidayInfoServicesImpl( Class<HolidayInfo> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public HolidayInfoServicesImpl( EntityManager em )
	{
		super(HolidayInfo.class, em);
	}

	@Override
	@Transactional( readOnly = false )
	public void addHolidayInfoForOrganisation( HolidayInfoRequest holidayInfoRequest ) throws Exception
	{
		try
		{
			if( holidayInfoRequest == null || holidayInfoRequest.getOrganisationName() == null
					|| holidayInfoRequest.getOrganisationName().isEmpty() || holidayInfoRequest.getZipcode() == null
					|| holidayInfoRequest.getZipcode().isEmpty() || holidayInfoRequest.getAnnual() == null
					|| holidayInfoRequest.getSick() == null || holidayInfoRequest.getUnPaid() == null
					|| holidayInfoRequest.getYear() == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			List<Employee> employees = employeeServices.fetchAllEmployees(holidayInfoRequest.getOrganisationName(),
					holidayInfoRequest.getZipcode());

			if( employees != null && !employees.isEmpty() )
			{
				for( Employee employee : employees )
				{
					HolidayInfo e = fetchByEmailIdAndYear(employee.getEmailId(), holidayInfoRequest.getYear());
					HolidayInfo holidayInfo = new HolidayInfo();
					holidayInfo.setEmployeeId(employee.getId());
					holidayInfo.setEmailId(employee.getEmailId());
					holidayInfo.setYear(holidayInfoRequest.getYear());
					holidayInfo.setAnnual(holidayInfoRequest.getAnnual());
					holidayInfo.setSick(holidayInfoRequest.getSick());
					holidayInfo.setUnPaidLeaves(holidayInfoRequest.getUnPaid());

					if( e != null )
					{
						holidayInfo.setId(e.getId());
					}

					Notification notification = new Notification();
					notification.setEmployeeId(employee.getId());
					notification.setType("HolidayInfo");
					notification.setTypeId(save(holidayInfo).getId());
					notification.setDescription(
							"Organisation Leaves has been out for the year " + holidayInfoRequest.getYear());
					notificationServices.addNotification(notification);
				}

			}
			else
			{
				throw new Exception(Constants.ExceptionItems.USER_NOT_FOUND.getConstant() + ","
						+ Constants.StatusCode.NOT_FOUND.getConstant());
			}

		}
		catch( Exception e )
		{
			throw e;
		}

	}

	@Override
	@Transactional( readOnly = false )
	public void updateHolidayInfoForOrganisation( HolidayInfoRequest holidayInfoRequest ) throws Exception
	{
		try
		{
			if( holidayInfoRequest == null || holidayInfoRequest.getOrganisationName() == null
					|| holidayInfoRequest.getOrganisationName().isEmpty() || holidayInfoRequest.getZipcode() == null
					|| holidayInfoRequest.getZipcode().isEmpty() || holidayInfoRequest.getAnnual() == null
					|| holidayInfoRequest.getSick() == null || holidayInfoRequest.getUnPaid() == null
					|| holidayInfoRequest.getYear() == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			List<Employee> employees = employeeServices.fetchAllEmployees(holidayInfoRequest.getOrganisationName(),
					holidayInfoRequest.getZipcode());

			if( employees != null && !employees.isEmpty() )
			{
				for( Employee employee : employees )
				{
					HolidayInfo holidayInfo = fetchByEmailIdAndYear(employee.getEmailId(),
							holidayInfoRequest.getYear());

					Notification notification = new Notification();
					notification.setEmployeeId(employee.getId());
					notification.setType("HolidayInfo");

					if( holidayInfo != null )
					{
						holidayInfo.setEmployeeId(employee.getId());
						holidayInfo.setEmailId(employee.getEmailId());
						holidayInfo.setYear(holidayInfoRequest.getYear());
						holidayInfo.setAnnual(holidayInfoRequest.getAnnual());
						holidayInfo.setSick(holidayInfoRequest.getSick());
						holidayInfo.setUnPaidLeaves(holidayInfoRequest.getUnPaid());
						notification.setDescription(
								"Organisation Leaves has been updated for the year " + holidayInfoRequest.getYear());
					}
					else
					{
						holidayInfo = new HolidayInfo();
						holidayInfo.setEmployeeId(employee.getId());
						holidayInfo.setEmailId(employee.getEmailId());
						holidayInfo.setYear(holidayInfoRequest.getYear());
						holidayInfo.setAnnual(holidayInfoRequest.getAnnual());
						holidayInfo.setSick(holidayInfoRequest.getSick());
						holidayInfo.setUnPaidLeaves(holidayInfoRequest.getUnPaid());
						notification.setDescription(
								"Organisation Leaves has been out for the year " + holidayInfoRequest.getYear());
					}

					notification.setTypeId(save(holidayInfo).getId());

					notificationServices.addNotification(notification);
				}

			}
			else
			{
				throw new Exception(Constants.ExceptionItems.USER_NOT_FOUND.getConstant() + ","
						+ Constants.StatusCode.NOT_FOUND.getConstant());
			}

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	@Override
	@Transactional( readOnly = false )
	public void updateHolidayInfoOfAnEmployee( HolidayInfo holidayInfo ) throws Exception
	{
		try
		{
			if( holidayInfo == null || holidayInfo.getEmailId() == null || holidayInfo.getEmailId().isEmpty()
					|| holidayInfo.getYear() == null || holidayInfo.getOthers() == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			HolidayInfo holidayInfoOld = fetchByEmailIdAndYear(holidayInfo.getEmailId(), holidayInfo.getYear());

			if( holidayInfoOld != null )
			{
				if( holidayInfoOld.getOthers() != null )
				{
					holidayInfoOld.setOthers(holidayInfo.getOthers() + holidayInfoOld.getOthers());
				}
				else
				{
					holidayInfoOld.setOthers(holidayInfo.getOthers());
				}

				Notification notification = new Notification();
				notification.setEmployeeId(holidayInfoOld.getEmployeeId());
				notification.setType("HolidayInfo");
				notification.setTypeId(save(holidayInfoOld).getId());
				notification.setDescription(holidayInfo.getOthers() + "Leaves added");

				notificationServices.addNotification(notification);
			}
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	@Override
	public List<HolidayInfo> fetchHolidayInfoOfAnEmployee( String emailId ) throws Exception
	{
		try
		{
			if( emailId == null || emailId.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return fetchByEmailId(emailId);

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	@Override
	public HolidayInfo fetchAllHolidayInfoOfAnEmployee( String emailId, String year ) throws Exception
	{
		try
		{
			if( emailId == null || emailId.isEmpty() || year == null || year.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}
			return fetchByEmailIdAndYear(emailId, Integer.parseInt(year));
		}
		catch( Exception e )
		{
			throw e;
		}
	}

}
