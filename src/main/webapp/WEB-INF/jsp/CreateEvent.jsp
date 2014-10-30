<%@ include file="/WEB-INF/jspf/Prolog.jspf" %>
<c:set var="title" value="Vos évènements" scope="page"/>
<c:set var="content" value="true" scope="page"/>
<c:set var="userEventsPage" value="" scope="page"/>
<c:set var="createEventPage" value="active" scope="page"/>
<%@ include file="/WEB-INF/jspf/Header.jspf" %>

<!-- 
CREATE EVENT FORM
-->

<form class="form-horizontal" role="form" method="post" action="CreateEvent">
	<!-- SHOW OR HIDE ERROR -->
	<c:choose>
		<c:when test="${not empty createEventError}">
			<c:set var="hideError" value="" scope="page"/>
		</c:when>
		<c:otherwise>
			<c:set var="hideError" value="hidden=\"true\"" scope="page"/>
		</c:otherwise>
	</c:choose>
	<div class="form-group" ${hideError}>
		<div class="col-sm-offset-1 col-sm-10">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				${createEventError}
			</div>
		</div>
	</div>
	<!-- FROM -->
	<div class="form-group">
		<label for="name" class="col-sm-3 control-label">Nom de l'évènement</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="name" name="name" placeholder="Nom de l'évènement" value="" required>
		</div>
		<div id="nameHelp" class="col-sm-offset-3 col-sm-4 help" hidden="true">
			*Champ requis.
		</div>
	</div>
	<div class="form-group">
		<label for="address" class="col-sm-3 control-label">Adresse</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="address" name="address" placeholder="Adresse" value="" required>
		</div>
		<div id="addressHelp" class="col-sm-offset-3 col-sm-4 help" hidden="true">
			*Champ requis.
		</div>
	</div>
	<div class="form-group">
		<label for="startDate" class="col-sm-3 control-label">Date de début</label>
		<div class="col-sm-2">
			<input type="date" class="form-control" id="startDate" name="startDate" required>
		</div>
		<div class="col-sm-2">
			<input type="time" class="form-control" id="startTime" name="startTime" required>
		</div>
	</div>
	<div class="form-group">
		<label for="endDate" class="col-sm-3 control-label">Date de fin</label>
		<div class="col-sm-2">
			<input type="date" class="form-control" id="endDate" name="endDate" required>
		</div>
		<div class="col-sm-2">
			<input type="time" class="form-control" id="endTime" name="endTime" required>
		</div>
		<div id="endDateHelp" class="col-sm-offset-3 col-sm-4 help" hidden="true">
			*La fin de l'évènement doit survenir après son début.
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-4">
			<div class="checkbox">
				<label> 
					<input type="checkbox" id="publish" name="publish">Publier l'évènement
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-4">
			<button id="save" type="submit" class="btn btn-default">Enregister</button>
		</div>
	</div>
</form>

<script>

	$(document).ready(
		function(){
			// Initialize to current date
			var now = new Date();
			var day = ("0" + now.getDate()).slice(-2);
			var month = ("0" + (now.getMonth() + 1)).slice(-2);
			var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
			$('#startDate').val(today);
			$('#endDate').val(today);
			$('#startTime').val('00:00');
			$('#endTime').val('23:59');
			$('#name').focus();
			validator();
		}
	);
	
	$('#address').change(validator);
	$('#name').change(validator);
	$('#startDate').change(validator);
	$('#startTime').change(validator);
	$('#endDate').change(validator);
	$('#endTime').change(validator);

	// Check if dates are OKs
	function validator(){		
		// Do we have to disable the save button ?
		var disableButton = false ;
		// Name test
		var name = $('#name').val() ;
		if (name.trim() == ''){
			$('#nameHelp').show("slow");
			disableButton = true ;
		}else{
			$('#nameHelp').hide("slow");
		}
		// Address test
		var address = $('#address').val() ;
		if (address.trim() == ''){
			$('#addressHelp').show("slow");
			disableButton = true ;
		}else{
			$('#addressHelp').hide("slow");
		}
		// Test dates
		var startDate = $('#startDate').val() ;
		var endDate = $('#endDate').val() ;
		var startTime = $('#startTime').val() ;
		var endTime = $('#endTime').val() ;
		// Bad format
		if (startDate == '' || endDate == '' || startTime == '' || endTime == '') {
			disableButton = true ;
		// Good format
		}else{
			// The event has to finish after it begins
			var badDates = false ;
			if (startDate > endDate) {
				badDates = true ;
				disableButton = true ;
			}else if (startDate == endDate) {
				badDates = startTime > endTime ;
				disableButton = badDates || disableButton ;
			}
			if (badDates) $('#endDateHelp').show("slow") ;
			else $('#endDateHelp').hide("slow") ;
		}
		// Disable the save button
		if (disableButton) $('#save').attr('disabled', 'disabled') ; 
		// Enable the save button
		else $('#save').removeAttr('disabled') ;
	}
	
</script>

<%@ include file="/WEB-INF/jspf/Footer.jspf" %>