var $ = go.GraphObject.make;

var data = new Object();
// Make link labels visible if coming out of a "conditional" node.
// This listener is called by the "LinkDrawn" and "LinkRelinked" DiagramEvents.
function showLinkLabel(e) {
    var label = e.subject.findObject("LABEL");
    if (label !== null)
        label.visible = (e.subject.fromNode.data.figure === "Diamond");
}

// This function provides a common style for most of the TextBlocks.
    // Some of these values may be overridden in a particular TextBlock.
    function textStyle() {
      return { font: "9pt  Segoe UI,sans-serif", stroke: "white" };
    }

function nodeStyle() {
    return [{
        //isShadowed: true,
        //shadowColor: "#888",
        // handle mouse enter/leave events to show/hide the ports
        mouseEnter: function(e, obj) {
            showPorts(obj.part, true);
        },
        mouseLeave: function(e, obj) {
            showPorts(obj.part, false);
        }
    }];
}
var updatedEmployeeList=[];
// Show the diagram's model in JSON format that the user may edit
var isModified=false;
function save() {
    for(var i=0;i<assignedData.length;i++){
            updatedEmployeeList[i]=new Object();
            updatedEmployeeList[i].id=assignedData[i].key;
            updatedEmployeeList[i].teamLead=assignedData[i].parent;
            updatedEmployeeList[i].title=assignedData[i].title;
            updatedEmployeeList[i].department=assignedData[i].department;
        }

    for(var j=0;j<unAssignedData.length;j++){
            updatedEmployeeList[i]=new Object();
            updatedEmployeeList[i].id=unAssignedData[j].key;
            updatedEmployeeList[i].teamLead=unAssignedData[j].parent;
            updatedEmployeeList[i].title=unAssignedData[j].title;
            updatedEmployeeList[i].department=unAssignedData[j].department;
            i++;
        }

        updateDataInServer(updatedEmployeeList);
        refreshData();
    }


    function incrementCounter(e, obj) {
        var node = obj.part;
        var data = node.data;
        if (data) {
          console.log(data)
          $("#meetingRequest").removeClass('hide');
        }
  }


  
