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

@WebServlet(
		name="InfoPageServlet",
		urlPatterns={"/InfoPageServlet.action"}
		)
public class InfoPageServlet extends HttpServlet{

	HttpServletRequest req;
	HttpServletResponse resp;

	// 参数

	String mess = "";
	String newsId = "-1";

	NewsInfo newsInfo;
	NewsType newsType;
	ArrayList<NewsType> newsTypes;
	ArrayList<News> newsTop10;
	ArrayList<News> newsBrowserCountTop10;

	// 业务
	NewsInfoServer newsInfoServer;
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
		newsServer=new NewsServer();
		newsInfoServer=new NewsInfoServer();
		newsTypeServer=new NewsTypeServer();
		
		HashMap<String, String> prams = new HashMap<String, String>();
		newsId = req.getParameter("newsId");
		prams.put("newsId", newsId);
		if (ValidateUtil.validateNullAndEmpty(prams)) {
			mess = "你的参数有问题";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
		}
		
		int newsIdTemp =-1;
		
		if(tag){
			try {
				
				newsIdTemp=Integer.parseInt(newsId);
				
			} catch (Exception e) {
				e.printStackTrace();
				mess = "你的参数有问题,不是数字";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
			}
		}
		
		if(tag){
			
			try {
				
				newsInfo=newsInfoServer.findByNewsID(newsIdTemp);
				newsType= newsInfo.getNews().getNewsType();
				newsTypes=newsTypeServer.findByAll();
				newsTop10=newsServer.findByNewsTypeIdAndTop10(newsType.getId());
				newsBrowserCountTop10=newsServer.findByNewsTypeIdAndBrowserCountTop10(newsType.getId());
				req.setAttribute("newsType", newsType);
				req.setAttribute("newsTypes", newsTypes);
				req.setAttribute("newsInfo", newsInfo);
				req.setAttribute("newsTop10", newsTop10);
				req.setAttribute("newsBrowserCountTop10", newsBrowserCountTop10);
				req.getRequestDispatcher("info.jsp").forward(req, resp);
				tag = false;
				
			} catch (Exception e) {
				e.printStackTrace();
				
				
				mess = e.getMessage()==null?"你输入的参数有错误":e.getMessage();
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
			}
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	
	
}
