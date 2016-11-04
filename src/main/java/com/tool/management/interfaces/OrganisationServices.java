package com.tool.management.interfaces;

import com.tool.management.pojo.Organisation;

public interface OrganisationServices {

	public Organisation updateOrganisation( Organisation organisationInfo ) throws Exception;

	public Organisation addOrganisation( Organisation organisationInfo ) throws Exception;

	public Organisation getOrgInfo( String orgName, String zipcode ) throws Exception;
	
	public void activateOrganisation( String orgName, String zipcode ) throws Exception;

}
