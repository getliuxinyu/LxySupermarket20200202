package com.woniu.servlets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码
 * Servlet implementation class CheckCodeServlet
 */
@WebServlet("/checkCode.do")
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Random random = new Random(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doDispacher(request,response);
	}

	private void doDispacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建图片对象(BufferedImage.TYPE_INT_BGR 选择图片模式RGB模式)
		BufferedImage image = new BufferedImage(100, 40,BufferedImage.TYPE_INT_BGR);
		//得到画笔
		Graphics gp = image.getGraphics();
		//设置画笔颜色
		gp.setColor(Color.orange);
		//绘画填充范围，满布图片对象
		gp.fillRect(0, 0, 100, 40);
		//生成干扰线
		for(int i=0;i<30;i++){
			//颜色随机
			gp.setColor(getRandomColor());
			//两点确定一条直线
			int x1 = random.nextInt(100);
			int y1 = random.nextInt(40);
			int x2 = random.nextInt(100);
			int y2 = random.nextInt(40);
			gp.drawLine(x1, y1, x2, y2);
		}
		//拼接4个验证码，画到图片上
		char[] arr = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		//bulider缓存即将存放验证码的字符
		StringBuilder builder = new StringBuilder();
		//得到四个验证码
		for(int i=0;i<4;i++){
			//获得数组中随机下标
			int index = random.nextInt(arr.length);
			//从数组中拿到对应字母追加到字符串中
			builder.append(arr[index]);
		}
		//创建session对象，将生成的验证码字符串存入session
		request.getSession().setAttribute("checkCode", builder.toString());
		
		//将字符画在图片上
		gp.setFont(new Font("微软雅黑", Font.BOLD, 30));
		//循环一次画一个字符
		for (int i = 0; i < builder.toString().length(); i++) {
			gp.setColor(getRandomColor());
			
			char item = builder.toString().charAt(i);
			///最后一个参数指文字的基线的纵坐标，基线大概在文字的四分之三的位置。
			gp.drawString(item+"", 5+(i*22), 30);
		}
		ImageIO.write(image, "png", response.getOutputStream());
	}
	/**创建随机色方法*/
	private Color getRandomColor() {
		//0~256之间的随机数
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		return new Color(r, g, b);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
