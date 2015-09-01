angular.module('jewelhaat').service('productService', ['$http', function($http){
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

angular.module('jewelhaat').controller('productController', ['$http', '$scope', 'FileUploader', '$rootScope', '$routeParams', 'productService', function($http, $scope, FileUploader, $rootScope, $routeParams, productService) {
	
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
					window.location = '#/add';
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
				window.location = '#/post';
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
