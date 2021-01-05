package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.BookDAO;
import book.BookVO;

@WebServlet("/BookInsert.do")
public class BookInsertController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// a태그가 처리
		// 도서 등록을 하기 위해 처음 등록 페이지에 접근
		
		// 1. 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		// DAO에서 메서드 처리
		BookDAO instance = BookDAO.getInstance();
		int maxCode = instance.getMaxCode();
	    // 공유 - 세션 
	    req.setAttribute("maxCode", maxCode);
		// 4. 화면 출력 -> jsp이 역할(view 역할 -> view페이지로)
		RequestDispatcher rd = req.getRequestDispatcher("insertBook.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 폼액션으로 들어와야함
		// 도서 정보 입력을 하고 등록 버튼을 눌러서 접근
	
		// 1. 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		// 2. 파라미터 가져오기
		int bcode = Integer.parseInt(req.getParameter("bcode"));
		String btitle = req.getParameter("btitle");
		String bwriter = req.getParameter("bwriter");
		int bpub = Integer.parseInt(req.getParameter("bpub"));
		int bprice = Integer.parseInt(req.getParameter("bprice"));
		Date bdate = Date.valueOf(req.getParameter("bdate"));
		// 3. vo 객체 생성 -> 담기 -> DAO에서 메서드 처리
		BookVO vo = new BookVO(bcode, btitle, bwriter, bpub, bprice, bdate);
		BookDAO instance = BookDAO.getInstance();
		int result = instance.insertBook(vo);
	    // 공유 - 세션 
	    req.setAttribute("result", result);
		// 4. 화면 출력 -> jsp이 역할(view 역할 -> view페이지로)
		RequestDispatcher rd = req.getRequestDispatcher("insertBookPro.jsp");
		rd.forward(req, resp);
	}
}