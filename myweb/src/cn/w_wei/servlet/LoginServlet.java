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
	 * request ��װ�˿ͻ��˱�����������
	 * response ��Ӧ�ͻ��������һ�ֶ���
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		/*
		 * 1.����content-type��Ϣͷ��ֵ��
		 * 2.out.println������ʹ��ָ�����ַ��������롣
		 */
		response.setContentType("text/html;charset=utf-8");
		 /*
	     * �����������ֵ�Ľ��뷽ʽ��
	     * ע��
	     *  a.�÷���һ��Ҫ��ӵ����е�getParameter������ǰ�档
	     *  b.�÷���ֻ���post������Ч��
	     */
		request.setCharacterEncoding("utf-8");//���ַ�ʽ����ʹ�������˽�������
		//��������������Խ�������
//		username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
//		password = new String(password.getBytes("ISO-8859-1"),"UTF-8");
		//ͨ��response������������������Ŀͻ���
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("�û�����"+username);
		System.out.println("��   �룺"+password);
		if("admin".equals(username)&&"admin888".equals(password)){
			System.out.println("��¼�ɹ���");
			//1������һ��Json���󣬷�װһ������2����Json����ת�����ַ���3����д���ͻ���
			//��Ӧһ��json��ʽ���ַ������ͻ���
			//{"result":"ok"}
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("result", "ok");
			//���������ͻ���
			out.print(jsonObj.toString());
		}else {
			System.out.println("��¼ʧ�ܣ�");
			//��Ӧһ��json��ʽ���ַ������ͻ���
			//{"result":"error"}
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("result", "error");
			//���������ͻ���
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
