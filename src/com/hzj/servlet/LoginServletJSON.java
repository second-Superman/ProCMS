package com.hzj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzj.beans.Admin;
import com.hzj.server.AdminServer;

import net.sf.json.JSONObject;

@WebServlet(name = "LoginServletJSON", urlPatterns = { "/LoginServletJSON.action" }

)
public class LoginServletJSON extends HttpServlet {

	
	public static final String MESS="mess";
	public static final String STATE="state";
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 收集数据
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String kaptcha = req.getParameter("kaptcha");

		// json对象{state\mess}
		String mess = "";
		String state = "0";

		//
		boolean tag = true;
		AdminServer adminServer = new AdminServer();
		if (username == null || password == null || kaptcha == null) {

			mess = "你输入的账号或者密码有误";
			state = "0";
			tag = false;
		}

		if (tag) {
			String resKaptcha = (String) req.getSession()
					.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			if (!kaptcha.equalsIgnoreCase(resKaptcha)) {
				mess = "你输入的验证码有错误";
				state = "0";
				tag = false;
			}
		}

		if (tag) {
			Admin admin = new Admin();
			admin.setUsername(username);
			admin.setPassword(password);
			Admin resultAdmin = adminServer.login(admin);
			if (resultAdmin == null) {
				mess = "你输入的密码或者账号有误";
				state = "0";

			} else {
				req.getSession().setAttribute("admin", resultAdmin);
				state = "1";
				mess = "成功";
			}
		}
		//返回JSON
		JSONObject jsonObject=new JSONObject();
		jsonObject.accumulate(MESS, mess);
		jsonObject.accumulate(STATE, state);
		PrintWriter out = resp.getWriter();
		
	
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
