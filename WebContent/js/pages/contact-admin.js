getContacts();

var CONTACTSHOLDER;

function getContacts(){
	$(document).ready(function(){
		var input = '{}';
		$.ajax({
			url: '/northgateuniform/servlet/GetAllContactServlet?json='+input,
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
					var contacts = data["contacts"];
					CONTACTSHOLDER = contacts;
					createContactItem(contacts);
				}	
			}
		});
	});
}

function createContactItem(contacts){
	var contactsHtml = '';
	
	for(var o in contacts){
		var contact = contacts[o];
		var id = contact["id"];
		var date = contact["createDate"];
		var title = contact["title"];
		var name = contact["name"];
		var company = contact["company"];
		var address = contact["address"];
		var phone = contact["phone"];
		var email = contact["email"];
		var industry = contact["industry"];
		var requirement = contact["requirement"];
		var status = contact["status"];
		var images = contact["images"];
		
		var imagesHtml = '';
		
		for(var i in images){
			var image = images[i];
			var imageUrl = image["image"];
			imagesHtml += '\
				<div class="col-sm-4">\
					<img class="img-thumbnail img-responsive center-block" src="'+imageUrl+'" style="height:140px;">\
				</div>';
		}
		
		contactsHtml += '\
			<div class="row">\
				<div class="col-sm-2">\
					<strong><font>'+date+'</font></strong>\
				</div>\
				<div class="col-sm-8">\
					<div class="row"><div class="col-sm-12"><strong>'+title+' '+name+'</strong>&nbsp;<span class="label label-info">'+industry+'</span></div></div>\
					<div class="row"><div class="col-sm-3">Company: </div><div class="col-sm-9">'+company+'</div></div>\
					<div class="row"><div class="col-sm-3">Address: </div><div class="col-sm-9">'+address+'</div></div>\
					<div class="row"><div class="col-sm-3">Phone: </div><div class="col-sm-9">'+phone+'</div></div>\
					<div class="row"><div class="col-sm-3">Email: </div><div class="col-sm-9">'+email+'</div></div>\
					<div class="row"><div class="col-sm-3">Requirement: </div><div class="col-sm-9">'+requirement+'</div></div><hr>\
					<div class="row"><div class="col-sm-3">Sample image:</div><div class="col-sm-9"><div class="row">'+imagesHtml+'</div></div></div>\
				</div>\
				<div class="col-sm-2">\
					<strong>'+status+'</strong>\
				</div>\
			</div><hr>';
	}
	
	$("#contactWindow").html(contactsHtml);
}



