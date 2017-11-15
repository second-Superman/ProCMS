package com.hzj.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzj.beans.News;
import com.hzj.beans.NewsInfo;
import com.hzj.server.NewsInfoServer;
import com.hzj.server.NewsServer;

@WebServlet(name = "NewInfoServlet", urlPatterns = { "/admin/NewInfoServlet.action" })
public class NewInfoServlet extends HttpServlet {

	public static final String FIND_BY_NEWS_ID = "findbynewsid";

	HttpServletRequest req;
	HttpServletResponse resp;

	// ����
	String method = "";

	String mess = "";
	String newsId = "-1";

	NewsInfo newsInfo;

	// ҵ��
	NewsInfoServer newsInfoServer;
	// tag
	boolean tag = true;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ��ʼ��
		this.req = req;
		this.resp = resp;
		tag = true;
		newsInfoServer = new NewsInfoServer();
		methodParameter();

		// ��ʼѡ�񷽷�ִ��
		if (tag) {
			if (method.equals(FIND_BY_NEWS_ID)) {

				findByNewsId();

			} else {
				mess = "��ѡ��ķ��񲻴���";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
			}
		}
	}

	private void findByNewsId() throws ServletException, IOException {

		newsId = req.getParameter("newsId");
		if (newsId == null || newsId.equals("")) {

			mess = "��Ĳ���������";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}

		Integer tempNewsId = -1;
		try {

			tempNewsId = Integer.parseInt(newsId);
		} catch (Exception e) {
			mess = "��Ĳ�������������";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}

		try {
			newsInfo = newsInfoServer.findByNewsID(tempNewsId);
			req.setAttribute("newsInfo", newsInfo);
			req.getRequestDispatcher("newsInfo.jsp").forward(req, resp);
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
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
