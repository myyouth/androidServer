package com.zjKingdee.androidServer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.entity.UseInfoEntity;
import com.zjKingdee.androidServer.service.PDALoginInService;

@Controller
public class PDALoginController {
	private static final Logger LOG = LoggerFactory.getLogger(PDALoginController.class);
	@Autowired
	private PDALoginInService service;
	@RequestMapping(value = "/login")
	public void login(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	        String jsonParams=request.getParameter("jsonParams");
	        JSONObject info=JSONObject.parseObject(jsonParams);
	        String username=info.getString("username");
	        String password=info.getString("password");
	        UseInfoEntity entity=service.getUserInfo(username, password);
	    	JSONObject obj=new JSONObject();
	    	if(entity==null) {
	        	obj.put("success", "false");
		    	obj.put("msg","用户不存在或账号密码错误");
	        }else {
	        	JSONObject useInfo=JSONObject.parseObject(JSONObject.toJSONString(entity));
	        	obj.put("success", "true");
		    	obj.put("data",useInfo);
	        }
	    	response.getWriter().write(obj.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject obj=new JSONObject();
            obj.put("success", "false");
	    	obj.put("msg",e.getLocalizedMessage());
	    	response.getWriter().write(obj.toJSONString());
			LOG.error(e.getLocalizedMessage());
		}
	} 
	@RequestMapping(value = "/checkLogin")
	public void checkLogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	        String jsonParams=request.getParameter("jsonParams");
	        JSONObject info=JSONObject.parseObject(jsonParams);
	        String staffID=info.getString("userInner");
	        String staffNumber=info.getString("userNumber");
	        boolean flag=service.checkStaffInfo(staffID, staffNumber);
	    	JSONObject obj=new JSONObject();
            obj.put("success", flag);
            obj.put("msg","");
	    	response.getWriter().write(obj.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject obj=new JSONObject();
            obj.put("success", "false");
	    	obj.put("msg",e.getLocalizedMessage());
	    	response.getWriter().write(obj.toJSONString());
			LOG.error(e.getLocalizedMessage());
		}
	} 
	@RequestMapping(value = "/revisePassword")
	public void revisePassword(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	        String staffID=request.getParameter("staffID");
	        String staffPassword=request.getParameter("staffPassword");
	        service.revisePassord(staffID, staffPassword);
	    	JSONObject obj=new JSONObject();
            obj.put("success", true);
            obj.put("msg","密码修改成功");
	    	response.getWriter().write(obj.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject obj=new JSONObject();
            obj.put("success", "false");
	    	obj.put("msg",e.getLocalizedMessage());
	    	response.getWriter().write(obj.toJSONString());
			LOG.error(e.getLocalizedMessage());
		}
	}
	@RequestMapping(value = "/searchPermission")
	public void searchPermission(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String jsonParams=request.getParameter("jsonParams");
	        JSONObject info=JSONObject.parseObject(jsonParams);
	        String roleName=info.getString("roleName");
	        String permission=service.search(roleName);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
            obj.put("msg",permission);
	    	response.getWriter().write(obj.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject obj=new JSONObject();
            obj.put("success", "false");
	    	obj.put("msg",e.getLocalizedMessage());
	    	response.getWriter().write(obj.toJSONString());
			LOG.error(e.getLocalizedMessage());
		}
	} 
}
