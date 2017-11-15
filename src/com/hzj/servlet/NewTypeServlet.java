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
	// ҵ��
	NewsTypeServer newsTypeServer = null;

	// ��������ǰ�˵���ˡ�======================================
	// ��������
	String method = "";

	// ����beans����
	String id = "0";
	String name = "";

	// ��������ת������˵���ˣ���˵�ǰ�ˣ�Ҳ����Ҫ����req.setAttribute(arg0,
	// arg1);��======================================
	// �����������ת��
	String mess = "";
	String state = "";
	ArrayList<NewsType> newsTypes;
	// ���
	boolean tag = true;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��ʼ��
		this.req = req;
		this.resp = resp;
		tag = true;
		newsTypeServer = new NewsTypeServer();
		// ����������ȡ
		methodParameter();

		// ��ʼѡ�񷽷�ִ��
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
				mess = "��ѡ��ķ��񲻴���";
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
			mess = "������Ʋ��������";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}
		
		
		
		int tempId = -1;
		try {
			tempId = Integer.parseInt(id);
		} catch (Exception e) {
			mess = "��Ĳ�������ת����������";
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
				mess = "���޸����ݳɹ�";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
				return;
			} else {
				mess = "���޸ĵ�����������";
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
			mess = "��Ĳ�����������";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}

		int tempId = -1;
		try {
			tempId = Integer.parseInt(id);
		} catch (Exception e) {
			mess = "��Ĳ�������ת����������";
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
			mess = "��Ĳ�����������";
			state = "0";
			tempTag = false;
		}

		int tempID = -1;
		if (tempTag) {
			try {
				// ת��
				tempID = Integer.parseInt(id);
			} catch (Exception e) {
				mess = "��Ĳ�������ת�ʹ���";
				state = "0";
				tempTag = false;
			}
		}

		if (tempTag) {

			try {
				if (newsTypeServer.del(tempID)) {

					mess = "ɾ���ɹ�";
					state = "1";

				} else {
					mess = "��ɾ��������ʧ��";
					state = "0";
				}
			} catch (Exception e) {
				mess = e.getMessage();
				state = "0";
			}

		}

		// ���
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
			mess = "������Ʋ��������";
			req.setAttribute("mess", mess);
			req.getRequestDispatcher("mess.jsp").forward(req, resp);
			tag = false;
			return;
		}

		NewsType newsType = new NewsType();
		newsType.setName(name);

		try {

			if (newsTypeServer.add(newsType)) {
				mess = "��������ݳɹ�";
				req.setAttribute("mess", mess);
				req.getRequestDispatcher("mess.jsp").forward(req, resp);
				tag = false;
				return;
			} else {
				mess = "����ӵ�����������";
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
