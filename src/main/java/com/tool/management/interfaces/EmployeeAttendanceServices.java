package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.pojo.EmployeeAttendance;

public interface EmployeeAttendanceServices {

	public EmployeeAttendance takeEmployeeAttendance( EmployeeAttendance employeeAttendance ) throws Exception;

	public EmployeeAttendance updateEmployeeAttendance( EmployeeAttendance employeeAttendance ) throws Exception;

	public List<EmployeeAttendance> fetchEmployeeAttendance( EmployeeAttendance employeeAttendance ) throws Exception;

	public List<EmployeeAttendance> fetchEmployeeAttendance( String emailId ) throws Exception;

}
