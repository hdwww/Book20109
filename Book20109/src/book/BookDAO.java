package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDAO {
	
	private static BookDAO instance = new BookDAO();
	
	private BookDAO() {} //  DAO 생성자 만들기
	
	public static BookDAO getInstance() {
		return instance;
	} // instance 반환
	
	private static ArrayList<BookVO> list = new ArrayList<BookVO>();
	
	//2 : 오라클(또는 mysql)커넥션 객체생성
	//drive
	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";// db 사용자
		String password = "hr";// db 비밀번호
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("오라클접속 성공");
		}catch (Exception e) {
			System.out.println("오라클접속 실패");
			e.printStackTrace();
		}
		return conn;
	}

	// DB객체, 초기화
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs= null;

	//	select--------
	public ArrayList<BookVO> selectAllBooks(){
		ArrayList<BookVO> booklist  = new ArrayList<BookVO>();
	
		try {
			String sql = "select * from BOOK_TBL order by bcode asc";
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// rs.next row 한줄을 가져온다
				BookVO book = new BookVO();
				// student.setStuNo(rs.getInt(1)); 컬럼 순서
				book.setBcode(rs.getInt("bcode")); // 컬럼 이름으로 값 가져오기
				book.setBtitle(rs.getString("btitle"));
				book.setBwriter(rs.getString("bwriter"));
				book.setBpub(rs.getInt("bpub"));
				book.setBprice(rs.getInt("bprice"));
				book.setBdate(rs.getDate("bdate"));
				booklist.add(book);
			}
			System.out.println("테이블 출력 완료");

		} catch (Exception e) {
			System.out.println("booklist 오류");
			e.printStackTrace(); 
		} finally {
			close(rs,  pstmt, conn);
		}
		
		return booklist;
	}
	
//	도서코드
	public int getMaxCode() {
		int bcode = 0; 

		try {
			//커넥션
			conn = instance.getConnection();
			String sql = "SELECT MAX(BCODE) FROM BOOK_TBL";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bcode = rs.getInt(1) + 1;
			}
		} catch (Exception e) {
			System.out.print("getMaxCode() 오류");
			e.printStackTrace();
		} finally {
			//객체반환
			close(rs,  pstmt, conn);
		}
		return bcode;
	}
	
//	insert--------
	public int insertBook(BookVO vo) {
		int result = 0;
		
		try {
			conn = instance.getConnection();
			String insertsql = "INSERT INTO BOOK_TBL(bcode, btitle, bwriter, bpub, bprice, bdate) VALUES(?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(insertsql);
			pstmt.setInt(1, vo.getBcode());
			pstmt.setString(2, vo.getBtitle());
			pstmt.setString(3, vo.getBwriter());
			pstmt.setInt(4, vo.getBpub());
			pstmt.setInt(5, vo.getBprice());
			pstmt.setDate(6, vo.getBdate());
			
			result = pstmt.executeUpdate();
			System.out.println("회원추가 완료");
		} catch (Exception e) {
			System.out.println("insertBook() 오류");
			e.printStackTrace(); // 콘솔창에 에러 출력
		} finally {
			//객체반환
			close(null,  pstmt, conn);
		}
		return result;
	}
	
	//update--------
	public int updateBook(BookVO vo) {
		int result = 0;
		
		try {
			conn = instance.getConnection(); //오라클연동
			//회원정보를 수정해주기위해 쿼리문을 가져온다
			//updatsql구현
			String sql = "UPDATE BOOK_TBL SET BTITLE=?, BWRITER=?, BPUB=?, BPRICE=?, BDATE = ? WHERE BCODE=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBwriter());
			pstmt.setInt(3, vo.getBpub());
			pstmt.setInt(4, vo.getBprice());
			pstmt.setDate(5,  vo.getBdate());
			pstmt.setInt(6, vo.getBcode());
			result = pstmt.executeUpdate();
			System.out.println("도서수정 완료");
		} catch (Exception e) {
			System.out.println("getBookrList() 오류");
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		return result;
	}
	
//	select
	public BookVO getABook(int bcode) {
		BookVO book = null;
		
		try {
			conn = getConnection();
			String sql = "select * from BOOK_TBL where bcode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bcode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				book = new BookVO();
				
				book.setBcode(rs.getInt("bcode"));
				book.setBtitle(rs.getString("btitle"));
				book.setBwriter(rs.getString("bwriter"));
				book.setBpub(rs.getInt("bpub"));
				book.setBprice(rs.getInt("bprice"));
				book.setBdate(rs.getDate("bdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//객체반환
			close(rs, pstmt, conn);
		}
		return book;
	}
	
	public int deleteBook(int bcode) {
		int result = 0;
		
		try {
			Connection conn = getConnection();
			String sql = "DELETE FROM BOOK_TBL WHERE BCODE = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bcode);
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("deleteMember오류");
			e.printStackTrace();
		} finally {
			close(null,  pstmt, conn);
		}
		
		return result;
	}
	
//	jdbc 활용한 객체들 역순으로 자원반환
	//conn객체, preparedStatement객체, resultset객체를 생성했으니
	//나중에 생성한 객체부터 close해준다.
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) try {rs.close();} catch(Exception e) {e.printStackTrace();}
		if(pstmt != null) try {pstmt.close();} catch(Exception e) {e.printStackTrace();}
		if(conn != null) try {conn.close();} catch(Exception e) {e.printStackTrace();}
	}
}
