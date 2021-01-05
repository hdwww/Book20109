<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<h2>회원 목록</h2>
<table border="1">
	<tr align="center">
		<th width="100">도서코드</th>	
		<th width="100">도서제목</th>
		<th width="100">도서저자</th>
		<th width="100">출판사</th>
		<th width="100">가격</th>
		<th width="100">출간날짜</th>
		<th width="50">삭제</th>
	</tr>
		
	<c:forEach var="book" items="${list}">
	<tr align="center">
		<td width="100"><a href="./BookUpdate.do?bcode=${book.bcode}">${book.bcode}</a></td>
		<td width="100">${book.btitle}</td>
		<td width="100">${book.bwriter}</td>
		<td width="100">
		<%--출판사코드에 맞는 출판사를 띄우는 작업 --%>
			<c:if test="${book.bpub == 1001}">
			 	양영디지털
			</c:if>
			<c:if test="${book.bpub == 1002}">
				북스미디어
			</c:if>
			<c:if test="${book.bpub == 1003}">
				한빛아카데미
			</c:if>
			<c:if test="${book.bpub == 1004}">
				이지스
			</c:if>
		</td>
		<%-- pattern "#,###"을 이용하여 도서 가격에 , 을 찍어준다 --%>
		<td width="100"><fmt:formatNumber value="${book.bprice}" pattern="#,###" /></td>
		<%-- pattern "yyyy년 MM월 dd일"을 이용하여 String 형태로 바꿔준다 --%>
		<td width="150"><fmt:formatDate value="${book.bdate}" pattern="yyyy년 MM월 dd일" /></td>
		<td width="100"><a href="javascript:deleteBook(${book.bcode});">삭제</a></td>
	</tr>
	</c:forEach>
</table>

<script>
	function deleteBook(bcode) {
		<%-- confirm으로 정말 삭제할건지 확인 한 후 삭제시켜준다--%>
		if(confirm("정말 삭제하시겠습니까?")) {
			location.href = location.href.substring(0, location.href.lastIndexOf("/") + 1) + "BookDelete.do?bcode=" + bcode;
		}
	}
</script>

<jsp:include page="footer.jsp"/>