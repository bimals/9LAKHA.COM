<!DOCTYPE html>
<html ng-app="bs">
<head>
    <!-- Meta-Information -->
    <title>Accounting And Taxation</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="description" content="BAHIKHAATA.COM">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
		
	<link rel="stylesheet" type="text/css" media="screen" href="/bahikhaata/resources/css/bootstrap-theme.css"/>	
	<link rel="stylesheet" type="text/css" media="screen" href="/bahikhaata/resources/css/bootstrap.css"/>	
	<link rel="stylesheet" type="text/css" media="screen" href="/bahikhaata/resources/css/custom.css" />
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="/bahikhaata/resources/js/bootstrap.js"></script>
	<script src="/bahikhaata/resources/js/angular.js"></script>
	<script src="/bahikhaata/resources/js/angular-route.js"></script>
	<script src="/bahikhaata/resources/js/bs.js"></script>
	<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
	<tiles:insertAttribute name="masthead" />
	<tiles:insertAttribute name="topnav" />	
	<tiles:insertAttribute name="menu" />		
 	<tiles:insertAttribute name="main" />
	<tiles:insertAttribute name="footer" />
</body>
</html>