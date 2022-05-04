<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../common/header.jsp" %>

<script>
$(function(){
	$("#btnAdd").click(function(){
		location.href="/goods/product/write.do";
	});
});
</script>
	<br>
	<h2 class="text-center">Goods List</h2>
	<!-- 세션 확인 -->
<c:if test="${sessionScope.adminid != null}">
	<div align="right">
	<button type="button" id="btnAdd" class="btn btn-outline-secondary">상품등록</button>
	</div>
</c:if>
<c:if test="${sessionScope.adminid == null}">
	<br>
</c:if>
	<table border="1" width="500px" class="table table-hover">
	<tr>
		<th>상품ID</th>
		<th>이미지</th>
		<th>상품명</th>
		<th>가격</th>
	</tr>
<c:forEach var="row" items="${list}">
	<tr>
		<td>${row.product_code}</td>
		<td><img src="/resources/static/images/${row.filename}" width="100px" height="100px"></td>
		<td><a href="/goods/product/detail/${row.product_code}">${row.product_name}</a>
	<c:if test="${sessionScope.adminid != null}">
			<br>
			<a class="btn btn-outline-secondary btn-sm" href="/goods/product/edit/${row.product_code}">편집</a>
	</c:if>
		</td>
		<td><fmt:formatNumber value="${row.price}" pattern="#,###"/></td>
	</tr>
</c:forEach>
	</table>
	<br>
<%@ include file="../common/footer.jsp" %>