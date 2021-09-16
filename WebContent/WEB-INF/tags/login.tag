<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="control.jsp" method="post">
	<c:choose>
		<c:when test="${empty seUser}">
			<input type="hidden" name="action" value="login">
			<!-- 로그인 전 상태 글 계속 멈춰있도록 설정 -> control에 현재 mcnt값 넘겨줘서 유지시킴 -->
			<input type="hidden" name="mcnt" value="${mcnt}">

			<input type="text" name="memid">
			<input type="password" name="passwd">
			<input type="submit" value="로그인">
		</c:when>
		<c:otherwise>
      ${seUser}님, 환영합니다!
      <input type="hidden" name="action" value="logout">
			<input type="submit" value="로그아웃">
			<a href="control.jsp?action=main&selUser=${seUser}">내글목록보기</a>
			<br>
			<br>
			<!-- <a href="form.jsp">댓글 작성하기</a> -->
		</c:otherwise>
	</c:choose>
</form>