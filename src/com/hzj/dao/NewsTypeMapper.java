package com.hzj.dao;

import java.util.ArrayList;

import com.hzj.beans.NewsType;

public interface NewsTypeMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(NewsType record);

	NewsType selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(NewsType record);
	
	ArrayList<NewsType> selectByAll();
}