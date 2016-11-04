$(document).ready(function(){

	projectName = getURLParameterByName('pName');

	currentBoardProject = getURLParameterByName('pName');

	$("#projectHeaderInProjectPage").html(projectName);

	if(projectName==""){
	  	projectName=currentProject;
	  }
		
});

$("#logoutButton").click(function(){
	userDetails=null;
	localStorage.setItem("userDetails","null");
});

function addTaskListener(){
	$(".taskTile").click(function(){
		var taskId=this.id;
	})
}

function getUserOfProject(){

  var orgName = userDetails.organisation;
  var zipCode = userDetails.zipCode;


  var endpoint=URL.FETCH_USERS_OF_PROJECT+orgName+"/"+userDetails.zipCode+"/"+projectName;


  
  if(projectName!="" && projectName!=undefined){
	  HTTPUtil.GET(endpoint,function(output){
	            if(output!=undefined && output!=""){
	              if(output.responseEntity!=undefined &&output.responseEntity!=null){
	                $("#boardRows").empty();
	                if(output.responseEntity.length>0){

	                  for(var i=0;i<output.responseEntity.length;i++){
	                    var emailId = output.responseEntity[i];
	                    var userLabel = "others";
	                    if(emailId==userDetails.emailId){
	                    	userLabel = "mines";
	                    }
	                    var userName = emailId.split("@");
	                    userName=userName[0].toLowerCase();
	                    var createBoardRow="<section class=\"panel tasks-widget "+userLabel+"\"><header class=\"panel-heading\"><h4 id=\""+emailId+"\">"+emailId.toLowerCase()+"</h4></header><div class=\"panel-body\" ><div  class=\"col-md-2\" style=\"background-color:#f5f5f5;margin-right:3px\"><ul id=\""+userName+"NotAccepted\" ondrop=\"drop(event)\" class=\"drag Accept\" ondragover=\"allowDrop(event)\"></ul></div><div class=\"col-sm-2\" style=\"background-color:#f5f5f5;margin-right:3px\"><ul class=\"drag Accepted\" id=\""+userName+"Accepted\" ondrop=\"drop(event)\" ondragover=\"allowDrop(event)\"></ul></div><div class=\"col-sm-2\" style=\"background-color:#f5f5f5;margin-right:3px\"><ul class=\"drag Review\" id=\""+userName+"Review\" ondrop=\"drop(event)\" ondragover=\"allowDrop(event)\"></ul></div><div class=\"col-sm-2\" style=\"background-color:#f5f5f5;margin-right:3px\"><ul class=\"drag QA\" id=\""+userName+"QA\" ondrop=\"drop(event)\" ondragover=\"allowDrop(event)\"></ul></div><div class=\"col-sm-2\" style=\"background-color:#f5f5f5;margin-right:3px\"><ul class=\"drag Completed\" id=\""+userName+"Completed\" ondrop=\"drop(event)\" ondragover=\"allowDrop(event)\"></ul></div></div></section>";
	                    $("#boardRows").append(createBoardRow);
	                    $("#projectAssignee").append("<option>"+emailId+"</option>");
	                  }
	                  getAllTasksOfSprint();
	                }else{
	                   var createBoardRow="<section class=\"panel tasks-widget\"><header class=\"panel-heading\"><center><h4 >No User Assigned to Project</h4></center></header></section>";
	                    $("#boardRows").append(createBoardRow);
	                    $("#projectAssignee").append("<option>Not Available</option>");
	                }
	              }
	            }
	          });
  }
}

var currentProject;
function getProjectsOfAnOrganisation() {
	var orgName=localStorage.getItem('orgName');
	var endpoint=URL.FETCH_PROJECT_OF_ORG+userDetails.organisation+"/"+userDetails.zipCode+"/"+userDetails.emailId;
	HTTPUtil.GET(endpoint,function(output){
						if(output!=undefined && output!=""){
							if(output.responseEntity!=undefined &&output.responseEntity!=null){
							
							if(output.responseEntity.length>0){
								$("#topProjectDropdown").empty();
								$("#topBoardDropdown").empty();
								for(var i=0;i<output.responseEntity.length;i++){
									if(output.responseEntity[i]!=null){
									var liVal = "<li><a href=\"project.html?pName="+output.responseEntity[i].projectName+"\">"+output.responseEntity[i].projectName+" </a></li>"
									$("#topProjectDropdown").append(liVal);

									var liVal = "<li><a href=\"board.html?pName="+output.responseEntity[i].projectName+"\">"+output.responseEntity[i].projectName+" </a></li>"
									$("#topBoardDropdown").append(liVal)

									var opVal="<option>"+output.responseEntity[i].projectName+"</option>";
									$("#taskProjectName").append(opVal)
									$("#sprintProjectName").append(opVal);
									

									if(i==0){
										currentProject=output.responseEntity[i].projectName;
										if(projectName=="" || projectName==undefined){
										  	projectName=currentProject;
										  }
									}}
								}
							}
						  }else{
								$("#taskProjectName").append("<option>No Projects Assigned</option>")
								$("#sprintProjectName").append("<option>No Projects Assigned</option>");
							}
						}

						if(userDetails.employeeType=="ROOT" || userDetails.employeeType=="Admin"){
							$("#topProjectDropdown").append("<li class=\"divider\"></li><li><a href=\"#\" data-toggle=\"modal\" data-target=\"#addProject\">Create Project</a></li>");
						}
					});
				getUserOfProject();
};

function getTaskOfActiveSprintAndBacklogSprint(){

	var orgName = userDetails.organisation;
	var zipCode = userDetails.zipCode;

	var endpoint=URL.FETCH_TASKS_OF_PROJECT+orgName+"/"+projectName+"/"+userDetails.zipCode;

	HTTPUtil.GET(endpoint,function(output){
						if(output!=undefined && output!=""){
							if(output.responseEntity!=undefined &&output.responseEntity!=null){
							if(output.responseEntity.length>0){

								addRowsToTable(output.responseEntity);
							}
						  }
						}
					});

}

var taskList=[];
function addRowsToTable(input){
		taskList=input;

	for(var i=0;i<input.length;i++){
		var sprintId=input[i].sprintName.replace(/\s+/, "");
		var title=input[i].title;
		var createdAt=new Date(input[i].createdAt);
		var status = input[i].status;
		var taskId = input[i].id;
		var completedPer=100-(input[i].remainingTime/input[i].estimatedTime)*100;
		completedPer=Math.ceil(completedPer);


		if(completedPer==NaN){
			completedPer=0;
		}

		var rowCount = $("#"+sprintId+"tbody tr").length;

		var rowValue="<tr><td class=\"p-name\" ><a href=\"task.html?taskId="+taskId+"\">"+title+"</a><br><small>Created "+createdAt+"</small></td><td class=\"p-progress\"><div class=\"progress progress-xs\"><div style=\"width: "+completedPer+"%;\" class=\"progress-bar progress-bar-success\"></div></div><small>"+completedPer+"% Complete </small></td><td><a href=\"task.html?taskId="+taskId+"\" class=\"btn btn-primary btn-xs\" style=\"margin-right:10px\"><i class=\"fa fa-folder\"></i> View </a><a href=\"#\" class=\"editTask btn btn-info btn-xs \" id=\""+taskId+"\" data-toggle=\"modal\" data-target=\"#createTask\" style=\"margin-right:3px\" ><i class=\"fa fa-pencil\"></i> Edit </a></td><td><span class=\"label label-primary\">"+status+"</span></td></tr>";
		$("#"+sprintId+"tbody").append(rowValue);
		if(rowCount+1>0)
			$("#"+sprintId+"row").addClass("hide");

	}
	addEditTaskFunction();

}

function addEditTaskFunction(){

	$(".editTask").click(function(){
		for(var i=0;i<taskList.length;i++){
			if(taskList[i].id==this.id){
				console.log(taskList[i])
				$("#taskProjectName").val(taskList[i].projectName);
				$("#taskType").val(taskList[i].type);
				$("#taskTitle").val(taskList[i].title);
				//$("#taskPriority").hmtl(taskList[i].);
				$("#taskSprintName").val(taskList[i].sprintName);
				$("#projectAssignee").val(taskList[i].assignee);
				$("#taskEstimatedTime").val(taskList[i].estimatedTime);
				//$("#taskDescription").val(taskList[i].description);
				var desc=taskList[i].description;
				desc=desc.replace(/<br \/>/g,"\n")
				$("#taskDescription").val(desc);
				$("#updateTaskId").val(this.id)	;			
			}

		}
	});

}

