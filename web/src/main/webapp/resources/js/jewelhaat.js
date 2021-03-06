var app = angular.module("jewelhaat", ['angularFileUpload','ngRoute', 'infinite-scroll', 'facebook', 'ngTagsInput']).config(function(FacebookProvider) {
    FacebookProvider.init('481717542008286');
});

app.filter('nospace', function () {
    return function (value) {
        return (!value) ? '' : value.replace(/ /g, '');
    };
});

app.directive('formatPhone', [
                              function() {
                                  return {
                                      require: 'ngModel',
                                      restrict: 'A',
                                      link: function(scope, elem, attrs, ctrl, ngModel) {
                                          elem.add(phonenumber).on('keyup', function() {
                                             var origVal = elem.val().replace(/[^\w\s]/gi, '');
                                             if(origVal.length === 10) {
                                               var str = origVal.replace(/(.{3})/g,"$1-");
                                               var phone = str.slice(0, -2) + str.slice(-1);
                                               jQuery("#phonenumber").val(phone);
                                             }

                                          });
                                      }
                                  };
                              }
                          ]);

app.directive('ensureUnique', ['$http', '$timeout', function($http, $timeout) {
	  var checking = null;
	  return {
	    require: 'ngModel',
	    link: function(scope, ele, attrs, c) {
	      scope.$watch(attrs.ngModel, function(newVal) {
	        if (!checking) {
	          checking = $timeout(function() {
	            $http({
	              method: 'POST',
	              url: '/jewelhaat/check/' + attrs.ensureUnique,
	              data: {'field': attrs.ensureUnique}
	            }).success(function(data, status, headers, cfg) {
	              c.$setValidity('unique', data.isUnique);
	              checking = null;
	            }).error(function(data, status, headers, cfg) {
	              checking = null;
	            });
	          }, 500);
	        }
	      });
	    }
	  }
	}]);


app.service('sellerService', ['$http', function($http){
	this.createSeller = function() {
		var res = $http.post('/jewelhaat/blog');
		res.success(function(data, status, headers, config) {
				
		});
		res.error(function(data, status, headers, config) {
			console.log("blog service 1");
			window.location = '/jewelhaat/login/#/login';
		});
		
		return res;
	};
	
}]);

app.service('companyService', function() {
	  var company = null;

	  var setCompany = function(newObj) {
	      company = newObj;
	  };

	  var getCompany = function(){
	      return company;
	  };

	  return {
		  setCompany: setCompany,
		  getCompany: getCompany
	  };

	});


app.service('companyServiceImpl', ['$http', 'companyService', function($http, companyService) {
	  
    this.registerCompany = function(companyObj) {
				var res = $http.post(
						'/jewelhaat/seller/create',
						companyObj);
				res.success(function(data, status,
						headers, config) {
					companyService.setCompany(data);
				});
				res.error(function(data, status,
						headers, config) {
					alert("failure message: "
							+ JSON.stringify({
								data : data
							}));
				});

				window.location = "#/address";
				return res;
    };	  
    
    

	}]);


app.controller("RegistrationController", [ '$scope', '$http', '$routeParams','companyService', 'companyServiceImpl', function($scope, $http, $routeParams,companyService, companyServiceImpl) {
	
	  $scope.tags = [
	                 { id: 1, name: 'Tag1' },
	                 { id: 2, name: 'Tag2' },
	                 { id: 3, name: 'Tag3' }
	               ];
	
      $scope.loadTags = function(query) {
          return $http.get('tags.json');
        };
        
       
	$scope.plan = $routeParams.plan;
	$scope.submitted = false;

	$scope.registerCompany = function() {
		if ($scope.sellerSignUp.$valid) {
			if ($scope.terms) {
				var companyObj = {
						companyName : $scope.company.companyName,
						companyURL : $scope.company.url,
						businessLegalName : $scope.company.businessLegalName,
						phoneNumber : $scope.company.phoneNumber,
						companyEmail : $scope.company.companyEmail,
						contactPerson : $scope.company.contactPerson,
						planType : $scope.plan
					};

				companyServiceImpl.registerCompany(companyObj).then(function(response) {
					$scope.company = response.data;
					companyService.setCompany(response.data);
				});

			} else {
				$scope.sellerSignUp.submitted = true;
			}
		}

	};
	
	$scope.getCompany = function() {
		$scope.company = companyService.getCompany();
	}

	$scope.backToStart = function() {
		$scope.company = companyService.getCompany();
		window.location = '#/setup/' + $scope.company.planType;
	}
			
	$scope.captureCreditCard = function() {
		
		var company = companyService.getCompany();
		
		var companyObj = {
				id : company.id,
				companyName : company.companyName,
				companyURL : company.url,
				businessLegalName : company.legalName,
				phoneNumber : company.phoneNumber,
				companyEmail : company.companyEmail,
				addressFullName : $scope.company.addressFullName,
				addressLine1 : $scope.company.addressLine1,
				city : $scope.company.city,
				state : $scope.company.state,
				zipCode : $scope.company.zipCode,
				country : $scope.company.country
				
			};
		companyServiceImpl.registerCompany(companyObj).then(function(response) {
			$scope.company = response.data;
			companyService.setCompany(response.data);
		});
		
		window.location = "#/cc";
	};
			
	$scope.review = function() {
		
		var company = companyService.getCompany();
		
		var companyObj = {
				id : company.id,
				companyName : company.companyName,
				companyURL : company.url,
				businessLegalName : company.legalName,
				phoneNumber : company.phoneNumber,
				companyEmail : company.companyEmail,
				addressFullName : company.addressFullName,
				addressLine1 : company.addressLine1,
				city : company.city,
				state : company.state,
				zipCode : company.zipCode,
				country : company.country,
				cardHolderName : $scope.company.cardHolderName,
				cardNumber : $scope.company.cardNumber,
				expireMonth : $scope.company.expireMonth,
				expireYear : $scope.company.expireYear,
				cvv : $scope.company.cvv
			};
		companyServiceImpl.registerCompany(companyObj).then(function(response) {
			$scope.company = response.data;
			companyService.setCompany(response.data);
		});
										
		window.location = "#/review";
	};
			
			$scope.invoice = function() {
				window.location = "#/invoice";
			};
			
			$scope.startSelling = function() {
				window.location = "/jewelhaat/";
			};
			
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
	
	$scope.hasDraftCompany = function() {
		
	};
	
	
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
} ]);


app.controller('searchController', ['$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {
	
	$scope.products = [];
	$scope.after;
	$scope.busy = true;
	
	$scope.startSelling = function(){
		window.location = "/jewelhaat/pricing/#pricing";
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
			
			window.location = "/jewelhaat/s/#/list";
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
}]);

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
	$('.interval').carousel({
		interval : 10000
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

/*
$('.interval').carousel({
    interval: 10000 //changes the speed
})*/

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



function checkLoginState() {
	window.location = "/jewelhaat";
}

app.service('userService', ['$http', function($http){
	this.getAllProducts = function() {
		var res = $http.post('/jewelhaat/myproduct');
		res.success(function(data, status, headers, config) {
				
		});
		res.error(function(data, status, headers, config) {
			console.log("blog service 1");
			window.location = '/jewelhaat/login/#/login';
		});
		
		return res;
	};
}]);

app.controller('userController', ['$scope', 'userService', function($scope, userService) {
	$scope.myProducts = function() {
		userService.getAllProducts().then(function(response) {
			$scope.products = response.data;
		});
	}
}]);
