authenticate();

function authenticate(){
	var adminAuth = localStorage.getItem("adminAuth");
	var input = '{"id":1,"adminAuth":"'+adminAuth+'"}';
	input = encodeURIComponent(input);
	$(document).ready(function(){
		$.ajax({
			url: '/northgateuniform/servlet/AuthServlet?json='+input,
			type: 'POST',
			dataType: 'json',
			error: function(err){
				$("#messageModContent").html('Internet connection issue!');
				$("#messageMod").modal('show');
			},
			success: function(data){
				console.log(data);
				var status = data["status"];
				if(status == 0){
					window.location.replace("/northgateuniform/pages/AdminLogin.html");
				}else{
					//do nothing
					getContent();
				}
			}
		});
	
	});
}

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
				var title = welcome["title"];
				var company = welcome["company"];
				var text = welcome["text"];
				var address = welcome["address"];
				
				var addressElements = '';
				
				try{
					addressElements = address.split(", ");
				}catch(err){
					//do nothing
				}
				
				var address1 = '';
				var address2 = '';
				var address3 = '';
				var address4 = '';
				
				try{
					address4 = addressElements[addressElements.length - 1];
					address1 = addressElements[0];
					
					if(addressElements.length > 2){
						address2 = addressElements[1];
					}
					
					if(addressElements.length > 3){
						address3 = addressElements[2];
					}
				}catch(err){
					//do nothing
				}
				
				var tel = welcome["tel"];
				var handphone = welcome["handphone"];
				var fax = welcome["fax"];
				var email = welcome["email"];
				
				$("#titleInput").val(title);
				$("#companyInput").val(company);
				$("#textInput").val(text);
				$("#addressInput1").val(address1);
				$("#addressInput2").val(address2);
				$("#addressInput3").val(address3);
				$("#addressInput4").val(address4);
				$("#telInput").val(tel);
				$("#faxInput").val(fax);
				$("#handphoneInput").val(handphone);
				$("#emailInput").val(email);
				$("#passwordInput").val(localStorage.getItem("adminAuth"));
			}
		});
	});
}

function updateWelcome(){
	titleValidate();
	companyValicate();
	telValidate();
	handphoneValidate();
	emailValicate();
	faxValidate();
	addressValidate();
	textValidate();
	passwordValidate();
	
	if(titleValidate()&&companyValicate()&&telValidate()&&handphoneValidate()&&emailValicate()&&faxValidate()&&addressValidate()&&textValidate()&&passwordValidate()){
		var title = $("#titleInput").val();
		var company = $("#companyInput").val();
		var text = $("#textInput").val();
		
		text = text.replace(/"/g,"'");
		
		var address1 = $("#addressInput1").val();
		var address2 = $("#addressInput2").val();
		var address3 = $("#addressInput3").val();
		var address4 = $("#addressInput4").val();
		var tel = $("#telInput").val();
		var fax = $("#faxInput").val();
		var handphone = $("#handphoneInput").val();
		var email = $("#emailInput").val();
		
		var password = $("#passwordInput").val();
		
		var address = address1;
		if(address2.length > 0){
			address += ', '+address2;
		}
		if(address3.length > 0){
			address += ', '+address3;
		}
		address += ', '+address4;
		var input = '{"id":1,"title":"'+title+'","company":"'+company+'","text":"'+text+'","address":"'+address+'","tel":"'+tel+'","fax":"'+fax+'","handphone":"'+handphone+'","email":"'+email+'","adminAuth":"'+password+'"}';
		input = encodeURIComponent(input);
		
		$.ajax({
			url: '/northgateuniform/servlet/EditWelcomeServlet?json='+input,
			type: 'POST',
			dataType: 'json',
			error: function(err){
				$("#messageModContent").html('Internet connection issue!');
				$("#messageMod").modal('show');
			},
			success: function(data){
				console.log(data);
				var status = data["status"];
				var message = data["message"];
				if(status == 0){
					$("#messageModContent").html(message);
					$("#messageMod").modal('show');
				}else{
					$("#messageModContent").html('Success!');
					$("#messageMod").modal('show');
				}
			}
		});
	}
}

function recommendInputs(){
	var title = 'Welcome';
	var company = 'NorthGate Intertrade Uniform';
	var text = '\
<p><font size=4 color="#00B8E6">NorthGate InterTrade Pte Ltd </font><font size=4>is an established Uniform\
supplier and manufacturer with many years of experience in\
Singapore.</font></p>\
<p><font size=4>We have all kind of ready made or custom made uniform,\
t-shirt and garment available in a wide choices of colors and\
fabrics to meet different needs and requirement.</font></p>\
<p><font size=4>Be rest assured\
that we can provide you with the best workmanship, competitive\
pricings and most important our SINCERE services.</font></p>\
<p><font size=3 color="#660066">Free consultation and quotation is given to interested customers . Please contact us for an appointment.</font></p>';
	var address1 = 'BLK 3021';
	var address2 = 'UBI AVE 2';
	var address3 = '#03-181';
	var address4 = 'SINGAPORE 408897';
	var tel = '67456792';
	var fax = '67430089';
	var handphone = '97586096';
	var email = 'northgateuniform@gmail.com';
	var password = 'admin';
	
	$("#titleInput").val(title);
	$("#companyInput").val(company);
	$("#textInput").val(text);
	$("#addressInput1").val(address1);
	$("#addressInput2").val(address2);
	$("#addressInput3").val(address3);
	$("#addressInput4").val(address4);
	$("#telInput").val(tel);
	$("#faxInput").val(fax);
	$("#handphoneInput").val(handphone);
	$("#emailInput").val(email);
	$("#passwordInput").val(password);
}

function titleValidate(){
	var title = $("#titleInput").val();
	if(title.length == 0){
		$("#titleMsg").html('Please give me a title!');
		return false;
	}else{
		$("#titleMsg").html('');
		return true;
	}
}

function companyValicate(){
	var company = $("#companyInput").val();
	if(company.length == 0){
		$("#companyMsg").html('Please give me your company name!');
		return false;
	}else{
		$("#companyMsg").html('');
		return true;
	}
}

function telValidate(){
	var tel = $("#telInput").val();
	if(tel.length == 0){
		$("#telMsg").html('Please give me your telephone number!');
		return false;
	}else if(!$.isNumeric(tel)){
		$("#telMsg").html('Please give me your real telephone number!');
		return false;
	}else{
		$("#telMsg").html('');
		return true;
	}
}

function handphoneValidate(){
	var handphone = $("#handphoneInput").val();
	if(handphone.length == 0){
		$("#handphoneMsg").html('Please give me your handphone number!');
		return false;
	}else if(!$.isNumeric(handphone)){
		$("#handphoneMsg").html('Please give me your real handphone number!');
		return false;
	}else{
		$("#handphoneMsg").html('');
		return true;
	}
}

function emailValicate(){
	var email = $("#emailInput").val();
	if(email.length == 0){
		$("#emailMsg").html('Please give me your email address!');
		return false;
	}else if(!isValidEmail(email)){
		$("#emailMsg").html('Please give me your real email address!');
		return false;
	}else{
		$("#emailMsg").html('');
		return true;
	}
}

function faxValidate(){
	var fax = $("#faxInput").val();
	if(fax.length == 0){
		$("#faxMsg").html('Please give me your fax number!');
		return false;
	}else if(!$.isNumeric(fax)){
		$("#faxMsg").html('Please give me your real fax number!');
		return false;
	}else{
		$("#faxMsg").html('');
		return true;
	}
}

function addressValidate(){
	var address1 = $("#addressInput1").val();
	var address2 = $("#addressInput2").val();
	var address3 = $("#addressInput3").val();
	var address4 = $("#addressInput4").val();
	
	var address = address1 + address2 + address3 + address4;
	
	if(address1.length == 0){
		$("#addressMsg").html('Please give me your address!');
		return false;
	}else if(address4.length == 0){
		$("#addressMsg").html('Please give me your postcode!');
		return false;
	}else if(address.indexOf(",") != -1){
		$("#addressMsg").html('I cannot take comma sign!');
		return false;
	}else{
		$("#addressMsg").html('');
		return true;
	}
}

function textValidate(){
	var text = $("#textInput").val();
	if(text.length == 0){
		$("#textMsg").html('Please give me a welcome content!');
		return false;
	}else{
		$("#textMsg").html('');
		return true;
	}
}

function passwordValidate(){
	var password = $("#passwordInput").val();
	if(password.length == 0){
		$("#passwordMsg").html('Please set a admin password!');
		return false;
	}else{
		$("#passwordMsg").html('');
		return true;
	}
}

function isValidEmail(email) {
    var pattern = new RegExp(/^(("[\w-+\s]+")|([\w-+]+(?:\.[\w-+]+)*)|("[\w-+\s]+")([\w-+]+(?:\.[\w-+]+)*))(@((?:[\w-+]+\.)*\w[\w-+]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][\d]\.|1[\d]{2}\.|[\d]{1,2}\.))((25[0-5]|2[0-4][\d]|1[\d]{2}|[\d]{1,2})\.){2}(25[0-5]|2[0-4][\d]|1[\d]{2}|[\d]{1,2})\]?$)/i);
    return pattern.test(email);
}

function logout(){
	localStorage.clear();
	location.reload();
}
