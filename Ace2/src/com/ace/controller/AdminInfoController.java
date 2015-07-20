package com.ace.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ace.model.AdminInfo;
import com.ace.service.AdminInfoService;
import com.ace.utils.AdminCommon;
import com.ace.utils.StringUtil;


@Lazy(value=true)
@Controller
@RequestMapping("/adminInfo")
public class AdminInfoController {
	@Autowired
	private AdminInfoService adminInfoService;
	@Autowired
	private AdminCommon adminCommon;
	
    /**
	 *  get Login Form
	 */	
/*    @RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public ModelAndView loginForm(ModelMap model) throws Exception
	{
    	return new ModelAndView("/adminInfo/loginForm");
	}*/
    
	/**
	 *  Login Info Check
	 */	
    @RequestMapping(value="/loginForm", method = RequestMethod.POST)    
	public @ResponseBody boolean loginForm(@RequestBody AdminInfo adminInfo, Model model, HttpServletRequest request) throws Exception
	{   //
    	String strPassword = adminInfo.getPassword();//EncryptUtils.toSHA256(    	
    	adminInfo = adminInfoService.selectAdminInfoByEmail(adminInfo);
    	
    	
    	if(adminInfo != null) {
    		int loginFailCount = StringUtil.nullToInteger(adminInfo.getLoginFailCount());    		
    		/*    		long loginInterval = DateUtil.getDateTimesDiff(DateUtil.getCurrentTime("yyyyMMddHHmm"), 
    				DateUtil.dateFormat(adminInfo.getLastLoginDate(), "yyyyMMddHHmm"));
    		if(loginFailCount > 5 && loginInterval < 15) {
    			model.addAttribute("result", "FailLogin5");
    		}
    		else {*/
    			adminInfo.setPassword(strPassword);
    			String email = adminInfo.getEmail();
    			adminInfo = adminInfoService.selectAdminInfo(adminInfo);
    			
	    		if(adminInfo == null) {
	        		adminInfoService.updateLoginFail(email);
	        		loginFailCount += 1;
	        		
	    			model.addAttribute("result", "Fail");
	    			model.addAttribute("failCount", loginFailCount);
	    			
	        		return false;
	        	}
	        	else {
	        		adminInfoService.updateLoginSuccess(adminInfo);
	        		request.getSession().setAttribute(AdminCommon.ADMIN_KEY, adminInfo);

/*	        		int dateInterval = DateUtil.getDaysDiff(DateUtil.getToday(), DateUtil.dateFormat(adminInfo.getLastPassCreatedDate(), "yyyyMMdd"));

	        		if(dateInterval > 90) {
	        			adminInfo.setLastPassYN("Y");
	        		}
	        		else {
	        			adminInfo.setLastPassYN("N");
	        		}*/
	        		model.addAttribute("id", adminInfo.getId());
	        		return true;
	        	}
    		//}
    	}
    	else {
    		model.addAttribute("result", "noneUser");
    	}
    	
    	return false;
	}
    
    /**
	 *  Logout
	 */	
    @RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(@ModelAttribute("adminInfo") AdminInfo adminInfo, HttpServletRequest request) throws Exception
	{
    	request.getSession().setAttribute(AdminCommon.ADMIN_KEY, null);
    	request.getSession().invalidate();
    	return "redirect:/adminInfo/loginForm"; 
	}    
    
    /**
	 * Get Register Form
	 */	
    @RequestMapping(value="/adminInfoReg", method=RequestMethod.GET)
	public String adminInfoForm(@ModelAttribute("adminInfo") AdminInfo adminInfo, Model model) throws Exception
	{
    	model.addAttribute("command", "I");
    	
    	return "/adminInfo/adminInfoReg"; 
	}  
    
    /**
 	 * insert,update Member Info
 	 */	
    @RequestMapping(value="/adminInfoReg", method = RequestMethod.POST)    
 	public String insertAdminInfo(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, HttpServletRequest request) throws Exception	
 	{ 
 	    //adminInfo.setEmail(adminCommon.getAdminEmail(request));
 	    adminInfo.setPassword(adminInfo.getPassword());//EncryptUtils.toSHA256(
 	   		
 	   	if("I".equals(StringUtil.nullToEmpty(request.getParameter("command")))) {
 	   		
 	   		adminInfoService.insertAdminInfo(adminInfo);
 	   	}
     	else if ("U".equals(StringUtil.nullToEmpty(request.getParameter("command")))) {
     		adminInfo.setId(StringUtil.nullToEmpty(request.getParameter("id")));
     		adminInfoService.updateAdminInfo(adminInfo);
     	}
 	   	
 	   	return "redirect:/adminInfo/adminInfoList";
 	}  
    
	/**
	 * Get member list
	 */	
    @RequestMapping(value="/adminInfoList")
	public @ResponseBody List<AdminInfo> adminInfoList(AdminInfo adminInfoVO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
	  	//adminCommon.setAdminPagingInfo(request, adminInfoVO, paginationInfo);	   	
	
		int count = adminInfoService.selectAdminInfoListCnt(adminInfoVO);
		
		//paginationInfo.setTotalRecordCount(count);
		List<AdminInfo> resultList = adminInfoService.selectAdminInfoList(adminInfoVO);      
	
		model.addAttribute("adminInfoList", resultList);
		//model.addAttribute("paginationInfo", paginationInfo);        
	       
	   	return resultList; 
	}   
  
	/**
	 * get Member Info by id
	 */	
  	@RequestMapping(value="/adminInfoView/{id}")
	public String selectAdminInfoById(@ModelAttribute AdminInfo adminInfo, @PathVariable String id, Model model) throws Exception
	{        	    	
	 	adminInfo.setId(StringUtil.nullToEmpty(id));
	 	adminInfo = adminInfoService.selectAdminInfoById(adminInfo);
	 	
	 	model.addAttribute("command", "U");
	 	model.addAttribute("adminInfo", adminInfo);
	 	
	 	return "/adminInfo/adminInfoReg"; 
	}     
  	
    /**
	 *  Delete member info
	 */
    @RequestMapping(value="/deleteAdminInfo/{id}")
	public String deleteAdminInfo(AdminInfo adminInfoVO, @PathVariable String id, Model model) throws Exception
	{    
    	adminInfoVO.setId(StringUtil.nullToEmpty(id));
       	adminInfoService.deleteAdminInfo(adminInfoVO);       	
       	
    	return "redirect:/adminInfo/adminInfoList";
	}    	
}
