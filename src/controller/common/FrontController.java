package controller.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.ActionForward;
import controller.action.DeleteAction;
import controller.action.JoinAction;
import controller.action.LikeAction;
import controller.action.LoginAction;
import controller.action.LogoutAction;
import controller.action.MainAction;
import controller.action.NewMsgAction;
import controller.action.NewReplyAction;
import controller.action.ReDeleteAction;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}
	
	
	/////////////////////////////////////
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자의 요청(액션 파라미터)을 분석
		String uri = request.getRequestURI();  
		
		ActionForward forward = null; // "패이지 전달방식"과 "경로"를 매개변수로 하는 객체
		
		// 2. context제거 
		String cp = request.getContextPath();
		String action = uri.substring(cp.length());
		
		// 3. 컨트롤러랑 매핑 
		if(action.equals("/main.do")) {
			forward = new MainAction().execute(request, response);
			
		}else if(action.equals("/login.do")) {
			forward = new LoginAction().execute(request, response);
		}
		else if(action.equals("/logout.do")) {
			forward = new LogoutAction().execute(request, response);
		}else if(action.equals("/newMsg.do")) {
			forward = new NewMsgAction().execute(request, response);
		}else if(action.equals("/newReply.do")) {
			forward = new NewReplyAction().execute(request, response);
		}else if(action.equals("/updatelike.do")) {
			forward = new LikeAction().execute(request, response);
		}else if(action.equals("/join.do")) {
			forward = new JoinAction().execute(request, response);
		}else if(action.equals("/delete.do")) {
			forward = new DeleteAction().execute(request, response);
		}else if(action.equals("/redelete.do")) {
			forward = new ReDeleteAction().execute(request, response);
		}
		
		else {
			// 에러페이지랑 연결
			forward= new ActionForward();
			forward.setRedirect(false); //어떤 에러가 발생했는지 분석할 경우 false 많이 사용
			forward.setPath("/error/error404.jsp");
		}
		
		// 4. 사용자에게 결과화면 출력
		if(forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request,response);
		}
		
		
	}
	
	
	
	
	

}
