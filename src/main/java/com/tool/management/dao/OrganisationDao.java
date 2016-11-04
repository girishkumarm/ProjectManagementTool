package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.Organisation;

@Service
public class OrganisationDao extends GenericRepositoryImpl<Organisation, Serializable> {

	public OrganisationDao( Class<Organisation> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public OrganisationDao( EntityManager em )
	{
		super(Organisation.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public Organisation fetchByOrgName( String orgName, String address ) throws Exception
	{
		List<Organisation> org = null;
		try
		{
			org = (List<Organisation>) this.getEm()
					.createQuery(
							"Select o from Organisation o where o.organisationName=:orgName and o.address=:address")
					.setParameter("orgName", orgName).setParameter("address", address).getResultList();

			if( org != null && !org.isEmpty() )
			{
				return org.get(0);
			}

		}
		catch( Exception e )
		{
			throw e;
		}
		return null;
	}
}
