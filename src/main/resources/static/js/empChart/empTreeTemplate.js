empPalette.nodeTemplateMap.add("",
    $(go.Node, "Auto",
        $(go.Shape, {
            fill: $(go.Brush, "Linear", {
                0: "#2672EC"
            }),
            stroke: "white",
            strokeWidth: 2
        }),
        $(go.Panel, "Table", {
                defaultAlignment: go.Spot.Center,
                margin: 4
            },
            $(go.RowColumnDefinition, {
                column: 1,
                width: 50
            }),
            $(go.Picture, "images.jpg", {
                    row: 0,
                    column: 0,
                    rowSpan: 3,
                    width: 40,
                    height: 40,
                    alignment: go.Spot.Center
                },
                new go.Binding("source")),
            
            $(go.TextBlock, textStyle(),{
                    row: 0,
                    column: 2,
                    editable: false, isMultiline: false,
                    minSize: new go.Size(10, 14),
                    margin: new go.Margin(0, 0, 0, 3)
                },
                new go.Binding("text", "name"))
        )
    ));