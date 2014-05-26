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
					getProducts();
				}
			}
		});
	
	});
}

function getProducts(){
	$(document).ready(function(){
		var input = '{}';
		$.ajax({
			url: '/northgateuniform/servlet/GetAllProductServlet?json='+input,
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
					var productsHtml = '';
					var products = data["products"];
					for(var o in products){
						var product = products[o];
						var name = product["name"];
						var images = product["images"];
						var category = product["category"];
						var price = product["price"];
						var material = product["material"];
						var size = product["size"];
						var description = product["description"];
						var id = product["id"];
						
						var image = '/northgateuniform/img/nophoto.png';
						for(var i in images){
							image = images[i]["image"];
						}
						
						if(name.length > 15){
							name = name.substring(0, 15) + '...';
						}
						
						if(material.length > 10){
							material = material.substring(0, 10) + '...';
						}
						
						if(description.length > 60){
							description = description.substring(0, 60) + '...';
						}
						
						productsHtml += '\
							<div class="col-sm-2 thumbnail" style="height:380px">\
								<font><strong>'+name+'</strong></font><button onclick="hideProduct('+id+')" type="button" class="close" aria-hidden="true">&times;</button>\
								<img class="img-thumbnail img-responsive my-small-height" src="'+image+'" alt="product-image" style="height:180px">\
								<div class="row">\
									<div class="col-xs-9"><span class="label label-info">'+category+'</span></div><div class="col-xs-3"><span class="glyphicon glyphicon-edit my-edit" onclick="lanuchEditProduct('+id+')"></span></div>\
									<div class="col-xs-3"><font><strong>Price:</strong></font></div>\
									<div class="col-xs-9">'+price+' S$</div>\
									<div class="col-xs-3"><font><strong>Size:</strong></font></div>\
									<div class="col-xs-9">'+size+'</div>\
									<div class="col-xs-4"><font><strong>Material:</strong></font></div>\
									<div class="col-xs-8">'+material+'</div>\
									<div class="col-xs-12"><font><strong>Description:</strong></font></div>\
									<div class="col-xs-12"><font>'+description+'</font></div>\
								</div>\
							</div>';
					}
					$("#productWindow").html(productsHtml);
				}	
			}
		});
	});
}

function createProduct(){
	$("#createProductMessage").html('');
	var name = $("#inputName").val();
	var price = $("#inputPrice").val();
	var size = $("#inputSize").val();
	var category = $("#inputCategory").val();
	var material = $("#inputMaterial").val();
	var description = $("#inputDescription").val();
	var image = $("#inputImage").val();
	
	if(name.length == 0){
		$("#createProductMessage").html('Tell me the product name!');
		return false;
	}
	
	if(price.length == 0){
		$("#createProductMessage").html('Check your price input!');
		return false;
	}
	
	if(size.length == 0){
		$("#createProductMessage").html('Tell me the product size!');
		return false;
	}
	
	if(material.length == 0){
		material = "Unknown";
	}
	
	if(description.length == 0){
		description = "Unknown";
	}
	
	if(image.length == 0){
		image = "/northgateuniform/img/nophoto.png";
	}
	
	var input = '{"name":"'+name+'","price":'+price+',"size":"'+size+'","category":"'+category+'","material":"'+material+'","description":"'+description+'","image":"'+image+'"}';
	input = encodeURIComponent(input);
	
	$.ajax({
		url: '/northgateuniform/servlet/CreateProductServlet?json='+input,
		type: 'POST',
		dataType: 'json',
		error: function(err){
			$("#createProductMessage").html('Internet connection issue!');
		},
		success: function(data){
			console.log(data);
			var status = data["status"];
			var message = data["message"];
			if(status==0){
				$("#createProductMessage").html(message);
			}else{
				getProducts();
				$('#createProductMod').modal('hide');
			}	
		}
	});
}

function uploadPhoto(){
	$("#createProductMessage").html('');
	var fd = new FormData(document.getElementById("uploadImageForm"));
	$.ajax({
		url : '/northgateuniform/htmlutil/FileUploadServlet?json={"fileType":"image,jpeg,jpg,png","uploadDirectory":"product_files","folderName":"image"}',
		type : "POST",
		data : fd,
		dataType: 'json',
		processData : false,
		contentType : false,
		error : function(err) {
			$("#createProductMessage").html('Internet connection issue!');
		},
		success : function(data) {
			console.log(data);
			var status = data["status"];
			var message = data["message"];
			if(status==0){
				$("#createProductMessage").html(message);
			}else{
				var fileUrl = data["fileUrl"];
				$("#inputImage").val(fileUrl);
				$("#uploadImage").attr("src",fileUrl);
			}
		}
	});
}

function lanuchEditProduct(id){
	$("#editProductMessage").html('');
	var input ='{"id":'+id+'}';
	$.ajax({
		url: '/northgateuniform/servlet/GetProductServlet?json='+input,
		type: 'POST',
		dataType: 'json',
		error: function(err){
			$("#editProductMessage").html('Internet connection issue!');
		},
		success: function(data){
			console.log(data);
			var status = data["status"];
			var message = data["message"];
			if(status==0){
				$("#editProductMessage").html(message);
			}else{
				var product = data["product"];
				var name = product["name"];
				var images = product["images"];
				var category = product["category"];
				var price = product["price"];
				var material = product["material"];
				var size = product["size"];
				var description = product["description"];
				var id = product["id"];
				
				var image = '/northgateuniform/img/nophoto.png';
				for(var i in images){
					image = images[i]["image"];
				}
				
				$("#editName").val(name);
				$("#editImage").val(image);
				$("#editUploadImage").attr("src",image);
				$("#editCategory").val(category);
				$("#editPrice").val(price);
				$("#editMaterial").val(material);
				$("#editSize").val(size);
				$("#editDescription").val(description);
				
				$("#editButton").attr("onclick","editProduct("+id+")");
				
				$("#editProductMod").modal('show');
			}
		}
	});
}

function editProduct(id){
	$("#editProductMessage").html('');
	var name = $("#editName").val();
	var price = $("#editPrice").val();
	var size = $("#editSize").val();
	var category = $("#editCategory").val();
	var material = $("#editMaterial").val();
	var description = $("#editDescription").val();
	var image = $("#editImage").val();
	
	if(name.length == 0){
		$("#editProductMessage").html('Tell me the product name!');
		return false;
	}
	
	if(price.length == 0){
		$("#editProductMessage").html('Check your price input!');
		return false;
	}
	
	if(size.length == 0){
		$("#editProductMessage").html('Tell me the product size!');
		return false;
	}
	
	if(material.length == 0){
		material = "Unknown";
	}
	
	if(description.length == 0){
		description = "Unknown";
	}
	
	if(image.length == 0){
		image = "/northgateuniform/img/nophoto.png";
	}
	
	var input = '{"id":'+id+',"name":"'+name+'","price":'+price+',"size":"'+size+'","category":"'+category+'","material":"'+material+'","description":"'+description+'","image":"'+image+'"}';
	input = encodeURIComponent(input);
	
	$.ajax({
		url: '/northgateuniform/servlet/EditProductServlet?json='+input,
		type: 'POST',
		dataType: 'json',
		error: function(err){
			$("#editProductMessage").html('Internet connection issue!');
		},
		success: function(data){
			console.log(data);
			var status = data["status"];
			var message = data["message"];
			if(status==0){
				$("#editProductMessage").html(message);
			}else{
				getProducts();
				$('#editProductMod').modal('hide');
			}	
		}
	});
}

function editUploadPhoto(){
	$("#editProductMessage").html('');
	var fd = new FormData(document.getElementById("editUploadImageForm"));
	$.ajax({
		url : '/northgateuniform/htmlutil/FileUploadServlet?json={"fileType":"image,jpeg,jpg,png","uploadDirectory":"product_files","folderName":"image"}',
		type : "POST",
		data : fd,
		dataType: 'json',
		processData : false,
		contentType : false,
		error : function(err) {
			$("#editProductMessage").html('Internet connection issue!');
		},
		success : function(data) {
			console.log(data);
			var status = data["status"];
			var message = data["message"];
			if(status==0){
				$("#editProductMessage").html(message);
			}else{
				var fileUrl = data["fileUrl"];
				$("#editImage").val(fileUrl);
				$("#editUploadImage").attr("src",fileUrl);
			}
		}
	});
}

function hideProduct(id){
	var q = confirm("Are you sure to remove this product?");
	
	if(q){
		var input = '{"id":'+id+',"displayStatus":"n"}';
		input = encodeURIComponent(input);
		
		$.ajax({
			url: '/northgateuniform/servlet/EditProductServlet?json='+input,
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
					getProducts();
				}	
			}
		});
	}
}

function goCustomerPage(){
	var win = window.open("/northgateuniform/pages/main.html", '_blank');
	win.focus();
}
