// replace the default Link template in the linkTemplateMap
orgChart.linkTemplate = $(go.Link, // the whole link panel
    {
        routing: go.Link.AvoidsNodes,
        curve: go.Link.JumpOver,
        corner: 5,
        toShortLength: 4,
        relinkableFrom: true,
        relinkableTo: true,
        reshapable: true,
        resegmentable: true,
        // mouse-overs subtly highlight links:
        mouseEnter: function(e, link) {
            link.findObject("HIGHLIGHT").stroke = "black";
        },
        mouseLeave: function(e, link) {
            link.findObject("HIGHLIGHT").stroke = "transparent";
        }
    }, new go.Binding("points").makeTwoWay(), $(go.Shape, // the highlight shape, normally transparent
        {
            isPanelMain: true,
            strokeWidth: 8,
            stroke: "transparent",
            name: "HIGHLIGHT"
        }), $(go.Shape, // the link path shape
        {
            isPanelMain: true,
            stroke: "gray",
            strokeWidth: 2
        }), $(go.Shape, // the arrowhead
        {
            toArrow: "standard",
            stroke: null,
            fill: "gray"
        }), $(go.Panel, "Auto", // the link label, normally not visible
        {
            visible: false,
            name: "LABEL",
            segmentIndex: 2,
            segmentFraction: 0.5
        }, new go.Binding("visible", "visible").makeTwoWay(), $(go.Shape,
            "RoundedRectangle", // the label shape
            {
                fill: "#F8F8F8",
                stroke: null
            }), $(go.TextBlock, "Yes", // the label
            {
                textAlign: "center",
                font: "10pt helvetica, arial, sans-serif",
                stroke: "#333333",
                editable: true
            }, new go.Binding("text").makeTwoWay())));