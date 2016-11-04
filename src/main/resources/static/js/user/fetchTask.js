var taskDetails;

$(document).ready(function(){
	var taskId = getURLParameterByName('taskId');
	var orgName = localStorage.getItem('orgName');
	if(taskId!="" && taskId!=undefined){
		fetchTask(orgName,taskId);

		$("#logWorkButton").click(function(){
			taskDetails.totalTimeTaken = $("#taskTimeTaken").val();
			$("#taskTimeTaken").val("")

			updateTask(taskDetails)
		});

		$("#reviewButton").click(function(){
				taskDetails.status = "Review";
				taskDetails.totalTimeTaken = 0;
				updateTask(taskDetails)
		});

		$("#qaButton").click(function(){
				taskDetails.status = "QA";
				taskDetails.totalTimeTaken = 0;
				updateTask(taskDetails)
		});

		$("#completedButton").click(function(){
				taskDetails.status = "Completed";
				taskDetails.totalTimeTaken = 0;
				updateTask(taskDetails)
		});

		$("#acceptedButton").click(function(){
			console.log("hereeeeeeeeeee"+taskDetails.status)
			if(taskDetails.status =="Not Accepted"){
				taskDetails.status = "Accepted";
				taskDetails.totalTimeTaken = 0;
				updateTask(taskDetails)
			}
		});
	}
	
});

function updateTask(input){
	HTTPUtil.POST(URL.UPDATE_TASK, input,function(output){
						if(output.responseEntity!=undefined && output.responseEntity!=null){
							taskDetails=output.responseEntity;
							renderTaskDetails(output.responseEntity);
							$('#logWork').modal('hide');
					  	}
					});
}

var currentTask;
function fetchTask(orgName,taskId){
	HTTPUtil.GET(URL.FETCH_TASKS_BY_TASK_ID + ""+orgName+"/"+taskId, function(output){
		if(output!=undefined && output!=""){
			if(output.responseEntity!=undefined && output.responseEntity!=null){
				taskDetails=output.responseEntity;
				renderTaskDetails(output.responseEntity);
				currentTask=taskDetails;
		  	}
		  	else
		  	{
			  	localStorage.setItem('projectName',"");
			  	localStorage.setItem('sprintName',"");
		  	}
		}else if(true){
			//display 404 page
		}else{
			//display 500 page
		}
	});	
}


function getURLParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}



$(".editTask").click(function(){
			if(currentTask.id==this.id){
				console.log(currentTask)
				$("#taskProjectName").val(currentTask.projectName);
				$("#taskType").val(currentTask.type);
				$("#taskTitle").val(currentTask.title);
				//$("#taskPriority").hmtl(taskList[i].);
				$("#taskSprintName").val(currentTask.sprintName);
				$("#projectAssignee").val(currentTask.assignee);
				$("#taskEstimatedTime").val(currentTask.estimatedTime);
				var desc=currentTask.description;
				desc=desc.replace(/<br \/>/g,"\n")
				$("#taskDescription").val(desc);
				$("#updateTaskId").val(this.id)	;			
			}
	});

function renderTaskDetails(responseEntity){

	$(".editTask").attr("id",responseEntity.id);

	$("#taskDisplayAssignee").html(responseEntity.assignee);
	
	$("#taskDisplayTitle").html(responseEntity.title);
	
	$("#taskDisplayStatus").html(responseEntity.status);
	
	$("#createdAt").html(new Date(responseEntity.createdAt).toUTCString());
	
	if(responseEntity.status=="Completed"){
		$("#updatedAtRow").addClass("hide")
		$("#reportedAt").html(new Date(responseEntity.updatedAt).toUTCString());
	}else{
		$("#reportedAtRow").addClass("hide")
		$("#updatedAt").html(new Date(responseEntity.updatedAt).toUTCString());
	}
	
	$("#tDisplayprojectName").html(responseEntity.projectName);

	$("#estimatedTime").html(responseEntity.estimatedTime);
	
	$("#totalTimeTaken").html(responseEntity.totalTimeTaken);
	
	$("#remainingTime").html(responseEntity.remainingTime);

	$("#acceptedButton").html(responseEntity.status);

	$("#taskDisplayDescription").html(responseEntity.description)

	if(responseEntity.status=="Accepted" || responseEntity.status=="Accept"){
		$("#acceptedButton").addClass("btn-success");
		$("#acceptedButton").html("Accepted")
		$("#reviewButton").removeClass("hide");
	}
	else if(responseEntity.status=="Not Accepted"){
		$("#acceptedButton").html("Accept");
		$("#acceptedButton").addClass("btn-default");
	}
	else if(responseEntity.status=="Review"){
		$("#reviewButton").removeClass("hide");
		$("#acceptedButton").addClass("hide");
		$("#reviewButton").addClass("btn-success");
		$("#qaButton").removeClass("hide");
	}
	else if(responseEntity.status=="QA"){
		$("#reviewButton").addClass("hide");
		$("#qaButton").removeClass("hide");
		$("#qaButton").addClass("btn-success");
		$("#acceptedButton").addClass("hide");
		$("#completedButton").removeClass("hide");
	}
	else if(responseEntity.status=="Completed"){
		$("#assignButton").addClass("hide")
		$("#logworkButton").addClass("hide")
		$("#reviewButton").addClass("hide");
		$("#acceptedButton").addClass("hide");
		$("#qaButton").addClass("hide");
		$("#completedButton").removeClass("hide");
		$("#completedButton").addClass("btn-danger");
	}


	if(responseEntity.remainingTime==0){
		$("#remainingTime").css('width' , '0');
		$("#totalTimeTaken").css('width' , '100');
		$("#taskCompBar").css('width' , '0');
		
	}else{
		var remPercentage=(responseEntity.remainingTime/responseEntity.estimatedTime)*100;
		remPercentage=Math.ceil(remPercentage);
		$("#remainingTime").css('width' , remPercentage+"%");
		$("#totalTimeTaken").css('width' , (100-remPercentage)+"%");
		$("#taskCompBar").css('width' , (100-remPercentage)+"%");
		$("#taskCompBarPer").html((100-remPercentage)+"%")
	}
}