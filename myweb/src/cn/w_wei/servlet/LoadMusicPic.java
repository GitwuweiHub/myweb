package cn.w_wei.servlet;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoadMusicPic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream os = response.getOutputStream();
		//设置输出的类型为图片
		//输出图片时需注意图片格式要与原图片格式相同
        response.setContentType("image/png");

        //设置不进行缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        String path = "D:\\Java_Study\\Workspace_S1\\myweb\\WebContent\\images\\image_02.png";
        FileInputStream is = new FileInputStream(path);
        BufferedImage image = ImageIO.read(is);
        ImageIO.write(image, "PNG", os);
        os.flush();
        os.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
