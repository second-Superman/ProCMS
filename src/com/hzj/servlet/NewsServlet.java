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

//servlet3.0���from���ϴ����ԣ���ô��Ҫȡ�������Ĳ������������@MultipartConfigע�⣬��������ȡ��

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

	// ����
	String method = "";
	String mess = "";
	ArrayList<News> news;

	// ҵ��
	NewsTypeServer newsTypeServer;
	NewsServer newsServer;
	// tag
	boolean tag = true;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ��ʼ��
		this.req = req;
		this.resp = resp;
		tag = true;
		newsServer = new NewsServer();
		newsTypeServer = new NewsTypeServer();

		methodParameter();

		// ��ʼѡ�񷽷�ִ��
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
				mess = "��ѡ��ķ��񲻴���";
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
			mess = "���������";
		}

		int idTemp = -1;

		try {
			idTemp = Integer.parseInt(id);
		} catch (Exception e) {
			e.printStackTrace();
			tag = false;
			mess = "���������";
		}

		// ��ʼɾ��
		if (tag) {
			try {
				if (newsServer.del(req, idTemp)) {
					state = "1";
					mess = "�ɹ�";
				} else {
					mess = "ɾ��ʧ��";
				}

			} catch (Exception e) {

				e.printStackTrace();
				mess = e.getMessage();

			}
		}
		// ��ʼ����JSON
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("state", state);
		jsonObject.accumulate("mess", mess);
		PrintWriter out = resp.getWriter();
		out.write(jsonObject.toString());
		out.flush();
		out.close();

	}

	private void add() throws ServletException, IOException {

		// �ռ���������
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String st = req.getParameter("st");
		String browser_count = req.getParameter("browser_count");
		String news_type_id = req.getParameter("news_type_id");
		// ����
		String info = req.getParameter("info");
		// �������뼯��
		HashMap<String, String> prams = new HashMap<String, String>();
		prams.put("title", title);
		prams.put("author", author);
		prams.put("st", st);
		prams.put("browser_count", browser_count);
		prams.put("news_type_id", news_type_id);
		prams.put("info", info);
		boolean tempValidate = ValidateUtil.validateNullAndEmpty(prams);

		if (tempValidate) {
			mess = "������Ĳ���������";
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
			mess = "���������������������";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}

		String fileName = "";
		// �ϴ��ļ�
		try {
			Part part = req.getPart("photo");
			// �ؽ��ļ�����
			// 1:���ݲ�ͬ���ļ�������װ��һ��ȫ�µ��ļ������Ҳ����ظ�UUID
			fileName = UUID.randomUUID().toString();
			// 2:�ϴ�����ʲô���͵��ļ�
			String fileExtension = part.getHeader("Content-Type");
			// ����ļ���
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

			// 3:�ļ���������
			String savePath = req.getServletContext().getRealPath("/upload");
			part.write(savePath + "/" + fileName);

		} catch (Exception e) {
			e.printStackTrace();
			mess = "�ϴ��ļ���������";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}

		// ��ʼִ�����ҵ��
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
				mess = "��ӳɹ�";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
				return;
			} else {
				mess = "���ʧ�ܣ���������������11";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
				return;
			}

		} catch (Exception e) {
			// ɾ���ļ�
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
			mess = "��û��ѡ���κη���";
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
