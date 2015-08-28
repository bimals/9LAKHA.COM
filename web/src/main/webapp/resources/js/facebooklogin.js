/*  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
      loginRegister();
    } else if (response.status === 'not_authorized') {
      // The person is logged into Facebook, but not your app.
      //document.getElementById('status').innerHTML = 'Please log ' + 'into this app.';
    } else {
      // The person is not logged into Facebook, so we're not sure if
      // they are logged into this app or not.
      // document.getElementById('status').innerHTML = 'Please log ' + 'into Facebook.';
    }
  }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }



  // Now that we've initialized the JavaScript SDK, we call 
  // FB.getLoginStatus().  This function gets the state of the
  // person visiting this page and can return one of three states to
  // the callback you provide.  They can be:
  //
  // 1. Logged into your app ('connected')
  // 2. Logged into Facebook, but not your app ('not_authorized')
  // 3. Not logged into Facebook and can't tell if they are logged into
  //    your app or not.
  //
  // These three cases are handled in the callback function.

  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });
  
  FB.Event.subscribe('auth.login', function () {
	  var isLoggedIn = angular.element(document.getElementById('headerContainer')).scope().isLoggedIn();
	  var $rootScope = angular.element(document.getElementById('headerContainer')).scope().$root;
	  $rootScope.loggedIn = true;
      window.location = "/bimalsahay";
  });

  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function loginRegister() {
    console.log('Welcome!  Fetching your information.... ');
	  var isLoggedIn = angular.element(document.getElementById('headerContainer')).scope().isLoggedIn();
	  var $rootScope = angular.element(document.getElementById('headerContainer')).scope().$root;
	  $rootScope.loggedIn = true;
    
    FB.api('/me?fields=id,first_name,last_name,friends,email,public_key', function(response) {
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
    	    url: '/bimalsahay/account/user/create',
    	    data: JSON.stringify (userObj),
    	    success: function(data) {
    	    	alert('data: ' + data); 
    	    },
    	    contentType: "application/json",
    	    dataType: 'json'
    	});
      
    });
  }
  
  
function fbLogout() {
	FB.logout(function(response) {
		  // user is now logged out
		});
	Window.location = "/bimalsahay/j_spring_security_check";
}*/