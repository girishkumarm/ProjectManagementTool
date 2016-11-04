var sickLeavesDate =[];
var annualLeavesDate =[];
var otherLeavesDate =[];
var currentHolidayDate;
var sickLeaves=0;
var annualLeaves=0;
var otherLeaves=0;
var totalLeaves=0;
var leaveType = ["Annual Leaves","Sick Leaves","Others"];
var leaveTypeId = ["AnnualLeaves","SickLeaves","Others"];
var sickLeaveTaking = 0;
var annualLeaveTaking = 0;
var othersLeaveTaking = 0;
var requestLeaveDates = "";


$(document).ready(function(){

	fetchLeavesOfEmployees();

	addDatePicker();

});

$("#cancelRequestLeaveButton").click(function(){

	clearLeaveRequestPage();

})

function clearLeaveRequestPage(){

	$(".leaveRequestDiv").addClass("hide");
	$(".leavesInfo").empty();
	$(".selected_dates").addClass("hide");	
	sickLeaves=sickLeaves+sickLeaveTaking;
	annualLeaves=annualLeaves+annualLeaveTaking;
	console.log(annualLeaves)
	otherLeaves=otherLeaves+othersLeaveTaking;
	totalLeaves=0;
	sickLeaveTaking = 0;
	annualLeaveTaking = 0;
	othersLeaveTaking = 0;
	requestLeaveDates = "";
	sickLeavesDate =[];
	annualLeavesDate =[];
	otherLeavesDate =[];
	addLeavesInModal();
}

$("#requestLeaveButton").click(function(){

	var input = new Object();
	input.employeeId=userDetails.id;
	input.employeeName = userDetails.name;
	input.emailId = userDetails.emailId;
	input.status = "Pending";
	input.teamLead = userDetails.teamLead;
	if($("#leaveRequestDescription").val()=="" || $("#leaveRequestDescription").val()==""){
		input.description = "No Description Available";
	}else{
		input.description = $("#leaveRequestDescription").val();
	}
	input.dates = requestLeaveDates;
	input.totalDays = annualLeaveTaking+sickLeaveTaking+othersLeaveTaking;
	input.annual = annualLeaveTaking;
	input.sick = sickLeaveTaking;
	input.others = othersLeaveTaking;
	input.unPaid = 0;
	input.createdAt = new Date();

	console.log(URL.SEND_LEAVE_REQUEST);

	HTTPUtil.POST(URL.SEND_LEAVE_REQUEST, input,function(output){
		if(output.status==200){
			clearLeaveRequestPage();	
			fetchLeavesOfEmployees();
		}

	});

});

function addDateSelectEvent(){

	$('input[type="radio"]').click(function(){
	    if ($(this).is(':checked'))
	    {
	    	var id=$(this).attr("id");
	    	if(id=="SickLeaves"){
	    		sickLeavesDate[sickLeaveTaking]=currentHolidayDate;
	    		sickLeaves=sickLeaves-1;
	    		sickLeaveTaking=sickLeaveTaking+1;
	    	}else if(id=="AnnualLeaves"){
	    		annualLeavesDate[annualLeaveTaking]=currentHolidayDate;
	    		annualLeaves=annualLeaves-1;
	    		annualLeaveTaking=annualLeaveTaking+1;
	    	}else if(id=="Others"){
	    		otherLeavesDate[othersLeaveTaking]=currentHolidayDate;
	    		otherLeaves=otherLeaves-1;
	    		othersLeaveTaking=othersLeaveTaking+1;
	    	}
	    	addLeavesInModal();

	    	$(".leavesInfo").append("<div class=\"row\" style=\"margin-top:10px\"><div class=\"col-md-3\"><input type=\"text\" disabled class=\"form-control\" value=\""+currentHolidayDate+"\" ></div><div class=\"col-md-3\"><input type=\"text\" disabled class=\"form-control \" value=\""+id+"\" ></div><div class=\"col-md-6\"></div></div>");

	    	if(requestLeaveDates==""){
	    		requestLeaveDates=currentHolidayDate+":"+id;
	    	}else{
	    		requestLeaveDates=requestLeaveDates+"|"+(currentHolidayDate+":"+id);
	    	}
	    	console.log(requestLeaveDates)

	    	$("#selectHolidayType").modal("hide");
	    	$('#datePickerRow').removeClass("hide")
			$("#closeDatePicker").addClass("hide")
			$("#doneOnRequest").addClass("hide")
	    	$('#datetimepicker1').datepicker("hide");
	    	$(".selected_dates").removeClass("hide")
	    }
	  });

}

		        
function addDatePicker(){

	$( "#datetimepicker1" ).click(function(){

		$(".leaveRequestDiv").removeClass("hide");

		$('#datePickerRow').addClass("hide")
		$("#closeDatePicker").removeClass("hide")
		$("#doneOnRequest").removeClass("hide")

	});

	$( "#closeDatePicker" ).click(function(){

		$('#datetimepicker1').datepicker("hide");
		$("#closeDatePicker").addClass("hide")
		$("#datePickerRow").removeClass("hide")
		$("#doneOnRequest").addClass("hide")
	});

	$("#doneOnRequest").click(function(){

		$('#datetimepicker1').datepicker("hide");
		$("#closeDatePicker").addClass("hide")
		$("#datePickerRow").removeClass("hide")
		$("#doneOnRequest").addClass("hide")

	})

	jQuery.datepicker._checkExternalClick = function() {};
	$( "#datetimepicker1" ).datepicker(
	   { 
	      minDate: 0,
	      numberOfMonths: [2, 3],
	      onSelect: function(selected,evnt) {
	            $(this).data('datepicker').inline = true;  
	            currentHolidayDate=$('#datetimepicker1').val()
	             $('#datetimepicker1').val("Add Leave")
	            $("#selectHolidayType").modal("show");
	            addDateSelectEvent();
	       },
	       onClose: function() {
	          $(this).data('datepicker').inline = false;
	      },
	      beforeShowDay: function(date) {
	      		if(date.getDate()<10)
	      		{
	      			if(date.getMonth()<9)
	      			{
	      				date = "0"+(date.getMonth()+1)+"/0"+date.getDate()+"/"+date.getFullYear();
	      			}
	      			else
	      			{
	      				date = (date.getMonth()+1)+"/0"+date.getDate()+"/"+date.getFullYear();
	      			}
	      			
	      		}
	      		else
	      		{
	      			if(date.getMonth()<9)
	      			{
	      				date = "0"+(date.getMonth()+1)+"/"+date.getDate()+"/"+date.getFullYear();
	      			}
	      			else
	      			{
	      				date = (date.getMonth()+1)+"/"+date.getDate()+"/"+date.getFullYear();
	      			}

	      		}
	      		
		        if (sickLeavesDate.indexOf(date)!=-1) {
		             return [true, "event", "Sick leave"];
		        } 
		        else if (annualLeavesDate.indexOf(date)!=-1) {
		        	console.log(date)
		             return [true, "event", "Annual leave"];
		        }
		        else if (otherLeavesDate.indexOf(date)!=-1) {
		             return [true, "event", "Other leave"];
		        } 
		        else {
		             return [true, '', 'Want leave??'];
		        }
		     }
	   }
	); 

}

var leavesCount = 1;
$("#addLeaves").click(function(){

	leavesCount++;
	var row = "<div class=\"row\"><div class=\"col-md-6\"><div class=\"form-group\"><label>Select Date:</label><div class=\"input-group\"><div class=\"input-group-addon\"><i class=\"fa fa-calendar\"></i></div><input type=\"text\"class=\"form-control\"data-inputmask=\"'alias':'dd/mm/yyyy'\"data-mask placeholder=\"dd/mm/yyyy\"id=\"leaveDate"+leavesCount+"\"></div></div></div><div class=\"col-md-6\"><div class=\"form-group\"><label>Leave Type</label><select class=\"form-control\"style=\"width:100%;\"id=\"leaveType"+leavesCount+"\"><option selected=\"selected\">Annual Leave</option><option>Other Leave</option><option>Sick Leave</option></select></div></div></div>";
	$("#holidayTequestRows").append(row);

})



function fetchLeavesOfEmployees(){

	HTTPUtil.GET("http://localhost:8080/rest/holiday-info/fetch/"+userDetails.emailId+"/2016",function(output){
			if(output.responseEntity!=undefined && output.responseEntity!=null){
				

				console.log(output)
				$("#year").html(output.responseEntity.year);

				sickLeaves = output.responseEntity.sick;
				if(output.responseEntity.sickLeavesTaken!=null){
					sickLeaves = output.responseEntity.sick - output.responseEntity.sickLeavesTaken;
				}

				annualLeaves = output.responseEntity.annual;
				if(output.responseEntity.sickLeavesTaken!=null){
					annualLeaves = output.responseEntity.annual - output.responseEntity.annualLeavesTaken;
				}

				otherLeaves = 0;
				if(output.responseEntity.others!=null){
					otherLeaves = output.responseEntity.others;
				}

				totalLeaves = sickLeaves + annualLeaves + otherLeaves ; 

				$("#sickLeavesLeft").html(sickLeaves+ " days");
				$("#annualLeavesLeft").html(annualLeaves+ " days");
				$("#otherLeavesLeft").html(otherLeaves+ " days");
				$("#totalLeavesLeft").html(totalLeaves+ " days");

				addLeavesInModal();
		  	}
		});

}

function addLeavesInModal(){

	var totalLeavesInType = [annualLeaves,sickLeaves,otherLeaves];
	$(".holiday_type_modal").empty();
	for(var i = 0 ;i<3 ;i++){
		var leaveDetails=""
		if(totalLeavesInType[i]>0){
			leaveDetails = "<div class=\"radio row\"><div class=\"col-md-2\"></div><div class=\"col-md-4\"><input type=\"radio\" id=\""+leaveTypeId[i]+"\" name=\"selectLeave\">"+leaveType[i]+"</div><div class=\"col-md-4\"><input type=\"text\" disabled value=\""+totalLeavesInType[i]+" left\" style=\"float:right\"></div></div>";
		}else{
			leaveDetails = "<div class=\"radio row\"><div class=\"col-md-2\"></div><div class=\"col-md-4\"><input type=\"radio\" id=\""+leaveTypeId[i]+"\" disabled name=\"selectLeave\">"+leaveType[i]+"</div><div class=\"col-md-4\"><input type=\"text\" disabled value=\""+totalLeavesInType[i]+" left\" style=\"float:right\"></div></div>";
		}
		$(".holiday_type_modal").append(leaveDetails)
     }

}

