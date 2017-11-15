package com.hzj.dao;

import com.hzj.beans.Admin;

public interface AdminMapper {



	Admin selectByUsernameAndPassword(Admin record);
	int updateByPrimaryKey(Admin record);
}