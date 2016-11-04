package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.pojo.ActivityStream;

public interface ActivityStreamServices {

	public ActivityStream addActivity( ActivityStream activity ) throws Exception;

	public List<ActivityStream> fetchActivityList( ActivityStream activity ) throws Exception;

	public ActivityStream fetchActivity( Integer id ) throws Exception;

}
