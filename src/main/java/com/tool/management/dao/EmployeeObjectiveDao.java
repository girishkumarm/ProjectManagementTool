package com.tool.management.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.tool.management.generic.repository.GenericRepositoryImpl;
import com.tool.management.pojo.EmployeeObjective;

public class EmployeeObjectiveDao extends GenericRepositoryImpl<EmployeeObjective, Serializable> {

	public EmployeeObjectiveDao( Class<EmployeeObjective> domainClass, EntityManager em )
	{
		super(domainClass, em);
	}

	@Autowired
	public EmployeeObjectiveDao( EntityManager em )
	{
		super(EmployeeObjective.class, em);
	}

	@SuppressWarnings( "unchecked" )
	public EmployeeObjective fetchById( Integer id ) throws Exception
	{
		List<EmployeeObjective> e = null;
		try
		{
			e = (List<EmployeeObjective>) this.getEm().createQuery("Select e from EmployeeObjective e where e.id=:id")
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
	public List<EmployeeObjective> fetchByEmployeeId( Integer employeeId ) throws Exception
	{
		List<EmployeeObjective> e = null;
		try
		{
			e = (List<EmployeeObjective>) this.getEm()
					.createQuery("Select e from EmployeeObjective e where e.employeeId=:employeeId")
					.setParameter("employeeId", employeeId).getResultList();
		}
		catch( Exception ee )
		{
			throw ee;
		}
		return e;
	}

}
