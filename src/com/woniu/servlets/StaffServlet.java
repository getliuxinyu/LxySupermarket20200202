package com.woniu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
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

import com.mysql.cj.Session;
import com.woniu.daos.StaffsDao;
import com.woniu.entity.PageInfo;
import com.woniu.entity.Staffs;
import com.woniu.tools.MYMD5;

/**
 * Servlet implementation class StaffServlet
 */
@WebServlet({"/staffs/staff.do","/staffs/checkPwd.do","/staffs/changePwd.do","/staffs/staffsConf.do","/staffs/staffsReset.do","/staffs/staffsActive.do","/staffs/staffsAdd.do","/staffs/staffsUpdUI.do","/staffs/staffsUpd.do","/staffs/staffsInactive.do"})
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffServlet() {
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
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		StaffsDao sd = new StaffsDao();
		HttpSession session = request.getSession();
		if(path.equals("/staffs/staff.do")){
			//员工显示页面
			try {
				//List<Staffs> li = sd.selectStaffs();
				//request.setAttribute("staffsList", li);
				PageInfo<Staffs> pageInfo = new PageInfo<>();
				String staffName = request.getParameter("staffName");
				String staffCode = request.getParameter("staffCode");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("staffName", staffName);
				map.put("staffCode", staffCode);
				request.setAttribute("map", map);
				//总条目
				int totalCount = sd.selectTotalCount(staffName,staffCode);
				pageInfo.setTotalCount(totalCount);
				//页面显示条目
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
				
				List<Staffs> li = sd.selectStaffs(pageInfo,staffName,staffCode);
				pageInfo.setLi(li);
				request.setAttribute("PageInfo", pageInfo);
				request.getRequestDispatcher("staffs.jsp").forward(request, response);
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
		//原密码比对,用户登录成功，获得session中存储的id。比对当前用户的原密码
		else if(path.equals("/staffs/checkPwd.do")){
			System.out.println("请求到达");
			try {
				String oldPwd = request.getParameter("oldPwd");
				System.out.println(oldPwd);
				String ciphertext = MYMD5.getMD5(oldPwd);
				int staffId = (int) session.getAttribute("staffId");
				boolean isSuccess = sd.isExist(ciphertext,staffId);
				PrintWriter out = response.getWriter();
				if(isSuccess){
					out.print(true);
				}else{
					out.print(false);
				}
				out.flush();
				out.close();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		//修改密码操作
		else if(path.equals("/staffs/changePwd.do")){
			System.out.println("请求到达");
			try {
				String Pwd = request.getParameter("newPwd");
				System.out.println(Pwd);
				String newPwd = MYMD5.getMD5(Pwd);
				int staffId = (int) session.getAttribute("staffId");
				sd.updateStaffs(newPwd,staffId);
				PrintWriter out = response.getWriter();
					//弹出对话框，跳转到登录页面
				response.setContentType("text/html;charset=utf-8");
					out.print("out.print(\"<script>top.location.href='../Logon.jsp'</script>\")");
					out.flush();
					out.close();
					
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//确认
		else if(path.equals("/staffs/staffsConf.do")){
			try {
				int staffId = Integer.parseInt(request.getParameter("staffId"));
				sd.updateById(staffId);
				response.sendRedirect("staff.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//重置密码
		else if(path.equals("/staffs/staffsReset.do")){
			try {
				int staffId = Integer.parseInt(request.getParameter("staffId"));
				sd.resetPwd(staffId);
				response.sendRedirect("staff.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//设置活跃状态
		
		else if(path.equals("/staffs/staffsActive.do")){
			try {
				int staffId = Integer.parseInt(request.getParameter("staffId"));
				String staffStatus = request.getParameter("staffSta");
				
				sd.activeStatus(staffId,staffStatus);
				response.sendRedirect("staff.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(path.equals("/staffs/staffsInactive.do")){
			int staffId = Integer.parseInt(request.getParameter("staffId"));
			String staffStatus = request.getParameter("staffSta");
			try {
				sd.InactiveStatus(staffId,staffStatus);
				response.sendRedirect("staff.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//增加用户
		else if(path.equals("/staffs/staffsAdd.do")){
			System.out.println("请求到达");
			try {
				String staffCode = request.getParameter("staffCode");
				String staffName = request.getParameter("staffName");
				String staffFunction = request.getParameter("staffFunction");
				Staffs sta = new Staffs(staffCode, staffName, staffFunction);
				System.out.println(sta);
				sd.staffsAdd(sta);
				response.sendRedirect("staff.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//用户修改界面显示
		else if(path.equals("/staffs/staffsUpdUI.do")){
			int staffId = Integer.parseInt(request.getParameter("staffId"));
			try {
				Staffs st = sd.selectById(staffId);
				request.setAttribute("staff", st);
				request.getRequestDispatcher("staffsUpd.jsp").forward(request, response);
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
		//修改员工信息
		else if(path.equals("/staffs/staffsUpd.do")){
			int staffId = Integer.parseInt(request.getParameter("staffId"));
			String staffCode = request.getParameter("staffCode");
			String staffName = request.getParameter("staffName");
			String staffFunction = request.getParameter("staffFunction");
			Staffs st = new Staffs(staffId, staffCode, staffName, staffFunction);
			try {
				sd.staffUpdate(st);
				response.sendRedirect("staff.do");
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
