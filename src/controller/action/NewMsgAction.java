package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.msg.MessageDAO;
import model.msg.MessageVO;

public class NewMsgAction implements Action{

	/*if (mDAO.insert(mVO)) {
			response.sendRedirect(url);
		} else {
			throw new Exception("글작성 오류 발생");
		}
	 * */
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = null;
		
		MessageDAO mDAO = new MessageDAO();
		MessageVO mVO = new MessageVO();
		
		mVO.setMemid(request.getParameter("memid"));
		mVO.setMsg(request.getParameter("msg"));
		
		if (mDAO.insert(mVO)) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main.do");
		} else {
			throw new IOException("글작성에서 발생!");
		}
		
		
		
		return forward;
	}
	
}
