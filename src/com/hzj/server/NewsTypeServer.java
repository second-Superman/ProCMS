package com.hzj.server;

import java.util.ArrayList;
import org.apache.ibatis.session.SqlSession;
import com.hzj.beans.NewsType;
import com.hzj.dao.NewsTypeMapper;
import com.hzj.util.mybatis.MyBatisUtil;

public class NewsTypeServer {
	public boolean del(Integer id) throws Exception {

		boolean temp = false;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NewsTypeMapper newsTypeMapper = sqlSession.getMapper(NewsTypeMapper.class);
			temp = newsTypeMapper.deleteByPrimaryKey(id) > 0 ? true : false;
			sqlSession.commit();
		} catch (Exception e) {

			temp = false;
			throw new Exception() {
				@Override
				public String getMessage() {

					return "�����������޷�ɾ��������";
				}
			};
		} finally {
			sqlSession.close();
		}

		return temp;

	}

	public boolean add(NewsType record) throws Exception {

		boolean temp = false;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NewsTypeMapper newsTypeMapper = sqlSession.getMapper(NewsTypeMapper.class);
			temp = newsTypeMapper.insert(record) > 0 ? true : false;
			sqlSession.commit();
		} catch (Exception e) {

			temp = false;
			throw new Exception() {
				@Override
				public String getMessage() {
					// TODO Auto-generated method stub
					return "�����ظ�";
				}
			};

		} finally {
			sqlSession.close();
		}

		return temp;
	}

	public boolean edit(NewsType record) throws Exception {
		boolean temp = false;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NewsTypeMapper newsTypeMapper = sqlSession.getMapper(NewsTypeMapper.class);
			temp = newsTypeMapper.updateByPrimaryKey(record) > 0 ? true : false;
			sqlSession.commit();
		} catch (Exception e) {
			temp = false;
			throw new Exception() {
				@Override
				public String getMessage() {
					// TODO Auto-generated method stub
					return "�޸�����ʱ�����ظ�";
				}
			};
		} finally {
			sqlSession.close();
		}

		return temp;
	}

	public NewsType findById(Integer id) throws Exception {
		NewsType temp = null;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NewsTypeMapper newsTypeMapper = sqlSession.getMapper(NewsTypeMapper.class);
			temp = newsTypeMapper.selectByPrimaryKey(id);
		} catch (Exception e) {

			temp = null;
			throw new Exception() {

				@Override
				public String getMessage() {
					// TODO Auto-generated method stub
					return "��ѯ���ݿ����������";
				}
			};

		} finally {
			sqlSession.close();
		}

		return temp;
	}

	public ArrayList<NewsType> findByAll() throws Exception {
		ArrayList<NewsType> temp = new ArrayList<NewsType>();
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			NewsTypeMapper newsTypeMapper = sqlSession.getMapper(NewsTypeMapper.class);
			temp = newsTypeMapper.selectByAll();
		} catch (Exception e) {

			temp = new ArrayList<NewsType>();
			throw new Exception() {

				@Override
				public String getMessage() {
					// TODO Auto-generated method stub
					return "��ѯ���ݿ����������";
				}
			};

		} finally {
			sqlSession.close();
		}

		return temp;
	}

}
