var SAMPLEIMGS = [];

function submitContact(){
	var name = $("#customerNameInput").val();
	var title = $("#titleInput").val();
	var company = $("#companyInput").val();
	var industry = $("#industryInput").val();
	var otherIndustry = $("#otherIndustyInput").val();
	var address1 = $("#addressInput1").val();
	var address2 = $("#addressInput2").val();
	var address3 = $("#addressInput3").val();
	var phone = $("#phoneInput").val();
	var email = $("#emailInput").val();
	var requirement = $("#requirementInput").val();
	
	var isError = false;
	
	if(name.length == 0){
		$("#customerNameMsg").html('Please tell us your name!');
		isError = true;
	}else if(name.length >= 30){
		$("#customerNameMsg").html('Please give us a shorter name!');
		isError = true;
	}else{
		$("#customerNameMsg").html('*');
	}
	
	if(company.length == 0){
		$("#companyMsg").html('Put individual if no company!');
		isError = true;
	}else if(company.length >= 40){
		$("#companyMsg").html('Please give us a shorter company name!');
		isError = true;
	}else{
		$("#companyMsg").html('*');
	}
	
	if(address1.length == 0){
		$("#addressMsg").html('Please tell us your building!');
		isError = true;
	}else if(address1.length >= 20){
		$("#addressMsg").html('Please tell us a shorter building name!');
		isError = true;
	}else{
		$("#addressMsg").html('*');
	}
	
	if(address2.length == 0){
		$("#addressMsg").html('Please tell us your street!');
		isError = true;
	}else if(address2.length >= 20){
		$("#addressMsg").html('Please tell us a shorter street name!');
		isError = true;
	}else{
	}
	
	if(address3.length == 0){
		$("#addressMsg").html('Please tell us your postcode!');
		isError = true;
	}else if(address3.length != 6 || !$.isNumeric(address3)){
		$("#addressMsg").html('Please tell us a valid postcode!');
		isError = true;
	}else{
	}
	
	if(phone.length == 0){
		$("#phoneMsg").html('Please tell us your phone number!');
		isError = true;
	}else if(phone.length != 8 || !$.isNumeric(phone)){
		$("#phoneMsg").html('Please tell us a valid phone number!');
		isError = true;
	}else{
		$("#phoneMsg").html('*');
	}
	
	if(email.length == 0){
		$("#emailMsg").html('Please tell us your email!');
		isError = true;
	}else if(!isValidEmail(email)){
		$("#emailMsg").html('Please tell us a valid email!');
		isError = true;
	}else{
		$("#emailMsg").html('*');
	}
	
	if(requirement.length == 0){
		$("#requirementMsg").html('Please tell us what we can do for you!');
		isError = true;
	}else{
		$("#requirementMsg").html('*');
	}
	
	if(isError){
		//do nothing!!
	}else{
		if(industry == 'other institutions'){
			industry = industry + " - " + otherIndustry;
		}
		
		var address = address1 + ", " + address2 + ", Singapore" + address3;
		
		var images = '[';
		
		for(var o in SAMPLEIMGS){
			var tempImg = SAMPLEIMGS[o];
			images += "\"" + tempImg + "\"" + ",";
		}
		
		if(SAMPLEIMGS.length != 0){
			images = images.substring(0, images.length - 1);
		}
		
		images += ']';
		
		var input = '{"name":"'+name+'","title":"'+title+'","company":"'+company+'","industry":"'+industry+'","address":"'+address+'","phone":"'+phone+'","email":"'+email+'","requirement":"'+requirement+'","images":'+images+'}';
		
		var r = confirm('Please double check your input!\n\n'+title+' '+name+'\nCompany: '+company+'\nIndustry: '+industry+'\nAddress: '+address+'\nPhone: '+phone+'\nEmail: '+email+'\nRequirement: '+requirement);
		
		if(r){
			$("#messageModContent").html('');
			input = encodeURIComponent(input);
			$.ajax({
				url: '/northgateuniform/servlet/CreateContactServlet?json='+input,
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
					if(status==0){
						$("#messageModContent").html(message);
						$("#messageMod").modal('show');
					}else{
						$("#messageModContent").html('Your request submit successfully!');
						$("#messageMod").modal('show');
						setTimeout(function(){location.reload();}, 3000);
					}	
				}
			});
		}
	}
}

function sampleImgUpload(){
	$("#uploadMsg").html('');
	var fd = new FormData(document.getElementById("sampleImgUploadForm"));
	$.ajax({
		url : '/northgateuniform/htmlutil/FileUploadServlet?json={"fileType":"image,jpeg,jpg,png","uploadDirectory":"product_files","folderName":"image"}',
		type : "POST",
		data : fd,
		dataType: 'json',
		processData : false,
		contentType : false,
		error : function(err) {
			$("#uploadMsg").html('Internet connection issue!');
		},
		success : function(data) {
			console.log(data);
			var status = data["status"];
			var message = data["message"];
			if(status==0){
				$("#uploadMsg").html(message);
			}else{
				var fileUrl = data["fileUrl"];
				if(SAMPLEIMGS.length >= 3){
					$("#uploadMsg").html('You can only upload 3 images');
				}else{
					SAMPLEIMGS.push(fileUrl);
				}
				builtImage();
			}
		}
	});
}

function removeImage(fileUrl){
	SAMPLEIMGS.splice(SAMPLEIMGS.indexOf(fileUrl), 1);
	builtImage();
}

function builtImage(){
	var sampleImgUrl = '';
	for(var o in SAMPLEIMGS){
		var tempUrl = SAMPLEIMGS[o];
		sampleImgUrl += '\
			<div class="col-sm-4">\
				<button onclick="removeImage(\''+tempUrl+'\')" type="button" class="close" aria-hidden="true">&times;</button>\
				<img class="img-thumbnail img-responsive my-small-height" src="'+tempUrl+'" alt="sample-image">\
			</div>';
	}
	$("#sampleImgs").html(sampleImgUrl);
}

function isOtherIndustry(){
	var industry = $("#industryInput").val();
	if(industry == 'other institutions'){
		$("#otherIndustyInput").removeClass('sr-only');
	}else{
		$("#otherIndustyInput").addClass('sr-only');
	}
}

function isValidEmail(email) {
    var pattern = new RegExp(/^(("[\w-+\s]+")|([\w-+]+(?:\.[\w-+]+)*)|("[\w-+\s]+")([\w-+]+(?:\.[\w-+]+)*))(@((?:[\w-+]+\.)*\w[\w-+]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][\d]\.|1[\d]{2}\.|[\d]{1,2}\.))((25[0-5]|2[0-4][\d]|1[\d]{2}|[\d]{1,2})\.){2}(25[0-5]|2[0-4][\d]|1[\d]{2}|[\d]{1,2})\]?$)/i);
    return pattern.test(email);
}

