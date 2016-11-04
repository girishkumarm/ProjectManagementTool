package com.tool.management.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.management.dao.SprintDao;
import com.tool.management.generic.Constants;
import com.tool.management.interfaces.ProjectServices;
import com.tool.management.interfaces.SprintServices;
import com.tool.management.pojo.Project;
import com.tool.management.pojo.Sprint;

@Service
public class SprintServicesImpl extends SprintDao implements SprintServices {

	@Autowired
	private ProjectServices projServices;

	public SprintServicesImpl( Class<Sprint> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public SprintServicesImpl( EntityManager em )
	{
		super(Sprint.class, em);
	}

	@SuppressWarnings( "deprecation" )
	@Transactional( readOnly = false )
	public Sprint addSprint( Sprint sprintInfo ) throws Exception
	{
		try
		{
			if( sprintInfo == null || sprintInfo.getOrgName() == null || sprintInfo.getOrgName().isEmpty()
					|| sprintInfo.getProjName() == null || sprintInfo.getProjName().isEmpty()
					|| sprintInfo.getCreatedBy() == null || sprintInfo.getCreatedBy().isEmpty()
					|| sprintInfo.getZipCode() == null || sprintInfo.getZipCode().isEmpty()
					|| sprintInfo.getStartDate() == null || sprintInfo.getEndDate() == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			Project proj = projServices.getProjectInfo(sprintInfo.getOrgName(), sprintInfo.getProjName(),
					sprintInfo.getZipCode());

			if( proj != null )
			{
				int totalSprints = makeAllSprintInActiveInAProject(sprintInfo.getProjName(), sprintInfo.getOrgName(),
						sprintInfo.getZipCode());
				sprintInfo.setSprintName("Sprint " + (totalSprints + 1));
				sprintInfo.setActive(true);
				sprintInfo.setCreatedAt(System.currentTimeMillis());
				sprintInfo.setTotalHours((long) 0);

				//add working dates
				SortedSet<Long> weekend = new TreeSet<Long>();
				SortedSet<Long> weekdays = new TreeSet<Long>();
				Date startDate = new Date(new Long(sprintInfo.getStartDate()));
				Date endDate = new Date(new Long(sprintInfo.getEndDate()));
				do
				{
					//excluding start date
					if( !startDate.toString().startsWith("Sat") && !startDate.toString().startsWith("Sun") )
					{
						weekdays.add(startDate.getTime());
					}
					else
					{
						weekend.add(startDate.getTime());
					}
					startDate = new Date(new Long(startDate.getTime() + 86400000));
				} while( startDate.getTime() <= endDate.getTime() );

				//check for public holidays
				if( sprintInfo.getHolidays() != null )
				{
					//get all the dates in a array string
					String[] holidays = sprintInfo.getHolidays().split(",");

					//traverse the holidays
					for( String holiday : holidays )
					{
						System.out.println(holiday);
						//if holiday is in the working days or weekday
						Set<Long> temp = new HashSet<Long>();
						temp.addAll(weekdays);
						for( Long weekday : temp )
						{
							//remove holiday from weekday if exist and add to holiday list
							if( weekday.equals(new Date(holiday.trim()).getTime()) )
							{
								weekdays.remove(weekday);
								weekend.add(weekday);
							}

						}
					}

				}

				sprintInfo.setWorkingDates(weekdays.toString());
				sprintInfo.setHolidays(weekend.toString());
				sprintInfo.setWorkingDays(weekdays.size());

				sprintInfo = save(sprintInfo);
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
		return sprintInfo;
	}

	public List<Sprint> fetchAllSprint( String orgName, String projectName, String zipCode ) throws Exception
	{
		try
		{
			if( orgName == null || orgName.isEmpty() || projectName == null || projectName.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return (List<Sprint>) fetchByProjectNameOrgName(projectName, orgName, zipCode);

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	@Transactional( readOnly = false )
	public Boolean deleteSprint( Sprint sprintInfo ) throws Exception
	{
		try
		{
			if( sprintInfo == null || sprintInfo.getProjName() == null || sprintInfo.getProjName().isEmpty()
					|| sprintInfo.getOrgName() == null || sprintInfo.getOrgName().isEmpty()
					|| sprintInfo.getSprintName() == null || sprintInfo.getSprintName().isEmpty()
					|| sprintInfo.getZipCode() == null || sprintInfo.getZipCode().isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			deleteBySprintName(sprintInfo.getOrgName(), sprintInfo.getProjName(), sprintInfo.getSprintName());

		}
		catch( Exception e )
		{
			throw e;
		}
		return true;
	}

	public Sprint getSprintInfo( String orgName, String projectName, String zipCode, String sprintName )
			throws Exception
	{
		try
		{
			if( orgName == null || orgName.isEmpty() || projectName == null || projectName.isEmpty()
					|| sprintName == null || zipCode.isEmpty() || zipCode == null || sprintName.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			return (Sprint) fetchByProjectNameOrgNameSprintName(orgName, projectName, sprintName, zipCode);

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	public void addTaskTime( Sprint sprintInfo, Integer time ) throws Exception
	{
		try
		{
			if( sprintInfo == null || sprintInfo.getOrgName() == null || sprintInfo.getOrgName().isEmpty()
					|| sprintInfo.getProjName() == null || sprintInfo.getProjName().isEmpty()
					|| sprintInfo.getZipCode() == null || sprintInfo.getZipCode().isEmpty()
					|| sprintInfo.getSprintName() == null || sprintInfo.getSprintName().isEmpty() || time == null )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			Sprint sp = (Sprint) fetchByProjectNameOrgNameSprintName(sprintInfo.getOrgName(), sprintInfo.getProjName(),
					sprintInfo.getSprintName(), sprintInfo.getZipCode());

			if( sp != null )
			{
				if( sp.getTotalHours() != null && sp.getTotalHours() != 0 )
				{
					long totalTime = sp.getTotalHours() + time;
					sp.setTotalHours(totalTime);
				}
				else
				{
					sp.setTotalHours(new Long(time));
				}
			}

			save(sp);

		}
		catch( Exception e )
		{
			throw e;
		}

	}

}
