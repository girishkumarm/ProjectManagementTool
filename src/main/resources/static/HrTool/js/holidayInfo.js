$(document).ready(function(){

	fetchLeaves();

});


$("#updateLeaves").click(function(){

		console.log("hereeeeeeeeeeeeeeeeee")
		var input = new Object();
		input.organisationName = userDetails.organisation;
		input.zipcode = userDetails.zipCode;
		input.year = $("#leaveYear").val();
		input.annual = $("#totalAnnualLeaves").val();
		input.sick = $("#totalSickLeaves").val();
		input.unPaid = $("#totalUnPaidLeaves").val();

		HTTPUtil.POST("http://localhost:8080/rest/holiday-info/add-holiday-info-for-organisation", input,function(output){
			fetchLeaves();
		});

});

function fetchLeaves(){

	HTTPUtil.GET("http://localhost:8080/rest/holiday-info/fetch/"+userDetails.emailId+"/2016",function(output){
			if(output.responseEntity!=undefined && output.responseEntity!=null){
				
				$("#leaveYear").val(output.responseEntity.year);
				$("#totalAnnualLeaves").val(output.responseEntity.annual);
				$("#totalSickLeaves").val(output.responseEntity.sick);
				$("#totalUnPaidLeaves").val(output.responseEntity.unPaidLeaves);

		  	}
		});

}