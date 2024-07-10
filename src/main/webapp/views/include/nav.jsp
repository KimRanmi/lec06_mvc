<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.gn.user.vo.User"  %> <!-- import 코드 적어주기 -->
<link href='../../resources/css/include/nav.css' rel="stylesheet" type="text/css">
<nav>
	<div id="nav_wrap">
		<%
			User user = (User)session.getAttribute("user"); 
			if(user == null){		
		%>
		<div class="menu">
			<ul>
				<li>
					<a href="/user/login">로그인</a>
				</li>
				<li>
					<a href="../../user/create">회원가입</a>
				</li>
			</ul>
		</div>
		<%}else{%>		
			<div class = "menu">
				<ul>
					<li>
						<a href="/board/create">게시글 등록</a>
					</li>
					<li>
						<a href="/user/logout">로그아웃</a>
					</li>
					<li>
						<a href="#">계정수정</a>
					</li>
				</ul>
			</div>
		<%}%>
		<div class="search">
			<form>
				<input type="text">
				<input type="button" value="검색">
			</form>
		</div>
	</div>
</nav>	 