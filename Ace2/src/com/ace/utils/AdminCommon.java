package com.ace.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.ace.model.AdminInfo;
import com.ace.model.PageVO;
import com.ace.utils.StringUtil;


@Component
public class AdminCommon {
	public final static String ADMIN_KEY = "adminInfo";
	
	public String getAdminEmail(HttpServletRequest request) {
		AdminInfo adminInfo = ((AdminInfo)request.getSession().getAttribute("adminInfo"));
		if(adminInfo != null) {
			return adminInfo.getEmail();
		}
		else {
			return "";
		}		
	}
	
	public void setAdminPagingInfo(HttpServletRequest request, PageVO vo) {//, PaginationInfo paginationInfo
		if(request.getParameter("pageNo") != null){        	
			vo.setPageIndex(Integer.parseInt(request.getParameter("pageNo")));
		}        
/*	   	paginationInfo.setCurrentPageNo(vo.getPageIndex());
	   	paginationInfo.setRecordCountPerPage(vo.getPageUnit());
	   	paginationInfo.setPageSize(vo.getPageSize());	   	
*/
	   	vo.setFirstIndex((vo.getPageIndex() - 1) * vo.getPageUnit());
	   	vo.setLastIndex(vo.getPageIndex() * vo.getPageUnit());	   	
	}	
}
