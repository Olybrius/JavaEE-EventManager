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

<div class="row">
	<div class="col-sm-offset-1 col-sm-9">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Nom de l'évènement</th>
					<th>Publié</th>
				</tr>
			</thead>
			<tbody>
				<tr data-toggle="modal" data-id="1" data-target="#participants">
					<td>Evènement test 1</td>
					<td class="isPublished">Non</td>
				</tr>
				<tr data-toggle="modal" data-id="2" data-target="#participants">
					<td>Evènement test 2</td>
					<td class="isPublished">Oui</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

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
      			<div id="1" hidden="true">UN</div>
      			<div id="2" hidden="true">DEUX</div>
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
		    		if ("Oui" == $('tr[data-id='+eventSelected+'] td.isPublished').html()) $('#publish').hide();
		    		else $('#publish').show();
		    	}
    		)
    	}
    );	
</script>

<%@ include file="/WEB-INF/jspf/Footer.jspf" %>
