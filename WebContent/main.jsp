<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function openWin(){
		window.open("insertUser.jsp","회원가입",'top=10, left=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no');
	}

	
	function del(mid){
		
		check = confirm("해당 글을 영구적으로 삭제하시겠습니까?");
		if (check == true) {
			document.location.href = "control.jsp?action=delete&mid="+mid+"&mcnt="+${mcnt};
			
		} else {
			return;
		}
	}
	
	function redel(rid){
		
		check = confirm("해당 글을 영구적으로 삭제하시겠습니까?");
		if (check == true) {
			document.location.href = "control.jsp?action=redelete&rid="+rid+"&mcnt="+${mcnt};
			
		} else {
			return;
		}
	}r
</script>

</head>
<body>

	<ol>
		<li><a href="control.jsp?action=main">전체목록보기</a></li>
	</ol>

	<hr>

	<h2>전체목록</h2>

	<c:forEach var="v" items="${datas}">
		<c:set var="m" value="${v.m}" />

			<h3>
				[${m.memid}] ${m.msg} &gt;&gt; [좋아요 ${m.favcount} | 댓글
				${m.replycount} | ${m.day}]
				<c:if test="${m.memid == seUser}">
					<a href="#" onclick="del(${m.mid})"> ▶삭제하기</a>
				</c:if>

			</h3>
			<ol>

				<c:forEach var="r" items="${v.rlist}">
					<li>${r.memid}>>${r.rmsg}[${r.day}]<c:if
							test="${r.memid == seUser}">
							<a href="#" onclick="redel(${r.rid})"> ▶삭제하기</a>
						</c:if></li>

				</c:forEach>
			</ol>

		<!-- 로그인 시에만 댓글달기 보이도록  -->
		<c:if test="${not empty seUser }">
			<form action="control.jsp" method="post">
				<input type="hidden" name="action" value="newReply"> <input
					type="hidden" name="mid" value="${m.mid }"> <input
					type="hidden" name="mcnt" value="${mcnt}">
				<table border="1">
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
	<a href="control.jsp?action=main&mcnt=${mcnt+1}&selUser=${selUser}">더보기&gt;&gt;</a>

	<hr>

	<a href="#" onclick="openWin();">회원가입</a>
	<br>
	<br>
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

	<br>
	<hr>
	<h4>메세지 작성</h4>
	<c:if test="${seUser == null}">
		<input type="text" size="25" disabled value="로그인이 필요한 서비스입니다!">
	</c:if>
	<c:if test="${seUser != null}">
		<form action="control.jsp" method="post">
			<input type="hidden" name="action" value="newMsg"> <input
				type="hidden" name="memid" value="${seUser }"> <input
				type="hidden" name="mcnt" value="${mcnt}">
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


</body>
</html>
