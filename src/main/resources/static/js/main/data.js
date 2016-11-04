var assignedData = [];
var unAssignedData = [];
var unAssignedDataTemp = [];



function refreshData() {

    empPalette.model = new go.TreeModel(unAssignedData);
    orgChart.model = new go.TreeModel(assignedData);
}


function updateData() {
    var j = 0;
    var k = 0;
    if (modelData) {
        $(".receipentSelectOptions").empty();
        for (var i = 0; i < modelData.length; i++) {
            if (modelData[i] != null) {
                $(".receipentSelectOptions").append("<option>" + modelData[i].emailId + "</option>")
                if (modelData[i].parent == "-1") {
                    unAssignedData[j] = modelData[i];
                    j++;
                } else {
                    assignedData[k] = modelData[i];
                    k++;
                }
            }
        }
        $(".receipentSelectOptions").val("");
    }
    //assign the connected nodes
    orgTreeModelNodeDataArray = assignedData;
    orgChart.model = orgTreeModel;
    orgChart.model.nodeDataArray = orgTreeModelNodeDataArray;
    //assign the not connected nodes
    empPalette.model.nodeDataArray = unAssignedData;
}

var modelData;

function getData() {
    HTTPUtil.GET(URL.FETCH_ALL_USER + userDetails.organisation + "/" + userDetails.zipCode, function(callBackData) {
        if (callBackData != undefined && callBackData != "") {
            if (callBackData.responseEntity.length > 0) {
                modelData = callBackData.responseEntity;
            }
        }
    });
};

function updateDataInServer(data) {
    HTTPUtil.POST(URL.UPDATE_ALL_USER, data, function(callBackData) {

    });
};


