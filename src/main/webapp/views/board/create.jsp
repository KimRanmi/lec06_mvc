<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<link href='../../resources/css/board/create.css' rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<%@ include file="../include/nav.jsp" %>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>게시글 등록</h3>
			</div><br>
			<div class="register_book_form"> <!-- 파일데이터 보낼 때 enctype 꼭 적어줘야 함 /cos.jar는 파일 데이터를 쉽게 보내려고 하는 라이브러리 -->
				<form action='/board/createEnd' name="create_board_form" method="post" enctype="multipart/form-data">	
					<input type="text" name="board_title" placeholder="제목을 입력하세요."> <br>
					<input type="text" name="board_content" placeholder="내용을 입력하세요."><br>
				<!-- 	<input type="text" name="board_writer" placeholder="작성자를 입력하세요."> <br> -->
					<input type="file" name="thumbnail" accept=".png,.jpg,.jpeg"><br>
					<input type="button" value="등록" onclick="createBoardForm();"> 
					<input type="reset" value="취소">
				</form>
			</div> 
		</div>
	</section>
	<script type="text/javascript">
		function createBoardForm() {
			let form = document.create_board_form;	
			if(!form.board_title.value){
				alert("제목을 입력하세요.");
				form.board_title.focus();
			} else if(!form.board_content.value){
				alert("내용을 입력하세요.");
				form.board_content.focus();
			/* }  else if(!form.board_writer.value){
				alert("작성자를 입력하세요.");
				form.board_writer.focus();  */
			}else if (!form.thumbnail.value) {
				alert('이미지 파일을 선택하세요.');
				form.thumbnail.focus();	
			
			} else if(form.thumbnail.value){
				
				const val = form.thumbnail.value; // 이미지 사진 제목을 가져와서
				const idx = val.lastIndexOf('.'); //사진 확장자 이름을 .을 기준으로 가져오기
				const type = val.substring(idx+1,val.length);
				if(type== 'jpg' || type=='jpeg' || type=='png'){
					form.submit();
				}else{
					alert('이미지 파일만 선택할 수 있습니다.');
					form.thumbnail.value = '';
				}
			}
		}
	</script>
</body>
</html>