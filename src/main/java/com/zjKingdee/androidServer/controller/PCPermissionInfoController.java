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
import com.zjKingdee.androidServer.service.PCPermissionInfoService;

@Controller
public class PCPermissionInfoController {
	private static final Logger LOG = LoggerFactory.getLogger(PCPermissionInfoController.class);
	@Autowired
	private PCPermissionInfoService service;
	@RequestMapping(value = "/searchAllPermissionInfo")
	public void searchAllPermissionInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String query=request.getParameter("query");
	    	JSONObject paramObj=JSONObject.parseObject(query);
			Map<String,Object> map=service.getAllPermissionInfo(paramObj);
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
	
	@RequestMapping(value = "/createPermissionInfo")
	public void createPermissionInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String ruleForm=request.getParameter("ruleForm");
	    	JSONObject paramObj=JSONObject.parseObject(ruleForm);
			service.createPermissionInfo(paramObj);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg","权限创建成功");
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
	
	@RequestMapping(value = "/deletePermissionInfo")
	public void deletePermissionInfo(HttpServletRequest request,HttpServletResponse response,@RequestParam("roleInfo")String roleInfo) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
			service.deletePermissionInfo(roleInfo);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg","权限删除成功");
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
