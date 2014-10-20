<%@ include file="/WEB-INF/jspf/Prolog.jspf" %>
<c:set var="title" value="Vos �v�nements" scope="page"/>
<c:set var="content" value="true" scope="page"/>
<c:set var="eventPage" value="" scope="page"/>
<c:set var="createEventPage" value="active" scope="page"/>
<%@ include file="/WEB-INF/jspf/Header.jspf" %>

<!-- 
CREATE EVENT FORM
-->

<form class="form-horizontal" role="form" method="post" action="CreateEvent">
	<div class="form-group">
		<label for="name" class="col-sm-3 control-label">Nom de l'�v�nement</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="name" name="name" placeholder="Nom de l'�v�nement" value="" required>
		</div>
	</div>
	<div class="form-group">
		<label for="address" class="col-sm-3 control-label">Adresse</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="address" name="address" placeholder="Adresse" value="" required>
		</div>
	</div>
	<div class="form-group">
		<label for="startDate" class="col-sm-3 control-label">Date de d�but</label>
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
			*La fin de l'�v�nement doit survenir apr�s son d�but.
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-10">
			<div class="checkbox">
				<label> 
					<input type="checkbox" name="publish">Publier l'�v�nement
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-10">
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
		}
	);
	
	$('#address').change(trim);
	$('#name').change(trim);
	$('#startDate').change(validator);
	$('#startTime').change(validator);
	$('#endDate').change(validator);
	$('#endTime').change(validator);

	// Delete white spaces before and after inputs
	function trim(){
		$('#address').val($('#address').val().trim());
		$('#name').val($('#name').val().trim());
	}
	
	// Check if dates are OKs
	function validator(){		
		// Do we have to disable the save button ?
		var disableButton = false ;
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
			if (startDate > endDate) {
				disableButton = true ;
			}else if (startDate == endDate) {
				disableButton = startTime > endTime ;
			}
		}
		// Disable the save button
		if (disableButton) {
			$('#save').attr('disabled', 'disabled') ; 
			$('#endDateHelp').show("slow") ;
		// Enable the save button
		}else{
			$('#save').removeAttr('disabled') ;
			$('#endDateHelp').hide("slow") ;
		}
	}
	
</script>

<%@ include file="/WEB-INF/jspf/Footer.jspf" %>