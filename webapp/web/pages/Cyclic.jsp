<jsp:include page="/fragments/header.jsp" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700'
	rel='stylesheet' type='text/css'>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
<link href="${cssPath}css/style.css" rel="stylesheet">
<style>
h1,p{color :white;}
</style>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<title>Cyclic</title>
<div class="page">
	<div class="container">
		<div class="row">
			<h1>Cycle</h1>
			<p>${cycle}</p>
		</div>
	</div>
</div>

<jsp:include page="/fragments/footer.html" />