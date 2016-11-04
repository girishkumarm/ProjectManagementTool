$(document).ready(function(){

    $(".userName").html(userDetails.name);
    $(".userTitle").html(userDetails.title);
    $(".orgName").html(userDetails.organisation.toUpperCase());


    fetchNotification();
    fetchTasks();
    addOnClickListeners();

    if(localStorage.getItem("clockIn")!=null && localStorage.getItem("clockIn")!=undefined){
    	if(localStorage.getItem("clockIn")=="true"){
    		$("#clockIn").addClass("hide");
			$("#clockOut").removeClass("hide");
    	}else{
			$("#clockOut").addClass("hide");
			$("#clockIn").removeClass("hide");
    	}
    }else{
    	var input=new Object();
		input.employeeId = userDetails.id;
		input.name = userDetails.name;
		input.emailId = userDetails.emailId;
		var d = new Date();
		input.day = d.getDate();
		input.month = month[d.getMonth()];
		input.year = d.getFullYear() ;	
		HTTPUtil.POST("http://localhost:8080/rest/emp-attendance/fetch-emp-attendance", input,function(output){
			if(output.responseEntity!=undefined && output.responseEntity!=null){
				if(output.responseEntity.length>0){
					loginTime=output.responseEntity[0].loginTime;
		    		$("#clockIn").addClass("hide");
					$("#clockOut").removeClass("hide");
				}
		  	}
		});
    }

    checkForLogoutIssue();


});

var month = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
var loginTime = 0;


function  checkForLogoutIssue(){

var input=new Object();
input.employeeId = userDetails.id;
input.name = userDetails.name;
input.emailId = userDetails.emailId;

var currentDate = new Date();
currentDate.setDate(currentDate.getDate()-1);
input.month = month[currentDate.getMonth()];
input.year = currentDate.getFullYear() ;	
input.day = currentDate.getDate();	

console.log(input)
HTTPUtil.POST("http://localhost:8080/rest/emp-attendance/fetch-emp-attendance", input,function(output){
			if(output.responseEntity!=undefined && output.responseEntity!=null){
				if(output.responseEntity.length>0){
					for(var i=0 ;i < output.responseEntity.length ; i++){

						if(output.responseEntity[i].logoutTime==0 && output.responseEntity[i].status==1){
							console.log("Dint log out")
							$("#clockOutYestModal").modal("show");
						}
					}
				}
		  	}
		});
}




function addOnClickListeners(){

	$("#clockInModalClick").click(function(){
		var input=new Object();
		input.employeeId = userDetails.id;
		input.name = userDetails.name;
		input.emailId = userDetails.emailId;
		var d = new Date();
		input.day = d.getDate();
		input.month = month[d.getMonth()];
		input.year = d.getFullYear() ;
		input.status = 1 ;
		input.loginTime = d.getTime();

		console.log(input)

		HTTPUtil.POST("http://localhost:8080/rest/emp-attendance/add-emp-attendance", input,function(output){
			console.log(output)
			if(output.responseEntity!=undefined && output.responseEntity!=null){
				loginTime=output.responseEntity.loginTime;
		  	}
		});
		localStorage.setItem("clockIn","true");
		localStorage.setItem("clockOut","false");
		$("#clockIn").addClass("hide");
		$("#clockOut").removeClass("hide");
	})

	$("#clockOutModalClick").click(function(){

		var input=new Object();
		input.employeeId = userDetails.id;
		input.name = userDetails.name;
		input.emailId = userDetails.emailId;
		var d = new Date();
		input.day = d.getDate();
		input.month = month[d.getMonth()];
		input.year = d.getFullYear() ;	
		input.logoutTime = d.getTime();
		input.loginTime = loginTime;
		input.status = 1;

		console.log(input)

		clockOut(input);

		
	})

	$("#clockOut").click(function(){
		var input=new Object();
		input.employeeId = userDetails.id;
		input.name = userDetails.name;
		input.emailId = userDetails.emailId;
		var d = new Date();
		input.day = d.getDate();
		input.month = month[d.getMonth()];
		input.year = d.getFullYear() ;	
		HTTPUtil.POST("http://localhost:8080/rest/emp-attendance/fetch-emp-attendance", input,function(output){
			if(output.responseEntity!=undefined && output.responseEntity!=null){
				var startTime = new Date(output.responseEntity[0].loginTime);
				var currentTime = new Date();
				var difference = currentTime.getTime() - startTime.getTime(); // This will give difference in milliseconds
				var resultInMinutes = Math.round(difference / 60000);
				if(resultInMinutes>60){
					var hours = Math.floor(resultInMinutes);
					$("#clockOutText").html("You worked for "+hours+" hours "+(resultInMinutes-hours*60)+" minutes today.")
				}else{
					$("#clockOutText").html("You worked for "+resultInMinutes+" minutes today.")
				}
		  	}
		});
	});


	$("#clockOutYestModalClick").click(function(){

		var input=new Object();
		input.employeeId = userDetails.id;
		input.name = userDetails.name;
		input.emailId = userDetails.emailId;
		var d = new Date();
		input.day = d.getDate()-1;
		input.month = month[d.getMonth()];
		input.year = d.getFullYear() ;	
		input.logoutTime = -1;
		input.status = 1;
		console.log($("#lastDayworkingHours").val())
		console.log($("#lastDayworkingMinutes").val())
		input.numOfHoursWorked = $("#lastDayworkingHours").val()+" hr "+$("#lastDayworkingMinutes").val()+" m"

		console.log(input)

		clockOut(input);

	});

}

function fetchNotification(){

	HTTPUtil.GET("http://localhost:8080/rest/notification/fetch-notification/"+userDetails.id , function(output){
		if(output!=undefined && output!=""){
			if(output.responseEntity!=undefined && output.responseEntity!=null){
				//notification part 
			    //length
			    $("#notificationLength").html(output.responseEntity.length);
			    //length with data
			    $("#notificationMeta").html("You have "+output.responseEntity.length+" notifications")
			    //actual notification data
			    $("#notificationDataList").empty();
			    for(var i =0 ;i<output.responseEntity.length ;i++){
			    	$("#notificationDataList").append("<li><a href=\"#\"><i class=\"fa fa-users text-aqua\"></i> "+output.responseEntity[i].description+"</a></li>")
			    }
		  	}
		  	else
		  	{
		  		$("#notificationLength").html("0");
		  		$("#notificationMeta").html("You have no notifications");
		  	}
		}else if(true){
			//display 404 page
		}else{
			//display 500 page
		}
	});	
	

}

function fetchTasks(){

	//tasks of a user
    $("#taskLength").html("10");
    $("#taskMeta").html("You have 10 tasks")
    $("#taskData").empty();
    for(var i =0 ;i<5 ;i++){
    	$("#taskData").append("<li><a href=\"#\"><h3>Design some buttons<small class=\"pull-right\">"+i*20+"%</small></h3><div class=\"progress xs\"><div class=\"progress-bar progress-bar-aqua\" style=\"width: "+i*20+"%\" role=\"progressbar\" aria-valuenow=\""+i*20+"\" aria-valuemin=\"0\" aria-valuemax=\"100\"><span class=\"sr-only\">"+i*20+"% Complete</span></div></div></a></li>");
    }

}

function clockOut(input){

HTTPUtil.POST("http://localhost:8080/rest/emp-attendance/update-emp-attendance", input,function(output){
			$("#clockOutYestModal").modal("hide");
		});
		
	
		localStorage.setItem("clockIn","false");
		localStorage.setItem("clockOut","true");
		$("#clockOut").addClass("hide");
		$("#clockIn").removeClass("hide");

}