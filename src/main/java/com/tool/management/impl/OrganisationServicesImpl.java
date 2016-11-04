package com.tool.management.impl;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tool.management.dao.OrganisationDao;
import com.tool.management.generic.Constants;
import com.tool.management.interfaces.OrganisationServices;
import com.tool.management.pojo.Organisation;

@Service
public class OrganisationServicesImpl extends OrganisationDao implements OrganisationServices {

	public OrganisationServicesImpl( Class<Organisation> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public OrganisationServicesImpl( EntityManager em )
	{
		super(Organisation.class, em);
	}

	@Transactional( readOnly = false )
	public Organisation updateOrganisation( Organisation organisationInfo ) throws Exception
	{
		Organisation org = null;
		try
		{
			if( organisationInfo == null || organisationInfo.getOrganisationName() == null
					|| organisationInfo.getOrganisationName().isEmpty() || organisationInfo.getEmailId() == null
					|| organisationInfo.getEmailId().isEmpty() || organisationInfo.getPassword() == null
					|| organisationInfo.getPassword().isEmpty() || organisationInfo.getAddress() == null
					|| organisationInfo.getAddress().isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			org = getOrgInfo(organisationInfo.getOrganisationName(), organisationInfo.getAddress());

			if( org != null )
			{
				if( org.getEmailId().equalsIgnoreCase(organisationInfo.getEmailId()) )
				{
					org.setPassword(organisationInfo.getPassword());
					save(org);
				}
				else
				{
					throw new Exception(Constants.ExceptionItems.USER_NOT_FOUND.getConstant() + ","
							+ Constants.StatusCode.NOT_FOUND.getConstant());
				}
			}
			else
			{
				throw new Exception(Constants.ExceptionItems.ORGANISATION_NOT_FOUND.getConstant() + ","
						+ Constants.StatusCode.NOT_FOUND.getConstant());
			}

		}
		catch( Exception e )
		{
			throw e;
		}
		return org;
	}

	@Transactional( readOnly = false )
	public Organisation addOrganisation( Organisation organisationInfo ) throws Exception
	{
		Organisation org = null;
		try
		{
			if( organisationInfo == null || organisationInfo.getOrganisationName() == null
					|| organisationInfo.getOrganisationName().isEmpty() || organisationInfo.getEmailId() == null
					|| organisationInfo.getEmailId().isEmpty() || organisationInfo.getPassword() == null
					|| organisationInfo.getPassword().isEmpty() || organisationInfo.getRegistrationNumber() == null
					|| organisationInfo.getRegistrationNumber().isEmpty() || organisationInfo.getAddress() == null
					|| organisationInfo.getAddress().isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			org = fetchByOrgName(organisationInfo.getOrganisationName(), organisationInfo.getAddress());

			if( org == null )
			{
				//save the information in the database
				org = save(organisationInfo);

				/*String template = "<html><body><p>Your organisation has been registered with Team Management.</p><p>Activate your account by <a href=\"localhost:8080/index.html\">clicking here.</a></p></body></html>";

				SendEmail.getInstance().sendEmail(template, "", "girishkumarm710@gmail.com", "mkumargirish",
						org.getEmailId(), "Account Activation");*/
			}
			else
			{
				throw new Exception(Constants.ExceptionItems.DUPLICATE_ORGANISATION.getConstant() + ","
						+ Constants.StatusCode.DUPLICATE.getConstant());
			}
		}
		catch( Exception e )
		{
			throw e;
		}
		return org;
	}

	public Organisation getOrgInfo( String orgName, String zipcode ) throws Exception
	{
		Organisation org = null;
		try
		{
			org = fetchByOrgName(orgName, zipcode);
		}
		catch( Exception e )
		{
			throw e;
		}
		return org;
	}

	public void activateOrganisation( String orgName, String zipcode ) throws Exception
	{
		Organisation org = null;
		try
		{
			org = fetchByOrgName(orgName, zipcode);
			org.setActivated(true);
			save(org);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

}
