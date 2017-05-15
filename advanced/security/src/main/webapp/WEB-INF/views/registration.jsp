<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<c:url value="/logout" var="logoutUrl" />
	<c:url value="/login" var="loginUrl" />
	<c:url value="/registration" var="registrationUrl" />

	<!-- csrt for log out-->
	<form id="logoutForm" action="${logoutUrl}" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
			
	<form name='registerForm' action="${registrationUrl}" method='POST'>
		<h3>
			Welcome : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
		</h3>

		<c:if test="${pageContext.request.userPrincipal.name != null}">		
			<!-- Display only if user is not registered for 2FA-->			
			<c:if test="${using2FA != true}">
				<div id="qr">
				    <p>Scan this Barcode using Google Authenticator app on your phone to use it later in login
				           <a href="https://play.google.com/store/apps/details?id=com.google.android.apps.authenticator2">Android</a> and 
				           <a href="https://itunes.apple.com/us/app/google-authenticator/id388497605">iPhone</a>
				    </p>
				    <br/>
				    <img src="${qrUrl}"/>
				</div>
				
				<input type="checkbox" name="register" value="Yes" checked> Register for two factor authentication<br>
				<input type="submit" value="Submit">
			</c:if>
		</c:if>		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />			  			  
	</form>
	
	<!-- Only available to users that are using two factor authentication -->
	<c:if test="${using2FA}">
		<p>
			<a href="<c:url value="/admin" />">... admin page</a>
		</p>
	</c:if>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
</body>
</html>