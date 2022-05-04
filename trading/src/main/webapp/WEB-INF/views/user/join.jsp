<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/header.jsp" %> 
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	$(function(){
		$("#btnJoin").click(function(){
			var userid = $("#userid").val();
			var passwd = $("#passwd").val();
			var name = $("#name").val();
			if(userid == ""){
				alert("아이디를 입력하세요.");
				$("#userid").focus();
				return;
			}
			if(passwd == ""){
				alert("비밀번호를 입력하세요.");
				$("#passwd").focus();
				return;
			}
			if(name == ""){
				alert("이름을 입력하세요.");
				$("#name").focus();
				return;
			}
			if(age == ""){
				alert("나이를 입력하세요.");
				$("#age").focus();
				return;
			}
			document.form1.action = "/user/insert.do";
			document.form1.submit();
		});
	});
</script>
</head>
<body>
<h2 class="mx-5 my-3">회원 정보 입력</h2>
<form method="post" name="form1">
	<div class="mx-5">
		<table style="text-align:center;" class="table-borderless" width="350px">
			<tr>
				<td><input id="userid" name="userid" class="form-control" placeholder="아이디"></td>
			</tr>
			<tr>
				<td><br><input type="password" id="passwd" name="passwd" class="form-control" placeholder="비밀번호"></td>
			</tr>
			<tr>
				<td><br><input id="name" name="name" class="form-control" placeholder="이름"></td>
			</tr>
			<tr>
				<td><br><input id=age name="age" class="form-control" placeholder="나이"></td>
			</tr>
			<tr>
				<td><br><input id="phone" name="phone" class="form-control" placeholder="전화번호"></td>
			</tr>
			<tr>
				<td><br><input id="email" name="email" class="form-control" placeholder="이메일"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<br>
					<button type="button" id="btnJoin" class="btn btn-primary">가입하기</button>
				</td>
			</tr>
		</table>
	</div>
</form>
<%@ include file="../common/footer.jsp" %>
</body>
</html>