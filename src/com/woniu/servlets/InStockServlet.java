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
import com.woniu.daos.InStockDao;
import com.woniu.entity.Goods;
import com.woniu.entity.InStock;
import com.woniu.entity.OutStock;
import com.woniu.entity.PageInfo;

/**
 * Servlet implementation class InStockServlet
 */
@WebServlet({"/instock/in.do","/instock/instockAdd.do","/instock/inConf.do","/instock/inDel.do","/instock/inUpdUI.do","/instock/instockUpd.do"})
public class InStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InStockServlet() {
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
		InStockDao id = new InStockDao();
		PageInfo<InStock> pageInfo = new PageInfo<>();
		HttpSession session = request.getSession();
		if(path.equals("/instock/in.do")){
			try {
				String inCode = request.getParameter("inCode");
				int totalCount = id.getTotalCount(inCode);
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
				
				List<InStock> li = id.selectInStock(inCode,pageInfo);
				pageInfo.setLi(li);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("inCode", inCode);
				request.setAttribute("page", pageInfo);
				request.setAttribute("map", map);
				request.getRequestDispatcher("instock.jsp").forward(request, response);
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
		else if(path.equals("/instock/instockAdd.do")){
			try {
				String instockCode = request.getParameter("inCode");
				String instockCount = request.getParameter("inCount");
				String instockStatus = request.getParameter("inStatus");
				String goodsId = request.getParameter("goodsId");
				int staffId = (int) session.getAttribute("staffId");
				InStock in = new InStock(Integer.parseInt(goodsId), staffId, instockCode, instockStatus, Integer.parseInt(instockCount));
				id.addInStock(in);
				response.sendRedirect("in.do");
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/instock/inConf.do")){
			try {
				int inId = Integer.parseInt(request.getParameter("inId"));
				int instockCount = Integer.parseInt(request.getParameter("inCount"));
				int goodsId = Integer.parseInt(request.getParameter("goodsId"));
				InStock in = new InStock(inId, goodsId, instockCount);
				id.confirmInStock(in);
				response.sendRedirect("in.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/instock/inDel.do")){
			try {
				int inId = Integer.parseInt(request.getParameter("inId"));
				id.deleteInStock(inId);
				response.sendRedirect("in.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/instock/inUpdUI.do")){
			try {
				int inId = Integer.parseInt(request.getParameter("inId"));
				InStock in = id.selectById(inId);
				System.out.println(in);
				request.setAttribute("in", in);
				GoodsDao gd = new GoodsDao();
				List<Goods> li = gd.selectGoods();
				request.setAttribute("goods", li);
				request.getRequestDispatcher("instockUpd.jsp").forward(request, response);
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
		else if(path.equals("/instock/instockUpd.do")){
			try {
				int inId = Integer.parseInt(request.getParameter("inId"));
				int instockCount = Integer.parseInt(request.getParameter("inCount"));
				int goodsId = Integer.parseInt(request.getParameter("goodsId"));
				String instockCode = request.getParameter("inCode");
				String instockStatus = request.getParameter("inStatus");
				InStock in = new InStock(inId, goodsId, instockCode, instockCount);
				in.setInstockStatus(instockStatus);
				id.updateInStock(in);
				response.sendRedirect("in.do");
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
