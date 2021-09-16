<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, model.msg.*,model.member.*"%>
<jsp:useBean id="mDAO" class="model.msg.MessageDAO" />
<jsp:useBean id="rDAO" class="model.msg.ReplyDAO" />
<jsp:useBean id="uDAO" class="model.member.MemberDAO" />
<jsp:useBean id="mVO" class="model.msg.MessageVO" />
<jsp:setProperty property="*" name="mVO" />
<jsp:useBean id="rVO" class="model.msg.ReplyVO" />
<jsp:setProperty property="*" name="rVO" />
<jsp:useBean id="uVO" class="model.member.MemberVO" />
<jsp:setProperty property="*" name="uVO" />

<%
	String action = request.getParameter("action");
	String url = "control.jsp?action=main";

	//페이징 위해 (페이지유지의 핵심!)
	String mcntt = request.getParameter("mcnt");
	int mcnt = 0; // 처음에 글 0개 보여주기
	if (mcntt != null) {
		mcnt = Integer.parseInt(mcntt);
	}
	url = url + "&mcnt=" + mcnt;
	String selUser = request.getParameter("selUser");
	if (selUser != null) {
		url = url + "&selUser=" + selUser; //selUser : 내글보기 / 검색한 사용자 보기 
	}
	if (action.equals("main")) {
		ArrayList<MsgSet> datas = mDAO.selectAll(selUser, mcnt);
		ArrayList<MemberVO> newUsers = uDAO.selectAll();

		request.setAttribute("datas", datas);
		request.setAttribute("newUsers", newUsers);
		request.setAttribute("selUser", selUser);
		request.setAttribute("mcnt", mcnt);

		pageContext.forward("main.jsp");

	} else if (action.equals("login")) {
		if (uDAO.login(uVO)) { // 로그인성공
			session.setAttribute("seUser", uVO.getMemid());
			response.sendRedirect(url);
		} else {
			out.println("<script>alert('로그인 실패!!! 확인이 필요합니다.');history.go(-1);</script>");
		}
	} else if (action.equals("logout")) {
		session.invalidate();
		response.sendRedirect("control.jsp?action=main");

	} else if (action.equals("join")) {
		if (uDAO.insert(uVO)) {
			out.println("<script>alert('회원가입성공!');window.close();</script>");

		} else {
			throw new Exception("회원가입 오류 발생");
		}
	} else if (action.equals("newMsg")) {
		if (mDAO.insert(mVO)) {
			response.sendRedirect(url);
		} else {
			throw new Exception("글작성 오류 발생");
		}
	} else if (action.equals("newReply")) {
		if (rDAO.insert(rVO)) {

			response.sendRedirect(url);
		} else {
			throw new Exception("댓글달기 오류 발생");
		}
	} else if (action.equals("delete")) {

		if (mDAO.delete(mVO)) {
			response.sendRedirect(url);
		} else {
			throw new Exception("글 삭제 오류 발생");
		}

	} else if (action.equals("redelete")) {
		System.out.println("redelte 입장");
		if (rDAO.delete(rVO)) {
			response.sendRedirect(url);
		} else {
			throw new Exception("댓글 삭제 오류 발생");
		}

	} else if (action.equals("updatelike")) {
		System.out.println("업데이트컨트롤");
		mDAO.update(mVO);  
		response.sendRedirect(url);
	}
	
	
	
	
	
	
%>