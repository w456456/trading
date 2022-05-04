<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../common/header.jsp" %>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
$(function(){
	$("#btnList").click(function(){
		location.href="/goods/product/list.do";
	});
	$("#btnDelete").click(function(){
		if(confirm("장바구니를 비우시겠습니까?")) {
			location.href="/goods/cart/deleteAll.do";
		}
	});
	$("#testBtn").click(function(e){
		e.preventDefault();
		$("#payModal").modal("show");
		$("#modalY").show();
		$("#btnloading").hide();
	});
	$(".close").click(function(){
		$("#payModal").modal("hide");
	});
	$("#modalN").click(function(){
		$("#payModal").modal("hide");
	});
	
	$("#payModal").modal({backdrop:"static",keyboard:false});
});
</script>
	<br>
	<h2 class="text-center">장바구니</h2>
<c:choose>
	<c:when test="${map.count == 0}">
		장바구니가 비었습니다.
	</c:when>
	<c:otherwise>
		<button id="testBtn" class="btn btn-outline-success" style="float:right;">구매결정</button>
		<form id="form1" name="form1" method="post" action="/goods/cart/update.do">
		<table border="1" width="400px" class="table">
		<tr>
			<th>상품명</th>
			<th>단가</th>
			<th>수량</th>
			<th>금액</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach var="row" items="${map.list}">
		<tr>
			<td>${row.product_name}</td>
			<td>${row.price}</td>
			<td>
				<input type="number" style="width:30px;" min="0" 
					max="100" name="amount" value="${row.amount}">
				<input type="hidden" name="cart_id" value="${row.cart_id}">
			</td>
			<td>${row.money}</td>
			<td align="right">
				<a href="/goods/cart/delete.do?cart_id=${row.cart_id}" class="btn btn-outline-danger btn-sm">삭제</a>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				장바구니 금액 합계
				<fmt:formatNumber value="${map.sumMoney}" pattern="#,###,###" /><br>
				배송료: ${map.fee}<br>
				총합계: <fmt:formatNumber value="${map.sum}" pattern="#,###,###" />
			</td>
		</table>
		<button id="btnUpdate" class="btn btn-outline-success">수정</button> &nbsp;
		<button type="button" id="btnDelete" class="btn btn-outline-primary">장바구니 비우기</button>
		</form>
		<br>
		<div>
			<button id="btnList" class="btn btn-outline-info">상품목록 확인</button>
		</div>
	</c:otherwise>
</c:choose>

  <!-- 회원가입 확인 Modal-->
<div class="modal fade" id="payModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">결제 및 구매완료</h5>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">X</span>
				</button>
			</div>
			<div class="modal-body">
				배송료는 ${map.fee} 원.<br>
				결제금액은 <b><fmt:formatNumber value="${map.sum}" pattern="#,###,###" /></b>원 입니다.<br><br>
				<input class="form-check-input" type="radio" name="pay-gubun" checked>신용카드 &nbsp;
				<input class="form-check-input" type="radio" name="pay-gubun">앱결제 &nbsp;
				<input class="form-check-input" type="radio" name="pay-gubun">계좌입금
				<br><br>
				<div class="col-xs-3">
				    우편번호 : <input name="zipcode" id="post_code" readonly>
				    <input class="btn btn-info btn-sm" type="button" onclick="showPostcode()" value="찾기">
				</div>
				주소 : <input class="form-control" name="address1" id="address1">
				상세주소 : <input class="form-control" name="address2" id="address2">
				메시지 : <input class="form-control" name="message" id="message" value="부재시 집 문앞에 놔주시기 바랍니다.">
			</div>
			<div class="modal-footer">
				<button class="btn btn-success btn-sm" id="modalY" href="#" onclick="payComplete()">구매확정</button>
				<button class="btn btn-primary" id="btnloading" type="button" disabled>
  				<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
  				결제중...
				</button>
				<button class="btn btn-secondary btn-sm" id="modalN" type="button" data-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<script>
function showPostcode() {
	new daum.Postcode({
		oncomplete : function(data) {
			var fullAddr = "";
			var extraAddr = "";
			if(data.userSelectedType == "R") {
				fullAddr = data.readAddress;
			} else {
				fullAddr = data.jibunAddress;
			}
			if (data.userSelectedType == "R") {
				if(data.bname !== "") {
					extraAddr += data.bname;
				}
				if(data.vuildingName !== "") {
					extraAddr += (extraAddr !== "" ? ", "+data.buildingName : data.buildingName);
				}
				fullAddr += (extraAddr !== "" ? " ()"+ extraAddr+")":"");
			}
			document.getElementById("post_code").value = data.zonecode;
			document.getElementById("address1").value = fullAddr;
			document.getElementById("address2").focus();
		}
	}).open();
}
function payComplete() {
	if(confirm("결제하시겠습니까?")) {
		setTimeout(function(){
			payClear();
			alert("결제가 완료되었습니다.");
			$("#payModal").modal("hide");
			location.href="/goods/cart/complete.do";
		},2000);
		$("#modalY").hide();
		$("#btnloading").show();
	}
}
function payClear() {
	$("#address1").val("");
	$("#address2").val("");
	$("#message").val("");
	$("#post_code").val("");
	$($("input[name='pay-gubun']").get(0)).prop("checked",true);
}
</script>
<%@ include file="../common/footer.jsp" %>