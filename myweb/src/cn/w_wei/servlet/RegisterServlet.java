package cn.w_wei.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import net.sf.json.JSONObject;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");//���ַ�ʽ����ʹ�������˽�������
		//��������������Խ�������
//		name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
//		password = new String(password.getBytes("ISO-8859-1"),"UTF-8");
		PrintWriter out = response.getWriter();
//		ServletOutputStream output = response.getOutputStream();
//		ServletInputStream input = request.getInputStream();
//		BufferedReader reader = request.getReader();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		if(!username.equals("")&&!password.equals("")&&email.length()>0&&phone.length()>0) {
			System.out.println("ע��ɹ�");
			System.out.println("username="+username);
			System.out.println("password="+password);
			System.out.println("email="+email);
			System.out.println("phone="+phone);
			User user = new User(username,password,email,phone);
			
			//Json���������ֵ����ʹ�ַ�����Json����Ҳ������Json����
//			JSONObject user = new JSONObject();
//			user.put("result", "ok");
//			JSONObject jsonObj = new JSONObject();
//			jsonObj.put("username", username);
//			jsonObj.put("password", password);
//			jsonObj.put("email", email);
//			jsonObj.put("phone", phone);
//			user.put("user", jsonObj);
//			out.print(user.toString());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "ok");
			jsonObject.put("user", user);
			out.print(jsonObject.toString());
			
		}else {
			System.out.println("ע��ʧ��");
			System.out.println("�벹ȫע����Ϣ");
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("result", "erro");
			out.print(jsonObj.toString());
		}
		out.flush();
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
