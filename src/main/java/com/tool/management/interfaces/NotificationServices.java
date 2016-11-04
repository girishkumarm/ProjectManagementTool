package com.tool.management.interfaces;

import java.util.List;
import com.tool.management.pojo.Notification;

public interface NotificationServices {

	public Notification addNotification( Notification notification ) throws Exception;

	public List<Notification> fetchNotificationListOfEmployee( String emailId ) throws Exception;

	public Notification fetchNotification( Integer id ) throws Exception;

}
