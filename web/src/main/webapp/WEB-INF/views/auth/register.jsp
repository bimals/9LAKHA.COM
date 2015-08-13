	<div id="registration-box" class="row-fluid" ng-app="registrationApp" role="main">
	    <form class="form-horizontal" ng-controller="RegistrationController" ng-submit="registerUser()">
	        <div class="form-group">
	            <label for="inputEmail" class="control-label col-xs-2">Email</label>
	            <div class="col-xs-10">
	                <input type="email" class="form-control" id="inputEmail" placeholder="Email" ng-model="inputEmail">
	            </div>
	        </div>
	        <div class="form-group">
	            <label for="inputPassword" class="control-label col-xs-2">Password</label>
	            <div class="col-xs-10">
	                <input type="password" class="form-control" id="inputPassword" placeholder="Password" ng-model="password">
	            </div>
	        </div>
	        <div class="form-group">
	            <label for="confirmPassword" class="control-label col-xs-2">Confirm Password</label>
	            <div class="col-xs-10">
	                <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password" ng-model="confirmPassword">
	            </div>
	        </div>	        
	        <div class="form-group">
	            <label for="firstName" class="control-label col-xs-2">First Name</label>
	            <div class="col-xs-10">
	                <input type="text" class="form-control" id="firstName" placeholder="First Name" ng-model="firstName">
	            </div>
	        </div>	      
	        <div class="form-group">
	            <label for="lastName" class="control-label col-xs-2">Last Name</label>
	            <div class="col-xs-10">
	                <input type="text" class="form-control" id="lastName" placeholder="Last Name" ng-model="lastName">
	            </div>
	        </div>	 	          
	        <div class="form-group">
	            <div class="col-xs-offset-2 col-xs-10">
	                <div class="checkbox">
	                    <label><input type="checkbox"> Terms and Conditions</label>
	                </div>
	            </div>
	        </div>
	        <div class="form-group">
	            <div class="col-xs-offset-2 col-xs-10">
	                <button type="submit" class="btn btn-primary">Register</button>
	            </div>
	        </div>
	    </form>
	</div>