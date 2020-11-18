package com.zjKingdee.androidServer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.service.PCUserInfoService;

@Controller
public class PCUserInfoController {
	private static final Logger LOG = LoggerFactory.getLogger(PCUserInfoController.class);
	@Autowired
	private PCUserInfoService service;
	@RequestMapping(value = "/searchAllUserInfo")
	public void searchAllUserInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String query=request.getParameter("query");
	    	JSONObject paramObj=JSONObject.parseObject(query);
			Map<String,Object> map=service.getAllUseInfo(paramObj);
	    	JSONArray json= JSONArray.parseArray(JSON.toJSONString(map.get("data")));
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("data",json);
	    	obj.put("pageTotal",map.get("pageTotal"));
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
	
	@RequestMapping(value = "/createUserInfo")
	public void createUserInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String ruleForm=request.getParameter("ruleForm");
	    	JSONObject paramObj=JSONObject.parseObject(ruleForm);
			service.createUserInfo(paramObj);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg","用户创建成功");
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
	
	@RequestMapping(value = "/deleteUserInfo")
	public void deleteUserInfo(HttpServletRequest request,HttpServletResponse response,@RequestParam("userInfo")String userInfo) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
			service.deleteUserInfo(userInfo);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg","用户删除成功");
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
	
	@RequestMapping(value = "/reviseUserInfo")
	public void reviseUserInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String reviseForm=request.getParameter("reviseForm");
	    	JSONObject paramObj=JSONObject.parseObject(reviseForm);
			service.ReviseUserInfo(paramObj);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg","用户修改成功");
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
