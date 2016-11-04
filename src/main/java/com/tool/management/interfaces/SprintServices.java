package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.pojo.Sprint;

public interface SprintServices {

	public Sprint addSprint( Sprint sprintInfo ) throws Exception;
	
	public void addTaskTime( Sprint sprintInfo , Integer time) throws Exception;

	public List<Sprint> fetchAllSprint( String orgName, String projectName, String zipCode ) throws Exception;

	public Sprint getSprintInfo( String orgName, String projectName, String zipCode, String sprintName )
			throws Exception;

	public Boolean deleteSprint( Sprint sprintInfo ) throws Exception;
}
