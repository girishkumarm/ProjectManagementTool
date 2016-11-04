
var regObject=new Object();

$("#regButton").click(function(e){
	var emailId=$("#emailId").val();
	var userName=$("#userName").val();
	var orgName=$("#orgName").val();
	var orgAddress=$("#orgAddress").val();
	var regNum=$("#regNum").val();
	var pass1=$("#pass1").val();
	var pass2=$("#pass2").val();

	if(emailId!=undefined && emailId!=""){
		if(!validate(emailId)){
			$("#emailId").val('');
			$("#emailId").toggleClass("error-placeholder");
			$("#emailId").attr("placeholder","Please enter a valid e-mail address *");
			return;
		}	
	}else{
		console.log("1")
		showMandatoryFieldError();
		return;
	}

	if(pass1!=undefined && pass1!="" && pass2!=undefined && pass2!=""){
		if(pass1.length<8 || pass1.length>20){
			$("#pass1").val('')		
			$("#pass2").val('')
			$("#pass1").attr("placeholder","Length of password should be in range 8-20");
			$("#pass1").toggleClass("error-placeholder");	
			return;
		}
		if(!(pass1===pass2)){
			$("#pass1").val('')		
			$("#pass2").val('')
			$("#pass2").attr("placeholder","Password dint match");
			$("#pass2").toggleClass("error-placeholder");
			return;
		}
	}else{
		console.log("2")
		showMandatoryFieldError();
		return;
	}

	if(userName==undefined || userName=="" || emailId==undefined || emailId=="" || orgName==undefined || orgName=="" || 
		orgAddress==undefined || orgAddress=="" || regNum==undefined || regNum=="" ||
		pass1==undefined || pass1=="" || pass2==undefined || pass2=="" ){
		console.log("3")
		showMandatoryFieldError();
		return;
	}else{
		regObject.emailId=emailId;
		regObject.userName=userName;
		regObject.organisationName=orgName;
		regObject.password=pass1;
		regObject.registrationNumber=regNum;
		regObject.address=orgAddress;
	}
	registerUser(regObject);
})

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


function registerUser(input){

	HTTPUtil.POST(URL.REGISTER_USER, input,function(output){
						console.log(output.status)
						if(output.status==200){
							localStorage.setItem('userDetails',JSON.stringify(output.responseEntity));
							console.log(localStorage.getItem('userDetails'));
							location.href="main.html";
						}else if(output.status==500){
							$("#mandatoryFieldError").removeClass("hide");
							$("#mandatoryFieldError").html("Registration Failed")
							var message=output.responseJSON.message.split(",");
							var mes=message[0].split(":");
							showErrorMessage(message[1],mes[1])
						}

					});

}