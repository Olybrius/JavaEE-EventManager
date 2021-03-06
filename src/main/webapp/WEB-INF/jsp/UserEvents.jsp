<%@ include file="/WEB-INF/jspf/Prolog.jspf" %>
<c:set var="title" value="Mes �v�nements" scope="page"/>
<c:set var="content" value="true" scope="page"/>
<c:set var="userEventsPage" value="active" scope="page"/>
<c:set var="createEventPage" value="" scope="page"/>
<c:set var="eventsPage" value="" scope="page"/>
<%@ include file="/WEB-INF/jspf/Header.jspf" %>
 
<c:choose>

	<c:when test="${fn:length(userEvents) gt 0}">
	
		<!-- 
		TABLE OF EVENTS
		A data-id is generated in order to show the participants of the event clicked in the modal (see below).
		 -->
		 
		<div class="row">
			<div class="col-sm-offset-1 col-sm-10">
				<table id="listOfEvents" class="display">
					<thead>
						<tr>
							<th>Nom de l'�v�nement</th>
							<th>Lieu de l'�v�nement</th>
							<th>D�but de l'�v�nement</th>
							<th>Fin de l'�v�nement</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach begin="0" end="${fn:length(userEvents)}" step="1" varStatus="eventCounter" items="${userEvents}" var="event">
							<tr data-toggle="modal" data-id="${event.id}" data-target="#participants">
								<td>
									<!-- For the sort --> <label hidden="true">${event.name}</label>
									<c:choose>
										<c:when test="${event.published==1}">
											<span class="label label-default participants">${fn:length(event.listOfParticipants)} participant(s)</span>
										</c:when>
										<c:otherwise>
											<span class="label label-warning participants">non publi�</span>
										</c:otherwise>
									</c:choose>
									${event.name}
								</td>
								<td>
									${event.address}
								</td>
								<td>
									<fmt:formatDate value="${event.startdate}" pattern="dd/MM/yyyy HH:mm"/>
								</td>
								<td>
									<fmt:formatDate value="${event.enddate}" pattern="dd/MM/yyyy HH:mm"/>								
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
	</c:when>
	
	<c:otherwise>
	
		<!-- 
		NO EVENT TO SHOW
		 -->
	
		<div class="row">
			<div class="col-sm-offset-1 col-sm-10">
				<div class="alert alert-info" role="alert">Vous n'avez cr�� aucun �v�nement. Rendez-vous sur la <a href="CreateEvent" class="alert-link">page de cr�ation</a> !</div>
			</div>
		</div>
		
	</c:otherwise>
	
</c:choose>

<!-- 
MODAL FOR PARTICIPANTS
All the participants of all the events are loaded and hidden.
When a row is clicked, the participants of the event clicked is shown.
-->

<div class="modal fade" id="participants" tabindex="-1" role="dialog" aria-labelledby="participantsTitle" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
		
			<!-- HEADER -->
		
			<div class="modal-header">
 				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Fermer</span></button>
        		<h4 class="modal-title" id="participantsTitle">Participants</h4>
      		</div>
      		
      		<!-- 
      		BODY
      		For each event, we create the table of participants that we hide.
      		-->
      		
      		<div class="modal-body" id="participantsList">
      			<c:forEach begin="0" end="${fn:length(userEvents)}" step="1" varStatus="eventCounterModal" items="${userEvents}" var="event">
					<div id="${event.id}" hidden="true">
						<c:choose>
							<c:when test="${fn:length(event.listOfParticipants) gt 0}">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Nom</th>
											<th>Pr�nom</th>
											<th>Entreprise</th>
											<th>Mail</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach begin="0" end="${fn:length(event.listOfParticipants)}" step="1" varStatus="participantCounterModal" items="${event.listOfParticipants}" var="participant">
											<tr>
												<td>${participant.name}</td>
												<td>${participant.firstname}</td>
												<td>${participant.company}</td>
												<td>${participant.mail}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								Aucun participant ne s'est inscrit � cet �v�nement.
							</c:otherwise>
						</c:choose>
	
					</div>
				</c:forEach>
      		</div>
      		
      		<!-- FOOTER -->
      		
      		<div class="modal-footer">
				<form id="publish" method="post" role="form" action="Publish" style="display:inline-block">
					<input type="hidden" name="eventId" id="eventId">
					<button type="submit" class="btn btn-default">Publier</button>
				</form>
      			<button type="button" class="btn btn-primary" data-dismiss="modal">Fermer</button>
      		</div>
      		
		</div>
	</div>
</div>

<script>
    $(document).ready(
    	function(){
    		// Initialize datatable
	    	$('#listOfEvents').dataTable({
	    	    "bPaginate": false,
	    	    "bLengthChange": false,
	    	    "bFilter": false,
	    	    "bInfo": false,
	    	    "bAutoWidth": false
	    	});
    		// Before the modal is shown
	    	$('#participants').on(
    			'show.bs.modal', 
		    	function(event){
		    		// Hide every div
		    		$('#participantsList').children('div').each(function(){
		    			$(this).hide();
		    		});
		    		// Show participants of the even clicked
		    		var eventSelected = $(event.relatedTarget).closest('tr').data('id');
		    		$('#eventId').attr('value', eventSelected);
		    		$('#' + eventSelected).show();
		    		// Hide the publish button if the event is already published or hide it otherwise
		    		if ($('tr[data-id='+eventSelected+'] span.participants').html().trim() != 'non publi�') $('#publish').hide();
		    		else $('#publish').show();
		    	}
	    	);
    	}
    );	
</script>

<%@ include file="/WEB-INF/jspf/Footer.jspf" %>