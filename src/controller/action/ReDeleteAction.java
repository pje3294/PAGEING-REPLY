package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.msg.ReplyDAO;
import model.msg.ReplyVO;

public class ReDeleteAction implements Action{

	
	/*
	 * System.out.println("redelte ����");
		if (rDAO.delete(rVO)) {
			response.sendRedirect(url);
		} else {
			throw new Exception("��� ���� ���� �߻�");
		}
	 * */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = null;
		
		ReplyDAO rDAO = new ReplyDAO();
		ReplyVO rVO = new ReplyVO();
		
		rVO.setRid(Integer.parseInt(request.getParameter("rid")));
		rVO.setMid(Integer.parseInt(request.getParameter("mid")));
		
		
		
		if (rDAO.delete(rVO)) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main.do");
			
		} else {
			throw new IOException("��� ���� ���� �߻�");
		}
		
		return forward;
	}
	
	
}
