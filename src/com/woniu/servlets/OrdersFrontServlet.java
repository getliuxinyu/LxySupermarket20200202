package com.woniu.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniu.daos.OrdersDao;
import com.woniu.entity.Orders;

/**
 * 订单
 * Servlet implementation class OrdersServlet
 */
@WebServlet("/front/orderConfirm.do")
public class OrdersFrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersFrontServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("请求到达");
		OrdersDao od = new OrdersDao();
		String shIdArr=request.getParameter("shIdArr");
		String orderRemarks = request.getParameter("remarks");
		float orderPrice = Float.parseFloat(request.getParameter("price"));
		System.out.println(orderPrice);
		int customerId = (int) request.getSession().getAttribute("cusId");
		Orders order = new Orders(customerId, orderPrice, orderRemarks);
		try {
			od.confirmOrder(shIdArr,order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
