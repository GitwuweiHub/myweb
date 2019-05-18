package cn.w_wei.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadSong extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "D:\\Java_Study\\Workspace_S1\\myweb\\WebContent\\musics\\是什么让人无能为力.mp3";
		InputStream is = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		ByteArrayOutputStream baos = null;
		try {
			byte[] buffer = new byte[1024 * 8];
			baos = new ByteArrayOutputStream();
			is = new FileInputStream(path);
			bis = new BufferedInputStream(is);
			byte[] out = null;
			int len = 0;
			while ((len = bis.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			baos.flush();
			//在这里应该可以让它实现分段下载
			out = baos.toByteArray();
			os = response.getOutputStream();
			os.write(out);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			is.close();
			os.close();
			baos.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
