package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.pojo.Tasks;

public interface TasksServices {

	public Tasks addTask( Tasks task ) throws Exception;

	public Tasks fetchTask( String orgName, String taskId ) throws Exception;

	public Tasks updateTasks( Tasks task ) throws Exception;

	public List<Tasks> fetchTasks( String orgName, String projectName, String sprintName, String zipCode )
			throws Exception;

	public List<Tasks> fetchTasksOfUser( String assignee ) throws Exception;

	public List<Tasks> fetchTasksOfUserInSprint( String orgName, String projectName, String assignee,
			String sprintName ) throws Exception;

	public List<Tasks> fetchTasksOfProject( String orgName, String projectName, String zipCode ) throws Exception;

}
