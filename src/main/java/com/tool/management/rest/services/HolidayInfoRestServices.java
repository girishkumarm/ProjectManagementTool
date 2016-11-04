package com.tool.management.rest.services;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.tool.management.entities.HolidayInfoRequest;
import com.tool.management.generic.Constants;
import com.tool.management.generic.TransactionResponse;
import com.tool.management.interfaces.HolidayInfoServices;
import com.tool.management.pojo.HolidayInfo;

@Path( "/holiday-info" )
@RestController
public class HolidayInfoRestServices {

	@Autowired
	private HolidayInfoServices holidayInfoServices;

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/add-holiday-info-for-organisation" )
	public TransactionResponse addHolidayInfoForAnOrganisation( HolidayInfoRequest holidayInfoRequest ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.NOT_FOUND.getConstant());
		transactionResponse.setResponseMessage("Successful");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call add service
			holidayInfoServices.addHolidayInfoForOrganisation(holidayInfoRequest);

			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
		}
		catch( Exception e )
		{
			e.printStackTrace();
			throw e;
		}
		return transactionResponse;
	}

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/update-holiday-info-for-organisation" )
	public TransactionResponse updateHolidayInfoForAnOrganisation( HolidayInfoRequest holidayInfoRequest )
			throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.NOT_FOUND.getConstant());
		transactionResponse.setResponseMessage("Successful");
		transactionResponse.setResponseType("NULL");
		try
		{
			
			holidayInfoRequest = new HolidayInfoRequest();
			holidayInfoRequest.setOrganisationName("wipro");
			holidayInfoRequest.setZipcode("560011");
			holidayInfoRequest.setAnnual(30);
			holidayInfoRequest.setSick(30);
			holidayInfoRequest.setYear(2016);
			holidayInfoRequest.setUnPaid(50);
			// call update service
			holidayInfoServices.updateHolidayInfoForOrganisation(holidayInfoRequest);

			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
		}
		catch( Exception e )
		{
			e.printStackTrace();
			throw e;
		}
		return transactionResponse;
	}

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/update-holiday-info-for-employee" )
	public TransactionResponse updateHolidayInfoForAnEmployee( HolidayInfo holidayInfo ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.NOT_FOUND.getConstant());
		transactionResponse.setResponseMessage("Successful");
		transactionResponse.setResponseType("NULL");
		try
		{
			holidayInfo = new HolidayInfo();
			holidayInfo.setEmailId("girishkumarm710@gmail.com");
			holidayInfo.setOthers(3);
			holidayInfo.setYear(2016);
			
			// call update service
			holidayInfoServices.updateHolidayInfoOfAnEmployee(holidayInfo);

			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
		}
		catch( Exception e )
		{
			e.printStackTrace();
			throw e;
		}
		return transactionResponse;
	}

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path( "/fetch-all/{emailId}" )
	public TransactionResponse fetchEmployeeHolidayInfo(
			@NotNull( message = "Cannot be null" ) @PathParam( "emailId" ) String emailId ) throws Exception
	{
		List<HolidayInfo> list;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			list = holidayInfoServices.fetchHolidayInfoOfAnEmployee(emailId);

			if( list != null && !list.isEmpty() )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Fetch Successful");
				transactionResponse.setResponseType("LIST");
				transactionResponse.setResponseEntity(list);
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
			throw e;
		}
		return transactionResponse;
	}

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path( "/fetch/{emailId}/{year}" )
	public TransactionResponse fetchAllHolidayInfo(
			@NotNull( message = "Cannot be null" ) @PathParam( "emailId" ) String emailId,
			@NotNull( message = "Cannot be null" ) @PathParam( "year" ) String year ) throws Exception
	{
		HolidayInfo object;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			object = holidayInfoServices.fetchAllHolidayInfoOfAnEmployee(emailId, year);

			if( object != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Fetch Successful");
				transactionResponse.setResponseType("Object");
				transactionResponse.setResponseEntity(object);
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
			throw e;
		}
		return transactionResponse;
	}
}
