package com.woniu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.woniu.daos.StaffsDao;
import com.woniu.tools.MYMD5;

/**
 * Servlet implementation class LogonServlet
 */
@WebServlet({"/logon.do"})
public class LogonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doDispatcher(request,response);
		
	}
	

	private void doDispatcher(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
	
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		String sessionCode = (String) session.getAttribute("checkCode");
		String pageCode = request.getParameter("checkCode");
		if(sessionCode.equalsIgnoreCase((pageCode))){
			try {
				StaffsDao sd = new StaffsDao();
				String staffName = request.getParameter("userName");
				
				String staffPwd = request.getParameter("userPwd");
				//密码明文加密
				String newPwd = MYMD5.getMD5(staffPwd);
				//登陆成功将用户id存入session中，方便后期使用
				int isOk = sd.isSuccess(staffName,newPwd);
				session.setAttribute("staffId", isOk);
				session.setAttribute("uname", staffName);
				
				if(isOk!=0){
					response.sendRedirect("index.jsp");
				}else{
					response.sendRedirect("Logon.jsp");
				}
				
				//从session对象中获得验证码
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
