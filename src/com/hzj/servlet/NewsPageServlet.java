package com.hzj.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzj.beans.News;
import com.hzj.beans.NewsInfo;
import com.hzj.beans.NewsType;
import com.hzj.server.NewsInfoServer;
import com.hzj.server.NewsServer;
import com.hzj.server.NewsTypeServer;
import com.hzj.util.validate.ValidateUtil;

@WebServlet(name = "NewsPageServlet", urlPatterns = { "/NewsPageServlet.action" })
public class NewsPageServlet extends HttpServlet {

	HttpServletRequest req;
	HttpServletResponse resp;

	// 参数

	String mess = "";
	String newsTypeId = "-1";

	ArrayList<News> news;
	ArrayList<News> newsTop10;
	ArrayList<News> newsBrowserCountTop10;
	
	ArrayList<NewsType> newsTypes;
	
	NewsType newsType;

	// 业务
	NewsServer newsServer;
	NewsTypeServer newsTypeServer;
	// tag
	boolean tag = true;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 初始化
		this.req = req;
		this.resp = resp;
		tag = true;
		newsServer = new NewsServer();
		newsTypeServer=new NewsTypeServer();

		HashMap<String, String> prams = new HashMap<String, String>();
		newsTypeId = req.getParameter("newsTypeId");
		prams.put("newsTypeId", newsTypeId);
		if (ValidateUtil.validateNullAndEmpty(prams)) {
			mess = "你的参数有问题";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
		}

		int newsTypeIdTemp = -1;
		if (tag) {
			try {
				newsTypeIdTemp = Integer.parseInt(newsTypeId);
			} catch (Exception e) {
				mess = "你的参数有问题,不是个整数";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
			}

		}

		if (tag) {

			try {
				
				newsTypes=newsTypeServer.findByAll();
				newsType=newsTypeServer.findById(newsTypeIdTemp);
				news = newsServer.findByNewsTypeId(newsTypeIdTemp);
				newsTop10 = newsServer.findByNewsTypeIdAndTop10(newsTypeIdTemp);
				newsBrowserCountTop10 = newsServer.findByNewsTypeIdAndBrowserCountTop10(newsTypeIdTemp);
				
				req.setAttribute("newsType",newsType);
				req.setAttribute("newsTypes", newsTypes);
				req.setAttribute("news", news);
				req.setAttribute("newsTop10", newsTop10);
				req.setAttribute("newsBrowserCountTop10", newsBrowserCountTop10);
				req.getRequestDispatcher("news.jsp").forward(req, resp);
				tag = false;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mess = e.getMessage();
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
			}

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
