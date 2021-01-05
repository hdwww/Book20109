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

@WebServlet("/BookUpdate.do")
public class BookUpdateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// a태그가 처리
		// 도서 수정을 하기 위해 처음 수정 페이지에 접근
		
		// 1. 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		// 2. 파라미터 가져오기
		int bcode = Integer.parseInt(req.getParameter("bcode"));
		// DAO에서 메서드 처리
		BookDAO instance = BookDAO.getInstance();
		BookVO book = instance.getABook(bcode);
	    // 공유 - 세션 
	    req.setAttribute("book", book);
		// 4. 화면 출력 -> jsp이 역할(view 역할 -> view페이지로)
		RequestDispatcher rd = req.getRequestDispatcher("updateBook.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 폼액션으로 들어와야함
		// 도서 정보 수정을 하고 수정 버튼을 눌러서 접근
	
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
		int result = instance.updateBook(vo);
	    // 공유 - 세션 
	    req.setAttribute("result", result);
		// 4. 화면 출력 -> jsp이 역할(view 역할 -> view페이지로)
		RequestDispatcher rd = req.getRequestDispatcher("updateBookPro.jsp");
		rd.forward(req, resp);
	}
}