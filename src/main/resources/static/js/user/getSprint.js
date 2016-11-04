var sprintList = [];
var latestSprint;


function getAllSprintsOfAProject() {

    var orgName = userDetails.organisation;
    var zipCode = userDetails.zipCode;
    var endpoint = URL.FETCH_SPRINT_OF_PROJ + orgName + "/" + projectName + "/" + zipCode;

    HTTPUtil.GET(endpoint, function(output) {
        $("#sprintTbody").empty();
        $("#taskSprintName").empty();
        sprintList = [];
        if (output != undefined && output != "" && output.responseEntity != null) {
            if (output.responseEntity.length > 0) {
                $("#taskSprintName").empty();
                for (var i = 0; i < output.responseEntity.length; i++) {
                    sprintList[i] = output.responseEntity[i].sprintName;
                    $("#taskSprintName").append("<option>" + sprintList[i] + "</option>");

                    if(i==output.responseEntity.length-1){
                        latestSprint=output.responseEntity[i].sprintName;
                    }
                }
            }
        }else{
            	 $("#taskSprintName").append("<option class=\"disabled\">No Sprint Available</option>");
            }
    });


    for (var i = 0; i < sprintList.length; i++) {
        var rowvalue = "<div class=\"col-md-12\"><div class=\"panel panel-primary\"><div class=\"panel panel-heading\">" + sprintList[i] + "</div><div class=\"panel panel-body\"><table class=\"table table-hover\"><tbody id=" + sprintList[i].replace(/\s+/, "") + "tbody></tbody><tr id="+sprintList[i].replace(/\s+/, "")+"row><td>No Tasks Found</td><td></td><td></td><td ><div data-toggle=\"modal\" data-target=\"#createTask\" class=\"btn btn-success\" id="+sprintList[i].replace(/\s+/, "")+"task>Create Task</div></td></tr></table></div></div>"
        $("#default").addClass("hide")
        $("#sprintRows").append(rowvalue)
    }

    if(sprintList.length>0){

    }
};