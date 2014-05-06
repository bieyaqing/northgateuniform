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
					contacts = contacts.reverse();
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
		var dateStr = contact["createDate"];
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
		
		//date = date.replace("T"," ");
		
		if(status == 'pending'){
			status = '<font class="color-orange">'+status+'...</font>';
		}
		
		if(status == 'processing'){
			status = '<font class="color-green">'+status+'...</font>';
		}
		
		if(status == 'completed'){
			status = '<font class="color-yellow">'+status+'</font>';
		}
		
		if(status == 'canceled'){
			status = '<font class="color-gray">'+status+'</font>';
		}
		
		var date = new Date(dateStr);
		
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
					<strong><font class="color-darkblue">'+date.toDateString()+" "+date.toLocaleTimeString()+'</font></strong>\
				</div>\
				<div class="col-sm-8">\
					<div class="row"><div class="col-sm-12"><strong>'+title+' '+name+'</strong>&nbsp;&nbsp;&nbsp;<span class="label label-info">'+industry+'</span></div></div>\
					<div class="row"><div class="col-sm-3">Company: </div><div class="col-sm-9">'+company+'</div></div>\
					<div class="row"><div class="col-sm-3">Address: </div><div class="col-sm-9">'+address+'</div></div>\
					<div class="row"><div class="col-sm-3">Phone: </div><div class="col-sm-9">'+phone+'</div></div>\
					<div class="row"><div class="col-sm-3">Email: </div><div class="col-sm-9"><a href="mailto:'+email+'">'+email+'</a></div></div>\
					<div class="row"><div class="col-sm-3">Requirement: </div><div class="col-sm-9">'+requirement+'</div></div><hr>\
					<div class="row"><div class="col-sm-3">Sample image:</div><div class="col-sm-9"><div class="row">'+imagesHtml+'</div></div></div>\
				</div>\
				<div class="col-sm-2">\
					<strong onclick="openStatusOption('+id+')" id="statusDis-'+id+'">'+status+'</strong><label id="collapseSelect-'+id+'" onclick="openStatusOption('+id+')" class="glyphicon glyphicon-collapse-down pull-right"></label>\
					<select onchange="updateContactStatus('+id+')" id="statusSelect-'+id+'" class="sr-only form-control">\
						<option>select status</option>\
						<option>pending</option>\
						<option>processing</option>\
						<option>completed</option>\
						<option>canceled</option>\
					</select>\
				</div>\
			</div><hr>';
	}
	
	$("#contactWindow").html(contactsHtml);
}

function openStatusOption(id){
	$("#statusSelect-"+id).removeClass('sr-only');
	$("#collapseSelect-"+id).attr('onclick','closeStatusOption('+id+')');
	$("#statusDis-"+id).attr('onclick','closeStatusOption('+id+')');
}

function closeStatusOption(id){
	$("#statusSelect-"+id).addClass('sr-only');
	$("#collapseSelect-"+id).attr('onclick','openStatusOption('+id+')');
	$("#statusDis-"+id).attr('onclick','openStatusOption('+id+')');
}

function updateContactStatus(id){
	var statusInput = $("#statusSelect-"+id).val();
	var input = '{"id":'+id+',"status":"'+statusInput+'"}';
	
	$.ajax({
		url: '/northgateuniform/servlet/EditContactServlet?json='+input,
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
				if(statusInput == 'pending'){
					statusInput = '<font class="color-orange">'+statusInput+'...</font>';
				}
				
				if(statusInput == 'processing'){
					statusInput = '<font class="color-green">'+statusInput+'...</font>';
				}
				
				if(statusInput == 'completed'){
					statusInput = '<font class="color-yellow">'+statusInput+'</font>';
				}
				
				if(statusInput == 'canceled'){
					statusInput = '<font class="color-gray">'+statusInput+'</font>';
				}
				
				$("#statusDis-"+id).html(statusInput);
				closeStatusOption(id);
			}	
		}
	});
}

function filterContact(contactStatus,btnId){
	removeButtonDisable();
	$("#"+btnId).prop('disabled',true);
	var contacts = CONTACTSHOLDER.slice();
	if(contactStatus != 'all'){
		var removeIndex = [];
		for(var o in contacts){
			var tempContact = contacts[o];
			var tempStatus = tempContact["status"];
			if(tempStatus != contactStatus){
				removeIndex.push(o);
			}
		}
		removeIndex.reverse();
		for(var i in removeIndex){
			var index = removeIndex[i];
			contacts.splice(index, 1);
		}
		createContactItem(contacts);
	}else{
		createContactItem(contacts);
	}
}

function removeButtonDisable(){
	$("#btn-01").prop('disabled',false);
	$("#btn-02").prop('disabled',false);
	$("#btn-03").prop('disabled',false);
	$("#btn-04").prop('disabled',false);
	$("#btn-05").prop('disabled',false);
}
