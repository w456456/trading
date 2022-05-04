<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../common/header.jsp" %>
	<br>
	<h2 class="text-center">상품 정보</h2>
	<table class="table">
	<tr>
		<td>
			<img src="/images/${dto.filename}" width="300px" height="300px">
		</td>
		<td align="left">
			<table class="table">
			<tr>
				<td>상품명</td>
				<td>${dto.product_name}</td>
			</tr>
			<tr>
				<td><br>가격</td>
				<td><br>${dto.price}</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><br>${dto.description}</td>
			</tr>
			<tr>
  <c:if test="${sessionScope.adminid == null}">
				<td colspan="2">
					<form name="form1" method="post" action="/goods/cart/insert.do">
						<input type="hidden" name="product_code" value="${dto.product_code}">
						
						<select name="amount">
 <c:forEach begin="1" end="10" var="i">
							<option value="${i}">${i}</option>
 </c:forEach>
						</select> &nbsp; 개

						<input type="submit" class="btn btn-outline-success" value="장바구니에 담기">
					</form>

				</td>
 </c:if> 
			</tr>
			</table>
		</td>
	</tr>
	</table>
	
<%@ include file="../common/footer.jsp" %>