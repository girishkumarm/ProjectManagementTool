package com.tool.management.utils;

import java.util.Properties;
import java.util.UUID;
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import com.tool.management.generic.MeetingRequestPojo;

public class MeetingRequest {

	public static boolean send( MeetingRequestPojo request ) throws Exception
	{
		try
		{
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication("girish.kumar@razorthink.net", "9738769973");
				}
			});

			// Define message

			MimeMessage message = new MimeMessage(session);
			message.addHeaderLine("method=REQUEST");
			message.addHeaderLine("charset=UTF-8");
			message.addHeaderLine("component=VEVENT");

			message.setFrom(new InternetAddress(request.getFrom()));
			String rec = "";

			for( String r : request.getReceipent().split(",") )
			{
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(r));
				if( rec == "" )
				{
					rec = r;
				}
				else
					rec = rec + "," + r;

			}
			message.setSubject(request.getSubject());

			StringBuffer sb = new StringBuffer();

			StringBuffer buffer = sb.append("BEGIN:VCALENDAR\n"
					+ "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n" + "VERSION:2.0\n"
					+ "METHOD:REQUEST\n" + "BEGIN:VEVENT\n" + "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:" + rec
					+ "\n" + "ORGANIZER:MAILTO:" + request.getFrom() + "\n" + "DTSTART:" + request.getMeetingStartTime()
					+ "Z\n" + "DTEND:" + request.getMeetingEndTime() + "Z\n" + "LOCATION:" + request.getVenue() + "\n"
					+ "TRANSP:OPAQUE\n" + "SEQUENCE:0\n" + "UID:" + UUID.randomUUID() + "\n"
					+ " 000004377FE5C37984842BF9440448399EB02\n" + "DTSTAMP:" + request.getMeetingEndTime() + "Z\n"
					+ "CATEGORIES:Meeting\n" + "DESCRIPTION:" + request.getDescription() + "\n\n" + "SUMMARY:"
					+ request.getSummary() + "\n" + "PRIORITY:5\n" + "CLASS:PUBLIC\n" + "BEGIN:VALARM\n"
					+ "TRIGGER:PT1440M\n" + "ACTION:DISPLAY\n" + "DESCRIPTION:Reminder\n" + "END:VALARM\n"
					+ "END:VEVENT\n" + "END:VCALENDAR");

			System.out.println(buffer);
			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setHeader("Content-Class", "urn:content-classes:calendarmessage");
			messageBodyPart.setHeader("Content-ID", "calendar_message");
			messageBodyPart
					.setDataHandler(new DataHandler(new ByteArrayDataSource(buffer.toString(), "text/calendar")));//very important

			// Create a Multipart
			Multipart multipart = new MimeMultipart();

			// Add part one
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);

			// send message
			Transport.send(message);
			
		}
		catch( MessagingException me )
		{
			me.printStackTrace();
		}
		catch( Exception ex )
		{
			ex.printStackTrace();
		}
		return true;
	}

	public static void main( String[] args )
	{

		try
		{
			MeetingRequestPojo request = new MeetingRequestPojo();
			request.setMeetingEndTime("20160909T123000");
			request.setDescription("It explains the basic understanding of the problem we facing in marketing the app");
			request.setFrom("darshan.m@razorthink.net");
			request.setMeetingStartTime("20160909T110000");
			request.setReceipent("girishkumarm710@gmail.com,girish.kumar@razorthink.net");
			request.setSubject("Metting request to talk about brainblox");
			request.setSummary("It explains the basic understanding of the problem we facing in marketing the app");
			request.setVenue("Conference Room");

			send(request);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}