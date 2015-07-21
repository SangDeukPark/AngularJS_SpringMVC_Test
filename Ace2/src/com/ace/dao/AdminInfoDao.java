package com.ace.dao;

import java.util.List;

import org.springframework.context.annotation.Lazy;

import com.ace.model.AdminInfo;
import com.ace.model.PageVO;

@Lazy(value=true)
public interface AdminInfoDao {
	public AdminInfo selectAdminInfoByEmail(AdminInfo vo);
	
	public AdminInfo selectAdminInfo(AdminInfo vo);
	
	public void insertAdminInfo(AdminInfo vo);
	
	public List<AdminInfo> selectAdminInfoList(PageVO vo);
	
	public int selectAdminInfoListCnt(PageVO vo);
	
	public AdminInfo selectAdminInfoById(AdminInfo vo);
	
	public void updateAdminInfo(AdminInfo vo);
	
	public void deleteAdminInfo(AdminInfo vo);
	
	public void updateLoginFail(String email);
	
	public void updateLoginSuccess(AdminInfo vo);
}
