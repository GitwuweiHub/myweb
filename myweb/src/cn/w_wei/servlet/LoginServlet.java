package cn.w_wei.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * request 包装了客户端表单的请求数据
	 * response 响应客户端请求的一种对象
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		/*
		 * 1.设置content-type消息头的值。
		 * 2.out.println方法会使用指定的字符集来编码。
		 */
		response.setContentType("text/html;charset=utf-8");
		 /*
	     * 设置请求参数值的解码方式。
	     * 注：
	     *  a.该方法一定要添加到所有的getParameter方法的前面。
	     *  b.该方法只针对post请求有效。
	     */
		request.setCharacterEncoding("utf-8");//这种方式可以使服务器端接收中文
		//下属解决方案可以接收中文
//		username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
//		password = new String(password.getBytes("ISO-8859-1"),"UTF-8");
		//通过response获得输出流给发出请求的客户端
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("用户名："+username);
		System.out.println("密   码："+password);
		if("admin".equals(username)&&"admin888".equals(password)){
			System.out.println("登录成功！");
			//1、创建一个Json对象，封装一个属性2、把Json对象转换成字符串3、再写到客户端
			//响应一个json格式的字符串给客户端
			//{"result":"ok"}
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("result", "ok");
			//输出结果到客户端
			out.print(jsonObj.toString());
		}else {
			System.out.println("登录失败！");
			//响应一个json格式的字符串给客户端
			//{"result":"error"}
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("result", "error");
			//输出结果到客户端
			out.print(jsonObj.toString());
		}
		out.flush();
		out.close();
	}
	


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
