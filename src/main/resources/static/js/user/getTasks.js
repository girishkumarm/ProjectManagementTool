var tasksList = [];
var activityList = [];
var currentBoardProject;

function getAllTasksOfSprint() {
    var orgName = userDetails.organisation;
    var projectName = currentBoardProject;
    var sprintName = latestSprint;
    var endpoint = URL.FETCH_TASKS_OF_SPRINT + orgName + "/" + projectName + "/" + sprintName + "/" + userDetails.zipCode;


    if (sprintName != "" && sprintName != undefined) {
        HTTPUtil.GET(endpoint, function(output) {
            $("#taskTbody").empty();
            tasksList = [];
            if (output != undefined && output != "" && output.responseEntity != null) {
                if (output.responseEntity.length > 0) {
                    for (var i = 0; i < output.responseEntity.length; i++) {
                        tasksList[i] = output.responseEntity[i];
                    }
                }
            }
        });

    }
};


//used in the main.html page to fetch all the task of an user
function getAllTasksOfUser() {

    var emailId = userDetails.emailId;
    var endpoint = URL.FETCH_TASKS_BY_USER_NAME + emailId;

    if (emailId != "") {
        HTTPUtil.GET(endpoint, function(output) {

            $("#taskTbody").empty();
            tasksList = [];
            if (output != undefined && output != "" && output.responseEntity != null) {
                if (output.responseEntity.length > 0) {
                    for (var i = 0; i < output.responseEntity.length; i++) {
                        tasksList[i] = output.responseEntity[i];
                    }
                }
            }
        });

        $("#taskTbody").empty();

        if (tasksList.length == 0) {
            $("#taskTbody").append("<tr rowspan=\"3\"><td>No Tasks Found</td></td>");
        }
        for (var i = 0; i < tasksList.length; i++) {
            var rowvalue = "";
            var label = "success";
            if (tasksList[i].type == "Improvement") {
                label = "primary";
            } else if (tasksList[i].type == "Bug") {
                label = "danger";
            } else if (tasksList[i].type == "Others") {
                label = "warning";
            }

            rowvalue = "<li class=\"list-" + label + "\"><div class=\"task-title\"><span class=\"task-title-sp\"><a href=\"task.html?taskId=" + tasksList[i].id + "\">" + tasksList[i].title + " </a><span><span class=\"badge badge-sm label-primary\">" + tasksList[i].status + "</span></div></li>";
            $("#taskTbody").append(rowvalue);
        }
    }
};

//used in the main.html page to fetch all the task's activity of a project
function getAllTasksActivityOfProject() {

    var emailId = userDetails.emailId;
    var endpoint = URL.FETCH_TASKS_BY_USER_NAME + emailId;
    var input = new Object();
    input.orgName = userDetails.organisation;
    input.zipCode = userDetails.zipCode;
    input.activityType = "all";

    if (input != undefined) {
        HTTPUtil.POST(URL.FETCH_ACTIVITY_OF_PROJECT, input, function(output) {
            $("#activityTbody").empty();
            $("#activityTbody").append("<tr rowspan=\"3\"><td>No activity Found</td></td>");
            activityList = [];
            if (output != undefined && output != "" && output.responseEntity != null) {
                if (output.responseEntity.length > 0) {
                    for (var i = 0; i < output.responseEntity.length; i++) {
                        activityList[i] = output.responseEntity[i];
                    }
                    $("#activityTbody").empty();

                    if (activityList.length == 0) {
                        $("#activityTbody").append("<tr rowspan=\"3\"><td>No activity Found</td></td>");
                    }
                    for (var i = 0; i < activityList.length; i++) {
                        var rowvalue = "";
                        var label = "success"
                        rowvalue = "<li><div class=\"row\"><div class=\"col-md-1\"><img alt=\"\" src=\"img_avatar2.png\" height=\"30px\" width=\"30px\"></div><div class=\"col-md-10\"> <div class=\"row\">" + activityList[i].description + "</div><div class=\"row\"><a style=\"margin-right:20px\">Comment</a><a style=\"margin-right:20px\">Vote</a><a style=\"margin-right:20px\">Watch</a></div></div></div></li>";
                        $("#activityTbody").append(rowvalue);
                    }
                }
            }
        });
    }
};