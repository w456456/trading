<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>거래사이트</title>
        <!-- Favicon-->
        <link rel="icon" type="../image/x-icon" href="/assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/resources/static/css/styles.css" rel="stylesheet" />
        <script src="/resources/static/include/jquery-3.6.0.js"></script>
    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar-->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light"><a class="bg-light list-group-item-light" href="/">거래사이트</a></div>
                <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-4" href="/notice/list.do">공지사항</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-4" href="/goods/product/list.do">상품목록</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-4" href="/goods/cart/list.do">장바구니</a>
                </div>
            </div>
            <!-- Page content wrapper-->
            <div id="page-content-wrapper">
                <!-- Top navigation-->
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <div class="container-fluid">
                        <button class="btn btn-primary" id="sidebarToggle">메 뉴</button>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                               
                            <c:choose>
                            	<c:when test="${sessionScope.userid != null }">
                            		<li class="nav-item nav-link">${sessionScope.name }(${sessionScope.userid })님 환영합니다</li>
                            		<li class="nav-item active"><a class="nav-link" href="/user/detail/${sessionScope.userid }">내 정보</a></li>
									<li class="nav-item active"><a class="nav-link" href="/user/logout.do">로그아웃</a></li>
								</c:when>
								<c:when test="${sessionScope.adminid != null }">
									<li class="nav-item nav-link">관리자 ${sessionScope.name}(${sessionScope.adminid })님 환영합니다</li>
									<li class="nav-item active"><a class="nav-link" href="/admin/detail/${sessionScope.adminid }">관리자 정보</a></li>
									<li class="nav-item active"><a class="nav-link" href="/admin/logout.do">로그아웃</a></li>
								</c:when>
								<c:otherwise>
	                                <li class="nav-item active"><a class="nav-link" href="/user/login.do">로그인</a></li>
	                                <li class="nav-item"><a class="nav-link" href="/user/join.do">회원가입</a></li>
									<li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></a>
                                    <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="/admin/login.do">관리자 로그인</a>
                                    </div>
                                </li>
								</c:otherwise>
                            </c:choose>
                            </ul>
                        </div>
                    </div>
                </nav>
                <!-- Page content-->
                <div class="container-fluid">
