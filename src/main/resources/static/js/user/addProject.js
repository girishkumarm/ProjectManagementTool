var newProject = new Object();

$("#addProjectButton").click(function() {

    var projectName = $("#projectName").val();
    var createdBy = userDetails.emailId;
    var orgName = userDetails.organisation;
    var teamLead = $("#projectLead").val();


    if (projectName == undefined || projectName == "" || teamLead == undefined || teamLead == "") {
        showMandatoryFieldError();
        return;
    } else {
        newProject.projectName = projectName;
        newProject.createdBy = createdBy;
        newProject.orgName = orgName;
        newProject.zipCode = userDetails.zipCode;
        newProject.teamLead = teamLead;
    }
    console.log(newProject)
    addProject(newProject);

});



function showMandatoryFieldError() {
    $("#projectMandatoryFieldError").removeClass("hide");
    $("#projectMandatoryFieldError").html("Enter value in all the fields*")
}


function showProjectErrorMessage(code, message) {
    console.log(code);
    console.log(message);
    $("#projectMandatoryFieldError").removeClass("hide");
    $("#projectMandatoryFieldError").html(message)
}


function addProject(input) {
    console.log(URL.ADD_PROJECT)
    HTTPUtil.POST(URL.ADD_PROJECT, input, function(output) {
        if (output.status == 200) {
            $("#projectMandatoryFieldError").val('');
            $("#projectMandatoryFieldError").removeClass("hide");
            $("#projectMandatoryFieldError").html("Successfully Added Project");
            $("#projectName").val('');
            $("#projectLead").val('');

        } else if (output.status == 500) {
            var message = output.responseJSON.message.split(",");
            var mes = message[0].split(":");
            showProjectErrorMessage(message[1], mes[1])
        }
    });

}