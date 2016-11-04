package com.tool.management.generic;

public interface Constants {

	public enum ExceptionItems {

			DUPLICATE_ORGANISATION("Organisation already registered"), DUPLICATE_PROJECT("Project Name Already Exist"),
			DUPLICATE_SPRINT("Trying to add a duplicate SprintName"),
			DUPLICATE_TASK("Trying to add a duplicate task id"), PROJECT_NOT_FOUND("Project is not registered"),
			SPRINT_NOT_FOUND("Sprint name not found"), TASK_NOT_FOUND("Task not found"),
			ORGANISATION_NOT_FOUND("Organisation is not registered"), USER_NOT_FOUND("User does not exist"),
			PASSWORD_DINT_MATCH("Password dint match"), MANDATORY_FIELDS_MISSING("Mandatory fields are missing"),
			RECORD_NOT_FOUND("Record not found..!"), DUPLICATE_EMPLOYEE("Employee already Exist");

		private final String constant;

		private ExceptionItems( String constant )
		{
			this.constant = constant;
		}

		public String getConstant()
		{
			return constant;
		}
	}

	public enum StatusCode {

			SUCCESSFUL("200"), FAILED("500"), DUPLICATE("409"), NOT_FOUND("404"), MANDATORY_FIELDS("400"),
			AUTHENTICATION_ERROR("501");

		private final String constant;

		private StatusCode( String constant )
		{
			this.constant = constant;
		}

		public String getConstant()
		{
			return constant;
		}
	}

	public enum ActivityStream {

			ADDED("added"), ACCEPTED("accepted"), LOGGED_WORK("logged work"), READY_FOR_REVIEW("ready for review"),
			READY_FOR_QA("ready for QA"), COMPLETED("completed"), ASSIGNED("assigned"), STATUS("status"), DESCRIPTION("description");

		private final String constant;

		private ActivityStream( String constant )
		{
			this.constant = constant;
		}

		public String getConstant()
		{
			return constant;
		}
	}

}
