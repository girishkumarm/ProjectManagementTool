package com.tool.management;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyConfig extends ResourceConfig {

	public JerseyConfig()
	{
		register(RequestContextFilter.class);
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		packages("com.tool.management.rest.services");
		register(LoggingFilter.class);
		register(MultiPartFeature.class);
	}
}
