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
import com.tool.management.interfaces.TasksServices;
import com.tool.management.pojo.Tasks;

@Path( "/tasks" )
@RestController
public class TasksRestServices {

	@Autowired
	private TasksServices taskServices;

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/add-task" )
	public TransactionResponse addTask( Tasks tasksInfo ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("task info not saved");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			Tasks save = taskServices.addTask(tasksInfo);

			if( save != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("task info saved successfully");
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
	@Path( "/update-task" )
	public TransactionResponse updateTask( Tasks tasksInfo ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("task info not updated");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			Tasks save = taskServices.updateTasks(tasksInfo);

			if( save != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("task info saved successfully");
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
	@Path( "/fetch-tasks-by-sprint-name/{orgName}/{projectName}/{sprintName}/{zipCode}" )
	public TransactionResponse fetchTasksBySprintName(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "projectName" ) String projectName,
			@NotNull( message = "Cannot be null" ) @PathParam( "zipCode" ) String zipCode,
			@NotNull( message = "Cannot be null" ) @PathParam( "sprintName" ) String sprintName ) throws Exception
	{
		List<Tasks> tasks;
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			tasks = taskServices.fetchTasks(orgName, projectName, sprintName, zipCode);

			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
			transactionResponse.setResponseMessage("Fetch Successful");
			transactionResponse.setResponseEntity(tasks);
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
	@Path( "/fetch-tasks-of-user/{userName}" )
	public TransactionResponse fetchTasksByUserName(
			@NotNull( message = "Cannot be null" ) @PathParam( "userName" ) String userName ) throws Exception
	{
		List<Tasks> tasks;
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			tasks = taskServices.fetchTasksOfUser(userName);

			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
			transactionResponse.setResponseMessage("Fetch Successful");
			transactionResponse.setResponseEntity(tasks);
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
	@Path( "/fetch-tasks-of-user-in-sprint/{orgName}/{projectName}/{userName}/{sprintName}" )
	public TransactionResponse fetchTasksByUserNameInSprint(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "projectName" ) String projectName,
			@NotNull( message = "Cannot be null" ) @PathParam( "userName" ) String userName,
			@NotNull( message = "Cannot be null" ) @PathParam( "sprintName" ) String sprintName ) throws Exception
	{
		List<Tasks> tasks;
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			tasks = taskServices.fetchTasksOfUserInSprint(orgName, projectName, userName, sprintName);

			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
			transactionResponse.setResponseMessage("Fetch Successful");
			transactionResponse.setResponseEntity(tasks);
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
	@Path( "/fetch-tasks-of-project/{orgName}/{projectName}/{zipCode}" )
	public TransactionResponse fetchTasksByProjectName(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "projectName" ) String projectName,
			@NotNull( message = "Cannot be null" ) @PathParam( "zipCode" ) String zipCode ) throws Exception
	{
		List<Tasks> tasks;
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			tasks = taskServices.fetchTasksOfProject(orgName, projectName, zipCode);

			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
			transactionResponse.setResponseMessage("Fetch Successful");
			transactionResponse.setResponseEntity(tasks);
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
	@Path( "/fetch-task/{orgName}/{taskId}" )
	public TransactionResponse fetchTaskById(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "taskId" ) String taskId ) throws Exception
	{
		Tasks tasks;
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			tasks = taskServices.fetchTask(orgName, taskId);

			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
			transactionResponse.setResponseMessage("Fetch Successful");
			transactionResponse.setResponseEntity(tasks);
		}
		catch( Exception e )
		{
			e.printStackTrace();
			throw e;
		}
		return transactionResponse;
	}
}
