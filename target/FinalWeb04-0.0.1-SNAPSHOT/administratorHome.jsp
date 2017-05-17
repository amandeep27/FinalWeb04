<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.main {
	border: 3px solid #f1f1f1;
}

.container {
	padding: 16px;
}

button {
	background-color: #D32F2F;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
}

button:HOVER {
	opacity: 0.8;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta Http-Equiv="Cache-Control" Content="no-cache">
<meta Http-Equiv="Pragma" Content="no-cache">
<meta Http-Equiv="Expires" Content="0">
<title>Home Page</title>
</head>
<body class="main">
	<c:if test="${!empty sessionScope.uName}">

		<form action="info" method="post">
			<div class="container">
				<button style="float: right;" type="submit" name="submit"
					value="logOut">LOG OUT</button>
			</div>


			<div class="container">
				<c:out value="Hello, ${sessionScope.uName}"></c:out>
			</div>
		</form>

	</c:if>
	<c:if test="${empty sessionScope.uName}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>
</body>
</html>