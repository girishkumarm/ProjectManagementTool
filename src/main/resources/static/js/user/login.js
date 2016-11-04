$(document).ready(function(){

	$("#loginButton").click(function(){
		$("#loginFailed").addClass("hide");
		var taskDetails=new Object();
		var emailId = $("#emailId").val();
		var password = $("#password").val();

		if(emailId!=undefined && emailId!=""){
			if(!validate(emailId)){
				$("#emailId").val('');
				$("#emailId").toggleClass("error-placeholder");
				$("#emailId").attr("placeholder","Please enter a valid e-mail address *");
				return;
			}	
		}

		if(emailId==undefined || emailId=="" 
			|| password==undefined || password=="")
		{
			showMandatoryFieldError();
			return;
		}

		taskDetails.emailId = emailId;
		taskDetails.password = password;


		userLogin(taskDetails)
	});

});

function showMandatoryFieldError(){
	$("#loginFailed").removeClass("hide");
	$("#loginFailed").html("Enter value in all the fields*")
}

function userLogin(input){
	HTTPUtil.POST(URL.LOGIN, input,function(output){
						console.log(JSON.stringify(output))
						if(output!=undefined && output!=null){
							if(output.status=="404"){
								//user not exist
								$("#loginFailed").removeClass("hide");
								$("#loginFailed").html("Email id does not exist")
							}else if(output.status=="501"){
								//password dint match
								$("#loginFailed").removeClass("hide");
								$("#loginFailed").html("Password dint match try again")
							}else if(output.status=="200"){
								localStorage.setItem('userDetails',JSON.stringify(output.responseEntity));
								var type=output.responseEntity.employeeType;
								console.log(type)
								console.log(localStorage.getItem('userDetails'))
								location.href="main.html";
								
							}
					  	}
					});
}