<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>회원가입</h2>

<form action="control.jsp" method="post">
	<input type="hidden" name="action" value="join">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="memid"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="회원가입하기"></td>
		</tr>
	</table>
</form>


</body>
</html>