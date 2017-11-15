package com.hzj.dao;

import com.hzj.beans.NewsInfo;

public interface NewsInfoMapper {

	NewsInfo selectByNewsID(Integer newsID);
	
	
    int deleteByPrimaryKey(Integer id);
    int insert(NewsInfo record);
  
    int updateByPrimaryKey(NewsInfo record);
}