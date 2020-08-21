<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>welcome</h2>
	<ul>

		<c:forEach var="s" items="${studentList}">
			<li>${s.student_id}, ${s.name}, ${s.program}
				<hr>
		</c:forEach>
	</ul>
</body>
</html>