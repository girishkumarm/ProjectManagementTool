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
import com.tool.management.interfaces.ActivityStreamServices;
import com.tool.management.pojo.ActivityStream;

@Path( "/activity" )
@RestController
public class ActivityStreamRestServices {

	@Autowired
	private ActivityStreamServices activityStreamServices;

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/add-activity" )
	public TransactionResponse addActivityStream( ActivityStream activity ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Activity info not saved");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			ActivityStream save = activityStreamServices.addActivity(activity);

			if( save != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Activity saved successfully");
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
	@Path( "/fetch-activity/{id}" )
	public TransactionResponse fetchEmployeeInfo(
			@NotNull( message = "Cannot be null" ) @PathParam( "id" ) String id ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Activity info not fetched");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			ActivityStream save = activityStreamServices.fetchActivity(Integer.parseInt(id));

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

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/fetch-activity-list" )
	public TransactionResponse fetchActivityList( ActivityStream activity ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Activity info not fetched");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			List<ActivityStream> save = activityStreamServices.fetchActivityList(activity);

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
