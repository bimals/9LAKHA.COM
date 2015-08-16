var app = angular.module("bs", ['ngRoute']);

app.controller("RegistrationController", [ '$scope', '$http',
		function($scope, $http) {

			$scope.registerUser = function() {

				var userObj = {
					userName : $scope.inputEmail,
					password : $scope.password,
					firstName : $scope.firstName,
					lastName : $scope.lastName
				};
				var res = $http.post('/9lakha/account/user/create', userObj);
				res.success(function(data, status, headers, config) {
					$scope.message = data;
					$scope.inputEmail = '';
					$scope.password = '';
					$scope.firstName = '';
					$scope.lastName = '';
					window.location = '/9lakha/account/user/loginSuccess';
				});
				res.error(function(data, status, headers, config) {
					alert("failure message: " + JSON.stringify({
						data : data
					}));
				});
				// Making the fields empty
				//

			};
		} ]);

app.controller("loginController", [ '$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {

	$scope.login = function() {

		var userObj = {
			userName : $scope.inputEmail,
			password : $scope.password
		};
		var res = $http.post('/9lakha/account/user/login', userObj);
		res.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.inputEmail = '';
			$scope.password = '';
			$rootScope.loggedIn = 'true';
			window.location = '#/';
		});
		res.error(function(data, status, headers, config) {
			alert("failure message: " + JSON.stringify({
				data : data
			}));
		});
		// Making the fields empty
		//

	};
} ]);

app.controller("HomeController", [ '$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {

	   $scope.isLoggedIn = function() {

		      $http.get('/9lakha/account/user/checklogin')
		        .success(function(data) {
		          console.log(data);
		          $rootScope.loggedIn = data;
		        })
		        .error(function(data) {
		          console.log('error: ' + data);
		        });
		    };
		$scope.search = function() {

			var searchObj = {
				searchText : $scope.searchText
			};
			var res = $http.post('/9lakha/product/search', searchObj);
			res.success(function(data, status, headers, config) {
				$scope.message = data;
				$scope.inputEmail = '';
				$scope.password = '';
				$scope.firstName = '';
				$scope.lastName = '';
				window.location = '#/';
			});
			res.error(function(data, status, headers, config) {
				alert("failure message: " + JSON.stringify({
					data : data
				}));
			});
		};
} ]);

app.directive("oneBlog", function() {
	return {
		restrict : 'E',
		templateUrl : '/9lakha/account/user/login'
	}
});

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider
	// Home
	.when("/", {
		templateUrl : "resources/partials/home.html",
		controller : "PageCtrl"
	})
	// Pages
	.when("/about", {
		templateUrl : "resources/partials/about.html",
		controller : "PageCtrl"
	}).when("/faq", {
		templateUrl : "resources/partials/faq.html",
		controller : "PageCtrl"
	}).when("/pricing", {
		templateUrl : "resources/partials/pricing.html",
		controller : "PageCtrl"
	}).when("/services", {
		templateUrl : "resources/partials/services.html",
		controller : "PageCtrl"
	}).when("/contact", {
		templateUrl : "resources/partials/contact.html",
		controller : "PageCtrl"
	}).when("/registration", {
		templateUrl : "resources/partials/registration.html",
		controller : "PageCtrl"
	}).when("/login", {
		templateUrl : "resources/partials/login.html",
		controller : "PageCtrl"
	}).when("/customdesign", {
		templateUrl : "resources/partials/customdesign.html",
		controller : "PageCtrl"
	}).when("/selljwel", {
		templateUrl : "resources/partials/selljwel.html",
		controller : "PageCtrl"
	})
	
	
	// Blog
	.when("/blog", {
		templateUrl : "resources/partials/blog.html",
		controller : "BlogCtrl"
	}).when("/blog/post", {
		templateUrl : "resources/partials/blog_item.html",
		controller : "BlogCtrl"
	}).when("/jwel/details", {
		templateUrl : "resources/partials/jweldetails.html",
		controller : "BlogCtrl"
	})
	// else 404
	.otherwise("/404", {
		templateUrl : "resources/partials/404.html",
		controller : "PageCtrl"
	});
} ]);

/**
 * Controls the Blog
 */
app.controller('BlogCtrl', function(/* $scope, $location, $http */) {
	console.log("Blog Controller reporting for duty.");
});

/**
 * Controls all other Pages
 */
app.controller('PageCtrl', function(/* $scope, $location, $http */) {
	console.log("Page Controller reporting for duty.");

	// Activates the Carousel
	$('.carousel').carousel({
		interval : 5000
	});

	// Activates Tooltips for Social Links
	$('.tooltip-social').tooltip({
		selector : "a[data-toggle=tooltip]"
	})
});


app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl, data){
        var fd = new FormData();
        fd.append('fileToUpload', file);
        fd.append('productId', data.id);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        	window.location = '#/';
        })
        .error(function(){
        });
    }
}]);

app.controller('myCtrl', ['$http','$scope', 'fileUpload', function($http, $scope, fileUpload){
    
    $scope.uploadFile = function(){
        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
        var uploadUrl = "/9lakha/user/addproductimage";
		var productObj = {
				productName : $scope.productName,
				productDescription : $scope.productDescription
			};
		var res = $http.post('/9lakha/user/createproduct', productObj);
		res.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.productId = data._id;
			$scope.inputEmail = '';
			fileUpload.uploadFileToUrl(file, uploadUrl, data);
		});
		res.error(function(data, status, headers, config) {
			alert("failure message: " + JSON.stringify({
				data : data
			}));
		});		
    };
    
}]);