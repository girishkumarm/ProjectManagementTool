package com.tool.management.generic;

public class MeetingRequestPojo {

	private String receipent;
	private String subject;
	private String from;
	private String venue;
	private String description;
	private String summary;
	private String meetingStartTime;
	private String meetingEndTime;

	public String getReceipent()
	{
		return receipent;
	}

	public void setReceipent( String receipent )
	{
		this.receipent = receipent;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject( String subject )
	{
		this.subject = subject;
	}

	public String getFrom()
	{
		return from;
	}

	public void setFrom( String from )
	{
		this.from = from;
	}

	public String getVenue()
	{
		return venue;
	}

	public void setVenue( String venue )
	{
		this.venue = venue;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary( String summary )
	{
		this.summary = summary;
	}

	public String getMeetingStartTime()
	{
		return meetingStartTime;
	}

	public void setMeetingStartTime( String meetingStartTime )
	{
		this.meetingStartTime = meetingStartTime;
	}

	public String getMeetingEndTime()
	{
		return meetingEndTime;
	}

	public void setMeetingEndTime( String meetingEndTime )
	{
		this.meetingEndTime = meetingEndTime;
	}

}
