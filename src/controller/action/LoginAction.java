package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberVO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = null;

		// 1. uVO를 만들 수 있는 인자들을 get()
		// 2. 로그인 성공, 실패 여부 판단
		// 3. 성공했다면, session set / 실패 시, 스크립트 출력!
		
		/*	if (uDAO.login(uVO)) { // 로그인성공
			session.setAttribute("seUser", uVO.getMemid());
		 * */
		MemberDAO uDAO = new MemberDAO();
		MemberVO uVO = new MemberVO();
		uVO.setPasswd(request.getParameter("passwd"));
		uVO.setMemid(request.getParameter("memid"));
		
		if(uDAO.login(uVO)) {
			HttpSession session = request.getSession(); 
			// 세션가져오기 
			session.setAttribute("seUser", uVO.getMemid());
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main.do");
			
			
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 실패!!! 확인이 필요합니다.');history.go(-1);</script>");
		}
		

		return forward;
	}

}
