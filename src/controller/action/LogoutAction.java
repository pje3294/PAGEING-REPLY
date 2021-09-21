package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		/*
		 * session.invalidate();
		response.sendRedirect("control.jsp?action=main");
		 * */
		
		HttpSession session = request.getSession(); // 세션 가져오기 
		session.invalidate();
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false); 
		forward.setPath("main.do");
		
		
		
		return forward;
	}
			
}
