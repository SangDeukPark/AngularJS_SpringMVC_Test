package com.ace.service;


import java.util.List;

import com.ace.model.AdminInfo;
import com.ace.model.PageVO;

public interface AdminInfoService {
	public AdminInfo selectAdminInfoByEmail(AdminInfo vo) throws Exception;
	
	public AdminInfo selectAdminInfo(AdminInfo vo) throws Exception;
	
	public void insertAdminInfo(AdminInfo vo) throws Exception;
	
	public List<AdminInfo> selectAdminInfoList(PageVO vo) throws Exception;
	
	public int selectAdminInfoListCnt(PageVO vo) throws Exception;
	
	public AdminInfo selectAdminInfoById(AdminInfo vo) throws Exception;
	
	public void updateAdminInfo(AdminInfo vo) throws Exception;
	
	public void deleteAdminInfo(AdminInfo vo) throws Exception;	
	
	public void updateLoginFail(String email) throws Exception;
	
	public void updateLoginSuccess(AdminInfo vo) throws Exception;
	
}
