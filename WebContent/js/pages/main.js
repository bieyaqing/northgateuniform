clickHome();

function iframeHeight(){
	$(".my-iframe").height($(window).height() - 120);
}

function clickHome(){
	$("#nav-title").html("Welcome");
	$("#nav-title").attr("onclick","clickHome()");
	
	$("#page-content").html('<iframe id="welcomIframe" class="my-iframe animated fadeInUp" src="welcome.html"></iframe>');
	iframeHeight();
}

function clickProduct(){
	$("#nav-title").html("Product");
	$("#nav-title").attr("onclick","clickProduct()");
	
	$("#page-content").html('<iframe id="productIframe" class="my-iframe animated fadeInUp" src="product.html"></iframe>');
	iframeHeight();
}

function clickPricing(){
	$("#nav-title").html("Pricing");
	$("#nav-title").attr("onclick","clickPricing()");
	
	$("#page-content").html('<iframe id="pricingIframe" class="my-iframe animated fadeInUp" src="pricing.html"></iframe>');
	iframeHeight();
}

function clickContactUs(){
	$("#nav-title").html("Contact Us");
	$("#nav-title").attr("onclick","clickContactUs()");
	
	$("#page-content").html('<iframe id="contactIframe" class="my-iframe animated fadeInUp" src="contact.html"></iframe>');
	iframeHeight();
}