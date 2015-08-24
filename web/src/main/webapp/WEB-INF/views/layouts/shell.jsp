<!DOCTYPE html>
<html ng-app="bs">
<head>
    <!-- Meta-Information -->
    <title>Online Jewelry MarketPlace</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="description" content="JEWELHAAT.COM">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
		
	<link rel="stylesheet" type="text/css" media="screen" href="/jewelhaat/resources/css/bootstrap-theme.css"/>	
	<link rel="stylesheet" type="text/css" media="screen" href="/jewelhaat/resources/css/bootstrap.css"/>	
	<link rel="stylesheet" type="text/css" media="screen" href="/jewelhaat/resources/css/custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="/jewelhaat/resources/css/font-awesome.css" />
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="/jewelhaat/resources/js/bootstrap.js"></script>
	<script src="/jewelhaat/resources/js/angular.js"></script>
	<script src="/jewelhaat/resources/js/angular-route.js"></script>
	<script src="/jewelhaat/resources/js/bs.js"></script>
	<script src="/jewelhaat/resources/js/ng-infinite-scroll.js"></script>
</head>
<body>
	<tiles:insertAttribute name="masthead" />
	<tiles:insertAttribute name="topnav" />	
	<tiles:insertAttribute name="menu" />		
 	<tiles:insertAttribute name="main" />
	<tiles:insertAttribute name="footer" />
</body>
</html>