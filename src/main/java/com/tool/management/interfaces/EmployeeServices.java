package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.pojo.Employee;

public interface EmployeeServices {

	public Employee addEmployee( Employee employee ) throws Exception;

	public Employee fetchEmployee( String orgName, String zipCode, String emailId ) throws Exception;

	public List<String> fetchEmployeeOfProject( String orgName, String zipCode, String projectName ) throws Exception;

	public Employee fetchEmployeeById( Integer id ) throws Exception;

	public Employee login( String emailId, String password ) throws Exception;

	public List<Employee> fetchAllEmployees( String orgName, String zipcode ) throws Exception;

	public List<Employee> fetchEmpUnderLead( Long leadId ) throws Exception;

	public Boolean updateEmployeeInfo( List<Employee> employees ) throws Exception;

}
