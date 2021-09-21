package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.msg.MessageDAO;
import model.msg.MessageVO;

public class DeleteAction implements Action{

	/*
	 * if (mDAO.delete(mVO)) {
			response.sendRedirect(url);
		} else {
			throw new Exception("글 삭제 오류 발생");
		}
	 * */
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = null;
		
		MessageDAO mDAO = new MessageDAO();
		MessageVO mVO = new MessageVO();
		
		mVO.setMid(Integer.parseInt(request.getParameter("mid")));
		
		
		if (mDAO.delete(mVO)) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main.do");
		} else {
			throw new IOException("글 삭제 오류 발생");
		}
		
		
		return forward;
	}
	
}
