package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.pojo.EmpGrievance;

public interface EmployeeGrievanceServices {

	public EmpGrievance addEmpGrievance( EmpGrievance empGrievance ) throws Exception;

	public List<EmpGrievance> fetchEmpGrievance( String emailId ) throws Exception;

	public List<EmpGrievance> fetchAllEmpGrievance( String orgName, String zipcode ) throws Exception;

}
