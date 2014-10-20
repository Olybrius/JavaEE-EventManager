<%@ include file="/WEB-INF/jspf/Prolog.jspf" %>
<c:set var="title" value="Connexion" scope="page"/>
<c:set var="content" value="false" scope="page"/>
<c:set var="connectionPage" value="active" scope="page"/>
<c:set var="inscriptionPage" value="" scope="page"/>
<%@ include file="/WEB-INF/jspf/Header.jspf" %>

<!-- 
CONNECTION FORM
-->

<form class="form-horizontal" role="form" method="post" action="Login">
	<div class="form-group">
		<label for="mail" class="col-sm-3 control-label">Adresse mail</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="mail" name="mail" placeholder="Adresse mail" required>
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-3 control-label">Mot de passe</label>
		<div class="col-sm-4">
			<input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" required>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-10">
			<div class="checkbox">
				<label> 
					<input type="checkbox" name="rememberMe">Se souvenir de moi
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-10">
			<button type="submit" class="btn btn-default">Se connecter</button>
		</div>
	</div>
</form>

<script>

	$(document).ready(
		function(){
			$('#mail').focus();
		}
	);
	
</script>
	
<%@ include file="/WEB-INF/jspf/Footer.jspf" %>