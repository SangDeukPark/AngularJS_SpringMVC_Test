package com.ace.dao;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ace.model.AdminInfo;

@Lazy(value=true)
public interface AdminInfoDao {
	public AdminInfo selectAdminInfoByEmail(AdminInfo vo);
	
	public AdminInfo selectAdminInfo(AdminInfo vo);
	
	public void insertAdminInfo(AdminInfo vo);
	
	public List<AdminInfo> selectAdminInfoList(AdminInfo vo);
	
	public int selectAdminInfoListCnt(AdminInfo vo);
	
	public AdminInfo selectAdminInfoById(AdminInfo vo);
	
	public void updateAdminInfo(AdminInfo vo);
	
	public void deleteAdminInfo(AdminInfo vo);
	
	public void updateLoginFail(String email);
	
	public void updateLoginSuccess(AdminInfo vo);
}
