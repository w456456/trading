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
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$("#btnUpdate").click(function(){
			document.form1.action = "/user/update.do";
			document.form1.submit();
		});
		$("#btnDelete").click(function(){
			if(confirm("탈퇴하시겠습니까?")){
				document.form1.action = "/user/delete.do";
				document.form1.submit();
			}
		});
	});
</script>
</head>
<body>
<h2 class="mx-5 my-3">회원 정보 수정</h2>
<form method="post" name="form1">
	<div class="mx-5">
		<table style="text-align:center;" class="table-borderless" width="350px">
			<tr>
				<td><br>아이디</td>
				<td><br><input name="userid" class="form-control" value="${dto.userid }" readonly></td>
			</tr>
			<tr>
				<td><br>비밀번호</td>
				<td><br><input type="password" name="passwd" class="form-control"></td>
			</tr>
			<tr>
				<td><br>이름</td>
				<td><br><input name="name" class="form-control" value="${dto.name }"></td>
			</tr>
			<tr>
				<td><br>나이</td>
				<td><br><input name="age" class="form-control" value="${dto.age }"></td>
			</tr>
			<tr>
				<td><br>전화번호</td>
				<td><br><input name="phone" class="form-control" value="${dto.phone }"></td>
			</tr>
			<tr>
				<td><br>이메일</td>
				<td><br><input name="email" class="form-control" value="${dto.email }"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<br>
					<button type="button" id="btnUpdate" class="btn btn-primary">수정</button>
					<button type="button" id="btnDelete" class="btn btn-primary">탈퇴</button>
				</td>
			</tr>
		</table>
	</div>
</form>
<%@ include file="../common/footer.jsp" %>
</body>
</html>