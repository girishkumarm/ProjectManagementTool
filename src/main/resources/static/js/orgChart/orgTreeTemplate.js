function changeCategory(e, obj) {
    var node = obj.part;
    if (node) {
      var diagram = node.diagram;
      diagram.startTransaction("changeCategory");
      var cat = diagram.model.getCategoryForNodeData(node.data);
      console.log(cat)
      if (cat === "")
        cat = "simple";
      else
        cat = "";
      diagram.model.setCategoryForNodeData(node.data, cat);
      diagram.commitTransaction("changeCategory");
    }
  }

var receipents=[];
var count=0;
  function addUserToRecepient(e, obj) {
    var node = obj.part;
    if (node) {
      var diagram = node.diagram;
      diagram.startTransaction("changeCategory");
      diagram.model.setCategoryForNodeData(node.data, "meetingRequestSelected");
      receipents[count]=node.data.emailId;
      count++;
      diagram.commitTransaction("changeCategory");
    }
  }


  function addUserGroupToRecepient(e, obj) {
    var node = obj.part;
    if (node) {
      var diagram = node.diagram;
      diagram.startTransaction("changeCategory");
      diagram.model.setCategoryForNodeData(node.data, "meetingRequestSelected");
      receipents[count]=node.data.emailId;
      count++;
      diagram.commitTransaction("changeCategory");

      var nodeList = orgChart.model.nodeDataArray;

      for(var i=0;i<nodeList.length;i++){
        if(nodeList[i].parent==node.data.key){
            var exists = false;
            for(var j=0;j<receipents.length;j++){
                if(nodeList[i].emailId==receipents[j]){
                    exists = true;
                }
            }
            if(!exists){
                orgChart.model.setCategoryForNodeData(nodeList[i], "meetingRequestSelected");
                orgChart.commitTransaction("changeCategory");
                receipents[count]=nodeList[i].emailId;
                count++;
            }
        }
      }
    }
  }

  function removeUserFromRecepient(e, obj) {
    var node = obj.part;
    if (node) {
      var diagram = node.diagram;
      diagram.startTransaction("changeCategory");
      diagram.model.setCategoryForNodeData(node.data, "meetingRequest");
      diagram.commitTransaction("changeCategory");
      for(var i=0;i<receipents.length;i++){
        if(receipents[i]==node.data.emailId){
            receipents.splice(i,1);
            count--;
            return;
        }
      }
    }
  }




orgChart.nodeTemplateMap.add("simple",
    $(go.Node, "Auto",nodeStyle(),
        $(go.Shape, {
            fill: $(go.Brush, "Linear", {
                0: "#46b8da"
            }),
            stroke: "white",
            strokeWidth: 2
        }),
        $(go.Panel, "Table", {
                defaultAlignment: go.Spot.Center,
                margin: 8
            },
            $(go.RowColumnDefinition, {
                column: 1,
                width: 20
            }),

            //image field
            $(go.Picture, "images.jpg", {
                    row: 0,
                    column: 0,
                    rowSpan: 4,
                    width: 50,
                    height: 50,
                    alignment: go.Spot.Center
                },
                new go.Binding("source")),

            //Name Field
            $(go.TextBlock, textStyle(),  // the name
              {
                row: 0, column: 2, columnSpan: 5,
                font: "12pt Segoe UI,sans-serif",
                editable: false, isMultiline: false,
                minSize: new go.Size(10, 16)
              },
              new go.Binding("text", "name").makeTwoWay()),


            //Title field
            $(go.TextBlock, "Title: ", textStyle(),
              { row: 1, column: 2 }),
            $(go.TextBlock, textStyle(),
              {
                row: 1, column: 3, columnSpan: 4,
                editable: true, isMultiline: false,
                minSize: new go.Size(10, 14),
                margin: new go.Margin(0, 0, 0, 3)
              },
              new go.Binding("text", "title").makeTwoWay()),

            //department field
            $(go.TextBlock, "Department: ", textStyle(),
              { row: 2, column: 2 }),
            $(go.TextBlock, textStyle(),
              {
                row: 2, column: 3, columnSpan: 4,
                editable: true, isMultiline: false,
                minSize: new go.Size(10, 14),
                margin: new go.Margin(0, 0, 0, 3)
              },
              new go.Binding("text", "department").makeTwoWay()),

            $("Button",
                { alignment: go.Spot.TopRight },
                $(go.Shape, "AsteriskLine", { width: 8, height: 8 }),
                { click: changeCategory }),




            //Id field
            $(go.TextBlock, "Id: ", textStyle(),
              { row: 3, column: 2 }),

            $(go.TextBlock, textStyle(),{
                    row: 3,
                    column: 3,
                    editable: false, isMultiline: false,
                    minSize: new go.Size(10, 14),
                    margin: new go.Margin(0, 0, 0, 3)
                },
                new go.Binding("text", "key"))
        ),
        makePort("L", go.Spot.Left, true, true),
        makePort("R", go.Spot.Right, true, true),
        makePort("T", go.Spot.Top, true, true),
        makePort("B", go.Spot.Bottom, true, true)
    ));

orgChart.nodeTemplateMap.add("",
    $(go.Node, "Auto",nodeStyle(),
        $(go.Shape, {
            fill: $(go.Brush, "Linear", {
                0: "#2672EC"
            }),
            stroke: "white",
            strokeWidth: 2
        }),
        $(go.Panel, "Table", {
                defaultAlignment: go.Spot.Center,
                margin: 8
            },
            $(go.RowColumnDefinition, {
                column: 1,
                width: 20
            }),

            //image field
            $(go.Picture, "images.jpg", {
                    row: 0,
                    column: 0,
                    rowSpan: 4,
                    width: 50,
                    height: 50,
                    alignment: go.Spot.Center
                },
                new go.Binding("source")),

            //Name Field
            $(go.TextBlock, textStyle(),  // the name
              {
                row: 0, column: 2, columnSpan: 5,
                font: "12pt Segoe UI,sans-serif",
                editable: false, isMultiline: false,
                minSize: new go.Size(10, 16)
              },
              new go.Binding("text", "name").makeTwoWay()),

            $("Button",
                { alignment: go.Spot.TopRight },
                $(go.Shape, "AsteriskLine", { width: 8, height: 8 }),
                { click: changeCategory })
        ),
        makePort("L", go.Spot.Left, true, true),
        makePort("R", go.Spot.Right, true, true),
        makePort("T", go.Spot.Top, true, true),
        makePort("B", go.Spot.Bottom, true, true)
    ));


orgChart.nodeTemplateMap.add("meetingRequest",
    $(go.Node, "Auto",nodeStyle(),
        $(go.Shape, {
            fill: $(go.Brush, "Linear", {
                0: "#FF7F50"
            }),
            stroke: "blue",
            strokeWidth: 2
        }),
        $(go.Panel, "Table", {
                defaultAlignment: go.Spot.Center,
                margin: 8
            },
            $(go.RowColumnDefinition, {
                column: 1,
                width: 20
            }),

            //image field
            $(go.Picture, "images.jpg", {
                    row: 0,
                    column: 0,
                    rowSpan: 4,
                    width: 50,
                    height: 50,
                    alignment: go.Spot.Center
                },
                new go.Binding("source")),

            //Name Field
            $(go.TextBlock, textStyle(),  // the name
              {
                row: 0, column: 2, columnSpan: 5,
                font: "12pt Segoe UI,sans-serif",
                editable: false, isMultiline: false,
                minSize: new go.Size(10, 16)
              },
              new go.Binding("text", "name").makeTwoWay()),

            $("Button",
                { alignment: go.Spot.TopRight, row: 0, column: 10 },
                $(go.Shape, "PlusLine", { width: 10, height: 10 }),
                { click: addUserToRecepient }),

            $("Button",
                { alignment: go.Spot.BottomRight , row: 3, column: 10},
                $(go.Shape, "BpmnActivityLoop", { width: 10, height: 10 }),
                { click: addUserGroupToRecepient })
        )
    ));

orgChart.nodeTemplateMap.add("meetingRequestSelected",
    $(go.Node, "Auto",nodeStyle(),
        $(go.Shape, {
            fill: $(go.Brush, "Linear", {
                0: "#DC143C"
            }),
            stroke: "blue",
            strokeWidth: 2
        }),
        $(go.Panel, "Table", {
                defaultAlignment: go.Spot.Center,
                margin: 8
            },
            $(go.RowColumnDefinition, {
                column: 1,
                width: 20
            }),

            //image field
            $(go.Picture, "images.jpg", {
                    row: 0,
                    column: 0,
                    rowSpan: 4,
                    width: 50,
                    height: 50,
                    alignment: go.Spot.Center
                },
                new go.Binding("source")),

            //Name Field
            $(go.TextBlock, textStyle(),  // the name
              {
                row: 0, column: 2, columnSpan: 5,
                font: "12pt Segoe UI,sans-serif",
                editable: false, isMultiline: false,
                minSize: new go.Size(10, 16)
              },
              new go.Binding("text", "name").makeTwoWay()),

            $("Button",
                { alignment: go.Spot.TopRight, row: 0, column: 10 },
                $(go.Shape, "IrritationHazard", { width: 10, height: 10 }),
                { click: removeUserFromRecepient })
        )
    ));