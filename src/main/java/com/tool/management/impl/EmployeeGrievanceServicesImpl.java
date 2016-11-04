package com.tool.management.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tool.management.dao.EmpGrievanceDao;
import com.tool.management.generic.Constants;
import com.tool.management.interfaces.EmployeeGrievanceServices;
import com.tool.management.pojo.EmpGrievance;

@Service
public class EmployeeGrievanceServicesImpl extends EmpGrievanceDao implements EmployeeGrievanceServices {

	public EmployeeGrievanceServicesImpl( Class<EmpGrievance> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public EmployeeGrievanceServicesImpl( EntityManager em )
	{
		super(EmpGrievance.class, em);
	}

	@Override
	public EmpGrievance addEmpGrievance( EmpGrievance empGrievance ) throws Exception
	{
		EmpGrievance empGriev = null;
		try
		{
			if( empGrievance == null || empGrievance.getDescription() == null || empGrievance.getDescription().isEmpty()
					|| empGrievance.getEmployeeId() == null || empGrievance.getStatus() == null
					|| empGrievance.getStatus().isEmpty() || empGrievance.getType() == null
					|| empGrievance.getType().isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}

			empGriev = save(empGrievance);
		}
		catch( Exception e )
		{
			throw e;
		}
		return empGriev;
	}

	@Override
	public List<EmpGrievance> fetchEmpGrievance( String id ) throws Exception
	{
		List<EmpGrievance> res = null;
		try
		{
			if( id == null || id.isEmpty() )
			{
				throw new Exception(Constants.ExceptionItems.MANDATORY_FIELDS_MISSING.getConstant() + ","
						+ Constants.StatusCode.MANDATORY_FIELDS.getConstant());
			}
			res = fetchByEmployeeId(Integer.parseInt(id));
		}
		catch( Exception e )
		{
			throw e;
		}
		return res;
	}

	@Override
	public List<EmpGrievance> fetchAllEmpGrievance( String orgName, String zipcode ) throws Exception
	{
		return null;
	}

}
