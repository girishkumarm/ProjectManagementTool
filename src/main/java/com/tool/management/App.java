package com.tool.management;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableAutoConfiguration
@ComponentScan( "com.tool.management.*" )
public class App {

	@Resource
	private Environment env;

	public static void main( String[] args )
	{
		SpringApplication.run(App.class, args);
	}

	@Bean
	public ServletRegistrationBean jerseyServlet()
	{
		ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/rest/*");
		registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
		return registration;
	}

	@Bean
	public DataSource dataSource()
	{
		HikariConfig config = new HikariConfig();
		config.setMaximumPoolSize(100);
		config.setDataSourceClassName(env.getProperty("spring.datasource.driver-class-name"));
		config.addDataSourceProperty("url", env.getProperty("spring.datasource.url"));
		config.addDataSourceProperty("user", env.getProperty("spring.datasource.username"));
		config.addDataSourceProperty("password", env.getProperty("spring.datasource.password"));
		return new HikariDataSource(config);
	}
}
