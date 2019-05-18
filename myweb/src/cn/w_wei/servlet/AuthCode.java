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
    private static final int WIDTH = 100;//������֤��ͼƬ���
    private static final int HEIGHT = 25;//������֤��ͼƬ�߶�
    private static final int LENGTH = 4;//������֤�볤��
    //������֤��������ֵ��ַ�
    private static final String str = "1234567890" +
            "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static char[] chars = str.toCharArray();//���ַ����������з��������ȡ
    private static Random random = new Random();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//responseCode(request,response);
		OutputStream os = response.getOutputStream();
		//�������������ΪͼƬ
        response.setContentType("image/jpeg");

        //���ò����л���
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
        //����
        Graphics graphics = image.getGraphics();

        //���ñ�����ɫ�����ƾ��α���
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        //���ڼ�¼���ɵ���֤��
        String code = null;

        //������֤�벢����
        for (int i = 0; i < LENGTH; i++) {
            String c = "" + chars[random.nextInt(str.length())];
            graphics.setColor(getColor());
            graphics.drawString(c, 20 * i + 20, 20);
            code += c;
        }

        //���ɸ��ŵ�
        for (int i = 0; i < 50; i++) {
            graphics.setColor(getColor());
            graphics.drawOval(random.nextInt(100), random.nextInt(25), 1, 1);
        }

        //�����ɵ���֤�����session�У��Ա����У��
        HttpSession session = request.getSession();
        session.setAttribute("code", code);

        //����ͼƬ
        graphics.dispose();
        
        //��ͼƬ�����response��
        
        ImageIO.write(image, "JPEG",os);
        os.flush();
        os.close();
	}
	//���������ɫ
    private Color getColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
//1��ʹ��BufferedImage�������ڴ��д洢���ɵ���֤��ͼƬ
//2��ʹ��Graphics��������֤��ͼƬ�Ļ��ƣ�����������ͼƬ�ϵ���֤���ŵ�session�����ں�����֤
//3�����ͨ��ImageIO�����ɵ�ͼƬ�������
//4��ͨ��ҳ���ύ����֤��ʹ����session�е���֤��Ա�������У��

