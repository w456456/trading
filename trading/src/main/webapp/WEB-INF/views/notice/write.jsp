<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/header.jsp" %>
<script src="/resources/static/ckeditor/ckeditor.js"></script>
<script>
	$(function(){
		$("#btnSave").click(function() {
			var title = document.form1.title.value;
			if(title=="") {
				alert("제목을입력하세요.");
				document.form1.title.focus();
				return;
			}
			document.form1.submit();
		});
		
	});
</script>
	<br>
	<h2 class="text-center">공지사항 작성</h2>
	<br>
	<form id="form1" name="form1" method="post" action="/notice/insert.do">
	<div class="col-sm-8">
		<input class="form-control" name="title" id="title" size="80" placeholder="제목을 입력하세요.">
	</div>
	<br>
	<div style="width:800px">
		<textarea rows="5" cols="82" id="cont" name="cont" placeholder="내용을 입력하세요"></textarea>
		<script>
			CKEDITOR.replace("cont",{
				filebrowserUploadUrl:"/imageUpload.do"
				});
		</script>
	</div>
	<br>
	<div style="width:700px; text-align:center;">
		<button class="btn btn-outline-success" style="button" id="btnSave">확인</button>
	</div>
	</form>

<%@ include file="../common/footer.jsp" %>