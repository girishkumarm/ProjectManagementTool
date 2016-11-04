package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.entities.HolidayInfoRequest;
import com.tool.management.pojo.HolidayInfo;

public interface HolidayInfoServices {

	public void addHolidayInfoForOrganisation( HolidayInfoRequest holidayInfoRequest ) throws Exception;

	public void updateHolidayInfoForOrganisation( HolidayInfoRequest holidayInfoRequest ) throws Exception;

	public void updateHolidayInfoOfAnEmployee( HolidayInfo holidayInfo ) throws Exception;

	public List<HolidayInfo> fetchHolidayInfoOfAnEmployee( String emailId ) throws Exception;

	public HolidayInfo fetchAllHolidayInfoOfAnEmployee( String emailId, String year ) throws Exception;

}
