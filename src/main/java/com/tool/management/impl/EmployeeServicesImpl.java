package com.tool.management.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.management.dao.EmployeeDao;
import com.tool.management.generic.Constants;
import com.tool.management.generic.Utils;
import com.tool.management.interfaces.EmployeeServices;
import com.tool.management.interfaces.OrganisationServices;
import com.tool.management.pojo.Employee;

@Service
public class EmployeeServicesImpl extends EmployeeDao implements EmployeeServices {

	@Autowired
	private OrganisationServices orgServices;

	public EmployeeServicesImpl( Class<Employee> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public EmployeeServicesImpl( EntityManager em )
	{
		super(Employee.class, em);
	}

	@Transactional( readOnly = false )
	public Boolean updateEmployeeInfo( List<Employee> employees ) throws Exception
	{
		try
		{
			if( employees == null || employees.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			for( Employee e : employees )
			{
				if( e == null || e.getId() == null || e.getTitle() == null || e.getTitle().isEmpty()
						|| e.getTeamLead() == null )
				{
					throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
							+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
				}

				Employee ee = fetchById(e.getId());
				ee.setTitle(e.getTitle());
				ee.setTeamLead(e.getTeamLead());
				ee.setDepartment(e.getDepartment());

				if( e.getTeamLead() != null && e.getTeamLead() == -1 )
				{
					ee.setProjectName("");
				}
				else
				{
					Employee teamLead = fetchById(e.getTeamLead().intValue());
					if( teamLead != null && teamLead.getProjectName() != null && !teamLead.getProjectName().isEmpty() )
						ee.setProjectName(teamLead.getProjectName());
				}

				save(ee);
			}

		}
		catch( Exception e )
		{
			throw e;
		}
		return true;
	}

	@Transactional( readOnly = false )
	public Employee addEmployee( Employee employee ) throws Exception
	{
		try
		{
			if( employee == null || employee.getEmailId() == null || employee.getEmailId().isEmpty()
					|| employee.getEmployeeType() == null || employee.getEmployeeType().isEmpty()
					|| employee.getName() == null || employee.getName().isEmpty() || employee.getOrganisation() == null
					|| employee.getOrganisation().isEmpty()
					|| employee.getZipCode() == null && employee.getZipCode().isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			if( orgServices.getOrgInfo(employee.getOrganisation(), employee.getZipCode()) != null )
			{
				if( employee.getId() != null && employee.getId() != 0 )
				{
					if( fetchById(employee.getId()) != null )
					{
						employee = save(employee);
					}
					else
					{
						throw new Exception(Constants.ExceptionItems.USER_NOT_FOUND.getConstant() + ","
								+ Constants.StatusCode.NOT_FOUND.getConstant());
					}
				}
				else
				{
					Employee olEmp = fetchByEmailId(employee.getEmailId());
					if( olEmp == null )
					{
						if( employee.getEmployeeType().equalsIgnoreCase("root") )
						{
							employee.setTeamLead((long) 0);
							if( employee.getPassword() == null || employee.getPassword().isEmpty() )
							{
								employee.setPassword(Utils.randomAlphaNumeric(8));
							}
						}
						else
						{
							employee.setPassword(Utils.randomAlphaNumeric(8));
						}
						save(employee);
					}
					else
					{
						if( olEmp.getEmployeeType().equals(employee.getEmployeeType()) )
						{
							throw new Exception(Constants.ExceptionItems.DUPLICATE_EMPLOYEE.getConstant() + ","
									+ Constants.StatusCode.DUPLICATE.getConstant());
						}
						else
						{
							employee.setId(olEmp.getId());
							employee.setPassword(Utils.randomAlphaNumeric(8));
						}
					}

					save(employee);
					/* SendEmail.getInstance().sendEmail("", "Your Password is " +
					 * employee.getPassword(), "girishkumarm710@gmail.com", "mkumargirish",
					 * employee.getEmailId(), "Password"); */
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
		return employee;
	}

	public List<Employee> fetchAllEmployees( String orgName, String zipcode ) throws Exception
	{
		List<Employee> emp = null;
		try
		{
			if( orgName == null || orgName.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}
			emp = fetchAllEmployee(orgName, zipcode);
		}
		catch( Exception e )
		{
			throw e;
		}
		return emp;
	}

	public Employee fetchEmployee( String orgName, String zipCode, String emailId ) throws Exception
	{
		Employee emp = null;
		try
		{
			if( orgName == null || orgName.isEmpty() || emailId == null || emailId.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}
			emp = fetchEmployeeInfo(orgName, zipCode, emailId);
		}
		catch( Exception e )
		{
			throw e;
		}
		return emp;
	}

	public Employee login( String emailId, String password ) throws Exception
	{
		Employee emp;
		try
		{
			if( emailId == null || emailId.isEmpty() || password == null || password.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			emp = fetchByEmailId(emailId);

			if( emp != null )
			{
				if( emp.getPassword().equals(password) )
				{
					return emp;
				}
				else
				{
					emp.setPassword("");
					return emp;
				}
			}
			else
			{
				return null;
			}
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	public List<Employee> fetchEmpUnderLead( Long leadId ) throws Exception
	{
		try
		{
			return fetchByTeamLead(leadId);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	public Employee fetchEmployeeById( Integer id ) throws Exception
	{
		try
		{
			return fetchById(id);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	public List<String> fetchEmployeeOfProject( String orgName, String zipCode, String projectName ) throws Exception
	{
		List<String> empNames = new ArrayList<String>();
		try
		{
			List<Employee> e = fetchEmpOfProject(orgName, zipCode, projectName);
			if( e != null && !e.isEmpty() )
			{
				for( Employee eee : e )
				{
					empNames.add(eee.getEmailId());
				}
			}
		}
		catch( Exception e )
		{
			throw e;
		}
		return empNames;
	}

}
