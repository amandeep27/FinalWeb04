<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${langType }" scope="session" />

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle var="bundle" basename="com.web.bundle.messages" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="${language}">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
<title><fmt:message key="HomePage" bundle="${bundle}" /></title>
</head>

<body class="main">
	<c:if test="${!empty sessionScope.uName}">
		<div class="container">
			<form action="info" method="post">
				<div>
					<LABEL ><b><fmt:message key="language"
								bundle="${bundle}" /> : </b></LABEL> <SELECT name="lang">
						<OPTION value="en" selected><fmt:message key="english"
								bundle="${bundle}" /></OPTION>
						<OPTION value="be"><fmt:message key="belarusian"
								bundle="${bundle}" /></OPTION>
					</SELECT>
					<button type="submit" name="submit" value="changeLanguage">
						<fmt:message key="changeLanguage" bundle="${bundle}" />
					</button>
				</div>
			</form>
			<form action="info" method="post">
				<button style="float: right; margin-top: -55px; background-color: #D32F2F;" type="submit" name="submit"
					value="logOut">
					<fmt:message key="LogOut" bundle="${bundle}" />
				</button>
			</form>
		</div>
		<div class="container">
			<fmt:message key="hello" bundle="${bundle}" />
			<c:out value=", ${sessionScope.uName}"></c:out>
		</div>

		<form action="info" method="post" accept-charset="UTF-8">
			<div class="container">
				<p>
					<LABEL><b><fmt:message key="title" bundle="${bundle}" />
							: </b></LABEL> <INPUT type="text" name="title" pattern="{4,}"
						oninvalid="this.setCustomValidity('Invalid Entry')"
						oninput="setCustomValidity('')" /> <b><fmt:message key="note"
							bundle="${bundle}" />:</b>
					<fmt:message key="emptyTitleField" bundle="${bundle}" />
				</p>
				<p>
					<LABEL><b><fmt:message key="author" bundle="${bundle}" />
							: </b></LABEL> <INPUT type="text" name="author" pattern="{4,}"
						oninvalid="this.setCustomValidity('Invalid Entry')"
						oninput="setCustomValidity('')" /> <b><fmt:message key="note"
							bundle="${bundle}" />:</b>
					<fmt:message key="emptyAuthorField" bundle="${bundle}" />
				</p>
				<LABEL><b><fmt:message key="type" bundle="${bundle}" />
						: </b></LABEL> <SELECT name="type">
					<OPTION value="all" selected><fmt:message key="all"
							bundle="${bundle}" /></OPTION>
					<OPTION value="e-book"><fmt:message key="eBook"
							bundle="${bundle}" /></OPTION>
					<OPTION value="paperBound"><fmt:message key="paperBound"
							bundle="${bundle}" /></OPTION>
				</SELECT>
				<BUTTON type="submit" name="submit" value="getBook">
					<fmt:message key="Go" bundle="${bundle}" />
				</BUTTON>

			</div>
		</form>
		<c:if test="${gotResult}">
			<c:if test="${!has_result }">
				<strong><fmt:message key="noRecord" bundle="${bundle}" /></strong>
			</c:if>
			<c:if test="${has_result}">
				<strong>Info : </strong>
				<br>
				<br>
				<table border="2">
					<tr>
						<th><fmt:message key="title" bundle="${bundle}" /></th>
						<th><fmt:message key="description" bundle="${bundle}" /></th>
						<th><fmt:message key="publishingYear" bundle="${bundle}" /></th>
						<th><fmt:message key="author" bundle="${bundle}" /></th>
						<th><fmt:message key="bookType" bundle="${bundle}" /></th>
					</tr>
					<c:forEach var="book" items="${bookList}">
						<tr>
							<td>${book.booktitle}</td>
							<td>${book.description}</td>
							<td>${book.publishYear}</td>
							<td>${book.getAuthor().name}</td>
							<td>${book.getType().typeName}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</c:if>
		<c:if test="${empty sessionScope.uName}">
			<c:redirect url="login.jsp"></c:redirect>
		</c:if>
	</c:if>

</body>

</html>