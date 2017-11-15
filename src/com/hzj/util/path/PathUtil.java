package com.hzj.util.path;

import javax.servlet.http.HttpServletRequest;

public class PathUtil {

	public static String getPath(HttpServletRequest req, String url) {
		
		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		return basePath+url;
	}

}
