<jsp:include page="/fragments/header.jsp" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700'
	rel='stylesheet' type='text/css'>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
<link href="${cssPath}css/style.css" rel="stylesheet">


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<title>Admin Page</title>
<c:if test="${!empty msg}"><p style="color:red">${msg}</p></c:if>
<div class="pricing-container">
	<div class="pricing-switcher">
		<p class="fieldset">
			<input type="radio" name="duration-1" value="monthly" id="monthly-1"
				checked> <label for="monthly-1">Users</label> <input
				type="radio" name="duration-1" value="yearly" id="yearly-1">
			<label for="yearly-1">Add User</label> <span class="switch"></span>
		</p>
	</div>
	<ul class="pricing-list bounce-invert">
		<li >
			<ul class="pricing-wrapper">
				<li data-type="monthly" class="is-visible"><header
						class="pricing-header">
						<h2>Users</h2>

					</header>
					<div class="pricing-body">


						<form class="pricing-features" id="delete" action="AdminMang"
							method="post">
							<ul class="pricing-features">
								<c:forEach var="person" items="${allPersons}">
									<li><c:if test="${person.role==0}"><input class="pricing-features" type="checkbox"
										name="personSelected" id="personSelected"
										value="${person.id}"><em>${person.id} :</em>
										 ${person.name }</c:if><br></li>

								</c:forEach>
							</ul>
							<footer class="pricing-footer">
								<button class="select" type="submit" name="action">-
									Delete</button>

							</footer>
						</form>
					</div>
				<li data-type="yearly" class="is-hidden"><header
						class="pricing-header">
						<h2>Add new</h2>

					</header>
					<div class="pricing-body">
						<form class="pricing-features" action="AdminMang" method="get">
							<ul class="pricing-features">

								<li><input type="text" name="email" id="email"
									class="register-input" placeholder="Email address"><br></li>

								<li><input class="register-input" placeholder="First Name"
									type="text" name="firstname" id="firstname"><br></li>

								<li><input class="register-input" placeholder="Last Name"
									type="text" name="name" id="name"><br></li>

								<li><input class="register-input" placeholder="Password"
									type="password" name="pass" id="pass"></li>



							</ul>


							<footer class="pricing-footer">
								<button class="select" type="submit" name="action">Create User</button>
							</footer>
							
						</form>
						
					</div>
			
					</li>
			</ul>
		</li>
	</ul>
</div>

<script
	src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js'></script>

<script src="js/index.js"></script>
<jsp:include page="/fragments/footer.html" />