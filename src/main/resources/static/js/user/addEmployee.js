
var newEmployee=new Object();

$(document).ready(function(){
$("#addEmployeeButton").click(function(){

	console.log("hereeeeeeeeeee")


	var emailId=$("#empEmailId").val();
	var name=$("#name").val();
	var empId=$("#empId").val();
	var empType=$("#empType").val();
	var orgName=userDetails.organisation;
	var dateOfBirth=$("#dateOfBirth").val();

	if(emailId!=undefined && emailId!=""){
		if(!validate(emailId)){
			$("#empEmailId").val('');
			$("#empEmailId").toggleClass("error-placeholder");
			$("#empEmailId").attr("placeholder","Please enter a valid e-mail address *");
			return;
		}	
	}else{
		showMandatoryFieldError();
		return;
	}


	if(emailId==undefined || emailId=="" || name==undefined || name=="" || 
		empType==undefined || empType=="" || orgName==undefined || orgName=="" || dateOfBirth==undefined || dateOfBirth==""){
		showMandatoryFieldError();
		return;
	}else{
		newEmployee.emailId=emailId;
		newEmployee.name=name;
		newEmployee.organisation=orgName;
		newEmployee.employeeType=empType;
		newEmployee.key=empId;
		newEmployee.dateOfBirth=dateOfBirth;
		
		newEmployee.zipCode = userDetails.zipCode;
	}
	console.log(newEmployee)
	addEmployee(newEmployee);
});



function showMandatoryFieldError(){
	$("#mandatoryFieldError").removeClass("hide");
	$("#mandatoryFieldError").html("Enter value in all the fields*")
}


function showErrorMessage(code,message){
	console.log(code);
	console.log(message);
	$("#mandatoryFieldError").removeClass("hide");
	$("#mandatoryFieldError").html(message)
}


function addEmployee(input){
console.log(URL.ADD_EMPLOYEE)
	HTTPUtil.POST(URL.ADD_EMPLOYEE, input,function(output){
						console.log(JSON.stringify(output))
						if(output.status==200){
							$("#mandatoryFieldError").removeClass("hide");
							$("#mandatoryFieldError").html("Successfully Added Employee");
							$("#empEmailId").val('');
							$("#name").val('');
							$("#dateOfBirth").val('');
							$("#empId").val('');
						}else if(output.status==500){
							var message=output.responseJSON.message.split(",");
							var mes=message[0].split(":");
							showErrorMessage(message[1],mes[1])
						}
					});

}

});