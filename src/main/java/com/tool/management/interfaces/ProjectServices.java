package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.pojo.Project;

public interface ProjectServices {

	public Project addProject( Project projectInfo ) throws Exception;

	public List<Project> fetchAllProjects( String OrgName, String zipCode, String emailId ) throws Exception;

	public Project getProjectInfo( String orgName, String projectName, String zipCode ) throws Exception;

	public Project addEmployeesToProject( String orgName, String projectName, String emailId, String zipCode )
			throws Exception;

	public Project deleteEmployeesFromProject( String orgName, String projectName, String emailId, String zipCode )
			throws Exception;

}
