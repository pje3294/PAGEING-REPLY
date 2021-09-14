<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

	<ol>
		<li><a href="control.jsp?action=main">전체목록보기</a></li>
	</ol>

	<hr>

	<h2>전체목록</h2>
	<c:forEach var="v" items="${datas}">
		<c:set var="m" value="${v.m}" />
		<h3>[${m.memid}] ${m.msg} &gt;&gt; [좋아요 ${m.favcount} | 댓글
			${m.replycount} | ${m.day}]</h3>
		<ol>

			<c:forEach var="r" items="${v.rlist}">
				<li>${r.memid}>>${r.rmsg}[${r.day}]</li>
			</c:forEach>
		</ol>
		
		<!-- 로그인 시에만 댓글달기 보이도록  -->
		<c:if test="${not empty selUser }">
			<form action="control.jsp" method="post">
				<input type="hidden" name="action" value="newReply"> 
				<input type="hidden" name="mid" value="${m.mid }">
					<input type="hidden" name="mcnt" value="${mcnt}">
				<table>
					<tr>
						<td>작성자</td>
						<td><input type="text" name="memid"></td>
					</tr>
					<tr>
						<td>댓글</td>
						<td><input type="text" name="rmsg"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="댓글달기"></td>
					</tr>
				</table>
			</form>
		</c:if>

	</c:forEach>

	<hr>
	<!-- 기존 글에 1개씩 더보여줌  -->
	<a href="control.jsp?action=main&mcnt=${mcnt+1}&selUser=${param.selUser}">더보기&gt;&gt;</a>

	<hr>

	<a href="insertUser.jsp">회원가입</a>
	<br>
	<br>
	<form action="control.jsp" method="post">
		<c:choose>
			<c:when test="${empty selUser}">
				<input type="hidden" name="action" value="login">
				<!-- 로그인 전 상태 글 계속 멈춰있도록 설정 -> control에 현재 mcnt값 넘겨줘서 유지시킴 -->
				<input type="hidden" name="mcnt" value="${mcnt}"> 
				
				<input type="text" name="memid">
				<input type="password" name="passwd">
				<input type="submit" value="로그인">
			</c:when>
			<c:otherwise>
      ${selUser}님, 환영합니다!
      <input type="hidden" name="action" value="logout">
				<input type="submit" value="로그아웃">
				<a href="control.jsp?action=main&selUser=${selUser}">내글목록보기</a>
				<br>
				<br>
				<a href="form.jsp">글 작성하기</a>
			</c:otherwise>
		</c:choose>
	</form>



</body>
</html>
