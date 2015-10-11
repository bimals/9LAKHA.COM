<!DOCTYPE html>
<html ng-app="jewelhaat">
<head>
    <!-- Meta-Information -->
    <title>Online Jewelry MarketPlace</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="description" content="JEWELHAAT.COM">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
	
	<meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="941743424975-q33rhmhj20920ojj1pt48ocqpkep02lb.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
		
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
	
<link rel="stylesheet" type="text/css" media="screen" href="/jewelhaat/resources/css/bootstrap-theme.css"/>	
<link rel="stylesheet" type="text/css" media="screen" href="/jewelhaat/resources/css/bootstrap.css"/>	
<link rel="stylesheet" type="text/css" media="screen" href="/jewelhaat/resources/css/jewelhaat-custom.css" />
      <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
	  <link rel="stylesheet" href="/jewelhaat/resources/css/form-elements.css">
      <link rel="stylesheet" href="/jewelhaat/resources/css/style.css">
      <link rel="stylesheet" href="/jewelhaat/resources/css/ng-tags-input.css">

<script src="/jewelhaat/resources/js/jquery-2.1.4.js"></script>
<!-- 
<script src="/jewelhaat/resources/js/cors/jquery.postmessage-transport.js"></script>
<script src="/jewelhaat/resources/js/cors/jquery.xdr-transport.js"></script>
<script src="/jewelhaat/resources/js/vendor/jquery.ui.widget.js"></script> -->

<script src="/jewelhaat/resources/js/bootstrap.js"></script>
<script src="/jewelhaat/resources/js/angular.js"></script>
<script src="/jewelhaat/resources/js/fileupload/angular-file-upload.min.js"></script>

<script src="/jewelhaat/resources/js/angular-route.js"></script>
<script src="/jewelhaat/resources/js/angular-facebook.js"></script>
<script src="/jewelhaat/resources/js/ng-infinite-scroll.js"></script>
<script src="/jewelhaat/resources/js/ng-tags-input.js"></script>
<!-- <script type="text/javascript" src="//platform.linkedin.com/in.js">
    api_key:75tvyddibk47hf
    onLoad:checkLoginState
    authorize:true
    lang:en_US
</script> -->
<!-- <script src="/jewelhaat/resources/js/main.js"></script>
<script src="/jewelhaat/resources/js/jquery.iframe-transport.js"></script>
<script src="/jewelhaat/resources/js/jquery.fileupload.js"></script>
<script src="/jewelhaat/resources/js/jquery.fileupload-process.js"></script>
<script src="/jewelhaat/resources/js/jquery.fileupload-image.js"></script>
<script src="/jewelhaat/resources/js/jquery.fileupload-audio.js"></script>
<script src="/jewelhaat/resources/js/jquery.fileupload-video.js"></script>
<script src="/jewelhaat/resources/js/jquery.fileupload-validate.js"></script>
<script src="/jewelhaat/resources/js/jquery.fileupload-angular.js"></script>
<script src="/jewelhaat/resources/js/app.js"></script> -->
<script src="/jewelhaat/resources/js/jewelhaat.js"></script>
<script src="/jewelhaat/resources/js/jewelhaat-route.js"></script>
<script src="/jewelhaat/resources/js/jewelhaat-product.js"></script>
<script src="/jewelhaat/resources/js/jewelhaat-directive.js"></script>
<script src="/jewelhaat/resources/js/jquery.backstretch.min.js"></script>
<script src="/jewelhaat/resources/js/scripts.js"></script>
<script src="/jewelhaat/resources/js/retina-1.1.0.min.js"></script>

</head>
<body>
	<tiles:insertAttribute name="masthead" />
	<tiles:insertAttribute name="topnav" />	
	<tiles:insertAttribute name="menu" />		
 	<tiles:insertAttribute name="main" />
 	<tiles:insertAttribute name="bottomnav" />	
	<tiles:insertAttribute name="footer" />
</body>
</html>