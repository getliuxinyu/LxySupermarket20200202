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

import com.woniu.daos.CustomersDao;
import com.woniu.entity.Customers;
import com.woniu.entity.PageInfo;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet({"/customers/customer.do","/customers/customersUpdUI.do","/customers/customersUpd.do","/customers/customersReset.do","/customers/customersInactive.do","/customers/customersActive.do"})
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		CustomersDao cd = new CustomersDao();
		if(path.equals("/customers/customer.do")){
			try {
				PageInfo<Customers> pageInfo = new PageInfo<>();
				String cusName = request.getParameter("cusName");
				int totalCount = cd.selectTotalCount(cusName);
				pageInfo.setTotalCount(totalCount);
				
				int pageSize = 3;
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
				
				
				List<Customers> li = cd.selectCustomers(cusName,pageInfo);
				pageInfo.setLi(li);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cusName", cusName);
				request.setAttribute("map", map);
				request.setAttribute("PageInfo", pageInfo);
				request.getRequestDispatcher("customers.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/customers/customersUpdUI.do")){
			try {
				int cusId = Integer.parseInt(request.getParameter("cusId"));
				Customers cs = cd.selectById(cusId);
				request.setAttribute("customer", cs);
				request.getRequestDispatcher("customersUpd.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/customers/customersUpd.do")){
			try {
				String customerName = request.getParameter("cusName");
				String customerPhone = request.getParameter("cusPhone");
				String customerAddress = request.getParameter("cusAddress");
				int customerId = Integer.parseInt(request.getParameter("cusId"));
				Customers cs = new Customers(customerId, customerName, customerPhone, customerAddress);
				cd.updateCustomer(cs);
				response.sendRedirect("customer.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/customers/customersInactive.do")){
			try {
				int customerId = Integer.parseInt(request.getParameter("cusId"));
				cd.updateCustomer(customerId);
				response.sendRedirect("customer.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/customers/customersReset.do")){
			try {
				int customerId = Integer.parseInt(request.getParameter("cusId"));
				cd.resetCustomer(customerId);
				response.sendRedirect("customer.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/customers/customersActive.do")){
			try {
				int customerId = Integer.parseInt(request.getParameter("cusId"));
				cd.activeCustomer(customerId);
				response.sendRedirect("customer.do");
			} catch (SQLException e) {
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
