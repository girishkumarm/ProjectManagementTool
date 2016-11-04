$(document).ready(function(){
         
              $("#meetingRequestCancel").click(function(){
                $("#meetingRequest").addClass("hide")
              })

              $("#addRecepientButton").click(function(){
                $("#meetingReq").modal()
              })

             $( ".datepicker" ).datepicker();    
             getData();
             updateData();    

             //fetch header values
             getProjectsOfAnOrganisation() ;
             getAllSprintsOfAProject()


            $(".receipentSelectOptions").change(function(){ 
                var oldValue;
                if($("#receipents").html()!=""){
                  oldValue=$("#receipents").html()+","+$(this).val();
                }else{
                  oldValue=$(this).val();
                }
                $("#receipents").html(oldValue);
                $(this).val("")
            });

            $("#sendEmail").click(function(){
              $("#empRequests").addClass("hide");
              $("#empRequest").removeClass("hide");
              $("#empRequest1").addClass("hide");
              $("#empRequest2").removeClass("hide");
              $("#empRequest3").addClass("hide");
              if(userDetails.type=="Root"){
                  $("#SaveButton").addClass("hide");
              }
              var nodeList = orgChart.model.nodeDataArray;

              for(var i=0;i<nodeList.length;i++){
                orgChart.model.setCategoryForNodeData(nodeList[i], "meetingRequest");
                orgChart.commitTransaction("changeCategory");
              }
            })



            $("#sendMeetingRequest").click(function(){
              $("#empRequests").addClass("hide");
              $("#empRequest").removeClass("hide");
              $("#empRequest1").removeClass("hide");
              $("#empRequest2").addClass("hide");
              $("#empRequest3").addClass("hide");
              if(userDetails.type=="Root"){
                  $("#SaveButton").addClass("hide");
              }
              var nodeList = orgChart.model.nodeDataArray;

              for(var i=0;i<nodeList.length;i++){
                orgChart.model.setCategoryForNodeData(nodeList[i], "meetingRequest");
                orgChart.commitTransaction("changeCategory");
              }
            })

            $(".cancelRequest").click(function(){
              $("#empRequests").removeClass("hide");
              $("#empRequest").addClass("hide");
              if(userDetails.type=="Root"){
                  $("#SaveButton").removeClass("hide");
              }
              receipents=[];
              count=0;
              var nodeList = orgChart.model.nodeDataArray;

              for(var i=0;i<nodeList.length;i++){
                orgChart.model.setCategoryForNodeData(nodeList[i], "");
                orgChart.commitTransaction("changeCategory");
              }
            })


            $("#empRequest1").click(function(){
              var rString="";
              for(var i=0;i<receipents.length;i++){
                  if(rString==""){
                     rString=receipents[i];
                  }else{
                     rString=rString+","+receipents[i]
                  }
                
              }
              $("#meetingReceipents").html(rString)
              
            })


            $("#empRequest2").click(function(){
              var rString="";
              for(var i=0;i<receipents.length;i++){
                  if(rString==""){
                     rString=receipents[i];
                  }else{
                     rString=rString+","+receipents[i]
                  }
                
              }
              $("#emailReceipents").html(rString)
              
            })


            $("#sendMeetingRequestButton").click(function(){

             var request = new Object();

             var date = $("#meetingDate").val();
             date = date.split("/");
             date=date[2]+""+date[0]+""+date[1]+"T";

             var startTime = $("#meetingStartTime").val();
             startTime = startTime.replace(":","");
             startTime = startTime.replace(":","");

             var endTime = $("#meetingEndTime").val();
             endTime = endTime.replace(":","");
             endTime = endTime.replace(":","");

             request.subject=$("#meetingSubject").val();
             request.from=userDetails.emailId;
             request.venue=$("#meetingVenue").val();
             request.summary=$("#meetingDescription").val();
             request.description=$("#meetingDescription").val();
             request.meetingStartTime=date+""+startTime;
             request.meetingEndTime=date+""+endTime;
             request.receipent=$("#meetingReceipents").val();

             console.log(request)
             sendMeetingRequest(request)

         })


  })


function sendMeetingRequest(data) {
    HTTPUtil.POST(URL.SEND_MEETING_REQUEST, data, function(callBackData) {

    });
};

function sendEmailRequest(data) {
    HTTPUtil.POST(URL.SEND_EMAIL_REQUEST, data, function(callBackData) {

    });
};