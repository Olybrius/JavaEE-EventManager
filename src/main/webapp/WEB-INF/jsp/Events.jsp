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



<%@ include file="/WEB-INF/jspf/Footer.jspf" %>