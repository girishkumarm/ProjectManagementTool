package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.EmpGrievance;

public class EmpGrievanceDao extends GenericRepositoryImpl<EmpGrievance, Serializable> {

	public EmpGrievanceDao( Class<EmpGrievance> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public EmpGrievanceDao( EntityManager em )
	{
		super(EmpGrievance.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public EmpGrievance fetchById( Integer id ) throws Exception
	{
		List<EmpGrievance> e = null;
		try
		{
			e = (List<EmpGrievance>) this.getEm().createQuery("Select e from EmpGrievance e where e.id=:id")
					.setParameter("id", id).getResultList();

			if( e != null && !e.isEmpty() )
			{
				return e.get(0);
			}

		}
		catch( Exception ee )
		{
			throw ee;
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public List<EmpGrievance> fetchByEmployeeId( Integer employeeId ) throws Exception
	{
		List<EmpGrievance> e = null;
		try
		{
			e = (List<EmpGrievance>) this.getEm()
					.createQuery("Select e from EmpGrievance e where e.employeeId=:employeeId")
					.setParameter("employeeId", employeeId).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

}
