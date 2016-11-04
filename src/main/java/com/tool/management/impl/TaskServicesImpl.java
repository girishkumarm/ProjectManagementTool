package com.tool.management.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.management.dao.TasksDao;
import com.tool.management.generic.Constants;
import com.tool.management.interfaces.ActivityStreamServices;
import com.tool.management.interfaces.EmployeeServices;
import com.tool.management.interfaces.ProjectServices;
import com.tool.management.interfaces.SprintServices;
import com.tool.management.interfaces.TasksServices;
import com.tool.management.interfaces.WorkLoggedServices;
import com.tool.management.pojo.ActivityStream;
import com.tool.management.pojo.Employee;
import com.tool.management.pojo.Sprint;
import com.tool.management.pojo.Tasks;
import com.tool.management.pojo.WorkLogged;

@Service
public class TaskServicesImpl extends TasksDao implements TasksServices {

	@Autowired
	private SprintServices sprintServices;

	@Autowired
	private ActivityStreamServices activityStreamServices;

	@Autowired
	private ProjectServices projServices;

	@Autowired
	private EmployeeServices employeeServices;

	@Autowired
	private WorkLoggedServices workLoggedServices;

	public TaskServicesImpl( Class<Tasks> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public TaskServicesImpl( EntityManager em )
	{
		super(Tasks.class, em);
	}

	@Transactional( readOnly = false )
	public Tasks addTask( Tasks task ) throws Exception
	{
		try
		{
			if( task == null || task.getEstimatedTime() == null || task.getEstimatedTime() == 0
					|| task.getOrgName() == null || task.getOrgName().isEmpty() || task.getProjectName() == null
					|| task.getProjectName().isEmpty() || task.getSprintName() == null || task.getSprintName().isEmpty()
					|| task.getTitle() == null || task.getTitle().isEmpty() || task.getType() == null
					|| task.getType().isEmpty() || task.getAssignee() == null || task.getAssignee().isEmpty()
					|| task.getZipCode() == null || task.getZipCode().isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			if( projServices.getProjectInfo(task.getOrgName(), task.getProjectName(), task.getZipCode()) != null )
			{

				if( sprintServices.getSprintInfo(task.getOrgName(), task.getProjectName(), task.getZipCode(),
						task.getSprintName()) != null )
				{
					//check the team lead exist or not
					Employee empl = employeeServices.fetchEmployee(task.getOrgName(), task.getZipCode(),
							task.getAssignee());
					if( empl != null )
					{
						Tasks updateTask = null;
						if( (updateTask = fetchById(task.getId())) == null )
						{

							/* SendEmail.getInstance().sendEmail("", "The task with the title : " +
							 * task.getTitle() + " has been assigned to you",
							 * "girishkumarm710@gmail.com", "mkumargirish", task.getAssignee(),
							 * "Task Assigned"); */
							task.setRemainingTime(task.getEstimatedTime());
							task.setTotalTimeTaken(0);
							task.setStatus("Not Accepted");
							task.setTaskId(task.getProjectName() + "-"
									+ fetchTaskCountInAProject(task.getOrgName(), task.getProjectName()));
							task.setCreatedAt(System.currentTimeMillis());
							task.setUpdatedAt(System.currentTimeMillis());

							//add task time in to the sprint
							Sprint sprintInfo = new Sprint();
							sprintInfo.setOrgName(task.getOrgName());
							sprintInfo.setProjName(task.getProjectName());
							sprintInfo.setZipCode(task.getZipCode());
							sprintInfo.setSprintName(task.getSprintName());

							Integer time = task.getEstimatedTime();
							sprintServices.addTaskTime(sprintInfo, time);

							//save this activity into the database
							addTaskActivityStream(task, Constants.ActivityStream.ADDED.toString());

							//save task
							return save(task);
						}
						else
						{
							updateTask.setEstimatedTime(task.getEstimatedTime());
							updateTask.setType(task.getType());
							updateTask.setTitle(task.getTitle());
							updateTask.setDescription(task.getDescription());
							updateTask.setSprintName(task.getSprintName());

							//save this activity into the database
							addTaskActivityStream(task, "update");

							return save(updateTask);
						}
					}
					else
					{
						throw new Exception(Constants.ExceptionItems.USER_NOT_FOUND.getConstant() + ","
								+ Constants.StatusCode.NOT_FOUND.getConstant());
					}
				}
				else
				{
					throw new Exception(Constants.ExceptionItems.SPRINT_NOT_FOUND.getConstant() + ","
							+ Constants.StatusCode.NOT_FOUND.getConstant());
				}
			}
			else
			{
				throw new Exception(Constants.ExceptionItems.PROJECT_NOT_FOUND.getConstant() + ","
						+ Constants.StatusCode.NOT_FOUND.getConstant());
			}

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	private void addTaskActivityStream( Tasks task, String taskType ) throws Exception
	{
		ActivityStream activityStream = new ActivityStream();
		try
		{
			activityStream.setOrgName(task.getOrgName());
			activityStream.setProjectName(task.getProjectName());
			activityStream.setTaskName(task.getTaskId());
			activityStream.setZipCode(task.getZipCode());
			activityStream.setActivityType(taskType);
			activityStream.setCreatedAt(System.currentTimeMillis() + "");

			if( Constants.ActivityStream.ADDED.toString().equals(taskType) )
			{
				activityStream.setDescription(task.getCreatedBy() + " created issue");
			}
			else if( Constants.ActivityStream.LOGGED_WORK.toString().equals(taskType) )
			{
				activityStream.setDescription(task.getAssignee() + " has logged work of " + task.getTotalTimeTaken()
						+ " hours on " + new Date(System.currentTimeMillis()).toString());
			}
			else if( Constants.ActivityStream.ASSIGNED.toString().equals(taskType) )
			{
				activityStream.setDescription("Task has been re-assigned to " + task.getAssignee());
			}
			else if( Constants.ActivityStream.STATUS.toString().equals(taskType) )
			{
				activityStream
						.setDescription(task.getAssignee() + " has changed the status of issue to " + task.getStatus());
			}
			else if( Constants.ActivityStream.DESCRIPTION.toString().equals(taskType) )
			{
				activityStream.setDescription(task.getAssignee() + " has changed the description of the task");
			}

			//save activity stream
			activityStreamServices.addActivity(activityStream);

		}
		catch( Exception e )
		{
			throw e;
		}

	}

	@Transactional( readOnly = false )
	public Tasks updateTasks( Tasks task ) throws Exception
	{
		try
		{
			if( task == null || task.getId() == null || task.getId() == 0 )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			Tasks dbTask = fetchTaskByTaskId(task.getId() + "");

			if( dbTask != null )
			{
				//update description of the task
				if( task.getDescription() != null && !task.getDescription().trim().isEmpty()
						&& !task.getDescription().equals(dbTask.getDescription()) )
				{
					//save this activity into the database
					addTaskActivityStream(task, Constants.ActivityStream.DESCRIPTION.toString());

					dbTask.setDescription(task.getDescription());
				}

				//update the assignee of the task
				if( task.getAssignee() != null && !task.getAssignee().trim().isEmpty()
						&& !task.getAssignee().equals(dbTask.getAssignee()) )
				{
					//save this activity into the database
					addTaskActivityStream(task, Constants.ActivityStream.ASSIGNED.toString());

					dbTask.setDescription(task.getDescription());
				}

				//update status of the task
				if( task.getStatus() != null && !task.getStatus().trim().isEmpty()
						&& !task.getStatus().equals(dbTask.getStatus()) )
				{
					//save this activity into the database
					addTaskActivityStream(task, Constants.ActivityStream.STATUS.toString());

					dbTask.setStatus(task.getStatus());
				}

				//update total time taken in a task
				if( task.getTotalTimeTaken() != null && task.getTotalTimeTaken() != 0 )
				{
					//save this activity into the database
					addTaskActivityStream(task, Constants.ActivityStream.LOGGED_WORK.toString());

					dbTask.setTotalTimeTaken(task.getTotalTimeTaken() + dbTask.getTotalTimeTaken());
					if( dbTask.getEstimatedTime() - dbTask.getTotalTimeTaken() > 0 )
					{
						dbTask.setRemainingTime(dbTask.getEstimatedTime() - dbTask.getTotalTimeTaken());
					}
					else
					{
						dbTask.setRemainingTime(0);
					}
					WorkLogged logWork = new WorkLogged();
					logWork.setOrgName(dbTask.getOrgName());
					logWork.setProjectName(dbTask.getProjectName());
					logWork.setZipCode(dbTask.getZipCode());
					logWork.setLoggedHours(task.getTotalTimeTaken());
					logWork.setLoggedTime(System.currentTimeMillis());
					logWork.setRemainingTime(dbTask.getRemainingTime());
					logWork.setTaskId(dbTask.getId());
					logWork.setTaskName(dbTask.getTaskId());
					logWork.setSprintName(dbTask.getSprintName());

					workLoggedServices.logWork(logWork);
				}
			}
			else
			{
				throw new Exception(Constants.ExceptionItems.TASK_NOT_FOUND.getConstant() + ","
						+ Constants.StatusCode.NOT_FOUND.getConstant());
			}

			dbTask.setUpdatedAt(System.currentTimeMillis());
			return save(dbTask);

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	public List<Tasks> fetchTasks( String orgName, String projectName, String sprintName, String zipCode )
			throws Exception
	{
		try
		{
			if( orgName == null || orgName.isEmpty() || projectName == null || projectName.isEmpty()
					|| sprintName == null || sprintName.isEmpty() || zipCode == null || zipCode.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}
			return fetchTaskOfSprint(orgName, projectName, sprintName, zipCode);

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	public List<Tasks> fetchTasksOfUser( String assignee ) throws Exception
	{
		try
		{
			if( assignee == null || assignee.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return fetchTaskOfUserInAProject(assignee);

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	public List<Tasks> fetchTasksOfProject( String orgName, String projectName, String zipCode ) throws Exception
	{
		List<Tasks> tasks = new ArrayList<Tasks>();
		try
		{
			if( orgName == null || orgName.isEmpty() || projectName == null || projectName.isEmpty() || zipCode == null
					|| zipCode.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			List<Sprint> sprints = sprintServices.fetchAllSprint(orgName, projectName, zipCode);

			if( sprints != null && !sprints.isEmpty() )
			{
				for( Sprint sprint : sprints )
				{
					if( sprint.isActive() )
					{
						List<Tasks> t = fetchTaskOfSprint(orgName, projectName, sprint.getSprintName(), zipCode);

						if( t != null && !t.isEmpty() )
						{
							tasks.addAll(t);
						}
					}
				}
			}

			return tasks;

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	public List<Tasks> fetchTasksOfUserInSprint( String orgName, String projectName, String assignee,
			String sprintName ) throws Exception
	{
		try
		{
			if( orgName == null || orgName.isEmpty() || projectName == null || projectName.isEmpty() || assignee == null
					|| assignee.isEmpty() || sprintName == null || sprintName.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return fetchTaskOfUserInASprint(orgName, projectName, sprintName, assignee);

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	public Tasks fetchTask( String orgName, String taskId ) throws Exception
	{
		try
		{
			if( orgName == null || orgName.isEmpty() || taskId == null || taskId.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return fetchTaskByTaskId(taskId);

		}
		catch( Exception e )
		{
			throw e;
		}
	}
}
