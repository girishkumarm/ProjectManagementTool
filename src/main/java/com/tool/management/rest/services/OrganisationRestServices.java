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
import com.tool.management.interfaces.EmployeeServices;
import com.tool.management.interfaces.OrganisationServices;
import com.tool.management.pojo.Employee;
import com.tool.management.pojo.Organisation;

@Path( "/organisation" )
@RestController
public class OrganisationRestServices {

	@Autowired
	private OrganisationServices orgServices;

	@Autowired
	private EmployeeServices employeeServices;

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/add-organisation" )
	public TransactionResponse addOrganisation( Organisation OrgInfo ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("saved Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			Organisation save = orgServices.addOrganisation(OrgInfo);

			Employee e = new Employee();
			e.setEmailId(OrgInfo.getEmailId());
			e.setEmployeeType("ROOT");
			e.setTeamLead((long) 0);
			e.setName(OrgInfo.getUserName());
			e.setOrganisation(OrgInfo.getOrganisationName());
			e.setPassword(OrgInfo.getPassword());
			e.setZipCode(OrgInfo.getAddress());
			employeeServices.addEmployee(e);

			if( save != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Save successful");
				transactionResponse.setResponseEntity(e);
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
	@Path( "/update-organisation" )
	public TransactionResponse updateOrganisation( Organisation OrgInfo ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Update Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			Organisation save = orgServices.updateOrganisation(OrgInfo);

			if( save != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Update successful");
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
	@Path( "/activate-organisation/{orgName}/{zipCode}" )
	public void fetchSprintInfoByOrgNameAndProjectName(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "zipCode" ) String zipCode ) throws Exception
	{
		try
		{
			orgServices.activateOrganisation(orgName, zipCode);
		}
		catch( Exception e )
		{
			e.printStackTrace();
			throw e;
		}
	}
}
