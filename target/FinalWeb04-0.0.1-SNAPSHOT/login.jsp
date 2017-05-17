<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${langType}" scope="session" />

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle var="bundle" basename="com.web.bundle.messages" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="${language}">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<STYLE type="text/css">
input[type=text], input[type=password] {
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

a:LINK {
	color: red;
}

a:HOVER {
	color: blue;
	text-decoration: underline;
}

a {
	text-decoration: none;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
}

button:HOVER {
	opacity: 0.8;
}

.container {
	padding: 16px;
}

.main {
	border: 3px solid #f1f1f1;
}
</STYLE>
<title><fmt:message key="LoginPage" bundle="${bundle}" /></title>
</head>

<body class="main">
	<FORM action="info" method="post">
		<DIV class="container">

			<p>
				<LABEL><b><fmt:message key="UserId" bundle="${bundle}" />
						:</b></LABEL> <INPUT type="text" name="uId" pattern="[0-9]{6,}"
					oninvalid="this.setCustomValidity('Invalid UserId')"
					oninput="setCustomValidity('')" required />
			</p>
			<p>
				<LABEL><b><fmt:message key="Password" bundle="${bundle}" />
						:</b></LABEL> <INPUT type="password" name="pwd"
					pattern="[a-zA-Z0-9@#$%^&+=]{4,}"
					oninvalid="this.setCustomValidity('Invalid Password')"
					oninput="setCustomValidity('')" required />
			</p>
			<c:if test="${!validUser}">
				<strong style="color: red"><c:out value="${error}"></c:out></strong>
			</c:if>
			<p>
				<BUTTON type="submit" name="submit" value="SignIn">
					<fmt:message key="SignIn" bundle="${bundle}" />
				</BUTTON>
			</p>

			<div style="font-size: 14px">
				<c:set var="language" value="en_US" scope="session" />
				<fmt:setLocale value="${language}" scope="session" />
				<fmt:setBundle var="bundle" basename="com.web.bundle.messages" />
				<a href="info?langType=en_US"> <fmt:message key="english"
						bundle="${bundle}" />
				</a>&nbsp;
				<c:set var="language" value="be_BY" scope="session" />
				<fmt:setLocale value="${language}" scope="session" />
				<fmt:setBundle var="bundle" basename="com.web.bundle.messages" />
				<a href="info?langType=be_BY"> <fmt:message key="belarusian"
						bundle="${bundle}" />
				</a>
			</div>

		</DIV>

	</FORM>
	<c:set var="language" value="${langType}" scope="session" />

	<fmt:setLocale value="${language}" scope="session" />
	<fmt:setBundle var="bundle" basename="com.web.bundle.messages" />
	<div class="container">
		<a href="signUp.jsp"> <fmt:message key="NoAccount"
				bundle="${bundle}" />
		</a>
	</div>

</body>

</html>
