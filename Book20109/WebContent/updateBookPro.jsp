<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	if(${result} > 0) {
	<%-- 모든 항목을 입력한 후 수정 버튼을 누르면, '도서 정보 수정 성공' 이라는 알림창과 함께 데이터베이스의 도서테이블에 수정.실패경우 메시지를 띄워준다
	 el 태그를 이용해 result가 0보다 작을경우 도서 정보 수정 성공 얼럿을 띄워준다--%>
		alert("도서 정보 수정 성공");
		location.href = location.href.substring(0, location.href.lastIndexOf("/") + 1) + "BookList.do";
		<%--서브스트링으로 0부터 / +1 까지 잘라준후 booklist.do로 이동한다--%>
	} else {
		alert("도서 정보 수정 실패");
		history.back();
		<%--back을 이용해 페이지로 돌아간다
		 --%>
	}
</script>