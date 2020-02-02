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
import javax.servlet.http.HttpSession;

import com.woniu.daos.GoodsDao;
import com.woniu.daos.OutStockDao;
import com.woniu.entity.Goods;
import com.woniu.entity.OutStock;
import com.woniu.entity.PageInfo;

/**
 * Servlet implementation class OutStockServlet
 */
@WebServlet({"/outstock/out.do","/outstock/outstockAdd.do","/outstock/outsConf.do","/outstock/outsUpdUI.do","/outstock/outstockUpd.do","/outstock/outsDel.do"})
public class OutStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutStockServlet() {
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
		OutStockDao od = new OutStockDao();
		HttpSession session = request.getSession();
		if(path.equals("/outstock/out.do")){
			try {
				PageInfo<OutStock> pageInfo = new PageInfo<>();
				String outsCode = request.getParameter("outsCode");
				int totalCount = od.getTotalCount(outsCode);
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
				
				List<OutStock> li = od.selectOutStock(outsCode,pageInfo);
				pageInfo.setLi(li);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("outsCode", outsCode);
				request.setAttribute("page", pageInfo);
				request.setAttribute("map", map);
				request.getRequestDispatcher("outstock.jsp").forward(request, response);
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
		else if(path.equals("/outstock/outstockAdd.do")){
			try {
				String outstockCode = request.getParameter("outsCode");
				String outstockCount = request.getParameter("outsCount");
				String outstockStatus = request.getParameter("outsStatus");
				String goodsId = request.getParameter("goodsId");
				int staffId = (int) session.getAttribute("staffId");
				OutStock outs = new OutStock(Integer.parseInt(goodsId), staffId, outstockCode, Integer.parseInt(outstockCount), outstockStatus);
				od.addOutStock(outs);
				response.sendRedirect("out.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/outstock/outsConf.do")){
			try {
				String outstockCount = request.getParameter("outsCount");
				String goodsId = request.getParameter("goodsId");
				int outstockId = Integer.parseInt(request.getParameter("outsId"));
				OutStock outs = new OutStock(outstockId, Integer.parseInt(goodsId), Integer.parseInt(outstockCount));
				od.confirmOuts(outs);
				response.sendRedirect("out.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/outstock/outsUpdUI.do")){
			try {
				int outsId = Integer.parseInt(request.getParameter("outsId"));
				OutStock outs = od.selectById(outsId);
				request.setAttribute("outs", outs);
				GoodsDao gd = new GoodsDao();
				List<Goods> li = gd.selectGoods();
				request.setAttribute("goods", li);
				request.getRequestDispatcher("outstockUpd.jsp").forward(request, response);
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
		else if(path.equals("/outstock/outstockUpd.do")){
			try {
				String outstockCode = request.getParameter("outsCode");
				String outstockCount = request.getParameter("outsCount");
				String outstockStatus = request.getParameter("outsStatus");
				String goodsId = request.getParameter("goodsId");
				int outstockId = Integer.parseInt(request.getParameter("outsId"));
				OutStock outs = new OutStock(Integer.parseInt(goodsId), outstockCode, Integer.parseInt(outstockCount), outstockStatus);
				outs.setOutstockId(outstockId);
				od.updateOuts(outs);
				response.sendRedirect("out.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/outstock/outsDel.do")){
			try {
				int outstockId = Integer.parseInt(request.getParameter("outsId"));
				od.deleteOuts(outstockId);
				response.sendRedirect("out.do");
			} catch (SQLException e) {
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
