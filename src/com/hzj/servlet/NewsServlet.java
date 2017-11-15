package com.hzj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.hzj.beans.News;
import com.hzj.beans.NewsInfo;
import com.hzj.beans.NewsType;
import com.hzj.server.NewsServer;
import com.hzj.server.NewsTypeServer;
import com.hzj.util.validate.ValidateUtil;

import net.sf.json.JSONObject;

//servlet3.0如果from带上传属性，那么想要取得正常的参数，必须加上@MultipartConfig注解，才能正常取参

@WebServlet(name = "NewsServlet", urlPatterns = { "/admin/NewsServlet.action" })
@MultipartConfig
public class NewsServlet extends HttpServlet {

	public static final String ADD_BEFORE = "addBefore";
	public static final String ADD = "add";
	public static final String FIND_BY_ALL = "findbyall";
	public static final String EDIT = "edit";
	public static final String EDIT_BEFORE = "editBefore";

	public static final String DEL = "del";

	HttpServletRequest req;
	HttpServletResponse resp;

	// 参数
	String method = "";
	String mess = "";
	ArrayList<News> news;

	// 业务
	NewsTypeServer newsTypeServer;
	NewsServer newsServer;
	// tag
	boolean tag = true;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 初始化
		this.req = req;
		this.resp = resp;
		tag = true;
		newsServer = new NewsServer();
		newsTypeServer = new NewsTypeServer();

		methodParameter();

		// 开始选择方法执行
		if (tag) {

			if (method.equals(ADD)) {
				add();
			}

			else if (method.equals(ADD_BEFORE)) {

				addBefore();

			}

			else if (method.equals(EDIT)) {

			}

			else if (method.equals(EDIT_BEFORE)) {

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

	private void del() throws ServletException, IOException {

		String id = req.getParameter("id");
		String state = "0";
		String mess = "";
		
		HashMap<String, String> prams = new HashMap<String, String>();
		prams.put("id", id);
		if (ValidateUtil.validateNullAndEmpty(prams)) {
			tag = false;
			mess = "你参数错误";
		}

		int idTemp = -1;

		try {
			idTemp = Integer.parseInt(id);
		} catch (Exception e) {
			e.printStackTrace();
			tag = false;
			mess = "你参数错误";
		}

		// 开始删除
		if (tag) {
			try {
				if (newsServer.del(req, idTemp)) {
					state = "1";
					mess = "成功";
				} else {
					mess = "删除失败";
				}

			} catch (Exception e) {

				e.printStackTrace();
				mess = e.getMessage();

			}
		}
		// 开始返回JSON
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("state", state);
		jsonObject.accumulate("mess", mess);
		PrintWriter out = resp.getWriter();
		out.write(jsonObject.toString());
		out.flush();
		out.close();

	}

	private void add() throws ServletException, IOException {

		// 收集各种数据
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String st = req.getParameter("st");
		String browser_count = req.getParameter("browser_count");
		String news_type_id = req.getParameter("news_type_id");
		// 内容
		String info = req.getParameter("info");
		// 参数进入集合
		HashMap<String, String> prams = new HashMap<String, String>();
		prams.put("title", title);
		prams.put("author", author);
		prams.put("st", st);
		prams.put("browser_count", browser_count);
		prams.put("news_type_id", news_type_id);
		prams.put("info", info);
		boolean tempValidate = ValidateUtil.validateNullAndEmpty(prams);

		if (tempValidate) {
			mess = "你输入的参数有问题";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}

		int browser_countTemp = 0;
		int news_type_idTemp = 0;
		try {

			news_type_idTemp = Integer.parseInt(news_type_id);
			browser_countTemp = Integer.parseInt(browser_count);

		} catch (Exception e) {

			e.printStackTrace();
			mess = "你输入浏览量参数有问题";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}

		String fileName = "";
		// 上传文件
		try {
			Part part = req.getPart("photo");
			// 重建文件名称
			// 1:根据不同的文件类型组装出一个全新的文件名，且不能重复UUID
			fileName = UUID.randomUUID().toString();
			// 2:上传的是什么类型的文件
			String fileExtension = part.getHeader("Content-Type");
			// 组合文件名
			if (fileExtension.equalsIgnoreCase("image/tiff")) {
				fileName += ".tif";
			}
			if (fileExtension.equalsIgnoreCase("image/fax")) {
				fileName += ".fax";
			}
			if (fileExtension.equalsIgnoreCase("image/gif")) {
				fileName += ".gif";
			}
			if (fileExtension.equalsIgnoreCase("image/x-icon")) {
				fileName += ".ico";
			}
			if (fileExtension.equalsIgnoreCase("image/jpeg")) {
				fileName += ".jpg";
			}
			if (fileExtension.equalsIgnoreCase("image/pnetvue")) {
				fileName += ".net";
			}
			if (fileExtension.equalsIgnoreCase("image/png")) {
				fileName += ".png";
			}
			if (fileExtension.equalsIgnoreCase("image/vnd.rn-realpix")) {
				fileName += ".rp";
			}
			if (fileExtension.equalsIgnoreCase("image/vnd.wap.wbmp")) {
				fileName += ".wbmp";
			}

			if (fileExtension.equalsIgnoreCase("image/bmp")) {
				fileName += ".bmp";
			}

			// 3:文件存在哪里
			String savePath = req.getServletContext().getRealPath("/upload");
			part.write(savePath + "/" + fileName);

		} catch (Exception e) {
			e.printStackTrace();
			mess = "上传文件出了问题";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}

		// 开始执行你的业务
		News news = new News();
		news.setTitle(title);
		news.setSt(st);
		news.setAuthor(author);
		news.setBrowserCount(browser_countTemp);
		news.setPhoto(fileName);

		NewsType newsType = new NewsType();
		newsType.setId(news_type_idTemp);
		news.setNewsType(newsType);

		NewsInfo newsInfo = new NewsInfo();
		newsInfo.setInfo(info);
		try {

			if (newsServer.add(news, newsInfo)) {
				mess = "添加成功";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
				return;
			} else {
				mess = "添加失败！！！！！！！！11";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
				return;
			}

		} catch (Exception e) {
			// 删除文件
			e.printStackTrace();
			mess = e.getMessage();
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}
	}

	private void addBefore() throws ServletException, IOException {
		try {
			ArrayList<NewsType> newsTypes = newsTypeServer.findByAll();
			req.setAttribute("newsTypes", newsTypes);
			req.getRequestDispatcher("newsAdd.jsp").forward(req, resp);
			tag = false;

		} catch (Exception e) {
			e.printStackTrace();
			mess = e.getMessage();
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
		}

	}

	private void findByAll() throws ServletException, IOException {

		try {
			news = newsServer.findByAll();
			req.setAttribute("news", news);
			req.getRequestDispatcher("news.jsp").forward(req, resp);
			tag = false;
		} catch (Exception e) {
			e.printStackTrace();
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
