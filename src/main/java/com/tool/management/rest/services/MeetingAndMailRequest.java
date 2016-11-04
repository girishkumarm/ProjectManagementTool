package com.tool.management.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.tool.management.generic.Constants;
import com.tool.management.generic.MeetingRequestPojo;
import com.tool.management.generic.TransactionResponse;
import com.tool.management.utils.MeetingRequest;
import com.tool.management.utils.SendEmail;

@Path( "/meeting-email" )
@RestController
public class MeetingAndMailRequest {

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/meeting" )
	public TransactionResponse sendMeetingRequest( MeetingRequestPojo request ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseType("NULL");
		try
		{
			System.out.println(new Gson().toJson(request));
			// call update or add team info service
			Boolean save = MeetingRequest.send(request);

			if( save )
			{
				// set the response object
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
	@Path( "/email" )
	public TransactionResponse sendEmailRequest( MeetingRequestPojo request ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			String rec = "";

			for( String r : request.getReceipent().split(",") )
			{
				if( rec == "" )
				{
					rec = r;
				}
				else
					rec = rec + "," + r;

			}
			Boolean save = SendEmail.getInstance().sendEmail("", request.getDescription(), "girishkumarm710@gmail.com",
					"yuvanaish123", rec, request.getSubject());
			;

			if( save )
			{
				// set the response object
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
}
