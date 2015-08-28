var app = angular.module("jewelhaat", ['angularFileUpload','ngRoute', 'infinite-scroll', 'facebook']).config(function(FacebookProvider) {
    FacebookProvider.init('481717542008286');
});

app.controller("RegistrationController", [ '$scope', '$http',
		function($scope, $http) {

			$scope.registerUser = function() {

				var userObj = {
					userName : $scope.inputEmail,
					password : $scope.password,
					firstName : $scope.firstName,
					lastName : $scope.lastName
				};
				var res = $http.post('/jewelhaat/account/user/create', userObj);
				res.success(function(data, status, headers, config) {
					$scope.message = data;
					$scope.inputEmail = '';
					$scope.password = '';
					$scope.firstName = '';
					$scope.lastName = '';
					window.location = '/jewelhaat/account/user/loginSuccess';
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
		var res = $http.post('/jewelhaat/account/user/login', userObj);
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

app.controller("HomeController", [ '$scope', '$http', '$rootScope', 'Facebook', function($scope, $http, $rootScope, Facebook) {
	
	$scope.products = [];
	$scope.after;
	$scope.busy = true;
	
	$scope.fbLogout = function() {
		Facebook.logout(function(response) {
			  // user is now logged out
			});
		Window.location = "/jewelhaat/j_spring_security_check";
	}

    $scope.login = function() {
        // From now on you can use the Facebook service just as Facebook api says
        Facebook.login(function(response) {
          // Do something with response.
        });
      };

      $scope.checkLoginState = function() {
        Facebook.getLoginStatus(function(response) {
          if(response.status === 'connected') {
            $scope.loggedIn = true;
          } else {
            $scope.loggedIn = false;
          }
        });
      };

      $scope.me = function() {
        Facebook.api('/me', function(response) {
          $scope.user = response;
        });
      };
      
   $scope.isLoggedIn = function() {

        Facebook.getLoginStatus(function(response) {
	          if(response.status === 'connected') {
	        	  $rootScope.loggedIn = true;
	        	  Facebook.api('/me?fields=id,first_name,last_name,friends,email,public_key', function(response) {
	        		      console.log('Successful login for: ' + response);
	        		      
	        				var userObj = {
	        						userName : response.email,
	        						password : "",
	        						firstName : response.first_name,
	        						lastName : response.last_name,
	        						userType : "facebook",
	        						userTypeId : response.id
	        					};
	        		      
	        		      $.ajax({
	        		    	    type: 'POST',
	        		    	    url: '/jewelhaat/account/user/create',
	        		    	    data: JSON.stringify (userObj),
	        		    	    success: function(data) {
	        		    	    	alert('data: ' + data); 
	        		    	    },
	        		    	    contentType: "application/json",
	        		    	    dataType: 'json'
	        		    	});
	        		      
	        		    });
	          } else {
	        	  $rootScope.loggedIn = false;
			      $http.get('/jewelhaat/account/user/checklogin')
			        .success(function(data) {
			          console.log(data);
			          $rootScope.loggedIn = data;
			        })
			        .error(function(data) {
			          console.log('error: ' + data);
			        });		        	  
	          }
	        });
	    };
	   
	$scope.search = function(skip) {

		var searchObj = {
			searchText : $scope.searchText,
			skip : skip,
			
		};
		var res = $http.post('/jewelhaat/product/search', searchObj);
		res.success(function(data, status, headers, config) {
			if(data != null && data.length > 0) {
			for (var int = 0; int < data.length; int++) {
				$scope.products.push(data[int]);
			}
			$scope.after = $scope.products[$scope.products.length - 1].id;
			}
			$scope.busy = false;
		});
		res.error(function(data, status, headers, config) {
			alert("failure message: " + JSON.stringify({
				data : data
			}));
		});
	};
	
	$scope.searchMore = function(skip) {
		if (this.busy)  {
			$scope.busy = true;
			return;
		}
			
		var searchObj = {
			searchText : $scope.searchText,
			skip : skip,
			after : $scope.after
		};
		var res = $http.post('/jewelhaat/product/search', searchObj);
		res.success(function(data, status, headers, config) {
			for (var int = 0; int < data.length; int++) {
				$scope.products.push(data[int]);
			}
			
			if(data.length == 0) {
				$scope.busy = true;
			}
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
		templateUrl : '/jewelhaat/account/user/login'
	}
});

app.config([ '$routeProvider', function($routeProvider) {
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
    
    this.uploadDesignFileToUrl = function(file, uploadUrl, data){
        var fd = new FormData();
        fd.append('fileToUpload', file);
        fd.append('designId', data.id);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        	window.location = '#/customdesign';
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
        var uploadUrl = "/jewelhaat/user/addproductimage";
		var productObj = {
				productName : $scope.productName,
				productDescription : $scope.productDescription
			};
		var res = $http.post('/jewelhaat/user/createproduct', productObj);
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

app.controller('CustomDesignController', ['$http','$scope', 'fileUpload', function($http, $scope, fileUpload){
	
	$scope.designs = [];
	$scope.after;
	$scope.busy = false;
    
    $scope.uploadFile = function(){
        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
        var uploadUrl = "/jewelhaat/user/addcustomdesignimage";
		var customDesignObj = {
				designName : $scope.designName,
				designDescription : $scope.designDescription
			};
		var res = $http.post('/jewelhaat/user/addcustomdesign', customDesignObj);
		res.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.productId = data._id;
			$scope.inputEmail = '';
			fileUpload.uploadDesignFileToUrl(file, uploadUrl, data);
		});
		res.error(function(data, status, headers, config) {
			alert("failure message: " + JSON.stringify({
				data : data
			}));
		});		
    };
    
	$scope.searchDesign = function(skip) {

		var searchObj = {
			searchText : $scope.searchText,
			skip : skip,
			
		};
		var res = $http.post('/jewelhaat/design/search', searchObj);
		res.success(function(data, status, headers, config) {
			for (var int = 0; int < data.length; int++) {
				$scope.designs.push(data[int]);
			}
			$scope.after = $scope.designs[$scope.designs.length - 1].id;
		    $scope.busy = false;
		});
		res.error(function(data, status, headers, config) {
			alert("failure message: " + JSON.stringify({
				data : data
			}));
		});
	};
	
	$scope.searchMoreDesigns = function(skip) {
		if (this.busy)  {
			$scope.busy = true;
			return;
		}
			
		var searchObj = {
			searchText : $scope.searchText,
			skip : skip,
			after : $scope.after
		};
		var res = $http.post('/jewelhaat/design/search', searchObj);
		res.success(function(data, status, headers, config) {
			for (var int = 0; int < data.length; int++) {
				$scope.designs.push(data[int]);
			}
			
			if(data.length == 0) {
				$scope.busy = true;
			}
		});
		res.error(function(data, status, headers, config) {
			alert("failure message: " + JSON.stringify({
				data : data
			}));
		});
	};	    
}]);


$('.carousel').carousel({
    interval: 5000 //changes the speed
})

function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail());
	}

app.service('blogService', ['$http', function($http){
	this.getAllPublishedBlogs = function() {
		var res = $http.post('/jewelhaat/blog');
		res.success(function(data, status, headers, config) {
				
		});
		res.error(function(data, status, headers, config) {
			console.log("blog service 1");
			window.location = '/jewelhaat/login/#/login';
		});
		
		return res;
	};
	
	this.getFullBlog = function(id) {
		var res = $http.get('/jewelhaat/blog/' + id);
		
		res.success(function(data, status, headers, config) {
			
		});
		res.error(function(data, status, headers, config) {
			console.log("blog service 2");
			window.location = '/jewelhaat/login/#/login';
		});
		
		
		return res;
	};
}]);


app.directive('ngThumb', ['$window', function($window) {
        var helper = {
            support: !!($window.FileReader && $window.CanvasRenderingContext2D),
            isFile: function(item) {
                return angular.isObject(item) && item instanceof $window.File;
            },
            isImage: function(file) {
                var type =  '|' + file.type.slice(file.type.lastIndexOf('/') + 1) + '|';
                return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
            }
        };

        return {
            restrict: 'A',
            template: '<canvas/>',
            link: function(scope, element, attributes) {
                if (!helper.support) return;

                var params = scope.$eval(attributes.ngThumb);

                if (!helper.isFile(params.file)) return;
                if (!helper.isImage(params.file)) return;

                var canvas = element.find('canvas');
                var reader = new FileReader();

                reader.onload = onLoadFile;
                reader.readAsDataURL(params.file);

                function onLoadFile(event) {
                    var img = new Image();
                    img.onload = onLoadImage;
                    img.src = event.target.result;
                }

                function onLoadImage() {
                    var width = params.width || this.width / this.height * params.height;
                    var height = params.height || this.height / this.width * params.width;
                    canvas.attr({ width: width, height: height });
                    canvas[0].getContext('2d').drawImage(this, 0, 0, width, height);
                }
            }
        };
    }]);


app.service('productService', ['$http', function($http){
	this.getAllPublishedProducts = function() {
		var res = $http.post('/jewelhaat/product');
		res.success(function(data, status, headers, config) {
				
		});
		res.error(function(data, status, headers, config) {
			console.log("product service 1");
			window.location = '/jewelhaat/login/#/login';
		});
		
		return res;
	};
	
	this.getFullProduct = function(id) {
		var res = $http.get('/jewelhaat/product/' + id);
		
		res.success(function(data, status, headers, config) {
			
		});
		res.error(function(data, status, headers, config) {
			console.log("product service 2");
			window.location = '/jewelhaat/login/#/login';
		});
		
		
		return res;
	};
}]);

app.controller('productController', ['$http', '$scope', 'FileUploader', '$rootScope', '$routeParams', 'productService', function($http, $scope, FileUploader, $rootScope, $routeParams, productService) {
	
	$rootScope.useNew = false;
	
	$scope.homePageProducts = function() {
		productService.getAllPublishedProducts().then(function(response) {
			$scope.products = response.data;
		});
		window.location = '#/all';
	};
	
	$scope.readProduct = function(data) {
		window.location = '#/read';
		$rootScope.id = data.id;
		
	};
	
	$scope.fullProduct = function() {
		productService.getFullProduct($rootScope.id).then(function(response) {
			$scope.product = response.data;
		});
	};
	
	$scope.addComment = function(id) {
		var comment = $scope.comment;
		var res = $http.post('/jewelhaat/product/' + id + "/comment", comment);
		res.success(function(data, status, headers, config) {
			$scope.product.comments = data.comment;
		});
		res.error(function(data, status, headers, config) {
			alert("failure message: " + JSON.stringify({
				data : data
			}));
		});
	}
 	
	$scope.editDraft = function() {
		productService.getFullProduct($rootScope.currentDraft).then(function(response) {
			$scope.id = response.data.id;
			$scope.productTitle = response.data.productTitle;
			$scope.productWebsite = response.data.productWebsite;
			$scope.productYouTube = response.data.productYouTube;
			$scope.productText = response.data.productText;
			$scope.productMoreText = response.data.productMoreText;
			$scope.creationTimeStamp = response.data.creationTimeStamp;
			$scope.userFullName = response.data.userFullName;
		});
	};
		
	$scope.useThisTemplate = function(draft) {
		$rootScope.currentDraft = draft.id;
		window.location = "#/edit";
	};
	
	$scope.addNew = function(draft) {
		$rootScope.useNew = true;
		window.location = "#/new";
	};
	
	$scope.checkForDraftProducts = function() {
			var res = $http.post('/jewelhaat/product/draft');
			res.success(function(data, status, headers, config) {
				if(data != null && data.length != 0) {
					$scope.drafts = data;
					window.location = '#/drafts';
				}
				else {
					$scope.addDraftProduct();
				}
			});
			res.error(function(data, status, headers, config) {
				console.log("Product Controller 1");
				window.location = '/jewelhaat/login/#/login';
			});
	};
	
	$scope.addDraftProduct = function() {
		var productObj = {
				id : $scope.id,
				productTitle : $scope.productTitle,
				productWebsite : $scope.productWebsite,
				productYouTube : $scope.productYouTube,
				productText : $scope.productText,
				productMoreText : $scope.productMoreText
			};
			var res = $http.post('/jewelhaat/user/draft', productObj);
			res.success(function(data, status, headers, config) {
				$scope.id = data.id;
				$scope.productTitle = data.productTitle;
				$scope.productWebsite = data.productWebsite;
				$scope.productYouTube = data.productYouTube;
				$scope.productText = data.productText;
				$scope.productMoreText = data.productMoreText;
				window.location = '#/new';
			});
			res.error(function(data, status, headers, config) {
				alert("failure message: " + JSON.stringify({
					data : data
				}));
			});
	};
	
	
	$scope.publishProduct = function() {
		var productObj = {
				id : $scope.id,
				productTitle : $scope.productTitle,
				productWebsite : $scope.productWebsite,
				productYouTube : $scope.productYouTube,
				productText : $scope.productText,
				productMoreText : $scope.productMoreText
			};
			var res = $http.post('/jewelhaat/user/createproduct', productObj);
			res.success(function(data, status, headers, config) {
				$rootScope.useNew = false;
				$rootScope.currentDraft = null;
				window.location = '#/jewelhaat/post';
			});
			res.error(function(data, status, headers, config) {
				alert("failure message: " + JSON.stringify({
					data : data
				}));
			});
	};
	
	
	
    var uploader = $scope.uploader = new FileUploader({
        url: '/jewelhaat/user/'+$rootScope.currentDraft+'/image'
    });

    // FILTERS

    uploader.filters.push({
        name: 'imageFilter',
        fn: function(item /*{File|FileLikeObject}*/, options) {
            var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
            return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
        }
    });

    // CALLBACKS

    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
        console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function(progress) {
        console.info('onProgressAll', progress);
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {
        console.info('onCompleteAll');
    };

    console.info('uploader', uploader);
}]);

function checkLoginState() {
	window.location = "/jewelhaat";
}