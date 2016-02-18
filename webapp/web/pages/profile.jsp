<jsp:include page="/fragments/header.jsp" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700'
	rel='stylesheet' type='text/css'>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
<link href="${cssPath}css/style.css" rel="stylesheet">


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<title>Profile</title>


<p class="exit-btn exit-btn-3">here you can find and manage all your services </p>
<div class="pricing-container">
	<div class="pricing-switcher">
		<p class="fieldset">
			<input type="radio" name="duration-1" value="monthly" id="monthly-1"
				checked> <label for="monthly-1">Demands</label> <input
				type="radio" name="duration-1" value="yearly" id="yearly-1">
			<label for="yearly-1"> Offers</label> <span class="switch"></span>
		</p>
	</div>
	<ul class="pricing-list bounce-invert">
		<li>
		
		<li class="exclusive">

			<ul class="pricing-wrapper">
				<li data-type="monthly" class="is-visible"><header
						class="pricing-header">
						<h2>Services you Demand</h2>

					</header>
					<div class="pricing-body">


							<form class="pricing-features" id="profileform" action="example"
								method="post">
								<ul class="pricing-features">
									<c:forEach var="service" items="${listServicesDemandes}">
										<li><input class="pricing-features" type="checkbox"
											name="serviceSelected" id="serviceSelected"
											value="${service.id}">
											<em style="font-weight: bold">${service.name}</em><br>
											${service.des}<br>${service.endDate}<br>
											</li>

									</c:forEach>
								</ul>
								<footer class="pricing-footer">
									<button class="select" type="submit" name="action" id="Delete" value="Delete">
										Delete</button><br>
										<button class="select" type="submit" id="Cycl" name="action" value="C">
										Cyclic</button>

								</footer>
							</form>
					</div>
				<li data-type="yearly" class="is-hidden"><header
						class="pricing-header">
						<h2>Services you Offer</h2>

					</header>
					<div class="pricing-body">
						<form class="pricing-features" id="profileform2" action="example"
							method="post">
							<ul class="pricing-features">
								<c:forEach var="service" items="${listServicesOffers}">
									<li><input class="pricing-features" type="checkbox"
											name="serviceSelected" id="serviceSelected"
											value="${service.id}">
											<em style="font-weight: bold">${service.name}</em><br>
											${service.des}<br>${service.endDate}<br>
											</li>

								</c:forEach>

							</ul>



							<footer class="pricing-footer">
								<button class="select" type="submit" name="action" id="Delete2" value="Delete">
										Delete</button><br>
									
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
<script src="js/cjquery.js"></script>
<jsp:include page="/fragments/footer.html" />