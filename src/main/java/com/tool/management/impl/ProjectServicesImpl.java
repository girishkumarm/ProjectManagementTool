package com.tool.management.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;
import com.tool.management.dao.ProjectDao;
import com.tool.management.generic.Constants;
import com.tool.management.interfaces.EmployeeServices;
import com.tool.management.interfaces.OrganisationServices;
import com.tool.management.interfaces.ProjectServices;
import com.tool.management.pojo.Employee;
import com.tool.management.pojo.Organisation;
import com.tool.management.pojo.Project;

@Service
public class ProjectServicesImpl extends ProjectDao implements ProjectServices {

	@Autowired
	private EmployeeServices employeeServices;

	@Autowired
	private OrganisationServices orgServices;

	public ProjectServicesImpl( Class<Project> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public ProjectServicesImpl( EntityManager em )
	{
		super(Project.class, em);
	}

	@Transactional( readOnly = false )
	public Project addProject( Project projectInfo ) throws Exception
	{
		Project proj = null;
		try
		{
			if( projectInfo == null || projectInfo.getOrgName() == null || projectInfo.getOrgName().isEmpty()
					|| projectInfo.getProjectName() == null || projectInfo.getProjectName().isEmpty()
					|| projectInfo.getCreatedBy() == null || projectInfo.getCreatedBy().isEmpty()
					|| projectInfo.getZipCode() == null || projectInfo.getZipCode().isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			//check if the organisation exist
			Organisation org = orgServices.getOrgInfo(projectInfo.getOrgName(), projectInfo.getZipCode());

			if( org != null )
			{

				//check if the project is duplicate or not
				proj = getProjectInfo(projectInfo.getOrgName(), projectInfo.getProjectName(), projectInfo.getZipCode());

				if( proj == null )
				{
					//check the team lead exist or not
					Employee empl = employeeServices.fetchEmployee(projectInfo.getOrgName(), projectInfo.getZipCode(),
							projectInfo.getTeamLead());
					if( empl != null )
					{
						//check if team lead is already handling any other project
						if( empl.getProjectName() == null )
						{
							//add the project name to the employee
							empl.setProjectName(projectInfo.getProjectName());
							employeeServices.addEmployee(empl);

							//add the project to the team members
							addProjectToTeamMembers(projectInfo.getProjectName(), new Long(empl.getId()));

							//add the project to the team lead senior if exist
							if( empl.getTeamLead() != null )
							{
								addProjectToSeniors(projectInfo.getProjectName(), new Long(empl.getTeamLead()));
							}

							if( projectInfo.getEmployees() != null )
							{
								String[] emp = projectInfo.getEmployees().split(",");
								Set<String> set = new HashSet<String>();
								for( String e : emp )
								{
									set.add(e);
								}
								projectInfo.setEmployees(set.toString());
							}
							proj = save(projectInfo);
						}
						else
						{
							throw new Exception("The user is already the Team Lead of the project "
									+ empl.getProjectName() + "," + Constants.StatusCode.DUPLICATE.getConstant());

						}
					}
					else
					{
						throw new Exception(Constants.ExceptionItems.USER_NOT_FOUND.getConstant() + ","
								+ Constants.StatusCode.DUPLICATE.getConstant());

					}
				}
				else
				{
					throw new Exception(Constants.ExceptionItems.DUPLICATE_PROJECT.getConstant() + ","
							+ Constants.StatusCode.DUPLICATE.getConstant());
				}
			}
			else
			{
				throw new Exception(Constants.ExceptionItems.ORGANISATION_NOT_FOUND.getConstant() + ","
						+ Constants.StatusCode.NOT_FOUND.getConstant());
			}

		}
		catch( Exception e )
		{
			throw e;
		}
		return proj;
	}

	private void addProjectToSeniors( String projectName, Long id ) throws Exception
	{

		try
		{
			Employee emp = employeeServices.fetchEmployeeById(id.intValue());

			System.out.println(new Gson().toJson(emp));
			if( emp != null )
			{
				if( emp.getProjectUnder() == null || emp.getProjectUnder().isEmpty() )
				{
					emp.setProjectUnder(projectName);
				}
				else
				{
					emp.setProjectUnder(emp.getProjectUnder() + ":" + projectName);
				}
				employeeServices.addEmployee(emp);
			}

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	private void addProjectToTeamMembers( String projectName, Long id ) throws Exception
	{
		try
		{
			List<Employee> emp = employeeServices.fetchEmpUnderLead(id);
			if( emp != null )
			{
				for( Employee e : emp )
				{
					e.setProjectName(projectName);
					employeeServices.addEmployee(e);
				}
			}

		}
		catch( Exception e )
		{
			throw e;
		}

	}

	@Transactional( readOnly = false )
	public Project addEmployeesToProject( String orgName, String projectName, String emailId, String zipCode )
			throws Exception
	{
		Project proj = null;
		try
		{
			if( orgName == null || orgName.isEmpty() || projectName == null || projectName.isEmpty() || emailId == null
					|| emailId.isEmpty() || zipCode == null || zipCode.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			proj = fetchByOrgNameAndProjectName(orgName, projectName, zipCode);

			if( proj != null )
			{
				//check if the employee exists
				Employee employee = employeeServices.fetchEmployee(orgName, zipCode, emailId);
				if( employee != null )
				{
					//update the employee project list
					employee.setProjectName(mergeEmployees(employee.getProjectName(), proj.getProjectName()));
					employeeServices.addEmployee(employee);

					//add employee to this project
					proj.setEmployees(mergeEmployees(proj.getEmployees(), emailId));
					save(proj);
				}
				else
				{
					throw new Exception(Constants.ExceptionItems.USER_NOT_FOUND.getConstant() + ","
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
		return proj;
	}

	@SuppressWarnings( "unchecked" )
	public String mergeEmployees( String list, String item ) throws Exception
	{
		Set<String> emp = null;
		try
		{
			if( list != null )
			{
				emp = new Gson().fromJson(list, Set.class);
				emp.add(item);
			}
			else
			{
				emp = new HashSet<String>();
				emp.add(item);
			}

			return emp.toString();
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	@SuppressWarnings( "unchecked" )
	@Transactional( readOnly = false )
	public Project deleteEmployeesFromProject( String orgName, String projectName, String emailId, String zipCode )
			throws Exception
	{
		Project proj = null;
		try
		{
			if( orgName == null || orgName.isEmpty() || projectName == null || projectName.isEmpty() || emailId == null
					|| emailId.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			proj = fetchByOrgNameAndProjectName(orgName, projectName, zipCode);

			if( proj != null )
			{

				//check if the employee exists
				Employee employee = employeeServices.fetchEmployee(orgName, zipCode, emailId);
				if( employee != null )
				{
					if( employee.getProjectName() != null )
					{
						//remove the project from the employee data
						Set<String> project = new Gson().fromJson(employee.getProjectName(), Set.class);
						if( !project.isEmpty() )
							project.remove(projectName);
						employee.setProjectName(project.toString());

					}
					if( proj.getEmployees() != null )
					{
						//remove the employee from the project
						Set<String> emp = new Gson().fromJson(proj.getEmployees(), Set.class);
						if( !emp.isEmpty() )
							emp.remove(emailId);
						proj.setEmployees(emp.toString());
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
				throw new Exception(Constants.ExceptionItems.PROJECT_NOT_FOUND.getConstant() + ","
						+ Constants.StatusCode.NOT_FOUND.getConstant());
			}

		}
		catch( Exception e )
		{
			throw e;
		}
		return proj;
	}

	public Project getProjectInfo( String orgName, String projectName, String zipCode ) throws Exception
	{
		Project proj = null;
		try
		{
			proj = fetchByOrgNameAndProjectName(orgName, projectName, zipCode);
		}
		catch( Exception e )
		{
			throw e;
		}
		return proj;
	}

	public List<Project> fetchAllProjects( String OrgName, String zipCode, String emailId ) throws Exception
	{
		List<Project> proj = new ArrayList<Project>();
		try
		{
			Employee employee = employeeServices.fetchEmployee(OrgName, zipCode, emailId);
			if( employee != null && employee.getEmployeeType().equals("ROOT") )
			{
				return fetchByOrgName(OrgName, zipCode);

			}
			if( employee != null && employee.getProjectName() != null )
			{
				//add project on which he is working
				proj.add(fetchByOrgNameAndProjectName(OrgName, employee.getProjectName(), zipCode));

				//add project on which his juniors are working
				if( employee.getProjectUnder() != null && !employee.getProjectUnder().isEmpty() )
				{
					String[] projects = employee.getProjectUnder().split(":");

					for( String project : projects )
					{
						proj.add(fetchByOrgNameAndProjectName(OrgName, project, zipCode));
					}

				}

				/* List<Employee> empList = employeeServices.fetchEmpUnderLead(new
				 * Long(employee.getId())); for( Employee e : empList ) { if( e.getProjectName() !=
				 * null ) { boolean flag = false; for( Project p : proj ) { if(
				 * p.getProjectName().equals(e.getProjectName()) ) { flag = true; break; }
				 * 
				 * } if( !flag ) { proj.add(fetchByOrgNameAndProjectName(OrgName,
				 * e.getProjectName(), zipCode)); } } } */

			}
			else if( employee.getProjectName() == null && employee.getTeamLead() != null && employee.getTeamLead() > 0 )
			{
				//get the project of his team lead and return it
				Employee teamLead = employeeServices.fetchEmployeeById(employee.getTeamLead().intValue());
				if( teamLead.getProjectName() != null )
				{
					proj.add(fetchByOrgNameAndProjectName(OrgName, teamLead.getProjectName(), zipCode));
				}

			}

			//get the project of his team lead and return it
			if( employee.getProjectUnder() != null && !employee.getProjectUnder().isEmpty() )
			{
				String[] projectNames = employee.getProjectUnder().split(":");
				System.out.println(employee.getProjectUnder());
				for( String projectName : projectNames )
				{
					proj.add(fetchByOrgNameAndProjectName(OrgName, projectName, zipCode));
				}
			}

		}
		catch( Exception e )
		{
			throw e;
		}
		return proj;
	}
}
