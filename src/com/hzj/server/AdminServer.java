package com.hzj.server;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;

import com.hzj.beans.Admin;
import com.hzj.dao.AdminMapper;
import com.hzj.util.md5.MD5Util;
import com.hzj.util.mybatis.MyBatisUtil;

public class AdminServer {

	public Admin login(Admin admin) {

		Admin temp = null;

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
		   
		
		admin.setPassword(MD5Util.MD5Encode(admin.getPassword()));	    
		
		
		temp = adminMapper.selectByUsernameAndPassword(admin);
		sqlSession.close();
		return temp;

	}

}
