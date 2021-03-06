package com.tool.management.generic;

// Generated Jun 16, 2015 3:17:33 PM by Hibernate Tools 3.2.0.CR1

/**
 * SendEmailInput generated by hbm2java
 */
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SendEmailInput implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String fromAddress;
	private String inbloxUserId;
	private String fullName;
	private String subject;
	private String bodyText;
	private String bodyHTML;
	private boolean contentTypeHTML;

	public SendEmailInput()
	{
	}

	public SendEmailInput( String fromAddress, String inbloxUserId, String fullName, String subject, String bodyText,
			String bodyHTML, boolean contentTypeHTML )
	{
		this.fromAddress = fromAddress;
		this.inbloxUserId = inbloxUserId;
		this.fullName = fullName;
		this.subject = subject;
		this.bodyText = bodyText;
		this.bodyHTML = bodyHTML;
		this.contentTypeHTML = contentTypeHTML;
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getFromAddress()
	{
		return this.fromAddress;
	}

	public void setFromAddress( String fromAddress )
	{
		this.fromAddress = fromAddress;
	}

	public String getInbloxUserId()
	{
		return this.inbloxUserId;
	}

	public void setInbloxUserId( String inbloxUserId )
	{
		this.inbloxUserId = inbloxUserId;
	}

	public String getFullName()
	{
		return this.fullName;
	}

	public void setFullName( String fullName )
	{
		this.fullName = fullName;
	}

	public String getSubject()
	{
		return this.subject;
	}

	public void setSubject( String subject )
	{
		this.subject = subject;
	}

	public String getBodyText()
	{
		return this.bodyText;
	}

	public void setBodyText( String bodyText )
	{
		this.bodyText = bodyText;
	}

	public String getBodyHTML()
	{
		return this.bodyHTML;
	}

	public void setBodyHTML( String bodyHTML )
	{
		this.bodyHTML = bodyHTML;
	}

	public boolean isContentTypeHTML()
	{
		return this.contentTypeHTML;
	}

	public void setContentTypeHTML( boolean contentTypeHTML )
	{
		this.contentTypeHTML = contentTypeHTML;
	}

	private List<String> toAddresses = new ArrayList<String>();

	@XmlElement
	public List<String> getToAddresses()
	{
		return toAddresses;
	}

	public void settoAddresses( List<String> arr )
	{
		this.toAddresses = arr;
	}

	private List<String> ccAddresses = new ArrayList<String>();

	@XmlElement
	public List<String> getCcAddresses()
	{
		return ccAddresses;
	}

	public void setccAddresses( List<String> arr )
	{
		this.ccAddresses = arr;
	}

	private List<String> bccAddresses = new ArrayList<String>();

	@XmlElement
	public List<String> getBccAddresses()
	{
		return bccAddresses;
	}

	public void setbccAddresses( List<String> arr )
	{
		this.bccAddresses = arr;
	}

	private List<String> replyTo = new ArrayList<String>();

	@XmlElement
	public List<String> getReplyTo()
	{
		return replyTo;
	}

	public void setreplyTo( List<String> arr )
	{
		this.replyTo = arr;
	}

}
