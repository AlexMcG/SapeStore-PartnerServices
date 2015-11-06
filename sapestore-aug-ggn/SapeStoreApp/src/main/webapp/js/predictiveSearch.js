$(document).ready(function() {
	$(function() {
		$("#searchByText").autocomplete({

			source : function(request, response) {
				$.ajax({
					url : "PredictiveSearch",
					type : "POST",
					data : {
						search : request.search
					},
					dataType : "json",
					success : function(data) {
						response(data);
					}
				});
			}
		});
	});
});
