package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.pojo.EmployeeObjective;

public interface EmployeeObjectiveServices {

	public EmployeeObjective addEmployeeObjective( EmployeeObjective employeeObjective ) throws Exception;
	
	public EmployeeObjective updateEmployeeObjective( EmployeeObjective employeeObjective ) throws Exception;

	public List<EmployeeObjective> fetchListOfEmployeeObjective( Integer employeeId ) throws Exception;

	public EmployeeObjective fetchEmployeeObjective( Integer id ) throws Exception;

}
