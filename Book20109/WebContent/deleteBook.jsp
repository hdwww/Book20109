<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page = "header.jsp"/>
<script>
	<%-- result가 0 이상이면 선택한 도서를 삭제 후 얼럿 창을 띄워준다--%>
	if(${result} > 0) {
		alert("도서 삭제 완료");
	}
	<%--substring을 이용해 0번째부터 / +1 까지 잘라서  경고창을 띄운후 원래 페이지로 이동해준다--%>
	location.href = location.href.substring(0, location.href.lastIndexOf("/") + 1) + "BookList.do";
</script>
<jsp:include page = "footer.jsp"/>