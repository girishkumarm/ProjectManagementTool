package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.pojo.WorkLogged;

public interface WorkLoggedServices {

	public WorkLogged logWork( WorkLogged workLogged ) throws Exception;

	public List<WorkLogged> fetchLogWork( String orgName, String projName, String zipcode, String sprintName )
			throws Exception;

}
