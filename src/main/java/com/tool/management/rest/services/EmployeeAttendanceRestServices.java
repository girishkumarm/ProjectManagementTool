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
import com.tool.management.interfaces.EmployeeAttendanceServices;
import com.tool.management.pojo.EmployeeAttendance;

@Path( "/emp-attendance" )
@RestController
public class EmployeeAttendanceRestServices {

	@Autowired
	private EmployeeAttendanceServices employeeAttendanceServices;

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/add-emp-attendance" )
	public TransactionResponse addEmployeeAttendance( EmployeeAttendance employeeAttendance ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
		transactionResponse.setResponseMessage("Successful");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			transactionResponse
					.setResponseEntity(employeeAttendanceServices.takeEmployeeAttendance(employeeAttendance));

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
	@Path( "/update-emp-attendance" )
	public TransactionResponse updateEmployeeAttendance( EmployeeAttendance employeeAttendance ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
		transactionResponse.setResponseMessage("Successful");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			employeeAttendanceServices.updateEmployeeAttendance(employeeAttendance);

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
	@Path( "/fetch-emp-attendance" )
	public TransactionResponse fetchEmployeeAttendance( EmployeeAttendance employeeAttendance ) throws Exception
	{
		List<EmployeeAttendance> eg;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Successful");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			eg = employeeAttendanceServices.fetchEmployeeAttendance(employeeAttendance);

			if( eg != null )
			{
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Fetch Successful");
				transactionResponse.setResponseType("LIST");
				transactionResponse.setResponseEntity(eg);
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
		List<EmployeeAttendance> list;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			list = employeeAttendanceServices.fetchEmployeeAttendance(emailId);

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
