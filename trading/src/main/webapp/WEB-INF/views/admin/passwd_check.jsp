<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/header.jsp" %> 
</head>
<body>
<h2 class="mx-5 my-3">비밀번호 확인</h2>
<form method="post" action="/admin/password_check.do">
	<div class="mx-5">
		<table style="text-align:center;" class="table-borderless" width="350px">
			<tr class="form-group">
				<td><br><input type="password" name="passwd" class="form-control" placeholder="비밀번호"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<c:if test="${message == 'error' }">
						<div style="color: red;"><br>아이디 또는 비밀번호가 일치하지 않습니다.</div>
					</c:if>
					<br>
					<button type="submit" class="btn btn-primary">확인</button>
				</td>
			</tr>
		</table>
	</div>
</form>
<%@ include file="../common/footer.jsp" %>
</body>
</html>