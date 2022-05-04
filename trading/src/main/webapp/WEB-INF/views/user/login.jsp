<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/header.jsp" %> 
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$("#btnLogin").click(function(){
			login();
		});
		$("#btnJoin").click(function(){
			document.form1.action = "/user/join.do";
			document.form1.submit();
		});
	});
	
	// 함수 처리 - 김회웅 20220423
	function login() {
		var userid = $("#userid").val();
		var passwd = $("#passwd").val();
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
		document.form1.action = "/user/login_check.do";
		document.form1.submit();
	}
</script>
</head>
<body>
<h2 class="mx-5 my-3">로그인</h2>
<form name="form1" method="post">
	<div class="mx-5">
		<table style="text-align:center;" class="table-borderless" width="350px">
			<tr>
				<td><input id="userid" name="userid" class="form-control" placeholder="아이디"></td>
			</tr>
			<tr>
				<td><br><input type="password" id="passwd" name="passwd" class="form-control" placeholder="비밀번호"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<br>
					<button type="button" id="btnLogin" class="btn btn-primary">로그인</button>&nbsp;
					<button type="button" id="btnJoin" class="btn btn-primary">회원가입</button>
					<c:if test="${message == 'error' }">
						<div style="color: red;"><br>아이디 또는 비밀번호가 일치하지 않습니다.</div>
					</c:if>
					<c:if test="${param.message == 'logout' }">
						<div style="color:red;"><br>로그아웃되었습니다.</div>
					</c:if>
				</td>
			</tr>
		</table>
	</div>
</form>
<%@ include file="../common/footer.jsp" %>
</body>
<script>
// 입력 후 Enter키 적용 - 김회웅 20220423 
$(":input").keyup(function(event){
	if(event.keyCode == 13) {
		login();
	}
});
</script>
</html>