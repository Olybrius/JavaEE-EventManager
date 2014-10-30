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

<div class="col-sm-offset-1 col-sm-3">
	<ul class="nav nav-pills nav-stacked" role="tablist">
		<li role="presentation" class="active"><a href="Events?id=1">Evènement 1</a></li>
		<li role="presentation"><a href="#">Profile</a></li>
		<li role="presentation"><a href="#">Messages</a></li>
	</ul>
</div>

<div class="row">
	<div class="col-sm-7">
		<div class="panel panel-default">
			<div class="panel-heading"><strong>Evènement 1</strong></div>
			<div class="panel-body">
				<strong>Créateur :</strong> createur <br/>
				<strong>Adresse :</strong> adresse <br/>
				<strong>Début :</strong> début <br/>
				<strong>Fin :</strong> début <br/>
			</div>
			<ul class="list-group">
				<li class="list-group-item">
					<strong>Inscription</strong>
				</li>
				<li class="list-group-item">S'inscrire</li>
			</ul>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/jspf/Footer.jspf" %>