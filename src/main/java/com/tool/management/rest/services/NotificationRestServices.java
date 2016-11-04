package com.tool.management.rest.services;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.tool.management.generic.Constants;
import com.tool.management.generic.TransactionResponse;
import com.tool.management.interfaces.NotificationServices;
import com.tool.management.pojo.Notification;

@Path( "/notification" )
@RestController
public class NotificationRestServices {

	@Autowired
	private NotificationServices notificationServices;

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path( "/fetch-notification/{id}" )
	public TransactionResponse addEmpToProject(
			@NotNull( message = "Cannot be null" ) @PathParam( "id" ) String id ) throws Exception
	{
		List<Notification> projInfo;
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Adding employee to project Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			projInfo = notificationServices.fetchNotificationListOfEmployee(id);

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
