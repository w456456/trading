<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/header.jsp" %>
<script src="/ckeditor/ckeditor.js"></script>
<script>
function product_delete(){
	if(confirm("삭제하시겠습니까?")){
		document.form1.action = "/goods/product/delete.do";
		document.form1.submit();
	}
}
function product_update(){
	var product_name = document.form1.product_name.value;
	var price = document.form1.price.value;
	var description = document.form1.description.value;
	if(product_name == "") {
		alert("상품명을 입력하세요.");
		document.form1.product_name.focus();
		return;
	}
	if(price == ""){
		alert("가격을 입력하세요.");
		document.form1.price.focus();
		return;
	}
	/* if(description == ""){
		alert("상품설명을 입력하세요.");
		document.form1.description.focus();
		return;
	} */
	document.form1.action = "/goods/product/update.do";
	document.form1.submit();
}
</script>
	<br>
	<h2 class="text-center">상품 정보 수정/삭제</h2>
	<form id="form1" name="form1" method="post" enctype="multipart/form-data">
	<table class="table">
	<tr>
		<td>상품명</td>
		<td><input name="product_name" class="form-control" value="${dto.product_name}"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input name="price" class="form-control" value="${dto.price}"></td>
	</tr>
	<tr>
		<td>상품설명</td>
		<td>
			<textarea class="form-control" name="description" id="description">${dto.description}</textarea>
			<script>
				CKEDITOR.replace("description");
			</script>
		</td>
	</tr>
	<tr>
		<td>상품이미지</td>
		<td>
			<img src="/images/${dto.filename}" width="300px" height="300px">
			<br>
			<input type="file" name="file1">
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="hidden" name="product_code" value="${dto.product_code}">
			<input type="button" value="수정" class="btn btn-outline-success" onclick="product_update()">
			<input type="button" value="삭제" class="btn btn-outline-primary" onclick="product_delete()">
		</td>
	</tr>
	</table>
	</form>
	
<script>
$(":text").keyup(function(event){
	if(event.keyCode == 13) {
		product_write();
	}
});
</script>

<%@ include file="../common/footer.jsp" %>