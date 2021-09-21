<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${seUser == null}">
	<input type="text" size="25" disabled value="로그인이 필요한 서비스입니다!">
</c:if>
<c:if test="${seUser != null}">
	<!-- <form action="control.jsp" method="post">
		<input type="hidden" name="action" value="newMsg">  -->
	<form action="newMsg.do" method="post">
		<input type="hidden" name="memid" value="${seUser }"> 
		<input type="hidden" name="mcnt" value="${mcnt}">
		<table border="1">
			<tr>
				<td>작성자</td>
				<td>${seUser}</td>
			</tr>
			<tr>
				<td>메세지</td>
				<td><input type="text" name="msg"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="메세지작성"></td>
			</tr>
		</table>
	</form>
</c:if>
