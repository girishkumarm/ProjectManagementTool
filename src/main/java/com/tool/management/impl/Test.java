package com.tool.management.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Test {

	@SuppressWarnings( "deprecation" )
	public static void main( String[] args )
	{
		try
		{

			Date startDate = new Date(new Long("1472322600000"));
			Date endDate = new Date(new Long("1473359400000"));
			System.out.println(getWorkingDaysBetweenTwoDates(startDate, endDate));

			Date endDate1 = new Date("08/29/2016");
			System.out.println(endDate1.toString());

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	public static int getWorkingDaysBetweenTwoDates( Date startDate, Date endDate )
	{
		Set<String> weekend = new HashSet<String>();
		Set<String> weekdays = new HashSet<String>();
		do
		{
			//excluding start date
			if( !startDate.toString().startsWith("Sat") && !startDate.toString().startsWith("Sun") )
			{
				weekdays.add(startDate.toString());
			}
			else
			{
				weekend.add(startDate.toString());
			}
			startDate = new Date(new Long(startDate.getTime() + 86400000));
		} while( startDate.getTime() <= endDate.getTime() );

		System.out.println(weekend.toString());
		System.out.println(weekdays.toString());

		return 0;
	}

}
