package com.hzj.server;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.hzj.beans.News;
import com.hzj.beans.NewsInfo;
import com.hzj.dao.NewsInfoMapper;
import com.hzj.dao.NewsMapper;
import com.hzj.util.mybatis.MyBatisUtil;

public class NewsInfoServer {

	public NewsInfo findByNewsID(Integer newsID) throws Exception {
		NewsInfo temp = null;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NewsInfoMapper newsInfoMapper = sqlSession.getMapper(NewsInfoMapper.class);
			temp = newsInfoMapper.selectByNewsID(newsID);
		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception() {

				@Override
				public String getMessage() {
					// TODO Auto-generated method stub
					return "查询数据库出现了问题";
				}
			};

		} finally {
			sqlSession.close();
		}

		return temp;
	}

}
