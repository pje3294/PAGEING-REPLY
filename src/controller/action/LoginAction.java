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

		// 1. uVO�� ���� �� �ִ� ���ڵ��� get()
		// 2. �α��� ����, ���� ���� �Ǵ�
		// 3. �����ߴٸ�, session set / ���� ��, ��ũ��Ʈ ���!
		
		/*	if (uDAO.login(uVO)) { // �α��μ���
			session.setAttribute("seUser", uVO.getMemid());
		 * */
		MemberDAO uDAO = new MemberDAO();
		MemberVO uVO = new MemberVO();
		uVO.setPasswd(request.getParameter("passwd"));
		uVO.setMemid(request.getParameter("memid"));
		
		if(uDAO.login(uVO)) {
			HttpSession session = request.getSession(); 
			// ���ǰ������� 
			session.setAttribute("seUser", uVO.getMemid());
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main.do");
			
			
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�α��� ����!!! Ȯ���� �ʿ��մϴ�.');history.go(-1);</script>");
		}
		

		return forward;
	}

}
