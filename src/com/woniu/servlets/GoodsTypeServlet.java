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

import com.woniu.daos.GoodsTypeDao;
import com.woniu.entity.GoodsType;
import com.woniu.entity.PageInfo;

/**
 * Servlet implementation class GoodsTypeServlet
 */
@WebServlet({"/goodstype/gt.do","/goodstype/goodstypeAdd.do","/goodstype/goodstypeConf.do","/goodstype/goodstypeDel.do","/goodstype/goodstypeUpdUI.do","/goodstype/goodstypeUpd.do"})
public class GoodsTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		GoodsTypeDao gd = new GoodsTypeDao();
		PageInfo<GoodsType> pageInfo = new PageInfo<>();
		if(path.equals("/goodstype/gt.do")){
			try {
				String goodsTypeCode = request.getParameter("gsCode");
				String goodsTypeName = request.getParameter("gsName");
				int totalCount = gd.getTotalCount(goodsTypeCode,goodsTypeName);
				pageInfo.setTotalCount(totalCount);
				
				int pageSize = 3;
				String tempPageSize = request.getParameter("pageSize");
				if(tempPageSize!=null){
					pageSize=Integer.parseInt(tempPageSize);
				}
				pageInfo.setPageSize(pageSize);
				
				int currentPage = 1;
				String tempcurrentPage = request.getParameter("currentPage");
				if(tempcurrentPage!=null){
					currentPage = Integer.parseInt(tempcurrentPage);
				}
				if(currentPage < 1){
					currentPage=1;
				}
				if(currentPage > pageInfo.getTotalPages()){
					currentPage = pageInfo.getTotalPages();
					if(currentPage==0){
						currentPage=1;
					}
				}
				pageInfo.setCurrentPage(currentPage);
				
				List<GoodsType> li = gd.selectGoodsType(goodsTypeCode,goodsTypeName,pageInfo);
			
				pageInfo.setLi(li);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("gscode", goodsTypeCode);
				map.put("gsname", goodsTypeName);
				map.put("page", pageInfo);
				request.setAttribute("map", map);
				request.getRequestDispatcher("goodstype.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/goodstype/goodstypeAdd.do")){
			
			try {
				String gsCode = request.getParameter("goodsTypeCode");
				String gsName = request.getParameter("goodsTypeName");
				gd.addGoodsType(gsCode,gsName);
				response.sendRedirect("gt.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/goodstype/goodstypeConf.do")){
			try {
				int gsId = Integer.parseInt(request.getParameter("goodstypeId"));
				gd.confirmGoodsType(gsId);
				response.sendRedirect("gt.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/goodstype/goodstypeDel.do")){
			try {
				int gsId = Integer.parseInt(request.getParameter("goodstypeId"));
				gd.deleteGoodsType(gsId);
				response.sendRedirect("gt.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/goodstype/goodstypeUpdUI.do")){
			try {
				int gsId = Integer.parseInt(request.getParameter("goodstypeId"));
				GoodsType gt = gd.selectById(gsId);
				request.setAttribute("goodstype", gt);
				request.getRequestDispatcher("goodstypeUpd.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/goodstype/goodstypeUpd.do")){
			try {
				int gsId = Integer.parseInt(request.getParameter("goodstypeId"));
				String gsCode = request.getParameter("goodsTypeCode");
				String gsName = request.getParameter("goodsTypeName");
				gd.updateGoodsType(gsCode,gsName,gsId);
				response.sendRedirect("gt.do");
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
