package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;
import model.msg.MessageDAO;
import model.msg.MsgSet;
import model.msg.ReplyDAO;
import model.msg.ReplyVO;

public class NewReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * if (rDAO.insert(rVO)) { response.sendRedirect(url); } else { throw new
		 * Exception("댓글달기 오류 발생"); }
		 */

		ActionForward forward = null;

		ReplyVO rVO = new ReplyVO();
		ReplyDAO rDAO = new ReplyDAO();

		/*
		 * <input type="hidden" name="mid" value="${m.mid }"> <input type="hidden"
		 * name="mcnt" value="${mcnt}"> <input type="hidden" name="memid"
		 * value="${seUser}"> <td><input type="text" name="rmsg"></td>
		 */

		rVO.setMid(Integer.parseInt(request.getParameter("mid")));
		rVO.setRmsg(request.getParameter("rmsg"));
		rVO.setMemid(request.getParameter("memid"));

		if (rDAO.insert(rVO)) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main.do");
		} else {
			throw new IOException("댓글달기 오류 발생");
		}

		return forward;

	}

}
