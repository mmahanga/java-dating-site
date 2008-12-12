package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.dao.UserDAO;
import db.entities.User;
import db.session.Facade;
import db.session.TransactionAction;

/**
 * Servlet implementation class for Servlet: ViewPersonalInfo
 *
 */
 public class ViewPersonalInfo extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ViewPersonalInfo() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
		Facade.doInTransaction(new TransactionAction(){

			@Override
			public void execute() {
				// TODO Auto-generated method stub
				String userName =(String) request.getSession().getAttribute("onlineUserName");
//				String userName = request.getParameter("username");
//				String password = request.getParameter("password");
				User user = UserDAO.getUserByName(userName);
				request.getSession().setAttribute("onlineUser", user);
				redirect(request, response,"/pages/viewProfile.jsp" );
//				if (user != null) {
//					if (user.getPassword().equals(password)){
//						UserDAO.setUserOnline(user, true);
//						request.getSession().setAttribute("onlineUserName", user.getFullName());
//						redirect(request, response,"/pages/homePersonal.jsp" );
//					} else {
//						redirect(request, response,"/pages/login.jsp" );
//					}
//				} else {
//					redirect(request, response,"/pages/register.jsp" );
//				}				
			}
			
		});

	} 
	protected void redirect(HttpServletRequest request,
			HttpServletResponse response, String location) {
		try {
			request.getRequestDispatcher(location).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}