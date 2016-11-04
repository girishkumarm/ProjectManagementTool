package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.pojo.EmployeeHolidayInfo;

public interface EmployeeHolidayInfoServices {

	public void addEmployeeHolidayInfo( EmployeeHolidayInfo employeeHolidayInfo ) throws Exception;

	public void updateEmployeeHolidayInfo( EmployeeHolidayInfo employeeHolidayInfo ) throws Exception;

	public void updateEmployeeHolidayInfoOfAnEmployee( EmployeeHolidayInfo EmployeeHolidayInfo ) throws Exception;

	public List<EmployeeHolidayInfo> fetchEmployeeHolidayInfoOfAnEmployee( String emailId ) throws Exception;

	public EmployeeHolidayInfo fetchAllEmployeeHolidayInfoOfAnEmployee( String emailId, String year ) throws Exception;

}
