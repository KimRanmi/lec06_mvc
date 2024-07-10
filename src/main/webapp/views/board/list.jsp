<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href='../../resources/css/board/list.css' rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<%@ include file="../include/nav.jsp" %>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>게시글 목록</h3>
			</div><br>
			<div class="book_list">
				<table>
					<colgroup>
						<col width="10%">
						<col width="50%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일자</th>
						</tr>
					</thead>
					<tbody>
						<%@page import="com.gn.board.vo.Board, java.util.*" %>
						<%
							List<Board> list = (List<Board>)request.getAttribute("resultList");
							for(int i = 0 ; i < list.size(); i++){ %>
								<tr>
									<td><%=list.get(i).getBoard_no()%></td>
									<td><%=list.get(i).getBoard_title()%></td>
									<td><%=list.get(i).getBoard_writer()%></td>
									<td><%=list.get(i).getReg_date()%></td>
								</tr>
						<%}%>
					</tbody>
				</table>
			</div>
		</div>
	</section>	
</body>
</html>