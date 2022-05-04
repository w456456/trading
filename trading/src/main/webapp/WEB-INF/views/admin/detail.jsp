<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/header.jsp" %> 
</head>
<body>
<h2 class="mx-5 my-3">관리자 정보</h2>
<form method="post" name="form1">
	<div class="mx-5">
		<table style="text-align:center;" class="table-borderless" width="350px">
			<tr>
				<td><br>아이디</td>
				<td><br><input class="form-control" placeholder="${dto.adminid }" readonly></td>
			</tr>
			<tr>
				<td><br>이름</td>
				<td><br><input class="form-control" placeholder="${dto.name }" readonly></td>
			</tr>
			<tr>
				<td><br>등급</td>
				<td><br><input class="form-control" placeholder="${dto.level }" readonly></td>
			</tr>
			<tr>
				<td><br>전화번호</td>
				<td><br><input class="form-control" placeholder="${dto.phone }" readonly></td>
			</tr>
			<tr>
				<td><br>이메일</td>
				<td><br><input class="form-control" placeholder="${dto.email }" readonly></td>
			</tr>
			<tr>
				<td><br>가입일자</td>
				<td><br><input class="form-control" placeholder="<fmt:formatDate value="${dto.join_date }" pattern='yyyy-MM-dd HH:mm:ss' />" readonly></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<br>
					<button type="button" class="btn btn-primary" onclick="location.href='/admin/passwd_check.do'">수정</button>
				</td>
			</tr>
		</table>
	</div>
</form>
<%@ include file="../common/footer.jsp" %>
</body>
</html>