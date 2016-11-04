var orgChart =
    $(go.Diagram, "orgChart", {
        layout: $(go.TreeLayout, {
            angle: 90,
            layerSpacing: 35
        }),
        initialContentAlignment: go.Spot.Center,
        allowDrop: true, // must be true to accept drops from the Palette
        "LinkDrawn": showLinkLabel, // this DiagramEvent listener is defined below
        "LinkRelinked": showLinkLabel,
        "animationManager.duration": 800, // slightly longer than default (600ms) animation
        "undoManager.isEnabled": true // enable undo & redo
    });



orgChart.toolManager.linkingTool.temporaryLink.routing = go.Link.Orthogonal;
orgChart.toolManager.relinkingTool.temporaryLink.routing = go.Link.Orthogonal;

orgChart.toolManager.dragSelectingTool.box = $(go.Part, {
    layerName: "Tool",
    selectable: false
}, $(go.Shape, {
    name: "SHAPE",
    fill: null,
    stroke: "chartreuse",
    strokeWidth: 3
}));