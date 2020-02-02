package com.woniu.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniu.daos.OrdersDao;
import com.woniu.entity.Orders;
import com.woniu.entity.PageInfo;

/**
 * Servlet implementation class OrdersServlet
 */
@WebServlet("/orders/order.do")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersServlet() {
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
		String path = request.getServletPath();
		OrdersDao od = new OrdersDao();
		if(path.equals("/orders/order.do")){
			try {
				String odCode = request.getParameter("orderCode");
				PageInfo<Orders> pageInfo = new PageInfo<>();
				int totalCount = od.getTotalCount(odCode);
				pageInfo.setTotalCount(totalCount);
				
				int pageSize = 5;
				String frontPageSize = request.getParameter("pageSize");
				if(frontPageSize!=null){
					pageSize=Integer.parseInt(frontPageSize);
				}
				pageInfo.setPageSize(pageSize);
				//当前页
				int currentPage = 1;
				String frontCurrentPage = request.getParameter("currentPage");
				if(frontCurrentPage!=null){
					currentPage=Integer.parseInt(frontCurrentPage);
				}
				if(currentPage<1){
					currentPage=1;
				}
				if(currentPage>pageInfo.getTotalPages()){
					currentPage = pageInfo.getTotalPages();
					if(pageInfo.getTotalPages()<0){
						currentPage=1;
					}
				}
				pageInfo.setCurrentPage(currentPage);
				
				List<Orders> li = od.selectOrders(odCode,pageInfo);
				pageInfo.setLi(li);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("orderCode", odCode);
				request.setAttribute("page", pageInfo);
				request.setAttribute("map", map);
				request.getRequestDispatcher("orders.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
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
