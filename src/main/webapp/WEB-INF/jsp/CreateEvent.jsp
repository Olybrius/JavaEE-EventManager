<%@ include file="/WEB-INF/jspf/Prolog.jspf" %>
<c:set var="title" value="Vos évènements" scope="page"/>
<c:set var="content" value="true" scope="page"/>
<c:set var="eventPage" value="" scope="page"/>
<c:set var="createEventPage" value="active" scope="page"/>
<%@ include file="/WEB-INF/jspf/Header.jspf" %>

<!-- 
CREATE EVENT FORM
-->

<form class="form-horizontal" role="form" method="post" action="CreateEvent">
	<div class="form-group">
		<label for="name" class="col-sm-3 control-label">Nom de l'évènement</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="name" name="name" placeholder="Nom de l'évènement" required>
		</div>
	</div>
	<div class="form-group">
		<label for="address" class="col-sm-3 control-label">Adresse</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="address" name="address" placeholder="Adresse" required>
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
		<div id="endDateHelp" class="col-sm-offset-3 col-sm-10 help" hidden="true">
			*La fin de l'évènement doit survenir après son début.
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-10">
			<div class="checkbox">
				<label> 
					<input type="checkbox" name="publish">Publier l'évènement
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-10">
			<button id="save" type="submit" class="btn btn-default" disabled="disabled">Enregister</button>
		</div>
	</div>
</form>

<script>

	$(document).ready(
		function(){
			$('#endDateHelp').show("slow") ;
		}
	);

	$('#startDate').change(validator);
	$('#startTime').change(validator);
	$('#endDate').change(validator);
	$('#endTime').change(validator);
	
	function validator(){		
		// Do we have to disable the save button ?
		var disableButton = false ;
	
		// Disable the save button
		if (disableButton) $('#save').attr('disabled', 'disabled') ; 
		// Enable the save button
		else $('#save').removeAttr('disabled') ;
	}
	
</script>

<%@ include file="/WEB-INF/jspf/Footer.jspf" %>