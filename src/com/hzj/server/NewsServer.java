package com.hzj.server;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import com.hzj.beans.News;
import com.hzj.beans.NewsInfo;
import com.hzj.dao.NewsInfoMapper;
import com.hzj.dao.NewsMapper;
import com.hzj.dao.NewsTypeMapper;
import com.hzj.util.mybatis.MyBatisUtil;

public class NewsServer {

	public boolean del(HttpServletRequest req, Integer id) throws Exception {
		boolean temp = false;
		String photo = "";
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
			photo = newsMapper.selectPhotoById(id);
			newsMapper.deleteByPrimaryKey(id);
			
			
			sqlSession.commit();
			temp = true;
		} catch (Exception e) {
			e.printStackTrace();
			temp = false;
			throw new Exception() {
				@Override
				public String getMessage() {
					// TODO Auto-generated method stub
					return "删除错误";
				}
			};

		} finally {
			sqlSession.close();
		}

		if (temp) {
			String savePath = req.getServletContext().getRealPath("/upload");
			// 组合文件地址
			savePath = savePath + File.separator + photo;
			// 判断文件是否真实存在
			File file = new File(savePath);
			if (file.exists()) {
				// 如果存在则删除
				try {
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return temp;
	}

	public ArrayList<News> findByAll() throws Exception {
		ArrayList<News> temp = new ArrayList<News>();
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
			temp = newsMapper.selectByAll();
		} catch (Exception e) {
			e.printStackTrace();
			temp = new ArrayList<News>();
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

	public ArrayList<News> findByNewsTypeId(int newsTypeId) throws Exception {
		ArrayList<News> temp = new ArrayList<News>();
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
			temp = newsMapper.selectByNewsTypeId(newsTypeId);
		} catch (Exception e) {
			e.printStackTrace();
			temp = new ArrayList<News>();
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

	public ArrayList<News> findByNewsTypeIdAndTop10(int newsTypeId) throws Exception {
		ArrayList<News> temp = new ArrayList<News>();
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
			temp = newsMapper.selectByNewsTypeIdAndTop10(newsTypeId);
		} catch (Exception e) {
			e.printStackTrace();
			temp = new ArrayList<News>();
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

	public ArrayList<News> findByNewsTypeIdAndBrowserCountTop10(int newsTypeId) throws Exception {
		ArrayList<News> temp = new ArrayList<News>();
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
			temp = newsMapper.selectByNewsTypeIdAndBrowserCountTop10(newsTypeId);
		} catch (Exception e) {
			e.printStackTrace();
			temp = new ArrayList<News>();
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

	public ArrayList<News> findByNewsTypeAnd4to6(int newsTypeId) throws Exception {

		ArrayList<News> temp = new ArrayList<News>();
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
			temp = newsMapper.selectByNewsTypeAnd4to6(newsTypeId);
		} catch (Exception e) {

			e.printStackTrace();

			temp = new ArrayList<News>();
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

	public ArrayList<News> findByNewsTypeAnd1to3(int newsTypeId) throws Exception {

		ArrayList<News> temp = new ArrayList<News>();
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
			temp = newsMapper.selectByNewsTypeAnd1to3(newsTypeId);
		} catch (Exception e) {

			e.printStackTrace();

			temp = new ArrayList<News>();
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

	public boolean add(News news, NewsInfo newsInfo) throws Exception {
		boolean temp = false;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
			newsMapper.insert(news);
			NewsInfoMapper newsInfoMapper = sqlSession.getMapper(NewsInfoMapper.class);
			newsInfo.setNews(news);
			newsInfoMapper.insert(newsInfo);
			sqlSession.commit();
			temp = true;
		} catch (Exception e) {
			e.printStackTrace();
			temp = false;
			throw new Exception() {
				@Override
				public String getMessage() {
					// TODO Auto-generated method stub
					return "添加错误";
				}
			};

		} finally {
			sqlSession.close();
		}

		return temp;
	}

}
