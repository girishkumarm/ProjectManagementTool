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
import com.tool.management.interfaces.EmployeeGrievanceServices;
import com.tool.management.pojo.EmpGrievance;

@Path( "/emp-grievance" )
@RestController
public class EmpGrievanceRestServices {

	@Autowired
	private EmployeeGrievanceServices employeeGrievanceServices;

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/add-holiday-request" )
	public TransactionResponse addHolidayRequest( EmpGrievance empGrievance ) throws Exception
	{
		EmpGrievance eg;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.NOT_FOUND.getConstant());
		transactionResponse.setResponseMessage("Successful");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			eg = employeeGrievanceServices.addEmpGrievance(empGrievance);

			if( eg != null )
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
		List<EmpGrievance> list;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			list = employeeGrievanceServices.fetchEmpGrievance(emailId);

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
	@Path( "/fetch-all/{orgName}/{zipcode}" )
	public TransactionResponse fetchAllHolidayRequest(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "zipcode" ) String zipcode ) throws Exception
	{
		List<EmpGrievance> list;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			list = employeeGrievanceServices.fetchAllEmpGrievance(orgName, zipcode);

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
