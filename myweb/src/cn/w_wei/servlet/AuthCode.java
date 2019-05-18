package cn.w_wei.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class AuthCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final int WIDTH = 100;//设置验证码图片宽度
    private static final int HEIGHT = 25;//设置验证码图片高度
    private static final int LENGTH = 4;//设置验证码长度
    //设置验证码随机出现的字符
    private static final String str = "1234567890" +
            "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static char[] chars = str.toCharArray();//将字符放在数组中方便随机读取
    private static Random random = new Random();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//responseCode(request,response);
		OutputStream os = response.getOutputStream();
		//设置输出的类型为图片
        response.setContentType("image/jpeg");

        //设置不进行缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
        //画笔
        Graphics graphics = image.getGraphics();

        //设置背景颜色并绘制矩形背景
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        //用于记录生成的验证码
        String code = null;

        //生成验证码并绘制
        for (int i = 0; i < LENGTH; i++) {
            String c = "" + chars[random.nextInt(str.length())];
            graphics.setColor(getColor());
            graphics.drawString(c, 20 * i + 20, 20);
            code += c;
        }

        //生成干扰点
        for (int i = 0; i < 50; i++) {
            graphics.setColor(getColor());
            graphics.drawOval(random.nextInt(100), random.nextInt(25), 1, 1);
        }

        //将生成的验证码存入session中，以便进行校验
        HttpSession session = request.getSession();
        session.setAttribute("code", code);

        //绘制图片
        graphics.dispose();
        
        //将图片输出到response中
        
        ImageIO.write(image, "JPEG",os);
        os.flush();
        os.close();
	}
	//随机生成颜色
    private Color getColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
//1、使用BufferedImage用于在内存中存储生成的验证码图片
//2、使用Graphics来进行验证码图片的绘制，并将绘制在图片上的验证码存放到session中用于后续验证
//3、最后通过ImageIO将生成的图片进行输出
//4、通过页面提交的验证码和存放在session中的验证码对比来进行校验

