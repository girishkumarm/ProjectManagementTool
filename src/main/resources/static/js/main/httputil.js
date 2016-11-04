var HTTPUtil = {
	GET : function(endpoint, callback) {
		$.ajax({
			type : "GET",
			url : endpoint,
			dataType : "json",
			headers : {
				'userEmailId' : "userEmailId",
				'sessionToken' : "sessionToken"
			},
			crossOrigin: true,
			async : false
		}).done(function(msg) {
			callback(msg, undefined);
		}).error(function(msg) {
			callback(msg, undefined);
		});
	},
	POST : function(endpoint, dataBody, callback) {
		$.ajax({
			type : "POST",
			url : endpoint,
			dataType : "json",
			headers : {
				'userEmailId' : "userEmailId",
				'sessionToken' : "sessionToken"
			},
			crossOrigin: true,
			data : JSON.stringify(dataBody),
			contentType : "application/json; charset=utf-8"
		}).done(function(msg) {
			callback(msg, undefined);
		}).error(function(msg) {
			callback(msg, undefined);
		});

	}
};
