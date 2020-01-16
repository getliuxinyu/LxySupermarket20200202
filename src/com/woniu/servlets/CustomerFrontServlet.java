package com.woniu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.woniu.daos.CustomersDao;
import com.woniu.entity.Customers;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/front/logon.do")
public class CustomerFrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerFrontServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doDispatcher(request,response);
	}

	private void doDispatcher(HttpServletRequest request, HttpServletResponse response) {
		CustomersDao cd = new CustomersDao();
		PrintWriter out =null; 
		try {
			String customerName = request.getParameter("cusName");
			String customerPwd = request.getParameter("cusPwd");
			Customers cus = new Customers(customerName, customerPwd);
	
			int customerId = cd.isExist(customerName,customerPwd);
			//将顾客姓名和顾客id存入session,登录展示需要顾客姓名。id后期添加购物车需要用
			out = response.getWriter();
			if(customerId!=0){
				HttpSession session = request.getSession();
				session.setAttribute("cusId", customerId);
				session.setAttribute("cusName", customerName);
				out.print(true);
			} else{
				out.print(false);
			}
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.flush();
			out.close();
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
