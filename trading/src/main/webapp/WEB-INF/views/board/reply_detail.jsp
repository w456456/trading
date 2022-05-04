<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/static/include/jquery-3.6.0.js"></script>
<script>
$(function(){
	$("#btnReplyDelete").click(function(){
		$.ajax({
			url: "/reply/delete/${dto.idx}",
			success: function(result) {
				if(result=="success") {
					alert("삭제되었습니다.");
					$("#modifyReply").css("visibility","hidden");
					listReply("1");
				}
			}
		});
	});
	$("#btnReplyUpdate").click(function(){
		var reply_text = $("#detail_replytext").val();
		$.ajax({
			type: "post",
			url: "/reply/update/${dto.idx}",
			headers: {"Content-Type":"application/json"},
			data: JSON.stringify({reply_text:reply_text}),
			dataType: "text",
			success: function(result) {
				if(result == "success") {
					$("#modifyReply").css("visibility","hidden");
					listReply("1");
				}
			}
		});
	});
	$("#btnReplyClose").click(function(){
		$("#modifyReply").css("visibility","hidden");
	});
});
</script>
</head>
<body>
<textarea class="form-control" id="detail_replytext" rows="1">${dto.reply_text}</textarea>
<div style="text-align:center">
	<c:if test="${sessionScope.userid == dto.replyer}">
		<button class="btn btn-outline-success btn-sm" id="btnReplyUpdate" type="button">수정</button>
		<button class="btn btn-outline-success btn-sm" id="btnReplyDelete" type="button">삭제</button>
	</c:if>
	<button class="btn btn-outline-primary btn-sm" id="btnReplyClose" type="button">닫기</button>
	<br>
</div>
</body>
</html>