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
import com.tool.management.interfaces.ProjectServices;
import com.tool.management.pojo.Project;

@Path( "/project" )
@RestController
public class ProjectRestServices {

	@Autowired
	private ProjectServices projServices;

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/add-project" )
	public TransactionResponse addProject( Project projInfo ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("organisation info not saved");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			projInfo.setCreatedAt(System.currentTimeMillis());
			Project save = projServices.addProject(projInfo);

			if( save != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Organisation info saved successfully");
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
	@Path( "/add-emp-to-project/{orgName}/{projectName}/{emailId}/{zipCode}" )
	public TransactionResponse addEmpToProject(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "projectName" ) String projectName,
			@NotNull( message = "Cannot be null" ) @PathParam( "emailId" ) String emailId,
			@NotNull( message = "Cannot be null" ) @PathParam( "zipCode" ) String zipCode ) throws Exception
	{
		Project projInfo;
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Adding employee to project Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			projInfo = projServices.addEmployeesToProject(orgName, projectName, emailId, zipCode);

			if( projInfo != null )
			{
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Successful");
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
	@Path( "/fetch-all-projects/{orgName}/{zipCode}/{emailId}" )
	public TransactionResponse fetchAllProjects(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "zipCode" ) String zipCode,
			@NotNull( message = "Cannot be null" ) @PathParam( "emailId" ) String emailId ) throws Exception
	{
		List<Project> projInfo;
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			projInfo = projServices.fetchAllProjects(orgName, zipCode, emailId);

			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
			transactionResponse.setResponseMessage("Successful");
			transactionResponse.setResponseEntity(projInfo);
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
	@Path( "/remove-emp-from-project/{orgName}/{projectName}/{emailId}/{zipCode}" )
	public TransactionResponse deleteEmpFromProject(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "projectName" ) String projectName,
			@NotNull( message = "Cannot be null" ) @PathParam( "emailId" ) String emailId,
			@NotNull( message = "Cannot be null" ) @PathParam( "zipCode" ) String zipCode ) throws Exception
	{
		Project projInfo;
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Adding employee to project Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			projInfo = projServices.deleteEmployeesFromProject(orgName, projectName, emailId, zipCode);

			if( projInfo != null )
			{
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Successful");
				transactionResponse.setResponseEntity(projInfo);
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
