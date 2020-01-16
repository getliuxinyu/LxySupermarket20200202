package com.woniu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;

import com.woniu.daos.ShoppingsDao;
import com.woniu.entity.Shoppings;

/**
 * 购物车
 * Servlet implementation class ShoppingFrontServlet
 */
@WebServlet({"/front/shopping.do","/front/getShopping.do","/front/changeCount.do","/front/getCheckShopping.do","/front/cleanShopping.do"})
public class ShoppingFrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingFrontServlet() {
        super();
        // TODO Auto-generated constructor stub
     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		PrintWriter out = response.getWriter();
		//增加购物车
		ShoppingsDao shopDao = new ShoppingsDao();
		if(path.equals("/front/shopping.do")){
			
			int goodsId = Integer.parseInt(request.getParameter("goodsId"));
			int customerId=0;
			int seCusId = (int) request.getSession().getAttribute("cusId");
			
			if(seCusId!=0){
				customerId = (int)request.getSession().getAttribute("cusId");
			}
			
			try {
				Shoppings shop = new Shoppings(goodsId, customerId);
				int shoppingCount = shopDao.selectShopping(shop);
				out.print(shoppingCount);
				out.flush();
				out.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.flush();
				out.close();
			}
		}
		//购物车展示
		else if(path.equals("/front/getShopping.do")){
			int customerId = (int)request.getSession().getAttribute("cusId");
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				List<Shoppings> li = shopDao.selectById(customerId);
				String cusName = (String) request.getSession().getAttribute("cusName");
				map.put("shopList", li);
				map.put("cusName", cusName);
				JSONObject shopJson = new JSONObject(map);
				out.print(shopJson);
				out.flush();
				out.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.flush();
				out.close();
			}
		}
		//更改该用户选中商品的购物车数量
		else if(path.equals("/front/changeCount.do")){
		
			String shoppingId = request.getParameter("shoppingId");
			String shoppingCount = request.getParameter("shoppingCount");
			Shoppings shop = new Shoppings();
			shop.setShoppingId(Integer.parseInt(shoppingId));
			shop.setShoppingCount(Integer.parseInt(shoppingCount));
			try {
				shopDao.updateCount(shop);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//确认订单页面商品展示
		else if(path.equals("/front/getCheckShopping.do")){
			String shId = request.getParameter("shIdArr");
			List<Shoppings> li;
			String cusName=(String) request.getSession().getAttribute("cusName");
			Map<String, Object> map = new HashMap<>();
			try {
				li = shopDao.shoppingConfirm(shId);
				map.put("shopList", li);
				map.put("cuaName", cusName);
				JSONObject shopJson = new JSONObject(map);
				out.print(shopJson);
				out.flush();
				out.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.flush();
				out.close();
			}
		}
		//清空购物车
		else if(path.equals("/front/cleanShopping.do")){
			try {
				shopDao.cleanShopping();
				response.sendRedirect("shopping.html");
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
