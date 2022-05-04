<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../common/header.jsp" %>

<script>
	$(function() {
		$("#btnWrite").click(function() {
			location.href="/notice/write.do";
		});
	});
	
	function list(page) {
		location.href="/notice/list.do?cur_page=" + page + 
				"&search_option=${map.search_option}&keyword=${map.keyword}";
	}
</script>
	<br>
	<h2 class="text-center">공지 사항</h2>
	${map.count}개의 게시물이 있습니다.
	<table border="1" width="600px" class="table table_hover">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>이름</th>
		<th>날짜</th>
		<th>조회수</th>
	</tr>
<c:forEach var="row" items="${map.list}">
	<tr>
		<td>${row.num}</td>
		<td>
			<a href="/notice/detail.do?num=${row.num}&cur_page=${map.page_info.curPage}&search_option=${map.search_option}&keyword=${map.keyword}">
				${row.title}
			</a>
		</td>
		<td>${row.name}</td>
		<td>
			<fmt:formatDate value="${row.reg_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
		<td>${row.cnt}</td>
	</tr>
</c:forEach>
	<tr>
		<td colspan="5" align="center">
<c:if test="${map.page_info.curBlock > 1}">
			<a href="javasript:list('1')">[처음]</a>
</c:if>
<c:if test="${map.page_info.curBlock > 1}">
			<a href="javascript:list('${map.page_info.prevPage}')">[이전]</a>
</c:if>
<c:forEach var="num" begin="${map.page_info.blockBegin}" end="${map.page_info.blockEnd}">
	<c:choose>
		<c:when test="${num == map.page_info.curPage}">
			<span style="color:red">${num}</span>&nbsp;
		</c:when>
		<c:otherwise>
			<a href="javascript:list('${num}')">${num}</a>&nbsp;
		</c:otherwise>
	</c:choose>
</c:forEach>
	<c:if test="${map.page_info.curBlock <= map.page_info.totBlock}">
			<a href="javascript:list('${map.page_info.nextPage}')">[다음]</a>
	</c:if>
	<c:if test="${map.page_info.curPage <= map.page_info.totPage}">
			<a href="javascript:list('${map.page_info.totPage}')">[끝]</a>
	</c:if>
		</td>
	</tr>
	</table>
	<form name="form1" method="post" action="/notice/list.do">
	<select name="search_option">
		<option value="all" <c:out value="${map.search_option == 'all' ? 'selected' : ''}"/>>
			이름+내용+제목
		</option>
		<option value="name" <c:out value="${map.search_option == 'name' ? 'selected' : ''}"/>>
			이름
		</option>
		<option value="contents" <c:out value="${map.search_option == 'contents' ? 'selected' : ''}"/>>
			내용
		</option>
		<option value="title" <c:out value="${map.search_option == 'title' ? 'selected' : ''}"/>>
			제목
		</option>
	</select>
	<input name="keyword" value="${map.keyword}">
	<input type="submit" value="조회">
<c:if test="${sessionScope.adminid != null}">
	&nbsp;&nbsp;<button class="btn btn-outline-success" type="button" id="btnWrite">글쓰기</button>
</c:if>
	</form>
<%@ include file="../common/footer.jsp" %>