//package com.tool.management.rest.services;
//
//import java.util.List;
//import javax.validation.constraints.NotNull;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RestController;
//import com.tool.management.generic.Constants;
//import com.tool.management.generic.TransactionResponse;
//import com.tool.management.interfaces.EmployeeHolidayInfoServices;
//import com.tool.management.pojo.EmployeeHolidayInfo;
//import com.tool.management.pojo.HolidayInfo;
//
//@Path( "/holiday-info" )
//@RestController
//public class EmployeeHolidayInfoRestServices {
//
//	@Autowired
//	private EmployeeHolidayInfoServices employeeholidayInfoServices;
//
//	@POST
//	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
//	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
//	@Path( "/add-holiday-info-for-organisation" )
//	public TransactionResponse addEmployeeHolidayInfo( EmployeeHolidayInfo employeeHolidayInfo ) throws Exception
//	{
//		TransactionResponse transactionResponse = new TransactionResponse();
//		transactionResponse.setStatus(Constants.StatusCode.NOT_FOUND.getConstant());
//		transactionResponse.setResponseMessage("Successful");
//		transactionResponse.setResponseType("NULL");
//		try
//		{
//			// call add service
//			employeeholidayInfoServices.addEmployeeHolidayInfoForOrganisation(employeeHolidayInfo);
//
//			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//			throw e;
//		}
//		return transactionResponse;
//	}
//
//	@POST
//	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
//	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
//	@Path( "/update-holiday-info-for-organisation" )
//	public TransactionResponse updateHolidayInfoForAnOrganisation( EmployeeHolidayInfo EmployeeHolidayInfo )
//			throws Exception
//	{
//		TransactionResponse transactionResponse = new TransactionResponse();
//		transactionResponse.setStatus(Constants.StatusCode.NOT_FOUND.getConstant());
//		transactionResponse.setResponseMessage("Successful");
//		transactionResponse.setResponseType("NULL");
//		try
//		{
//
//			// call update service
//			employeeholidayInfoServices.updateHolidayInfoForOrganisation(EmployeeHolidayInfo);
//
//			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//			throw e;
//		}
//		return transactionResponse;
//	}
//
//	@POST
//	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
//	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
//	@Path( "/update-holiday-info-for-employee" )
//	public TransactionResponse updateHolidayInfoForAnEmployee( HolidayInfo holidayInfo ) throws Exception
//	{
//		TransactionResponse transactionResponse = new TransactionResponse();
//		transactionResponse.setStatus(Constants.StatusCode.NOT_FOUND.getConstant());
//		transactionResponse.setResponseMessage("Successful");
//		transactionResponse.setResponseType("NULL");
//		try
//		{
//			holidayInfo = new HolidayInfo();
//			holidayInfo.setEmailId("girishkumarm710@gmail.com");
//			holidayInfo.setOthers(3);
//			holidayInfo.setYear(2016);
//
//			// call update service
//			employeeholidayInfoServices.updateHolidayInfoOfAnEmployee(holidayInfo);
//
//			transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//			throw e;
//		}
//		return transactionResponse;
//	}
//
//	@GET
//	@Produces( MediaType.APPLICATION_JSON )
//	@Path( "/fetch-all/{emailId}" )
//	public TransactionResponse fetchEmployeeHolidayInfo(
//			@NotNull( message = "Cannot be null" ) @PathParam( "emailId" ) String emailId ) throws Exception
//	{
//		List<HolidayInfo> list;
//
//		TransactionResponse transactionResponse = new TransactionResponse();
//		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
//		transactionResponse.setResponseMessage("Fetch Failed");
//		transactionResponse.setResponseType("NULL");
//		try
//		{
//			// call fetch service
//			list = holidayInfoServices.fetchHolidayInfoOfAnEmployee(emailId);
//
//			if( list != null && !list.isEmpty() )
//			{
//				// set the response object
//				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
//				transactionResponse.setResponseMessage("Fetch Successful");
//				transactionResponse.setResponseType("LIST");
//				transactionResponse.setResponseEntity(list);
//			}
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//			throw e;
//		}
//		return transactionResponse;
//	}
//
//	@GET
//	@Produces( MediaType.APPLICATION_JSON )
//	@Path( "/fetch/{emailId}/{year}" )
//	public TransactionResponse fetchAllHolidayInfo(
//			@NotNull( message = "Cannot be null" ) @PathParam( "emailId" ) String emailId,
//			@NotNull( message = "Cannot be null" ) @PathParam( "year" ) String year ) throws Exception
//	{
//		HolidayInfo object;
//
//		TransactionResponse transactionResponse = new TransactionResponse();
//		transactionResponse.setStatus(Constants.StatusCode.FAILED.getConstant());
//		transactionResponse.setResponseMessage("Fetch Failed");
//		transactionResponse.setResponseType("NULL");
//		try
//		{
//			// call fetch service
//			object = employeeholidayInfoServices.fetchAllHolidayInfoOfAnEmployee(emailId, year);
//
//			if( object != null )
//			{
//				// set the response object
//				transactionResponse.setStatus(Constants.StatusCode.SUCCESSFUL.getConstant());
//				transactionResponse.setResponseMessage("Fetch Successful");
//				transactionResponse.setResponseType("Object");
//				transactionResponse.setResponseEntity(object);
//			}
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//			throw e;
//		}
//		return transactionResponse;
//	}
//}
