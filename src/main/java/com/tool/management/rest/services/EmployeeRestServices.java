package com.tool.management.rest.services;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import com.tool.management.generic.Constants;
import com.tool.management.generic.EmpChart;
import com.tool.management.generic.LoginInfo;
import com.tool.management.generic.TransactionResponse;
import com.tool.management.interfaces.EmployeeServices;
import com.tool.management.pojo.Employee;

@Path( "/employee" )
@RestController
public class EmployeeRestServices {

	@Autowired
	private EmployeeServices employeeServices;

	@POST
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	@Path( "/save-employee-list" )
	public TransactionResponse saveEmployeeList( List<Employee> OrgInfo ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("organisation info not saved");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			Boolean save = employeeServices.updateEmployeeInfo(OrgInfo);

			if( save )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Employees info saved successfully");
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
	@Path( "/save-employee" )
	public TransactionResponse saveEmployeeInfo( @ModelAttribute( "Employee" ) Employee employee ) throws Exception
	{
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("organisation info not saved");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call update or add team info service
			if( employee.getEmployeeType().equalsIgnoreCase("root") )
			{
				employee.setTeamLead((long) 0);
			}
			Employee save = employeeServices.addEmployee(employee);

			if( save != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Employee info saved successfully");
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
	@Path( "/fetch-all-emp-info/{orgName}/{zipCode}" )
	public TransactionResponse fetchAllTeamsInfo(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "zipCode" ) String zipCode ) throws Exception
	{
		List<Employee> empInfo;
		List<EmpChart> empChart = new ArrayList<EmpChart>();

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			empInfo = employeeServices.fetchAllEmployees(orgName, zipCode);

			if( empInfo != null && !empInfo.isEmpty() )
			{
				for( Employee e : empInfo )
				{
					EmpChart ec = new EmpChart();
					ec.setKey(e.getId());
					ec.setEmailId(e.getEmailId());
					ec.setName(e.getName());

					if( e.getSource() != null )
						ec.setSource(e.getSource());

					if( e.getDepartment() != null )
					{
						ec.setDepartment(e.getDepartment());
					}
					else
					{
						ec.setDepartment("Dept");
					}

					if( e.getTitle() == null || e.getTitle().isEmpty() )
					{
						ec.setTitle("Title");
					}
					else
						ec.setTitle(e.getTitle());

					if( e.getTeamLead() != null )
					{
						ec.setParent(e.getTeamLead() + "");
					}
					else
					{
						ec.setParent(-1 + "");
					}

					empChart.add(ec);

				}
			}
			// set the response object
			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
			transactionResponse.setResponseMessage("Fetch Successful");
			transactionResponse.setResponseType("LIST");
			transactionResponse.setResponseEntity(empChart);
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
	@Path( "/fetch-emp-info/{orgName}/{zipCode}/{emailId}" )
	public TransactionResponse fetchEmployeeInfo(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "zipCode" ) String zipCode,
			@NotNull( message = "Cannot be null" ) @PathParam( "emailId" ) String emailId ) throws Exception
	{
		Employee empInfo;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			empInfo = employeeServices.fetchEmployee(orgName, zipCode, emailId);

			if( empInfo != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Fetch Successful");
				transactionResponse.setResponseType("LIST");
				transactionResponse.setResponseEntity(empInfo);
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
	@Path( "/login" )
	public TransactionResponse login( @ModelAttribute( "Employee" ) LoginInfo loginInfo ) throws Exception
	{
		Employee empInfo;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.NOT_FOUND.getConstant());
		transactionResponse.setResponseMessage(Constants.ExceptionItems.USER_NOT_FOUND.getConstant());
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			empInfo = employeeServices.login(loginInfo.getEmailId(), loginInfo.getPassword());

			if( empInfo != null )
			{
				if( empInfo.getPassword().isEmpty() )
				{
					transactionResponse.setStatus(Constants.StatusCode.AUTHENTICATION_ERROR.getConstant());
					transactionResponse.setResponseMessage(Constants.ExceptionItems.USER_NOT_FOUND.getConstant());
				}
				else
				{
					// set the response object
					transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
					transactionResponse.setResponseMessage("Fetch Successful");
					transactionResponse.setResponseEntity(empInfo);
				}
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
	@Path( "/fetch-emp-of-project/{orgName}/{zipCode}/{projectName}" )
	public TransactionResponse fetchEmployeeOfProject(
			@NotNull( message = "Cannot be null" ) @PathParam( "orgName" ) String orgName,
			@NotNull( message = "Cannot be null" ) @PathParam( "zipCode" ) String zipCode,
			@NotNull( message = "Cannot be null" ) @PathParam( "projectName" ) String projectName ) throws Exception
	{
		List<String> empInfo;

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
		transactionResponse.setResponseMessage("Fetch Failed");
		transactionResponse.setResponseType("NULL");
		try
		{
			// call fetch service
			empInfo = employeeServices.fetchEmployeeOfProject(orgName, zipCode, projectName);

			if( empInfo != null )
			{
				// set the response object
				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
				transactionResponse.setResponseMessage("Fetch Successful");
				transactionResponse.setResponseType("LIST");
				transactionResponse.setResponseEntity(empInfo);
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
