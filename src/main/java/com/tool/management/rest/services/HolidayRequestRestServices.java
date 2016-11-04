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
import com.tool.management.generic.Constants;
import com.tool.management.generic.TransactionResponse;
import com.tool.management.interfaces.HolidayRequestServices;
import com.tool.management.pojo.HolidayRequest;

@Path( "/holiday-request" )
@RestController
public class HolidayRequestRestServices {

	@Autowired
	private HolidayRequestServices holidayRequestServices;

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/add-holiday-request" )
	public TransactionResponse addHolidayRequest( HolidayRequest holidayRequest ) throws Exception
	{
		HolidayRequest hr;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.NOT_FOUND.getConstant());
		transactionResponse.setResponseMessage("Successful");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			hr = holidayRequestServices.addHolidayRequest(holidayRequest);

			if( hr != null )
			{
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
			}
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
	@Path( "/update-holiday-request" )
	public TransactionResponse updateHolidayRequest( HolidayRequest holidayRequest ) throws Exception
	{
		HolidayRequest hr;

		/*holidayRequest = new HolidayRequest();
		holidayRequest.setId(1);
		holidayRequest.setCreatedAt("2016");
		holidayRequest.setDescription("Hi nange leave kodo");
		holidayRequest.setTeamLeadComments("You have got only one day leave");
		holidayRequest.setEmailId("girish.kumarm710@gmail.com");
		holidayRequest.setEmployeeId(2);
		holidayRequest.setEmployeeName("Girish kumar");
		holidayRequest.setDates("10-12-2016");
		holidayRequest.setStatus("Accepted");
		holidayRequest.setTeamLead(1);
		holidayRequest.setTotalDays(1);
		holidayRequest.setAnnual(1);*/

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.NOT_FOUND.getConstant());
		transactionResponse.setResponseMessage("Successful");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			hr = holidayRequestServices.updateHolidayRequest(holidayRequest);

			if( hr != null )
			{
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
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
	@Path( "/fetch/{emailId}" )
	public TransactionResponse fetchEmployeeInfo(
			@NotNull( message = "Cannot be null" ) @PathParam( "emailId" ) String emailId ) throws Exception
	{
		List<HolidayRequest> list;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			list = holidayRequestServices.fetchHolidayRequest(emailId);

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
	@Path( "/fetch-all/{emailId}/{year}" )
	public TransactionResponse fetchAllHolidayRequest(
			@NotNull( message = "Cannot be null" ) @PathParam( "emailId" ) String emailId,
			@NotNull( message = "Cannot be null" ) @PathParam( "year" ) String year ) throws Exception
	{
		List<HolidayRequest> list;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			list = holidayRequestServices.fetchAllHolidayRequest(emailId, year);

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
}
