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



import com.woniu.daos.GoodsDao;
import com.woniu.daos.ShoppingsDao;
import com.woniu.entity.Goods;
import com.woniu.entity.PageInfo;

/**
 * Servlet implementation class GoodsFrontServlet
 */
@WebServlet("/front/goodsAll.do")
public class GoodsFrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsFrontServlet() {
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
		GoodsDao gd = new GoodsDao();
		if(path.equals("/front/goodsAll.do")){
			PageInfo<Goods> pageInfo = new PageInfo<>();
			PrintWriter out=null;
			try {
				
				//查询符合条件总条目数
				String tempCurrentPage = request.getParameter("currentPage");
				String goodsName = request.getParameter("goodsName");
				
				//封装入pageInfo对象
				int cusId = (int) request.getSession().getAttribute("cusId");
				Map<String, Integer> messAge = gd.getTotalCount(goodsName,cusId);
				int totalCount = messAge.get("total");
				pageInfo.setTotalCount(totalCount);
				
				//页面显示条目数
				int pageSize = 4;
				pageInfo.setPageSize(pageSize);
				//当前页默认为1
				int currentPage = 1;
				if(tempCurrentPage!=null){
					currentPage=Integer.parseInt(tempCurrentPage);
				}
				if(currentPage<1){
					currentPage=1;
				}
				if(currentPage>pageInfo.getTotalPages()){
					currentPage=pageInfo.getTotalPages();
					if(pageInfo.getTotalPages()<=0){
						currentPage=1;
					}
				}
				pageInfo.setCurrentPage(currentPage);
				
				//查询符合查询条件和分页信息的数据
				List<Goods> li = gd.goodsList(pageInfo,goodsName);
				pageInfo.setLi(li);
				
				Map<String, Object> map = new HashMap<>();
				//转换为json对象
				response.setContentType("text/html;charset=utf-8");
				
				String customerName = (String) request.getSession().getAttribute("cusName");
				
				int cusCount=messAge.get("count");
				map.put("cusCount", cusCount);
				map.put("cusName", customerName);
				map.put("goodsJson", pageInfo);
				JSONObject gs = new JSONObject(map);
				out = response.getWriter();
				out.print(gs);
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
