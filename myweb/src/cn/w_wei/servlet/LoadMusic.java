package cn.w_wei.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoadMusic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+":"+password);

//		PrintWriter out = response.getWriter();
		OutputStream os = response.getOutputStream();
	    String path = "D:\\Java_Study\\Workspace_S1\\myweb\\WebContent\\music.txt";
//	    path = "http://192.168.56.1:8080/myweb/music.txt";
//	    InputStream is = null;
	    FileInputStream is = null;
	    InputStreamReader isr = null;
	    BufferedReader reader = null;
	    try {
//			URL url = new URL(path);//这种形式为什么读不出中文？
//			is = url.openStream();
			is = new FileInputStream(path);
			isr = new InputStreamReader(is);
			reader = new BufferedReader(isr);
			StringBuilder builder = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null) {
				builder.append(line);
			}
			String jsonStr = builder.toString();
			//这里应该以utf-8编码格式传输字节
			byte[] bytes = builder.toString().getBytes("utf-8");
			os.write(bytes);
//			out.print(jsonStr);
			System.out.println(jsonStr);
//			out.flush();
			os.flush();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//	    out.close();
	    os.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
