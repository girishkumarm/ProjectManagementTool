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
import com.tool.management.interfaces.SprintServices;
import com.tool.management.pojo.Sprint;

@Path( "/sprint" )
@RestController
public class SprintRestServices {

	@Autowired
	private SprintServices sprintServices;

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/add-sprint" )
	public TransactionResponse addSprint( Sprint sprintInfo ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Save Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			Sprint save = sprintServices.addSprint(sprintInfo);

			if( save != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("saved successfully");
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
	@Path( "/fetch-sprint-info-by-orgname-projectName/{orgName}/{projectName}/{zipCode}" )
	public TransactionResponse fetchSprintInfoByOrgNameAndProjectName(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "projectName" ) String projectName,
			@NotNull( message = "Cannot be null" ) @PathParam( "zipCode" ) String zipCode ) throws Exception
	{
		List<Sprint> sprintInfo;
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Adding employee to project Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			sprintInfo = sprintServices.fetchAllSprint(orgName, projectName, zipCode);

			if( sprintInfo != null )
			{
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Successful");
				transactionResponse.setResponseEntity(sprintInfo);
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
	@Path( "/delete-sprint" )
	public TransactionResponse deleteSprint( Sprint sprintInfo ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("delete Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			Boolean save = sprintServices.deleteSprint(sprintInfo);

			if( save )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("delete successfully");
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
