<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>나이</th>
		<th>전화번호</th>
		<th>이메일</th>
		<th>가입일자</th>
	</tr>
	<c:forEach var="row" items="${list }">
	<tr>
		<th>${row.userid }</th>
		<th>${row.name }</th>
		<th>${row.age }</th>
		<th>${row.phone }</th>
		<th>${row.email }</th>
		<th><fmt:formatDate value="${row.join_date }" pattern="yyyy-MM-dd" /></th>
	</tr>
	</c:forEach>
</table>
</body>
</html>