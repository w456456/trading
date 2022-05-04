<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/header.jsp" %>
<script src="/resources/static/ckeditor/ckeditor.js"></script>
<script>
	$(function(){
		$(".fileDrop").on("dragenter dragover",function(e) {
			e.preventDefault();//기본효과막음
		});
		
		$(".fileDrop").on("drop",function(e) {
			e.preventDefault();
			var files = e.originalEvent.dataTransfer.files;//파일
			var file = files[0];//첫번째
			var form_data = new FormData();//폼객체
			form_data.append("file",file);//폼에첨부파일추가
			$.ajax({
				url:"/upload/ajax_upload",
				data: form_data,
				processData: false,
				contentType: false,
				type: "post",
				success:function(data) {//첨부파일이름
					var fileInfo = getFileInfo(data);
					var html = "<a href='" + fileInfo.get_link + "'>" + 
								fileInfo.original_name + "</a><br>";
					html += "<input type='hidden' name='files' value='" + 
							fileInfo.file_name + "'>";
					$("#uploadedList").append(html);
				}
			});
		});
		
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
	
	function getFileInfo(file_name) {
		var get_link = "/upload/display_file?file_name=" + file_name;
		original_name = file_name.substr(file_name.indexOf("_")+1);
		return {
			original_name:original_name, get_link:get_link, file_name:file_name
		};
	}
</script>
<style>
	.fileDrop{
		width:600px; height:100px; border:1px dotted gray; background-color:lightgreen;
	}
</style>
	<br>
	<h2 class="text-center">게시물작성</h2>
	<br>
	<form id="form1" name="form1" method="post" action="/board/insert.do">
	<div class="col-sm-8">
		<input class="form-control" name="title" id="title" placeholder="제목을 입력하세요.">
	</div>
	<br>
	<div style="width:800px">
		<textarea id="cont" name="cont" placeholder="내용을 입력하세요"></textarea>
		<script>
			CKEDITOR.replace("cont");
		</script>
	</div>
	<div>
	<br>
		첨부파일등록
		<div class="fileDrop"></div>
		<div id="uploadedList"></div>
	</div>
	<br>
	<div style="width:700px; text-align:center;">
		<button style="button" class="btn btn-outline-success" id="btnSave">확인</button>
	</div>
	<br>
	</form>

<%@ include file="../common/footer.jsp" %>