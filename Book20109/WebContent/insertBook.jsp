<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />

<br/>
<form method="post" action="./BookInsert.do">
	<table border="1" style="width: 400">
		<tr>
			<td colspan="2" align="center"><b>도서 등록</b></td>
		<tr>
			<th>도서코드</th>
			<td><input type="text" name="bcode" value="${maxCode}" readonly></td>
			<%-- el을 사용해 maxCode(번호 자동생성)를 가져온다--%>
			<%-- readonly를 사용해 수정할 수 없도록 한다 --%>
		</tr>
		<tr>
			<th>도서제목</th>
			<td><input type="text" name="btitle" placeholder="도서제목를 입력해주세요" required></td>
			<%--required로 유효성 검사를 해준다 --%>
		</tr>
		<tr>
			<th>도서저자</th>
			<td><input type="text" name="bwriter" placeholder="도서저자를 입력해주세요" required></td>
			<%--required로 유효성 검사를 해준다 --%>
		</tr>
		<tr>
			<th>출판사코드</th>
			<td><select name="bpub">
					<option value="1001">양영디지털</option>
					<option value="1002">북스미디어</option>
					<option value="1003">한빛아카데미</option>
					<option value="1004">이지스</option>
			</select></td>
		</tr>
		<tr>
			<th>가격</th>
			<td><input type="text" name="bprice" placeholder="가격을 입력해주세요" required></td>
			<%--required로 유효성 검사를 해준다 --%>
		</tr>
		<tr>
			<th>출간날짜</th>
			<td><input type="date" name="bdate" placeholder="출간날짜를 선택해주세요" required></td>
			<%--required로 유효성 검사를 해준다 --%>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="등록">
				<%-- 재작성을 할 경우에는 reset으로 다시 등록화면으로 이동한다 --%>
				<input type="reset" value="재작성">
			</td>
		</tr>
	</table>
</form>

<jsp:include page="footer.jsp" />