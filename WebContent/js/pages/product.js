getProducts();

var PRODUCTSHOLDER;

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
					var products = data["products"];
					PRODUCTSHOLDER = products;
					createProductItem(products);
				}	
			}
		});
	});
}

function filterProduct(category,itemId){
	removeItemActive();
	$("#"+itemId).addClass('active');
	var products = PRODUCTSHOLDER.slice();
	if(category != 'all'){
		var removeIndex = [];
		for(var o in products){
			var tempProduct = products[o];
			var tempCategory = tempProduct["category"];
			if(tempCategory != category){
				removeIndex.push(o);
			}
		}
		removeIndex.reverse();
		for(var i in removeIndex){
			var index = removeIndex[i];
			products.splice(index, 1);
		}
		createProductItem(products);
	}else{
		createProductItem(products);
	}
}

function removeItemActive(){
	$("#listItem0").removeClass('active');
	$("#listItem1").removeClass('active');
	$("#listItem2").removeClass('active');
	$("#listItem3").removeClass('active');
	$("#listItem4").removeClass('active');
	$("#listItem5").removeClass('active');
	$("#listItem6").removeClass('active');
	$("#listItem7").removeClass('active');
	$("#listItem8").removeClass('active');
}

function createProductItem(products){
	var productsHtml = '';
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
		
		if(name.length > 26){
			name = name.substring(0, 26) + '...';
		}
		
		if(material.length > 30){
			material = material.substring(0, 30) + '...';
		}
		
		if(description.length > 110){
			description = description.substring(0, 110) + '...';
		}
		
		productsHtml += '\
			<div class="col-6 col-sm-6 col-lg-4 thumbnail" style="height:480px">\
				<font size="4"><strong>'+name+'</strong></font>\
				<img class="img-thumbnail img-responsive my-small-height" src="'+image+'" alt="product-image">\
				<div class="row">\
					<div class="col-xs-12"><span class="label label-info">'+category+'</span></div>\
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
	
	if(productsHtml.length == 0){
		productsHtml = '<h2>No relevent products!!</h2>';
	}
	$("#productWindow").html(productsHtml);
}