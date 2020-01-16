package com.woniu.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.oreilly.servlet.MultipartRequest;
import com.woniu.daos.GoodsDao;
import com.woniu.entity.Goods;
import com.woniu.entity.PageInfo;



/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet({"/goods/showgoods.do","/goods/goodsAdd.do","/goods/goodsConf.do","/goods/goodsUpdUI.do","/goods/goodsUpd.do","/goods/goodsDel.do"})
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsServlet() {
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
		PageInfo<Goods> pageInfo = new PageInfo<>();
		if(path.equals("/goods/showgoods.do")){
			try {
				String goodsCode = request.getParameter("goodsCode");
				String goodsName = request.getParameter("goodsName");
				
				//总条目数
				int totalCount = gd.getTotalCount(goodsName, goodsCode);
				pageInfo.setTotalCount(totalCount);
				
				//页面显示条目数
				int pageSize = 3;
				pageInfo.setPageSize(pageSize);
				
				//当前页
				int currentPage = 1;
				pageInfo.setCurrentPage(currentPage);
				
				//查询符合条件的分页信息
				//List<Goods> li = gd.selectGoods(goodsCode,goodsName,pageInfo);
				List<Goods> goodsli = gd.selectGoods();
				request.setAttribute("goodsList", goodsli);
				
				request.getRequestDispatcher("goods.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(path.equals("/goods/goodsAdd.do")){
			
			try {
				
				//得到项目在服务器上的真实路径
				String realPath = request.getServletContext().getRealPath("/");
				//项目路径下的front/upload目录
				String dirPath = realPath+File.separator+"front"+File.separator+"upload";
				
				File f = new File(dirPath);
				if(!f.exists()){
					f.mkdirs();
				}
				//处理文件上传,将请求对象、文件保存路径、文件夹大小、编码格式封装。
				MultipartRequest mreq = new MultipartRequest(request, dirPath, 10*1024*1024,"utf-8");
				//封装完成用mreq对象
				
				
				String goodsCode = mreq.getParameter("goodsCode");
				
				String goodsName = mreq.getParameter("goodsName");
				float goodsPrice = Float.parseFloat(mreq.getParameter("goodsPrice"));
				int goodsCount = Integer.parseInt(mreq.getParameter("goodsCount"));
				int proId = Integer.parseInt(mreq.getParameter("proName"));
				int gstId = Integer.parseInt(mreq.getParameter("gstId"));
				String goodsRemarks = mreq.getParameter("goodsRemarks");
				
				
				
				//文件为了防止重名，将文件名重命名
				String oldImgName = mreq.getFilesystemName("goodsImg");
				
				
				//得到新文件名称,日期+随机数
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
				String datestr = sdf.format(d);
				Random random = new Random();
				int rd = random.nextInt(10000);
				
				//得到原文见得后缀
				String ext = oldImgName.substring(oldImgName.indexOf("."));
				//得到新文件的名字,文件目录、随机数、文件名后缀
				String newImgName = datestr+rd+ext;
				
				//创建针对于原文件的新文件
				File oldFile = new File(dirPath+File.separator+oldImgName);
				//更改原文件名称
				oldFile.renameTo(new File(dirPath+File.separator+newImgName));
				
				//存放入数据库
				String goodsImg = "front"+File.separator+"upload"+File.separator+newImgName;
				
				Goods gs = new Goods(goodsCode, goodsName, goodsPrice, goodsCount, goodsImg, goodsRemarks, proId, gstId);
				gd.goodsAdd(gs);
				response.sendRedirect("showgoods.do");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//确认信息
		else if(path.equals("/goods/goodsConf.do")){
			try {
				int goodsId = Integer.parseInt(request.getParameter("goodsId"));
				gd.confirmGoods(goodsId);
				response.sendRedirect("showgoods.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		//更改信息展示
		else if(path.equals("/goods/goodsUpdUI.do")){
			int goodsId = Integer.parseInt(request.getParameter("goodsId"));
			try {
				Map<String,Object> map = gd.selectById(goodsId);
				request.setAttribute("goodsInfo", map);
				request.getRequestDispatcher("goodsUpd.jsp").forward(request, response);
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
		//修改元素
		else if(path.equals("/goods/goodsUpd.do")){
			
			
			try {
				//项目在tomcat发布目录上绝对路径
				String realPath = request.getServletContext().getRealPath("/");
				//文件上传路径
				String dirPath = realPath+File.separator+("front")+File.separator+("upload");
				
				File f = new File(dirPath);
				if(!f.exists()){
					f.mkdirs();
				}
				
				MultipartRequest mreq = new MultipartRequest(request, dirPath,10*1024*1024, "utf-8");
				
				String goodsCode = mreq.getParameter("goodsCode");
				String goodsName = mreq.getParameter("goodsName");
				String goodsPrice = mreq.getParameter("goodsPrice");
				String goodsCount = mreq.getParameter("goodsCount");
				String gsdId = mreq.getParameter("goodstypeId");
				String poodsId = mreq.getParameter("producerId");
				String goodsId = mreq.getParameter("goodsId");
				//原图片文件路径
				String originalImgName = mreq.getParameter("goodsOldImg");
				
				//新上传图片文件路径
				String oldImgName = mreq.getFilesystemName("goodsNewImg");
				
				
				//新上传文件
				File newImgFile = new File(dirPath+File.separator+oldImgName);
			
				
				
				InputStream in = new FileInputStream(newImgFile);
				
				
				
				OutputStream out = new FileOutputStream(realPath+File.separator+originalImgName);
				//字节流写出新文件覆盖原文件
				byte[] bs = new byte[1024];
				
				int len=0;
				while((len=in.read(bs))!=-1){
					out.write(bs, 0, len);
				}
				
				out.flush();
				out.close();
				
				in.close();
				
				//删除上传原文件
				System.out.println(newImgFile.delete());
				//存入数据库
				
				Goods gs = new Goods(Integer.parseInt(goodsId), goodsCode, goodsName, Float.parseFloat(goodsPrice), Integer.parseInt(goodsCount), originalImgName, Integer.parseInt(poodsId), Integer.parseInt(gsdId));
				gd.updateGoods(gs);
				response.sendRedirect("showgoods.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//删除
		else if(path.equals("/goods/goodsDel.do")){
			try {
				int goodsId = Integer.parseInt(request.getParameter("goodsId"));
				gd.deleteGoods(goodsId);
				response.sendRedirect("showgoods.do");
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
