function allowDrop(ev) {
              ev.preventDefault();
          }

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    var status = ev.target.className;
    status=status.split(" ");
    status=status[1];
    ev.target.appendChild(document.getElementById(data));

    updateDragTaskData(status,ev.dataTransfer.getData("text"));
}

function updateDragTaskData(status,taskId){
        var taskDetails=new Object();
        taskDetails.status = status;
        taskDetails.id=taskId;

        HTTPUtil.POST(URL.UPDATE_TASK, taskDetails,function(output){
            console.log(JSON.stringify(output));
          });

}

$("#report").click(function(){

  console.log(latestSprint)
  location.href="chart.html?sprint="+latestSprint;

})

var openIssue=false;
var myIssue=false;
var updatedIssue=false;
$("#openIssue").click(function(){
      if(openIssue){
          $(".openIssue").removeClass("hide");
          openIssue=false;
      }else{
          $(".openIssue").addClass("hide");
          openIssue=true;
      }

})

$("#myIssue").click(function(){
      if(myIssue){
          $(".others").removeClass("hide");
          $(this).removeClass("btn btn-success");
          myIssue=false;
      }else{
          $(".others").addClass("hide");
          $(this).addClass("btn btn-success");
          myIssue=true;
      }
})

$("#updatedIssue").click(function(){
      if(updatedIssue){
          $(".notUpdated").removeClass("hide");
          updatedIssue=false;
          $(this).removeClass("btn btn-success");
      }else{
          $(".notUpdated").addClass("hide");
          $(this).addClass("btn btn-success");
          updatedIssue=true;
      }

})


function getAllTasksOfSprint() {
  getAllSprintsOfAProject();
  var orgName=userDetails.organisation;
  var projectName=currentBoardProject;
  var sprintName=latestSprint;
  var endpoint=URL.FETCH_TASKS_OF_SPRINT+orgName+"/"+projectName+"/"+sprintName+"/"+userDetails.zipCode;
  
  if(sprintName!=""){
    HTTPUtil.GET(endpoint,function(output){
              $("#taskTbody").empty();
              tasksList=[];
              if(output!=undefined && output!="" && output.responseEntity!=null){
                if(output.responseEntity.length>0){
                  for(var i=0;i<output.responseEntity.length;i++){
                    tasksList[i]=output.responseEntity[i];
                  }
                }
              }
            });
    renderTaskInBoard(tasksList);
    }
};

function renderTaskInBoard(input){

    for(var i=0;i<input.length;i++){

        var taskBody="";
        var emailId = input[i].assignee;
        var userName = emailId.split("@");
        userName=userName[0].toLowerCase();
        var recentlyUpdate="updated";

        var date=new Date();
        var yesterday = new Date(date.getTime());
        yesterday.setDate(date.getDate() - 1);

        if(input[i].updatedAt<yesterday.getTime()){
          recentlyUpdate="notUpdated";
        }

        console.log(input[i])

        var label="success";
        if(input[i].type=="Improvement"){
          label="primary";
        }else if(input[i].type=="Bug"){
          label="danger";
        }else if(input[i].type=="Others"){
          label="warning";
        }

        var liValue="<li id=\""+input[i].id+"\" draggable=\"true\" ondragstart=\"drag(event)\" class=\"panel "+recentlyUpdate+" row list-"+label+"\"><div class=\"col-md-10\"> <h5 class=\"text-primary\"><a href=\"task.html?taskId="+input[i].id+"\">"+input[i].taskId+"</a></h5><p class=\"big\">"+input[i].title+"</p></div><div class=\"col-md-2\"><div class=\"row \"><img alt=\"\" src=\"img_avatar2.png\" height=\"30px\" width=\"30px\"></div><div class=\"row\" style=\"margin-top:20px\"><span class=\"badge\" style=\"float:left\">"+input[i].remainingTime+"h</span></div></div></li>";

        if(input[i].status=="Not Accepted"){
            $("#"+userName+"NotAccepted").append(liValue);
        }else if(input[i].status=="Accepted" || input[i].status=="Accept"){
            $("#"+userName+"Accepted").append(liValue);
        }else if(input[i].status=="Review"){
            $("#"+userName+"Review").append(liValue);
        }else if(input[i].status=="QA"){
            $("#"+userName+"QA").append(liValue);
        }else if(input[i].status=="Completed"){
            $("#"+userName+"Completed").append(liValue);
        }

    }


}