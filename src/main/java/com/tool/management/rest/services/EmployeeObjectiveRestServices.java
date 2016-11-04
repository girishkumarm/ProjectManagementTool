package com.tool.management.rest.services;

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
import com.tool.management.interfaces.EmployeeObjectiveServices;
import com.tool.management.pojo.EmployeeObjective;

@Path( "/emp-objective" )
@RestController
public class EmployeeObjectiveRestServices {

	@Autowired
	private EmployeeObjectiveServices empObjectiveServices;

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/add-emp-objective" )
	public TransactionResponse addEmployeeObjective( EmployeeObjective employeeObjective ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("employeeObjective info not saved");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			EmployeeObjective save = empObjectiveServices.addEmployeeObjective(employeeObjective);

			if( save != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("employeeObjective saved successfully");
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
	@Path( "/update-emp-objective" )
	public TransactionResponse updateEmployeeObjective( EmployeeObjective employeeObjective ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("employeeObjective info not saved");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			EmployeeObjective save = empObjectiveServices.updateEmployeeObjective(employeeObjective);

			if( save != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("employeeObjective saved successfully");
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
	@Path( "/fetch-emp-objective/{id}" )
	public TransactionResponse fetchEmployeeInfo( @NotNull( message = "Cannot be null" ) @PathParam( "id" ) String id )
			throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Activity info not fetched");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			EmployeeObjective save = empObjectiveServices.fetchEmployeeObjective(Integer.parseInt(id));

			if( save != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Activity fetched successfully");
				transactionResponse.setResponseEntity(save);
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
	@Path( "/fetch-emp-objective-list/{employeeId}" )
	public TransactionResponse fetchEmployeeObjectList(
			@NotNull( message = "Cannot be null" ) @PathParam( "employeeId" ) String employeeId ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Activity info not fetched");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			EmployeeObjective save = empObjectiveServices.fetchEmployeeObjective(Integer.parseInt(employeeId));

			if( save != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Activity fetched successfully");
				transactionResponse.setResponseEntity(save);
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
