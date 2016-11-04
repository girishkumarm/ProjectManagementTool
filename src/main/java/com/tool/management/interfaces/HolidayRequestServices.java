package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.pojo.HolidayRequest;

public interface HolidayRequestServices {

	public HolidayRequest addHolidayRequest( HolidayRequest holidayRequest ) throws Exception;

	public HolidayRequest updateHolidayRequest( HolidayRequest holidayRequest ) throws Exception;

	public List<HolidayRequest> fetchHolidayRequest( String emailId ) throws Exception;

	public List<HolidayRequest> fetchAllHolidayRequest( String emailId, String year ) throws Exception;

}
