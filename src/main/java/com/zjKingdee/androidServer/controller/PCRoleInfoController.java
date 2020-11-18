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
import com.zjKingdee.androidServer.service.PCRoleInfoService;

@Controller
public class PCRoleInfoController {
	private static final Logger LOG = LoggerFactory.getLogger(PCRoleInfoController.class);
	@Autowired
	private PCRoleInfoService service;
	@RequestMapping(value = "/searchAllRoleInfo")
	public void searchAllRoleInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String query=request.getParameter("query");
	    	JSONObject paramObj=JSONObject.parseObject(query);
			Map<String,Object> map=service.getAllRoleInfo(paramObj);
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
	
	@RequestMapping(value = "/searchAllRoleInfoForUser")
	public void searchAllRoleInfoForUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	JSONArray array=service.getAllRoleInfoForUser();
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("data",array.toJSONString());
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
	
	@RequestMapping(value = "/createRoleInfo")
	public void createRoleInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String ruleForm=request.getParameter("ruleForm");
	    	JSONObject paramObj=JSONObject.parseObject(ruleForm);
			service.createRoleInfo(paramObj);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg","角色创建成功");
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
	
	@RequestMapping(value = "/deleteRoleInfo")
	public void deleteRoleInfo(HttpServletRequest request,HttpServletResponse response,@RequestParam("roleInfo")String roleInfo) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
			service.deleteRoleInfo(roleInfo);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg","角色删除成功");
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
	/**
     * ---------------------------------------------------------------------------------------------------------------------------------------------------------
     */
	@RequestMapping(value = "/createUsersInRole")
	public void createUsersInRole(HttpServletRequest request,HttpServletResponse response,@RequestParam("users")String users) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String roleId=request.getParameter("roleId");
			service.createUsersInRole(roleId, users);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg","创建成功");
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
	
	@RequestMapping(value = "/searchAllUserInfoForRole")
	public void searchAllUserInfoForRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	JSONArray array=service.getAllUserInfoForRole();
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("data",array.toJSONString());
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
	
	@RequestMapping(value = "/searchAllUseInfoInRole")
	public void searchAllUseInfoInRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String query=request.getParameter("query");
	    	JSONObject paramObj=JSONObject.parseObject(query);
			Map<String,Object> map=service.getAllUseInfoInRole(paramObj);
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
	
	@RequestMapping(value = "/deleteUsersInfoInRole")
	public void deleteUsersInfoInRole(HttpServletRequest request,HttpServletResponse response,@RequestParam("userInfo")String userInfo) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String roleId=request.getParameter("roleId");
			service.deleteUsersInfoInRole(userInfo,roleId);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg","删除成功");
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
	/**
     * ---------------------------------------------------------------------------------------------------------------------------------------------------------
     */
	@RequestMapping(value = "/createPermissionsInRole")
	public void createPermissionsInRole(HttpServletRequest request,HttpServletResponse response,@RequestParam("permissions")String permissions) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String roleId=request.getParameter("roleId");
			service.createPermissionsInRole(roleId, permissions);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg","创建成功");
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
	
	@RequestMapping(value = "/searchAllPermissionInfoForRole")
	public void searchAllPermissionInfoForRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	JSONArray array=service.getAllPermissionInfoForRole();
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("data",array.toJSONString());
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
	
	@RequestMapping(value = "/searchAllPermissionInfoInRole")
	public void searchAllPermissionInfoInRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String query=request.getParameter("query");
	    	JSONObject paramObj=JSONObject.parseObject(query);
			Map<String,Object> map=service.getAllPermissionInfoInRole(paramObj);
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
	
	@RequestMapping(value = "/deletePermissionsInfoInRole")
	public void deletePermissionsInfoInRole(HttpServletRequest request,HttpServletResponse response,@RequestParam("permissionInfo")String permissionInfo) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String roleId=request.getParameter("roleId");
			service.deletepermissionsInfoInRole(permissionInfo,roleId);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg","删除成功");
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
