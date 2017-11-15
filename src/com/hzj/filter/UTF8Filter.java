package com.hzj.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.google.code.kaptcha.servlet.KaptchaServlet;

@WebFilter(filterName = "A001UTF8Filter", urlPatterns = { "/*" })
public class UTF8Filter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	System.out.println("UTF8Filter.................");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {

	}

}
