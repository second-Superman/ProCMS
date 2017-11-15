package com.hzj.foreground.beans;

import java.util.ArrayList;

import com.hzj.beans.News;
import com.hzj.beans.NewsType;

public class IndexListItemBean {

	NewsType newsType;
	ArrayList<News> leftNews;
	ArrayList<News> rightNews;
	
	public NewsType getNewsType() {
		return newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public ArrayList<News> getLeftNews() {
		return leftNews;
	}

	public void setLeftNews(ArrayList<News> leftNews) {
		this.leftNews = leftNews;
	}

	public ArrayList<News> getRightNews() {
		return rightNews;
	}

	public void setRightNews(ArrayList<News> rightNews) {
		this.rightNews = rightNews;
	}

}
