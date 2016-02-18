<jsp:include page="/fragments/header.jsp" />

<title>Login</title>


<form class="register" action="login" method="post">
<c:if test=${not empty msg}> <p style="color:red">${msg}</p> </c:if>

	<input type="email" name="email" class="register-input"
		placeholder="Email address"> <input type="password"
		class="register-input" placeholder="Password" name="password">

	<button type="submit"  class="register-button">Login</button>
	<p>You don't have an account <a href="signUp">Sign up</a></p>
</form>

<br>
<br>
<br>
<br>
<br>



<jsp:include page="/fragments/footer.html" />
