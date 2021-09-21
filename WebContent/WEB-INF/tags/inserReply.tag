<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="mid"%>

<c:if test="${not empty seUser }">
	<!-- <form action="control.jsp" method="post">
		<input type="hidden" name="action" value="newReply">  -->
	<form action="newReply.do" method="post">
		<input type="hidden" name="mid" value="${mid}"> 
		<input type="hidden" name="mcnt" value="${mcnt}"> 
		<input type="hidden" name="memid" value="${seUser}">
		<table border="1">
			<tr>
				<td>댓글</td>
				<td><input type="text" name="rmsg"></td>
				<td><input type="submit" value="댓글달기"></td>
			</tr>
		</table>
	</form>
</c:if>
