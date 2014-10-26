<%@ include file="/WEB-INF/jspf/Prolog.jspf" %>
<c:set var="title" value="Inscription" scope="page"/>
<c:set var="content" value="false" scope="page"/>
<c:set var="connectionPage" value="" scope="page"/>
<c:set var="inscriptionPage" value="active" scope="page"/>
<%@ include file="/WEB-INF/jspf/Header.jspf" %>

<!-- 
REGISTER FORM
-->

<form id="validateForm" class="form-horizontal" role="form" method="post" action="Register">
	<!-- SHOW OR HIDE ERROR -->
	<c:choose>
		<c:when test="${not empty registerError}">
			<c:set var="hideError" value="" scope="page"/>
		</c:when>
		<c:otherwise>
			<c:set var="hideError" value="hidden=\"true\"" scope="page"/>
		</c:otherwise>
	</c:choose>
	<div class="form-group" ${hideError}>
		<div class="col-sm-offset-3 col-sm-5">
			<div class="alert alert-danger" role="alert">
				${registerError}
			</div>
		</div>
	</div>
	<!-- FROM -->
	<div class="form-group">
		<label class="col-sm-3 control-label">Pseudo</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" name="pseudo" id="pseudo" placeholder="Pseudo" value="" required>
		</div>
		<div id="pseudoHelp" class="col-sm-offset-3 col-sm-10 help">
			*Le pseudo doit contenir entre 4 et 16 lettres et chiffres.
		</div>	
	</div>
	<div class="form-group">
		<label for="mail" class="col-sm-3 control-label">Adresse mail</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" name="mail" id="mail" placeholder="Adresse mail" value="" required>
		</div>
		<div id="mailHelp" class="col-sm-offset-3 col-sm-10 help">
			*Le mail doit être formé selon le pattern mail@domaine.ex.
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-3 control-label">Mot de passe</label>
		<div class="col-sm-4">
			<input type="password" class="form-control" name="password" id="password" placeholder="Mot de passe" value="" required>
		</div>
		<div id="passwordHelp" class="col-sm-offset-3 col-sm-10 help">
			*Le mot de passe doit contenir entre 4 et 16 lettres et chiffres.
		</div>
	</div>
	<div class="form-group">
		<label for="passwordConfirmation" class="col-sm-3 control-label">Mot de passe (confirmation)</label>
		<div class="col-sm-4">
			<input type="password" class="form-control" name="passwordConfirmation" id="passwordConfirmation" placeholder="Confirmation du mot de passe" required>
		</div>
		<div id="passwordConfirmationHelp" class="col-sm-offset-3 col-sm-10 help">
			*Les deux mots de passent doivent être identiques.
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-10">
			<button id="register" type="submit" class="btn btn-default" disabled="disabled">S'inscrire</button>
		</div>
	</div>
</form>

<%@ include file="/WEB-INF/jspf/Footer.jspf" %>

<script>
	
	$(document).ready(
		function(){
			$('#pseudo').focus();
		}
	);

	$('#pseudo').change(validator);
	$('#mail').change(validator);
	$('#password').change(validator);
	$('#passwordConfirmation').change(validator);
	
	function validator(){		
		// Do we have to disable the register button ?
		var disableButton = false ;
		// Pseudo test
		var pseudo = $('#pseudo').val() ;
		var pseudoPatt = new RegExp('^[a-zA-Z0-9]{4,16}$') ;
		if (!pseudoPatt.test(pseudo)){
			$('#pseudoHelp').show("slow") ;
			disableButton = true ;
		}else{
			$('#pseudoHelp').hide("slow") ;
			disableButton = disableButton || false ;			
		}
		// Mail test
		var mail = $('#mail').val() ;
		var mailPatt = new RegExp('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$') ;
		if (!mailPatt.test(mail)){
			$('#mailHelp').show("slow") ;
			disableButton = true ;
		}else{
			$('#mailHelp').hide("slow") ;
			disableButton = disableButton || false ;
		}	
		// Password test
		var password = $('#password').val() ;
		var passwordPatt = new RegExp('^[a-zA-Z0-9]{4,16}$') ;
		if (!passwordPatt.test(password)){
			$('#passwordHelp').show("slow") ;
			disableButton = true ;
		}else{
			$('#passwordHelp').hide("slow") ;
			disableButton = disableButton || false ;
		}
		// Password confirmation test
		var passwordConfirmation = $('#passwordConfirmation').val() ;
		if (password != passwordConfirmation || passwordConfirmation == ''){
			$('#passwordConfirmationHelp').show("slow") ;
			disableButton = true ;
		}else{
			$('#passwordConfirmationHelp').hide("slow") ;
			disableButton = disableButton || false ;
		}
		// Disable the register button
		if (disableButton) $('#register').attr('disabled', 'disabled') ; 
		// Enable the register button
		else $('#register').removeAttr('disabled') ;
	}
	
</script>