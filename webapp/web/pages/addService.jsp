<jsp:include page="/fragments/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<script>
	$(function() {
		$("#datepicker,#datepicker2").datepicker();
	});
	$(function() {
		  
		  
	    $( "#name" ).autocomplete({
	        source: ${json}
	      });
	    });
</script>
 
<title>Add a service</title>
<c:if test="${!empty param['message']}">
	<p class="message" style="color: red">${param["message"]}</p>
</c:if>
<div style="float:center">
<center style="color:red">${msg}</center>
</div>
<form class="register" action="addService" method="post">
	<input class="register-input" placeholder="Name of your service"
		type="text" name="name" id="name">

	<textarea class="register-input" placeholder="Description"
		name="description" id="description"></textarea>
	<div class="select-style">
		<select name="type" id="type">
			<option value="1">Demand</option>
			<option value="0">Offer</option>
		</select>
	</div>
	<input class="register-input" placeholder=from name="datepicker"
		type="text" id="datepicker"> <input class="register-input"
		placeholder=To name="datepicker2" type="text" id="datepicker2">

	<button class="register-input" type="submit" name="action">Add
		Service</button>
</form>
<br>
<br>
<br>
<br>
<br>

<jsp:include page="/fragments/footer.html" />
