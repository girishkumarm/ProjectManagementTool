package com.tool.management.impl;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;
import com.tool.management.dao.HolidayRequestDao;
import com.tool.management.entities.HolidayInfoRequest;
import com.tool.management.generic.Constants;
import com.tool.management.interfaces.HolidayRequestServices;
import com.tool.management.interfaces.NotificationServices;
import com.tool.management.pojo.HolidayInfo;
import com.tool.management.pojo.HolidayRequest;
import com.tool.management.pojo.Notification;
import com.tool.management.rest.services.HolidayInfoRestServices;

@Service
public class HolidayRequestServicesImpl extends HolidayRequestDao implements HolidayRequestServices {

	@Autowired
	private NotificationServices notificationServices;

	@Autowired
	private HolidayInfoRestServices holidayInfoRestServices;

	public HolidayRequestServicesImpl( Class<HolidayRequest> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public HolidayRequestServicesImpl( EntityManager em )
	{
		super(HolidayRequest.class, em);
	}

	@Override
	@Transactional( readOnly = false )
	public HolidayRequest addHolidayRequest( HolidayRequest holidayRequest ) throws Exception
	{
		HolidayRequest res = null;
		try
		{
			if( holidayRequest == null || holidayRequest.getDescription() == null
					|| holidayRequest.getDescription().isEmpty() || holidayRequest.getEmployeeId() == null
					|| holidayRequest.getDates() == null || holidayRequest.getDates().isEmpty()
					|| holidayRequest.getStatus() == null || holidayRequest.getStatus().isEmpty()
					|| holidayRequest.getTeamLead() == null || holidayRequest.getTotalDays() == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			res = save(holidayRequest);

			Notification notification = new Notification();
			notification.setEmployeeId(res.getTeamLead());
			notification.setType("HolidayRequest");
			notification.setTypeId(res.getId());
			notification.setDescription(res.getEmployeeName() + " has Holiday Request");
			notificationServices.addNotification(notification);

			System.out.println(holidayRequest.getEmailId());
			System.out.println(Calendar.getInstance().get(Calendar.YEAR));

			//update it in the holiday info of an employee
			HolidayInfo hInfo = (HolidayInfo) holidayInfoRestServices
					.fetchAllHolidayInfo(holidayRequest.getEmailId(), Calendar.getInstance().get(Calendar.YEAR) + "")
					.getResponseEntity();

			System.out.println(new Gson().toJson(hInfo));

			if( holidayRequest.getSick() != 0 )
			{
				hInfo.setSick(hInfo.getSick() - holidayRequest.getSick());
			}
			if( holidayRequest.getAnnual() != 0 )
			{
				hInfo.setAnnual(hInfo.getAnnual() - holidayRequest.getAnnual());
			}
			holidayInfoRestServices.updateHolidayInfoForAnEmployee(hInfo);
			/* if( holidayRequest.getOthers() != 0 ) {
			 * hInfo.setOthers(hInfo.get-holidayRequest.getOthers()); } */
		}
		catch( Exception e )
		{
			throw e;
		}
		return res;
	}

	@Override
	@Transactional( readOnly = false )
	public HolidayRequest updateHolidayRequest( HolidayRequest holidayRequest ) throws Exception
	{
		HolidayRequest res = null;
		try
		{
			if( holidayRequest == null || holidayRequest.getDescription() == null
					|| holidayRequest.getDescription().isEmpty() || holidayRequest.getEmployeeId() == null
					|| holidayRequest.getDates() == null || holidayRequest.getDates().isEmpty()
					|| holidayRequest.getStatus() == null || holidayRequest.getStatus().isEmpty()
					|| holidayRequest.getTeamLead() == null || holidayRequest.getTotalDays() == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			res = save(holidayRequest);

			Notification notification = new Notification();
			notification.setEmployeeId(res.getEmployeeId());
			notification.setType("HolidayRequest");
			notification.setTypeId(res.getId());
			if( holidayRequest.getStatus().equals("Accepted") )
			{
				notification.setDescription("You holiday request has been granted");
			}
			else if( holidayRequest.getStatus().equals("Rejected") )
			{
				notification.setDescription("Your Holiday Request has been rejected");
			}
			notificationServices.addNotification(notification);
		}
		catch( Exception e )
		{
			throw e;
		}
		return res;
	}

	@Override
	public List<HolidayRequest> fetchHolidayRequest( String emailId ) throws Exception
	{
		List<HolidayRequest> res = null;
		try
		{
			if( emailId == null || emailId.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}
			res = fetchByEmailId(emailId);
		}
		catch( Exception e )
		{
			throw e;
		}
		return res;
	}

	@Override
	public List<HolidayRequest> fetchAllHolidayRequest( String emailId, String year ) throws Exception
	{
		List<HolidayRequest> res = null;
		try
		{
			if( emailId == null || emailId.isEmpty() || year == null || year.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}
			res = fetchByEmailIdAndYear(emailId, year);
		}
		catch( Exception e )
		{
			throw e;
		}
		return res;
	}

}
