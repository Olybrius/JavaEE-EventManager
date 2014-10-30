<%@ include file="/WEB-INF/jspf/Prolog.jspf" %>
<c:set var="title" value="Evènements disponibles" scope="page"/>
<c:choose>
	<c:when test="${user eq null}">
		<c:set var="content" value="false" scope="page"/>
		<c:set var="loginPage" value="" scope="page"/>
		<c:set var="registerPage" value="" scope="page"/>
	</c:when> 
	<c:otherwise>
		<c:set var="content" value="true" scope="page"/>
		<c:set var="userEventsPage" value="" scope="page"/>
		<c:set var="createEventPage" value="" scope="page"/>
	</c:otherwise>
</c:choose>
<c:set var="eventsPage" value="active" scope="page"/>
<%@ include file="/WEB-INF/jspf/Header.jspf" %>

<c:choose>

	<c:when test="${fn:length(events) gt 0}">

		<!-- 
		LIST OF EVENTS
		-->

		<div class="col-sm-offset-1 col-sm-3">
			<ul class="nav nav-pills nav-stacked" role="tablist">
				<c:forEach begin="0" end="${fn:length(events)}" step="1" varStatus="eventCounter" items="${events}" var="event">
					<c:choose>
						<c:when test="${event.id eq eventDisplayed.id}">
							<c:set var="class" value="active" scope="page"/>
						</c:when>
						<c:otherwise>
							<c:set var="class" value="" scope="page"/>
						</c:otherwise>
					</c:choose>
					<li role="presentation" class="${class}"><a href="Events?id=${event.id}">${event.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<!-- 
		INFORMATIONS OF THE EVENT SELECTED + SUBSCRIBE FORM
		-->
		
		<div class="row">
			<div class="col-sm-7">
				<div class="panel panel-default">
					<div class="panel-heading"><strong>eventDisplayed.name</strong></div>
					<div class="panel-body">
						<strong>Créateur :</strong> eventDisplayed.users.name <br/>
						<strong>Adresse :</strong> eventDisplayed.address <br/>
						<strong>Début :</strong> <fmt:formatDate value="${eventDisplayed.startdate}" pattern="dd/MM/yyyy HH:mm"/> <br/>
						<strong>Fin :</strong> <fmt:formatDate value="${eventDisplayed.enddate}" pattern="dd/MM/yyyy HH:mm"/> <br/>
					</div>
					<ul class="list-group">
						<li class="list-group-item">
							<strong>Inscription</strong>
						</li>
						<li class="list-group-item">
							<form class="form-horizontal" role="form" method="post" action="Subscribe">
								<div class="form-group">
									<label for="mail" class="col-sm-3 control-label">Prénom</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" id="firstName" name="firstName" placeholder="Prénom" required>
									</div>
								</div>
								<div class="form-group">
									<label for="mail" class="col-sm-3 control-label">Nom</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" id="name" name="name" placeholder="Nom" required>
									</div>
								</div>
								<div class="form-group">
									<label for="mail" class="col-sm-3 control-label">Entreprise</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" id="company" name="company" placeholder="Entreprise" required>
									</div>
								</div>
								<div class="form-group">
									<label for="mail" class="col-sm-3 control-label">Adresse mail</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" id="mail" name="mail" placeholder="Adresse mail" required>
									</div>
								</div>
								<input type="hidden" name="eventId" id="eventId" value="${eventDisplayed.id}">
								
							</form>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
	</c:when>
	
	<c:otherwise>

		<!-- 
		NO EVENT TO SHOW
		-->

		<div class="row">
			<div class="col-sm-offset-1 col-sm-10">
				<div class="alert alert-info" role="alert">Aucun évènement n'est disponible pour le moment. Revenez un peu plus tard !</div>
			</div>
		</div>

	</c:otherwise>
	
</c:choose>

<%@ include file="/WEB-INF/jspf/Footer.jspf" %>