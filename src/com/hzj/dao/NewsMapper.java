package com.hzj.dao;
import java.util.ArrayList;
import com.hzj.beans.News;
import com.hzj.beans.NewsType;

public interface NewsMapper {
	ArrayList<News>  selectByAll();
    int deleteByPrimaryKey(Integer id);
    int insert(News record);
    
    
    String selectPhotoById(Integer id);
   
    
    
    ArrayList<News>  selectByNewsTypeAnd1to3(int newsTypeId);
    ArrayList<News>  selectByNewsTypeAnd4to6(int newsTypeId);
    
    
    ArrayList<News>  selectByNewsTypeId(int newsTypeId);
    ArrayList<News>  selectByNewsTypeIdAndTop10(int newsTypeId);
    ArrayList<News>  selectByNewsTypeIdAndBrowserCountTop10(int newsTypeId);
    
     
    
    int updateByPrimaryKey(News record);
}