var sprintId;
var loggedTimeInSprint;
var remainingTimeInSprint;
var idealSprintData;


$(document).ready(function(){

sprintId = getURLParameterByName('sprint');

fetchReport();
})

function fetchReport(){
    HTTPUtil.GET(URL.FETCH_SPRINT_REPORT + ""+userDetails.organisation+"/"+userDetails.projectName+"/"+userDetails.zipCode+"/"+sprintId, function(output){
        if(output!=undefined && output!=""){
            if(output.responseEntity!=undefined && output.responseEntity!=null){
                console.log(output.responseEntity)
                loggedTimeInSprint=output.responseEntity.loggedTimeInSprint;
                remainingTimeInSprint=output.responseEntity.remainingTimeInSprint;
                idealSprintData=output.responseEntity.idealSprintData;
            }
        }
    }); 
}



 window.onload = function () {
    var chart = new CanvasJS.Chart("chartContainer",
    {
      title:{
      text: "Burndown Chart"  
      },
      data: [
      {        
        type: "line",
        xValueType: "dateTime",
        legendText: "no marker",
        markerType: "none", 
        toolTipContent: "<p>Remaining : {y}h</p>",
        dataPoints: idealSprintData
      },
        {        
        type: "line",
        xValueType: "dateTime",
        toolTipContent: "<a href={path}>{name}</a>",
        dataPoints: loggedTimeInSprint
      },
        {        
        type: "line",
         xValueType: "dateTime",
        toolTipContent: "<a href={path}>{name}</a>",
        dataPoints: remainingTimeInSprint
      }
      ]
    });

    chart.render();
  }
   var loggedTimeInSprint =  [
            {
                "x": 1472409000000,
                "y": 0
            },
            {
                "x": 1472466600000,
                "y": 2,
                "path" : "task.html?taskId=3",
                "name" : "TeamManagement-3" 
            },
            {
                "x": 1472473800000,
                "y": 5,
                "path" :  "task.html?taskId=5",
                "name" :  "TeamManagement-5"
            },
            {
                "x": 1472481000000,
                "y": 8,
                "path" :  "task.html?taskId=15",
                "name" :  "TeamManagement-15"
            }
        ]

  var remainingTimeInSprint =  [
            {
                "x": 1472409000000,
                "y": 64
            },
            {
                "x": 1472466600000,
                "y": 62,
                "path" : "task.html?taskId=3",
                "name" : "TeamManagement-3" 
            },
            {
                "x": 1472473800000,
                "y": 59,
                "path" :  "task.html?taskId=5",
                "name" :  "TeamManagement-5"
            },
            {
                "x": 1472481000000,
                "y": 56,
                "path" :  "task.html?taskId=15",
                "name" :  "TeamManagement-15"
            }
        ];

  var idealSprintData =[
            {
                "x": 1472409000000,
                "y": 64
            },
            {
                "x": 1472495400000,
                "y": 56
            },
            {
                "x": 1472581800000,
                "y": 48
            },
            {
                "x": 1472668200000,
                "y": 40
            },
            {
                "x": 1472754600000,
                "y": 40
            },
            {
                "x": 1472841000000,
                "y": 40
            },
            {
                "x": 1472927400000,
                "y": 40
            },
            {
                "x": 1473013800000,
                "y": 32
            },
            {
                "x": 1473100200000,
                "y": 24
            },
            
            {
                "x": 1473186600000,
                "y": 24
            },
            {
                "x": 1473273000000,
                "y": 16
            },
            {
                "x": 1473359400000,
                "y": 8
            },
            {
                "x": 1473445800000,
                "y": 8
            },
            {
                "x": 1473532200000,
                "y": 8
            }
        ];