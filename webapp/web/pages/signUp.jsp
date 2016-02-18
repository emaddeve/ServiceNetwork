<jsp:include page="/fragments/header.jsp" />

<title>sign up </title>

<form class="register" action="signUp" method="post">
 <c:if test=${not empty msg}> <p style="color:red">${msg}</p> </c:if>
<input type="email" name="email"  id="email" class="register-input"
		placeholder="email">



<input class="register-input" placeholder="First Name" type="text" name="firstname"  id="firstname">


<input class="register-input" placeholder="Last Name" type="text" name="name" id="name">


<input class="register-input" placeholder="Password" type="password" name="pass" id="pass">


<button type="submit"  class="register-button">Create Account</button>
<p>You have an account <a href="login">Sign in</a></p>
</form>
<br>
<br>
<br>
<br>
<br>


<jsp:include page="/fragments/footer.html" />




