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

<!-- SHOW OR HIDE ERROR -->
<c:choose>
	<c:when test="${not empty eventsError}">
		<c:set var="hideError" value="" scope="page"/>
	</c:when>
	<c:otherwise>
		<c:set var="hideError" value="hidden=\"true\"" scope="page"/>
	</c:otherwise>
</c:choose>
<div class="col-sm-offset-1 col-sm-10" ${hideError}>
	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		${eventsError}
	</div>
</div>

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
							<c:set var="eventClass" value="class=\"active\"" scope="page"/>
						</c:when>
						<c:otherwise>
							<c:set var="eventClass" value="" scope="page"/>
						</c:otherwise>
					</c:choose>
					<li role="presentation" ${eventClass}><a href="Events?id=${event.id}">${event.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<c:choose>
		
			<c:when test="${eventDisplayed eq null}">
			
				<!-- 
				NO EVENT SELECTED
				-->
				
				<div class="row">
					<div class="col-sm-7">
						<div class="alert alert-info" role="alert">Sélectionnez un évènement dans la liste ci-contre pour voir ses informations.</div>
					</div>
				</div>
			
			</c:when>
			
			<c:otherwise>
				
				<!-- 
				INFORMATIONS OF THE EVENT SELECTED + SUBSCRIBE FORM
				-->
				
				<div class="row">
					<div class="col-sm-7">
						<div class="panel panel-default">
							<div class="panel-heading"><strong>${eventDisplayed.name}</strong></div>
							<div class="panel-body">
								<strong>Créateur :</strong> ${eventDisplayed.users.pseudo} (${eventDisplayed.users.mail}) <br/>
								<strong>Adresse :</strong> ${eventDisplayed.address} <br/>
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
											<label for="firstName" class="col-sm-3 control-label">Prénom</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="firstName" name="firstName" placeholder="Prénom" required>
											</div>
											<div id="firstNameHelp" class="col-sm-offset-3 col-sm-4 help">
												*Champ requis.
											</div>
										</div>
										<div class="form-group">
											<label for="name" class="col-sm-3 control-label">Nom</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="name" name="name" placeholder="Nom" required>
											</div>
											<div id=nameHelp class="col-sm-offset-3 col-sm-4 help">
												*Champ requis.
											</div>
										</div>
										<div class="form-group">
											<label for="company" class="col-sm-3 control-label">Entreprise</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="company" name="company" placeholder="Entreprise" required>
											</div>
											<div id="companyHelp" class="col-sm-offset-3 col-sm-4 help">
												*Champ requis.
											</div>
										</div>
										<div class="form-group">
											<label for="mail" class="col-sm-3 control-label">Adresse mail</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="mail" name="mail" placeholder="Adresse mail" required>
											</div>
											<div id="mailHelp" class="col-sm-offset-3 col-sm-4 help">
												*Le mail doit être formé selon l'exemple email@example.com.
											</div>
										</div>
										<div class="form-group">
											<input type="hidden" name="eventId" id="eventId" value="${eventDisplayed.id}">
											<div class="col-sm-offset-3 col-sm-4">
												<button id="participate" type="submit" class="btn btn-default" disabled="disabled">Participer</button>
											</div>
										</div>
									</form>
								</li>
							</ul>
						</div>
					</div>
				</div>
				
			</c:otherwise>
			
		</c:choose>
		
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

<script>

	$('#firstName').change(validator);
	$('#name').change(validator);
	$('#company').change(validator);
	$('#mail').change(validator);
	
	function validator(){		
		// Do we have to disable the register button ?
		var disableButton = false ;
		// First name test
		var firstName = $('#firstName').val() ;
		if (firstName.trim() == ''){
			$('#firstNameHelp').show("slow");
			disableButton = true ;
		}else{
			$('#firstNameHelp').hide("slow");
		}
		// Name test
		var name = $('#name').val() ;
		if (name.trim() == ''){
			$('#nameHelp').show("slow");
			disableButton = true ;
		}else{
			$('#nameHelp').hide("slow");
		}
		// Company test
		var company = $('#company').val() ;
		if (company.trim() == ''){
			$('#companyHelp').show("slow");
			disableButton = true ;
		}else{
			$('#companyHelp').hide("slow");
		}
		// Mail test
		var mail = $('#mail').val() ;
		var mailPatt = new RegExp('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$') ;
		if (!mailPatt.test(mail)){
			$('#mailHelp').show("slow") ;
			disableButton = true ;
		}else{
			$('#mailHelp').hide("slow") ;
		}	
		// Disable the participate button
		if (disableButton) $('#participate').attr('disabled', 'disabled') ; 
		// Enable the register button
		else $('#participate').removeAttr('disabled') ;
	}
	
</script>

<%@ include file="/WEB-INF/jspf/Footer.jspf" %>