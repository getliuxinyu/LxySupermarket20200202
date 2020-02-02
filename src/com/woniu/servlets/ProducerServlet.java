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

import com.woniu.daos.ProducerDao;
import com.woniu.entity.PageInfo;
import com.woniu.entity.Producer;

/**
 * Servlet implementation class ProducerServlet
 */
@WebServlet({"/producer/pro.do","/producer/producerAdd.do","/producer/producerDel.do","/producer/producerUpdUI.do","/producer/producerUpd.do"})
public class ProducerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProducerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		ProducerDao pd = new ProducerDao();
		if(path.equals("/producer/pro.do")){
			try {
				String pCode = request.getParameter("proCode");
				String pName = request.getParameter("proName");
				PageInfo<Producer> pageInfo = new PageInfo<>();
				int totalCount = pd.getTotalCount(pCode,pName);
				
				pageInfo.setTotalCount(totalCount);
				
				int pageSize = 3;
				String tempPageSize = request.getParameter("pageSize");
				if(tempPageSize!=null){
					pageSize = Integer.parseInt(tempPageSize);
				}
				
				pageInfo.setPageSize(pageSize);
				
				int currentPage = 1;
				String tempCurrentPage = request.getParameter("currentPage");
				if(tempCurrentPage!=null){
					currentPage = Integer.parseInt(tempCurrentPage);
					
				}
				if(currentPage<1){
					currentPage = 1;
				}
				if(currentPage > pageInfo.getTotalPages()){
					currentPage = pageInfo.getTotalPages();
					if(currentPage==0){
						currentPage=1;
					}
				}
				
				pageInfo.setCurrentPage(currentPage);
				
				List<Producer> li = pd.selectProducer(pCode,pName,pageInfo);
				pageInfo.setLi(li);
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("proCode", pCode);
				map.put("proName", pName);
				map.put("page", pageInfo);
				request.setAttribute("map", map);
				request.getRequestDispatcher("producer.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/producer/producerAdd.do")){
			try {
				String proCode = request.getParameter("proCode");
				String proName = request.getParameter("proName");
				String proPhone = request.getParameter("proPhone");
				String proAddress = request.getParameter("proAddress");
				Producer pro = new Producer(proCode, proName, proPhone, proAddress);
				pd.addProducer(pro);
				response.sendRedirect("pro.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(path.equals("/producer/producerDel.do")){
			try {
				int pId = Integer.parseInt(request.getParameter("producerId"));
				pd.deleteProducer(pId);
				response.sendRedirect("pro.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/producer/producerUpdUI.do")){
			try {
				int pId = Integer.parseInt(request.getParameter("producerId"));
				Producer pro = pd.selectById(pId);
				request.setAttribute("producer", pro);
				request.getRequestDispatcher("producerUpd.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/producer/producerUpd.do")){
			try {
				int pId = Integer.parseInt(request.getParameter("proId"));
				String proCode = request.getParameter("proCode");
				String proName = request.getParameter("proName");
				String proPhone = request.getParameter("proPhone");
				String proAddress = request.getParameter("proAddress");
				Producer pro = new Producer(pId, proCode, proName, proPhone, proAddress);
				pd.updateProducer(pro);
				response.sendRedirect("pro.do");
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
