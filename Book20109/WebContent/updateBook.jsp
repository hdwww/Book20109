<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp" />
<form method="post" action="./BookUpdate.do">
	<table border="1" style="width: 400">
		<tr>
			<td colspan="2" align="center"><b>도서 정보 수정</b></td>
		<tr>
			<th>도서코드</th>
			<td><input type="text" name="bcode" value="${book.bcode}" readonly></td><!--  becode 값 가져옴-->
		</tr>
		<tr>
			<th>도서제목</th>
			<td><input type="text" name="btitle" value="${book.btitle}" required></td><!--  betitle 값 가져옴-->
		</tr>
		<tr>
			<th>도서저자</th>
			<td><input type="text" name="bwriter" value="${book.bwriter}" required></td><!--  bewriter 값 가져옴-->
		</tr>
		<tr>
			<th>출판사코드</th>
			<td><select name="bpub">
				<c:if test="${book.bpub == 1001}">
					<option value="1001" selected >양영디지털</option><!-- b-->
				</c:if>
				<c:if test="${book.bpub != 1001}">
					<option value="1001" >양영디지털</option>
				</c:if>
				
				<c:if test="${book.bpub == 1002}">
					<option value="1001" selected >북스미디어</option>
				</c:if>
				<c:if test="${book.bpub != 1002}">
					<option value="1001" >북스미디어</option>
				</c:if>
				
				<c:if test="${book.bpub == 1003}">
					<option value="1001" selected >한빛아카데미</option>
				</c:if>
				<c:if test="${book.bpub != 1004}">
					<option value="1001" >한빛아카데미</option>
				</c:if>
				
				<c:if test="${book.bpub == 1004}">
					<option value="1001" selected >이지스</option>
				</c:if>
				<c:if test="${book.bpub != 1004}">
					<option value="1001" >이지스</option>
				</c:if>
			</select></td>
		</tr>
		<tr>
			<th>가격</th>
			<td><input type="text" name="bprice" value="${book.bprice}"></td><!-- bprice 값 가져옴-->
		</tr>
		<tr>
			<th>출간날짜</th>
			<td><input type="date" name="bdate" value="${book.bdate}"></td><!--  bdate 값 가져옴-->
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="수정">
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>
</form>

<jsp:include page="footer.jsp" />