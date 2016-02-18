<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">

<head>
<link async href="http://fonts.googleapis.com/css?family=Coda"
	data-generated="http://enjoycss.com" rel="stylesheet" type="text/css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${cssPath}css/main.css" />
<link rel="stylesheet" type="text/css" href="${cssPath}css/signin.css" />
<link href='http://fonts.googleapis.com/css?family=Just+Another+Hand' rel='stylesheet' type='text/css'>
<script src="js/cjquery.js"></script>
<!--  jquery Core -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<!-- Bootstrap Core CSS -->
	<link href="${cssPath}css/bootstrap.min.css" rel="stylesheet">

		<!-- Custom CSS -->
		<link href="${cssPath}css/business-casual.css" rel="stylesheet">
			<link href="${cssPath}css/style.css" rel="stylesheet">
				<!-- Fonts -->
			<link
				href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
				rel="stylesheet" type="text/css">
				<link
					href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
					rel="stylesheet" type="text/css">

					<title>Services Exchange</title>
</head>

<body background="../img/2.jpeg">
	<a href="LogOutServlet"><button class="exit-btn exit-btn-3">logout</button></a>
	
	
	<c:choose>
	
    <c:when test="${not empty person}">
    	<p class="exit-btn exit-btn-3">Welcom back ${person.firstName}</p>
    	
 
      
        <br />
    </c:when>    
    <c:otherwise>
        <p class="exit-btn exit-btn-3"> Welcome to our Service Exchange Network</p>
        <br />
    </c:otherwise>

</c:choose>


	<c:if test="${!empty param['message']}">
		<p class="message" style="color: red">${param["message"]}</p>
	</c:if>
	<div class="brand">DNR-2I</div>
	<div class="address-bar">University of Caen Normandy</div>

	<!-- Navigation -->
	<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
			<a class="navbar-brand" href="index.html">Business Casual</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="home.jsp">Home</a></li>
				<li><a href="profile">profile</a></li>
				<li><a href="addService">Add Service</a></li>
				<li><a href="signUp">Sing up</a></li>

				<li><a href="About">About</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>