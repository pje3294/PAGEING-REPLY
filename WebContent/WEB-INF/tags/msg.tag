<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="mid"%>
<%@ attribute name="memid"%>


<%-- <a href="control.jsp?action=updatelike&mid=${mid}&mcnt=${mcnt}">👍</a> --%>
<a href="updatelike.do?mid=${mid}&mcnt=${mcnt}">👍</a>
<c:if test="${memid == seUser}">
	<a href="#" onclick="del(${mid})"> ▶삭제하기</a>
</c:if>