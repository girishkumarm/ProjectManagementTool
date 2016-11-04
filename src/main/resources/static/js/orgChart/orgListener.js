
orgChart.addDiagramListener("ExternalObjectsDropped", function(e) {

			var obj = JSON.parse(orgChart.model.toJson());

			obj = obj.nodeDataArray;


			for (var j = 0; j < obj.length; j++) {
				for (var i = 0; i < unAssignedData.length; i++) {
					if (unAssignedData[i].key == obj[j].key) {
						console.log("deleting the key:"+unAssignedData[i].key)
						var key=unAssignedData[i].key;
						delete unAssignedData[i];
						empPalette.remove(empPalette.findPartForKey(key));
						break;
					}
				}
			}
			var temp=[];
			var k=0;
			for (var i = 0; i < unAssignedData.length; i++) {
				if(unAssignedData[i]!=undefined){
					temp[k]=unAssignedData[i];
					k++;
				}
			}	
			unAssignedData=temp;
		});


var beforeDeleting=[];
orgChart.addDiagramListener("SelectionDeleting", function(e) {

			var obj = JSON.parse(orgChart.model.toJson());
			obj = obj.nodeDataArray;
			beforeDeleting=obj;
		});

orgChart.addDiagramListener("SelectionDeleted", function(e) {
			var obj = JSON.parse(orgChart.model.toJson());
			obj = obj.nodeDataArray;

			for (var j = 0; j < beforeDeleting.length; j++) {
				var matched=false;
				for (var i = 0; i < obj.length; i++) {
					if (beforeDeleting[j].key == obj[i].key) {
						matched = true;
					}
				}
				if(!matched){
					var unlength=unAssignedData.length;
					unAssignedData[unlength]=new Object();
					unAssignedData[unlength].key=beforeDeleting[j].key;
					unAssignedData[unlength].name=beforeDeleting[j].name;
					unAssignedData[unlength].parent=-1;
					unAssignedData[unlength].title=beforeDeleting[j].title;
					unAssignedData[unlength].source=beforeDeleting[j].source;
					unAssignedData[unlength].department=beforeDeleting[j].department;
					empPalette.model = new go.TreeModel(unAssignedData);
				}
			}	
		});