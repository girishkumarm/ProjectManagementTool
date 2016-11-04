var userDetails;
var currentLocation ;
var projectName ;
var clockIn ;
var clockOut ;

function authenticate(){
	userDetails = localStorage.getItem("userDetails");
	userDetails = JSON.parse(userDetails);
	currentLocation = location.href;
	if(userDetails==undefined || userDetails == null || userDetails == "null"){
		if(!currentLocation.includes("login.html") && !currentLocation.includes("register.html"))
		{
			if(!currentLocation.includes("HrTool") ){
				location.href="login.html";
			}else{
				location.href="../login.html";
			}
		}
	}else{	

		//adding the addemployee and create sprint button	
		console.log("userDetails.employeeType : "+userDetails.employeeType)
		if(userDetails.employeeType=="Normal"){
			$("#addEmployeeIcon").addClass("hide");
			$("#createSprintBut").addClass("hide");

			//hide classes in profile
			$(".rootUser").addClass("hide");
			$(".adminUser").addClass("hide");
		}

		//add employee button for admin
		if(userDetails.employeeType=="Admin"){
			$("#addEmployeeIcon").addClass("hide");

			//hide classes in profile
			$(".rootUser").addClass("hide");
		}

		//dashboard button
		$("#homeButton").click(function(){
			location.href="main.html";
		});
		

		//home page button
		$("#homeButton1").click(function(){
			location.href="main.html";
		});

		//displaying name on top right
		$("#topRightUserName").html(userDetails.name)

		$(document).ready(function(){
		    if(currentLocation.includes("index.html")){

		    	if(userDetails.employeeType!="ROOT"){
		    		console.log("adding class hide")
		    		$(".unAssignedUsers").addClass("hide");
		    		$(".orgChart").addClass("orgChartAlone");
		    		$(".orgChartAlone").removeClass("orgChart");
		    		$("#SaveButton").addClass("hide")
		    		$("#SaveButton").removeClass("button")
		    		$("#orgChart").attr("style","width:1810px")
		    	}
		    		$("#meetingRequest").addClass("hide")

		    }
		})
	}
}


$("#logoutButton").click(function(){
	userDetails=null;
	localStorage.setItem("userDetails","null");
	//location.href="login.html";
	if(!currentLocation.includes("HrTool") ){
		location.href="login.html";
	}else{
		location.href="../login.html";
	}
});


function getURLParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

$(document).ready(function(){

			userDetails=localStorage.getItem('userDetails');

			authenticate();

	});


