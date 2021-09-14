<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h2>글 작성하기</h2>
	<form action="control.jsp" method="post">
	<input type="hidden" name="action" value="newMsg"> 
	<input type="hidden" name="memid" value="${selUser }">
		<table>
			<tr>
				<td>작성자</td>
				<td>${selUser}</td>
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


</body>
</html>