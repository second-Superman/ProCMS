package com.hzj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzj.beans.NewsType;
import com.hzj.server.NewsTypeServer;

import net.sf.json.JSONObject;

@WebServlet(name = "NewTypeServlet", urlPatterns = { "/admin/NewTypeServlet.action" })
public class NewTypeServlet extends HttpServlet {

	public static final String ADD = "add";
	public static final String FIND_BY_ALL = "findbyall";
	public static final String EDIT = "edit";
	public static final String EDIT_BEFORE = "editBefore";
	public static final String DEL = "del";

	HttpServletRequest req;
	HttpServletResponse resp;
	// 业务
	NewsTypeServer newsTypeServer = null;

	// 参数区域【前端到后端】======================================
	// 方法参数
	String method = "";

	// 数据beans参数
	String id = "0";
	String name = "";

	// 【请求跳转】【后端到后端，后端到前端，也就是要进入req.setAttribute(arg0,
	// arg1);】======================================
	// 结果【请求跳转】
	String mess = "";
	String state = "";
	ArrayList<NewsType> newsTypes;
	// 标记
	boolean tag = true;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 初始化
		this.req = req;
		this.resp = resp;
		tag = true;
		newsTypeServer = new NewsTypeServer();
		// 方法参数获取
		methodParameter();

		// 开始选择方法执行
		if (tag) {

			if (method.equals(ADD)) {
				add();
			} else if (method.equals(EDIT)) {
edit();
			}

			else if (method.equals(EDIT_BEFORE)) {
				editBefore();
			} else if (method.equals(DEL)) {

				del();

			} else if (method.equals(FIND_BY_ALL)) {

				findByAll();

			} else {
				mess = "你选择的服务不存在";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
			}

		}

		
		
		
		
		
		
		
		
		
	}

	private void edit() throws ServletException, IOException {
		
		name = req.getParameter("name");
		id = req.getParameter("id");
	

		if (name == null || name.equals("") || id==null || id.equals("") ) {
			mess = "你的名称不能输入空";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}
		
		
		
		int tempId = -1;
		try {
			tempId = Integer.parseInt(id);
		} catch (Exception e) {
			mess = "你的参数类型转换出了问题";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;

		}
		
		

		NewsType newsType = new NewsType();
		newsType.setId(tempId);
		newsType.setName(name);

		try {

			if (newsTypeServer.edit(newsType)) {
				mess = "你修改数据成功";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
				return;
			} else {
				mess = "你修改的数据有问题";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
				return;
			}

		} catch (Exception e) {
			mess = e.getMessage();
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}
		
	}

	private void editBefore() throws ServletException, IOException {

		id = req.getParameter("id");
		if (id == null || id.equals("")) {
			mess = "你的操作参数错误";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}

		int tempId = -1;
		try {
			tempId = Integer.parseInt(id);
		} catch (Exception e) {
			mess = "你的参数类型转换出了问题";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;

		}

		try {
			NewsType newsType = newsTypeServer.findById(tempId);
			
			req.setAttribute("newsType", newsType);
			req.getRequestDispatcher("newsTypeEdit.jsp").forward(req, resp);
			tag = false;
			return;
			
			
			
		} catch (Exception e) {
			mess = e.getMessage();
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}
		
		
	}

	private void del() throws IOException {

		boolean tempTag = true;

		id = req.getParameter("id");
		if (id == null || id.equals("")) {
			mess = "你的操作参数错误";
			state = "0";
			tempTag = false;
		}

		int tempID = -1;
		if (tempTag) {
			try {
				// 转型
				tempID = Integer.parseInt(id);
			} catch (Exception e) {
				mess = "你的操作参数转型错误";
				state = "0";
				tempTag = false;
			}
		}

		if (tempTag) {

			try {
				if (newsTypeServer.del(tempID)) {

					mess = "删除成功";
					state = "1";

				} else {
					mess = "你删除的数据失败";
					state = "0";
				}
			} catch (Exception e) {
				mess = e.getMessage();
				state = "0";
			}

		}

		// 结果
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("mess", mess);
		jsonObject.accumulate("state", state);
		PrintWriter out = resp.getWriter();
		String str = new String(jsonObject.toString().getBytes("utf-8"), "utf-8");
		out.write(str);
		out.flush();
		out.close();

	}

	private void add() throws ServletException, IOException {

		name = req.getParameter("name");

		// URLDecoder.decode(name, "utf-8")

		if (name == null || name.equals("")) {
			mess = "你的名称不能输入空";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}

		NewsType newsType = new NewsType();
		newsType.setName(name);

		try {

			if (newsTypeServer.add(newsType)) {
				mess = "你添加数据成功";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
				return;
			} else {
				mess = "你添加的数据有问题";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
				return;
			}

		} catch (Exception e) {
			mess = e.getMessage();
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}
	}

	private void findByAll() throws ServletException, IOException {

		try {
			newsTypes = newsTypeServer.findByAll();
			req.setAttribute("newsTypes", newsTypes);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			tag = false;
		} catch (Exception e) {
			mess = e.getMessage();
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
		}
	}

	private void methodParameter() throws ServletException, IOException {
		method = req.getParameter("method");
		if (method == null || method.equals("")) {
			mess = "你没有选择任何服务";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
