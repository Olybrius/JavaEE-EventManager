<%@ include file="/WEB-INF/jspf/Prolog.jspf" %>
<c:set var="title" value="Vos évènements" scope="page"/>
<c:set var="content" value="true" scope="page"/>
<c:set var="eventPage" value="active" scope="page"/>
<c:set var="createEventPage" value="" scope="page"/>
<%@ include file="/WEB-INF/jspf/Header.jspf" %>

<!-- 
TABLE OF EVENTS
A data-id is generated in order to show the participants of the event clicked in the modal (see below).
 -->
 
<c:choose>

	<c:when test="${fn:length(events) gt 0}">
	
		<div class="row">
			<div class="col-sm-offset-1 col-sm-10">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Nom de l'évènement</th>
							<th>Lieu de l'évènement</th>
							<th>Début de l'évènement</th>
							<th>Fin de l'évènement</th>
							<th>Publié</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach begin="0" end="${fn:length(events)}" step="1" varStatus="loopCounter" items="${events}" var="event">
							<tr data-toggle="modal" data-id="${loopCounter.index}" data-target="#participants">
								<td>
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
								<td class="isPublished">
									<c:choose>
										<c:when test="${event.published==1}">
											Oui
										</c:when>
										<c:otherwise>
											Non
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
	</c:when>
	
	<c:otherwise>
	
		<div class="row">
			<div class="col-sm-offset-1 col-sm-10">
				<div class="alert alert-info" role="alert">Vous n'avez créé aucun évènement. Rendez-vous sur la <a href="CreateEvent" class="alert-link">page de création</a> !</div>
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
		
			<div class="modal-header">
 				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Fermer</span></button>
        		<h4 class="modal-title" id="participantsTitle">Participants</h4>
      		</div>
      		
      		<div class="modal-body" id="participantsList">
      			<c:forEach begin="0" end="${fn:length(events)}" step="1" varStatus="loopCounter" items="${events}" var="event">
					<div id="${loopCounter.index}" hidden="true">
						<c:choose>
							<c:when test="${1 eq 2}">
								Aucun participant ne s'est inscrit à cet évènement.
							</c:when>
							<c:otherwise>
								Oui.
							</c:otherwise>
						</c:choose>
	
					</div>
				</c:forEach>
      		</div>
      		
      		<div class="modal-footer">
				<form id="publish" method="post" action="/Publish" style="display:inline-block">
					<button type="button" class="btn btn-default">Publier</button>
				</form>
      			<button type="button" class="btn btn-primary" data-dismiss="modal">Fermer</button>
      		</div>
      		
		</div>
	</div>
</div>

<script>
	// Before the participants modal is shown
    $(document).ready(
    	function(){
    	$('#participants').on(
    			'show.bs.modal', 
		    	function(event){
		    		// Hide every div
		    		$('#participantsList').children('div').each(function(){
		    			$(this).hide();
		    		});
		    		// Show participants of the even clicked
		    		var eventSelected = $(event.relatedTarget).closest('tr').data('id');
		    		$('#' + eventSelected).show();
		    		// Hide the publish button if the event is already published or hide it otherwise
		    		if ("Oui" == $('tr[data-id='+eventSelected+'] td.isPublished').html().trim()) $('#publish').hide();
		    		else $('#publish').show();
		    	}
    		)
    	}
    );	
</script>

<%@ include file="/WEB-INF/jspf/Footer.jspf" %>
