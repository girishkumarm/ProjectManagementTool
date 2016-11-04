var newSprint=new Object();

$("#addSprintButton").click(function(){
	var startDate=new Date($("#startDateOfSprint").val());
	console.log($("#endDateOfSprint").val())
	var endDate=new Date($("#endDateOfSprint").val());
	var createdBy=userDetails.emailId;
	var orgName=userDetails.organisation;
	var projName=$("#sprintProjectName").val();
	var zipCode=userDetails.zipCode;
	var holidays=$("#holidays").val();
	console.log(holidays)


	if(projectName==undefined || projectName=="" 
	   || startDate==undefined || startDate==""
	   || endDate==undefined || endDate=="" || endDate==null ){
		showMandatoryFieldError();
		return;
	}else{
		newSprint.startDate=startDate.getTime();
		console.log(endDate)
		newSprint.endDate=endDate.getTime();
		newSprint.createdBy=createdBy;
		newSprint.orgName=orgName;
		newSprint.projName=projName;
		newSprint.zipCode=zipCode;
		newSprint.holidays=holidays;
	}
	console.log(newSprint)
	addSprint(newSprint);
});



function showMandatoryFieldError(){
	$("#sprintMandatoryFieldError").removeClass("hide");
	$("#sprintMandatoryFieldError").html("Enter value in all the fields*")
}


function showSprintErrorMessage(code,message){
	console.log(code);
	console.log(message);
	$("#sprintMandatoryFieldError").removeClass("hide");
	$("#sprintMandatoryFieldError").html(message)
}


function addSprint(input){
console.log(URL.ADD_SPRINT)
	HTTPUtil.POST(URL.ADD_SPRINT, input,function(output){
						console.log(JSON.stringify(output))
						if(output.status==200){
							$("#sprintMandatoryFieldError").removeClass("hide");
							$("#sprintMandatoryFieldError").html("Successfully Added new Sprint");
							$("#startDate").val('');
							$("#endDate").val('');
							$("#addSprint").modal('hide')
						}else if(output.status==500){
							var message=output.responseJSON.message.split(",");
							var mes=message[0].split(":");
							showSprintErrorMessage(message[1],mes[1])
						}
					});

}

