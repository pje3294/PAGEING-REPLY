package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.msg.MessageDAO;
import model.msg.MessageVO;

// 좋아요 누르기 액션 
public class LikeAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		ActionForward forward = new ActionForward();
		
		MessageVO mVO = new MessageVO();
		MessageDAO mDAO = new MessageDAO();
		
		mVO.setMid(Integer.parseInt(request.getParameter("mid")));
		
		
		mDAO.update(mVO);
		
		forward.setRedirect(false);
		forward.setPath("main.do");
		
		
		return forward;
	}
}

/*
 * System.out.println("업데이트컨트롤");
		mDAO.update(mVO);  
		response.sendRedirect(url);
 * */