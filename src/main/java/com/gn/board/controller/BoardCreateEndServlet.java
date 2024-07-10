package com.gn.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Board;
import com.gn.user.vo.User;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/board/createEnd")
public class BoardCreateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BoardCreateEndServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. multipart/form-data 형식으로 파일이 왔는지 확인
		 if(ServletFileUpload.isMultipartContent(request)) {
			 // 2. 파일 저장 위치 설정
			 String dir = request.getServletContext().getRealPath("upload"); // 기본이 webapp에서 시작
			 // 3. 저장 파일의 최대 크기 설정(10MB) byte = 1024
			 int maxSize = 1024 * 1024 * 10;
			 // 4. 인코딩 설정
			 String encoding = "UTF-8";
			 // 5. Rename 클래스
			 DefaultFileRenamePolicy dfr = new DefaultFileRenamePolicy();
			 //6. MultipartRequest 객체 매개변수(5개) 
			 // HttpServletRequest, 저장위치, 최대크기, 인코딩, Rename 규칙
			 MultipartRequest mr = new MultipartRequest(request,dir,maxSize,encoding,dfr);
			 // 7. 파일명 정보
			 String oriName = mr.getOriginalFileName("thumbnail");
			 String reName = mr.getFilesystemName("thumbnail");
//			 System.out.println(oriName+"->"+reName);
			 
			 String title = mr.getParameter("board_title");
			 String content = mr.getParameter("board_content");
//			 int writer = mr.getParameter("board_writer");
			 HttpSession session = request.getSession(false);
			 
			 Board b = new Board();
			 b.setBoard_title(title);
			 b.setBoard_content(content);
			 b.setOri_thumbnail(oriName);
			 b.setNew_thumbnail(reName);
			 
			 if(session != null) {
				 User u  = (User)session.getAttribute("user");
				 int userNo = u.getUser_no();
				 b.setBoard_wirter(userNo);
			 }
			 
			 // board 객체에 정보 담기
			 
			 int result = new BoardService().createBoard(b);
			 RequestDispatcher view = request.getRequestDispatcher("/views/board/create_fail.jsp");
			 if(result > 0) {
				 view = request.getRequestDispatcher("/views/board/create_success.jsp");
			 }
			 view.forward(request, response);
			 
		 }else {
			 response.sendRedirect("/board/create");
		 }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
