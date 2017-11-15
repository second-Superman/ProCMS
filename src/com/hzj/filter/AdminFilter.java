package com.hzj.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hzj.util.path.PathUtil;

@WebFilter(filterName = "A002AdminFilter", urlPatterns = { "/admin/*" })
public class AdminFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("AdminFilter.................");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 转换request为HttpServletRequest
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		// 获取HttpSession对象
		HttpSession httpSession = httpServletRequest.getSession();
		// 判管理员是否已经登录
		if (httpSession.getAttribute("admin") != null) {
			// 如果已经有管理员登录，则继续前进【放行】
			chain.doFilter(request, response);
		} else {

			// 如果没有管理员登录，则重定向到某一个页面（因为不需要任何传递参数，因此直接用重定向）
			httpServletResponse.sendRedirect(PathUtil.getPath(httpServletRequest, "index.jsp"));
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
