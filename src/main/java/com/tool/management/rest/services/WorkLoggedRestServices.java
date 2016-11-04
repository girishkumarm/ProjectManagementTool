package com.tool.management.rest.services;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.tool.management.generic.Constants;
import com.tool.management.generic.LogGraphData;
import com.tool.management.generic.Point;
import com.tool.management.generic.TransactionResponse;
import com.tool.management.interfaces.SprintServices;
import com.tool.management.interfaces.WorkLoggedServices;
import com.tool.management.pojo.Sprint;
import com.tool.management.pojo.WorkLogged;

@Path( "/work-log" )
@RestController
public class WorkLoggedRestServices {

	@Autowired
	private WorkLoggedServices workLogServices;

	@Autowired
	private SprintServices sprintServices;

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path( "/fetch-logged-work-of-tasks/{orgName}/{projectName}/{zipCode}/{sprintName}" )
	public TransactionResponse fetchSprintInfoByOrgNameAndProjectName(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "projectName" ) String projectName,
			@NotNull( message = "Cannot be null" ) @PathParam( "zipCode" ) String zipCode,
			@NotNull( message = "Cannot be null" ) @PathParam( "sprintName" ) String sprintName ) throws Exception
	{
		List<WorkLogged> tasksInfo;
		LogGraphData log = new LogGraphData();
		Set<Point> idealSprintData = new TreeSet<Point>();
		Set<Point> loggedTimeInSprint = new TreeSet<Point>();
		Set<Point> remainingTimeInSprint = new TreeSet<Point>();

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Adding employee to project Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			tasksInfo = workLogServices.fetchLogWork(orgName, projectName, zipCode, sprintName);

			Sprint info = sprintServices.getSprintInfo(orgName, projectName, zipCode, sprintName);
			int perDayWork = (int) (info.getTotalHours() / info.getWorkingDays());

			int loggedWork = 0;
			Long remainingWork = info.getTotalHours();

			Point logged = new Point();
			logged.setX(new Double(info.getStartDate()));
			logged.setY(loggedWork);
			loggedTimeInSprint.add(logged);

			Point remaining = new Point();
			remaining.setX(new Double(info.getStartDate()));
			remaining.setY(remainingWork);
			remainingTimeInSprint.add(remaining);

			if( tasksInfo != null && !tasksInfo.isEmpty() )
			{
				for( WorkLogged workLog : tasksInfo )
				{
					Point logg = new Point();
					logg.setX(new Double(workLog.getLoggedTime()));
					loggedWork = loggedWork + workLog.getLoggedHours();
					logg.setY(loggedWork);
					loggedTimeInSprint.add(logg);

					Point rem = new Point();
					rem.setX(new Double(workLog.getLoggedTime()));
					remainingWork = remainingWork - workLog.getLoggedHours();
					rem.setY(remainingWork);
					remainingTimeInSprint.add(rem);
				}

			}

			info.setTotalHours(info.getTotalHours() + perDayWork);
			@SuppressWarnings( "unchecked" )
			SortedSet<Double> workDays = (SortedSet<Double>) new Gson().fromJson(info.getWorkingDates(),
					SortedSet.class);

			for( Double workDay : workDays )
			{
				Point p = new Point();
				p.setX(workDay);
				p.setY(info.getTotalHours() - perDayWork);
				info.setTotalHours(info.getTotalHours() - perDayWork);
				idealSprintData.add(p);
			}

			@SuppressWarnings( "unchecked" )
			SortedSet<Double> holidays = (SortedSet<Double>) new Gson().fromJson(info.getHolidays(), SortedSet.class);
			for( Double holiday : holidays )
			{
				Point p = new Point();
				p.setX(holiday);
				for( Point pp : idealSprintData )
				{
					if( pp.getX().equals(p.getX() - 86400000) )
					{
						p.setY(pp.getY());
					}
				}

				idealSprintData.add(p);
			}

			log.setIdealSprintData(idealSprintData);
			log.setLoggedTimeInSprint(loggedTimeInSprint);
			log.setRemainingTimeInSprint(remainingTimeInSprint);

			if( tasksInfo != null )
			{
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Successful");
				transactionResponse.setResponseEntity(log);
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
