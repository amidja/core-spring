<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<h3>
		<p>
			<a href="<c:url value="/admin" />">... admin page</a>
		</p>
		<p>
			<a href="<c:url value="/guest" />">... guest user page</a>
		</p>		
	</h3>

</body>
</html>