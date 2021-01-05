package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.BookDAO;
import book.BookVO;

@WebServlet("/BookList.do")
public class BookListController extends HttpServlet {
		//방명록 목록을 보여주는 서블릿
		//get, post 로도 서비스 해줘야 함
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doProcess(req, resp);
		}
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doProcess(req, resp);
		}
		
		public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//1. 한글인코딩
			req.setCharacterEncoding("utf-8");
			//2. 파라미터 가져오기
			//3. DAO, VO 객체 생성 -> vo객체에 담아주기 -> list에 옮겨주고 -> 어트리뷰트 자료 공유
			BookDAO instance = BookDAO.getInstance();
		    ArrayList<BookVO> list = instance.selectAllBooks();
		    // 공유 - 세션 
		    req.setAttribute("list", list);
			//4. view page -> selectBook.jsp
		    RequestDispatcher rd = req.getRequestDispatcher("selectBook.jsp");
		    rd.forward(req, resp);
		}
}
