var newTask = new Object();

$("#addTaskButton").click(function() {

    var taskProjectName = $("#taskProjectName").val();
    var taskSprintName = $("#taskSprintName").val();
    var taskType = $("#taskType").val();
    var taskAssignee = $("#projectAssignee").val();
    var taskTitle = $("#taskTitle").val();
    var taskEstimatedTime = $("#taskEstimatedTime").val();
    var taskDescription = $("#taskDescription").val();
    var zipCode = userDetails.zipCode;
    var updateTaskId = $("#updateTaskId").val();


    if (taskProjectName == undefined || taskProjectName == "" || taskSprintName == undefined || taskSprintName == "" ||
        taskType == undefined || taskType == "" || taskAssignee == undefined || taskAssignee == "" ||
        taskTitle == undefined || taskTitle == "" || taskEstimatedTime == undefined || taskEstimatedTime == "" ||
        taskDescription == undefined || taskDescription == "") {
        showMandatoryFieldError();
        return;
    } else {
        newTask.orgName =userDetails.organisation;
        newTask.projectName = taskProjectName;
        newTask.assignee = taskAssignee;
        newTask.title = taskTitle;
        newTask.description = taskDescription;
        newTask.type = taskType;
        newTask.estimatedTime = taskEstimatedTime;
        newTask.status = false;
        newTask.sprintName = taskSprintName;
        newTask.zipCode = zipCode;
        newTask.id = updateTaskId;
        newTask.createdBy = userDetails.name;
    }
    console.log(newTask)
    addTask(newTask);
});



function showMandatoryFieldError() {
    console.log("error")
    $("#taskMandatoryFieldError").removeClass("hide");
    $("#taskMandatoryFieldError").html("Enter value in all the fields*")
}


function showErrorMessage(code, message) {
    $("#taskMandatoryFieldError").removeClass("hide");
    $("#taskMandatoryFieldError").html(message)
}


function addTask(input) {
    input.description = input.description.replace(/(?:\r\n|\r|\n)/g, '<br />');
    console.log(JSON.stringify(input))
    HTTPUtil.POST(URL.ADD_TASK, input, function(output) {
        if (output.status == 200) {
            $("#taskMandatoryFieldError").removeClass("hide");
            $("#taskMandatoryFieldError").html("Successfully Added Task");
            $("#taskAssignee").val('');
            $("#taskTitle").val('');
            $("#taskEstimatedTime").val('');
            $("#taskDescription").val('');
        } else if (output.status == 500) {
            var message = output.responseJSON.message.split(",");
            var mes = message[0].split(":");
            showErrorMessage(message[1], mes[1])
        }
    });

}