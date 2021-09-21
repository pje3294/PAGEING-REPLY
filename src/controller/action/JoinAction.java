package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class JoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = null;
		
		
		/*
		 * if (uDAO.insert(uVO)) {
			out.println("<script>alert('ȸ�����Լ���!');window.close();</script>");

		} else {
			throw new Exception("ȸ������ ���� �߻�");
		}
		 * */
		
		MemberDAO uDAO = new MemberDAO();
		MemberVO uVO = new MemberVO();
		
		//System.out.println(request.getParameter("memid"));
		uVO.setMemid(request.getParameter("memid"));
		//System.out.println(request.getParameter("name"));
		uVO.setName(request.getParameter("name"));
		//System.out.println(request.getParameter("passwd"));
		uVO.setPasswd(request.getParameter("passwd"));
		
		
		if (uDAO.insert(uVO)) {
			System.out.println("ȸ������ insert Ȯ�� ");
			
			// �ڹٽ�ũ��Ʈ �ȵ�,,,,?????
			PrintWriter out = response.getWriter();
			out.println("<script>alert('ȸ�����Լ���!');window.close();</script>");
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("main.do");

		} else {
			throw new IOException("ȸ������ ���� �߻�");
		}
		
		
		
		return forward;
	}
}
