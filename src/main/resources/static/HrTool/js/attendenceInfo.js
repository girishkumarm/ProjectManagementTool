var currentDate;
$(document).ready(function(){

		var input=new Object();
		input.employeeId = userDetails.id;
		input.name = userDetails.name;
		input.emailId = userDetails.emailId;
		currentDate = new Date();
		input.month = month[currentDate.getMonth()];
		input.year = currentDate.getFullYear() ;

		$("#currentDateHeader").html(input.month+" "+input.year);	

		getAttendanceInfo(input);
		

});

$(".previous-month").click(function(){

		var input=new Object();
		input.employeeId = userDetails.id;
		input.name = userDetails.name;
		input.emailId = userDetails.emailId;

		currentDate.setDate(1);
  		currentDate.setMonth(currentDate.getMonth()-1);
		input.month = month[currentDate.getMonth()];
		input.year = currentDate.getFullYear() ;	

		$("#currentDateHeader").html(input.month+" "+input.year);	

		getAttendanceInfo(input);

})


$(".next-month").click(function(){

		var input=new Object();
		input.employeeId = userDetails.id;
		input.name = userDetails.name;
		input.emailId = userDetails.emailId;

		currentDate.setDate(1);
  		currentDate.setMonth(currentDate.getMonth()+1);
		input.month = month[currentDate.getMonth()];
		input.year = currentDate.getFullYear() ;	

		$("#currentDateHeader").html(input.month+" "+input.year);	

		getAttendanceInfo(input);

})

function getAttendanceInfo(input){

	console.log(input)

	HTTPUtil.POST("http://localhost:8080/rest/emp-attendance/fetch-emp-attendance", input,function(output){
			if(output.responseEntity!=undefined && output.responseEntity!=null){
				if(output.responseEntity.length>0){
					$(".content").removeClass("hide");
					$(".empty-content").addClass("hide");
					$("#attendenceInfoRow").empty();
					$(".dateInfo").html(input.month+" "+input.year)
					for(var i=0 ;i < output.responseEntity.length ; i++){

						var startTime = new Date(output.responseEntity[i].loginTime);
						var endTime = new Date(output.responseEntity[i].logoutTime);
						var difference = endTime.getTime() - startTime.getTime(); // This will give difference in milliseconds
						var resultInMinutes = Math.round(difference / 60000);
						var hours = 0;
						if(resultInMinutes>60){
							hours = Math.floor(resultInMinutes);
							resultInMinutes = resultInMinutes-hours*60;
						}

						var workingTimePercentage = "";

						console.log(endTime.getTime());
						if(endTime.getTime()==0){
							workingTimePercentage =( new Date().getTime() - startTime.getTime()) / 32400000 * 100
						}else{

							workingTimePercentage = (endTime.getTime() - startTime.getTime()) / 32400000 * 100;
						}	
						
						var workingTimePercentageColor = "danger";
						if(workingTimePercentage>85){
							workingTimePercentageColor = "success";
						}
						else if(workingTimePercentage>75){
							workingTimePercentageColor = "warning";
						}

						var loginTimeString = "";
						loginTimeString = startTime.getHours()+" : "+startTime.getMinutes();

						if(startTime.getHours()>11){
							loginTimeString=loginTimeString+" pm";
						}else{
							loginTimeString=loginTimeString+" am";
						}

						var logoutTimeString = "";
						logoutTimeString = endTime.getHours()+" : "+endTime.getMinutes();

						if(endTime.getHours()>11){
							logoutTimeString=logoutTimeString+" pm";
						}else{
							logoutTimeString=logoutTimeString+" am";
						}

						var status = "";
						var statusColor = "";
						if(output.responseEntity[i].status==1){
							status = "Present";
							statusColor = "success"
							var hoursWorked = "Still Working";
							if(output.responseEntity[i].numOfHoursWorked!=null &&
								output.responseEntity[i].numOfHoursWorked!=undefined){
								hoursWorked = output.responseEntity[i].numOfHoursWorked;
							}
							console.log(output.responseEntity[i].numOfHoursWorked)
							var row = "<tr><td class=\"p-name\">"+output.responseEntity[i].day+"-"+output.responseEntity[i].month+"-"+output.responseEntity[i].year+"</td><td class=\"p-name\">"+loginTimeString+"</td><td class=\"p-progress\"><div class=\"progress progress-xs\"><div style=\"width: "+workingTimePercentage+"%;\" class=\"progress-bar progress-bar-"+workingTimePercentageColor+"\"></div></div><small>"+hoursWorked+"</small></td><td><span class=\"label label-"+statusColor+"\">"+status+"</span></td><td>"+logoutTimeString+"</td></tr>";
							$("#attendenceInfoRow").append(row)
						}else if(output.responseEntity[i].status==0){
							status = "Paid Leave";
							statusColor = "warning";
							var row = "<tr><td class=\"p-name\">"+output.responseEntity[i].day+"-"+output.responseEntity[i].month+"-"+output.responseEntity[i].year+"</td><td class=\"p-name\"></td><td class=\"p-progress\"></td><td><span class=\"label label-"+statusColor+"\">"+status+"</span></td><td></td></tr>";
							$("#attendenceInfoRow").append(row)
						}else if(output.responseEntity[i].status==-1){
							status = "Unpaid Leave";
							statusColor = "danger";
							var row = "<tr><td class=\"p-name\">"+output.responseEntity[i].day+"-"+output.responseEntity[i].month+"-"+output.responseEntity[i].year+"</td><td class=\"p-name\"></td><td class=\"p-progress\"></td><td><span class=\"label label-"+statusColor+"\">"+status+"</span></td><td></td></tr>";
							$("#attendenceInfoRow").append(row)
						}
					}
				}
				else{
					$(".content").addClass("hide");
					$(".empty-content").removeClass("hide");
				}
		  	}
		});
}