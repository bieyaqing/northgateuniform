getContent();

function getContent(){
	var input = '{"id":1}';
	$(document).ready(function(){
		$.ajax({
			url: '/northgateuniform/servlet/GetWelcomeServlet?json='+input,
			type: 'POST',
			dataType: 'json',
			error: function(err){
				$("#messageModContent").html('Internet connection issue!');
				$("#messageMod").modal('show');
			},
			success: function(data){
				console.log(data);
				var welcome = data["welcome"];
				var company = welcome["company"];
				var text = welcome["text"];
				var address = welcome["address"];
				var tel = welcome["tel"];
				var handphone = welcome["handphone"];
				var fax = welcome["fax"];
				var email = welcome["email"];
				
				$("#myCompany").html(company);
				$("#myText").html(text);
				$("#myAddress").html('<font>'+address+'</font>');
				$("#myTel").html('<font>'+tel+'</font>');
				$("#myHandphone").html('<font>'+handphone+'</font>');
				$("#myFax").html('<font>'+fax+'</font>');
				$("#myEmail").html('<a href="mailto:'+email+'">'+email+'</a>');
			}
		});
	});
}