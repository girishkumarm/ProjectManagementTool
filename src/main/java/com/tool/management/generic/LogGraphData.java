package com.tool.management.generic;

import java.util.Set;

public class LogGraphData {

	private Set<Point> idealSprintData;
	private Set<Point> loggedTimeInSprint;
	private Set<Point> remainingTimeInSprint;

	public Set<Point> getIdealSprintData()
	{
		return idealSprintData;
	}

	public void setIdealSprintData( Set<Point> idealSprintData )
	{
		this.idealSprintData = idealSprintData;
	}

	public Set<Point> getLoggedTimeInSprint()
	{
		return loggedTimeInSprint;
	}

	public void setLoggedTimeInSprint( Set<Point> loggedTimeInSprint )
	{
		this.loggedTimeInSprint = loggedTimeInSprint;
	}

	public Set<Point> getRemainingTimeInSprint()
	{
		return remainingTimeInSprint;
	}

	public void setRemainingTimeInSprint( Set<Point> remainingTimeInSprint )
	{
		this.remainingTimeInSprint = remainingTimeInSprint;
	}

}

