clickWelcomeAdmin();

function iframeHeight(){
	$(".my-iframe").height($(window).height() - 120);
}

function clickWelcomeAdmin(){
	$("#nav-title").html("Welcome Admin");
	$("#nav-title").attr("onclick","clickWelcomeAdmin()");
	
	$("#page-content").html('<iframe id="welcomIframe" class="my-iframe animated fadeInUp" src="welcome-admin.html"></iframe>');
	iframeHeight();
}

function clickProductAdmin(){
	$("#nav-title").html("Product Admin");
	$("#nav-title").attr("onclick","clickProductAdmin()");
	
	$("#page-content").html('<iframe id="productIframe" class="my-iframe animated fadeInUp" src="product-admin.html"></iframe>');
	iframeHeight();
}

function clickPricingAdmin(){
	$("#nav-title").html("Pricing Admin");
	$("#nav-title").attr("onclick","clickPricingAdmin()");
	
	$("#page-content").html('<iframe id="pricingIframe" class="my-iframe animated fadeInUp" src="pricing-admin.html"></iframe>');
	iframeHeight();
}

function clickContactAdmin(){
	$("#nav-title").html("Contact Admin");
	$("#nav-title").attr("onclick","clickContactAdmin()");
	
	$("#page-content").html('<iframe id="contactIframe" class="my-iframe animated fadeInUp" src="contact-admin.html"></iframe>');
	iframeHeight();
}