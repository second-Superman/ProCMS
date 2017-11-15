package com.hzj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzj.beans.News;
import com.hzj.beans.NewsType;
import com.hzj.foreground.beans.IndexListItemBean;
import com.hzj.server.NewsServer;
import com.hzj.server.NewsTypeServer;

@WebServlet(name = "IndexServlet", urlPatterns = { "/IndexServlet.action" })
public class IndexServlet extends HttpServlet {
	HttpServletRequest req;
	HttpServletResponse resp;
	// 业务
	NewsTypeServer newsTypeServer;
	NewsServer newsServer;
	String mess = "";
	ArrayList<NewsType> newsTypes;
	ArrayList<IndexListItemBean> indexListItemBeans;
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
		indexListItemBeans=new ArrayList<IndexListItemBean>();
		
		
		//获取所有类型
		try {
			newsTypes=newsTypeServer.findByAll();
		} catch (Exception e) {
			e.printStackTrace();
			mess=e.getMessage();
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
		}
		
//		1：类型
//		2：该类型1-3 
//		3：该类型4-6  
		
		
		if(tag){
			
			
			try {
				
				
				for (NewsType newsTypeTemp : newsTypes) {
					IndexListItemBean indexListItemBean=new IndexListItemBean();
					indexListItemBean.setNewsType(newsTypeTemp);
					ArrayList<News> leftNews=newsServer.findByNewsTypeAnd1to3(newsTypeTemp.getId());
					ArrayList<News> rightNews=newsServer.findByNewsTypeAnd4to6(newsTypeTemp.getId());
					indexListItemBean.setLeftNews(leftNews);
					indexListItemBean.setRightNews(rightNews);
					indexListItemBeans.add(indexListItemBean);
				}
				
				req.setAttribute("indexListItemBeans", indexListItemBeans);
				req.setAttribute("newsTypes", newsTypes);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
				tag = false;
				
				
			} catch (Exception e) {
				
				
				e.printStackTrace();
				mess=e.getMessage();
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
