angular.module('jewelhaat').config([ '$routeProvider', function($routeProvider) {
	$routeProvider
	// Home
	.when("/", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "PageCtrl"
	})
	// Pages
	.when("/about", {
		templateUrl : "/jewelhaat/resources/partials/about.html",
		controller : "PageCtrl"
	}).when("/faq", {
		templateUrl : "/jewelhaat/resources/partials/faq.html",
		controller : "PageCtrl"
	}).when("/pricing", {
		templateUrl : "/jewelhaat/resources/partials/pricing.html",
		controller : "PageCtrl"
	}).when("/services", {
		templateUrl : "/jewelhaat/resources/partials/services.html",
		controller : "PageCtrl"
	}).when("/contact", {
		templateUrl : "/jewelhaat/resources/partials/contact.html",
		controller : "PageCtrl"
	}).when("/registration", {
		templateUrl : "/jewelhaat/resources/partials/registration.html",
		controller : "PageCtrl"
	}).when("/login", {
		templateUrl : "/jewelhaat/resources/partials/login.html",
		controller : "PageCtrl"
	}).when("/customdesign", {
		templateUrl : "/jewelhaat/resources/partials/customdesign.html",
		controller : "PageCtrl"
	}).when("/new", {
		templateUrl : "/jewelhaat/resources/partials/selljwel.html",
		controller : "PageCtrl"
	}).when("/newdesign", {
		templateUrl : "/jewelhaat/resources/partials/addcustomdesign.html",
		controller : "PageCtrl"
	}).when("/register", {
		templateUrl : "/jewelhaat/resources/partials/registration.html",
		controller : "PageCtrl"
	}).when("/drafts", {
		templateUrl : "/jewelhaat/resources/partials/drafts.html",
		controller : "PageCtrl"
	}).when("/edit", {
		templateUrl : "/jewelhaat/resources/partials/editdraft.html",
		controller : "PageCtrl"
	}).when("/me", {
		templateUrl : "/jewelhaat/resources/partials/me.html",
		controller : "PageCtrl"
	}).when("/myCarousel", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "BlogCtrl"
	}).when("/rowCarousel", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "BlogCtrl"
	}).when("/silverJewelry", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "BlogCtrl"
	}).when("/rv", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "BlogCtrl"
	}).when("/foryou", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "BlogCtrl"
	}).when("/gold", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "BlogCtrl"
	}).when("/best", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "BlogCtrl"
	}).when("/latestdesigns", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "BlogCtrl"
	}).when("/below5", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "BlogCtrl"
	}).when("/newones", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "BlogCtrl"
	}).when("/specials", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "BlogCtrl"
	}).when("/weddings", {
		templateUrl : "/jewelhaat/resources/partials/home.html",
		controller : "BlogCtrl"
	})
	
	
	
	// Blog
	.when("/blog", {
		templateUrl : "/jewelhaat/resources/partials/blog.html",
		controller : "BlogCtrl"
	}).when("/blog/post", {
		templateUrl : "/jewelhaat/resources/partials/blog_item.html",
		controller : "BlogCtrl"
	}).when("/jwel/details", {
		templateUrl : "/jewelhaat/resources/partials/jweldetails.html",
		controller : "BlogCtrl"
	})
	// else 404
	.otherwise("/404", {
		templateUrl : "/jewelhaat/resources/partials/404.html",
		controller : "PageCtrl"
	});
} ]);