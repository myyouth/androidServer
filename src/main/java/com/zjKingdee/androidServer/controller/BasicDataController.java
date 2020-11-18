package com.zjKingdee.androidServer.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.service.BasicDataService;
/**
 * 基础资料
 * @author Yinqi				
 * @date 2020年3月24日
 */
@Controller
public class BasicDataController {
	private static final Logger LOG=LoggerFactory.getLogger(BasicDataController.class);
	@Autowired
	private BasicDataService service;
	@RequestMapping(value = "/selectAllEmpInfo") 
	public void selectAllEmpInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	JSONArray json=service.searchAllEmpInfo();
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("data",json);
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
	
	@RequestMapping(value = "/selectAllUserInfo") 
	public void selectAllUserInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	JSONArray json=service.searchAllUserInfo();
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("data",json);
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
	
	@RequestMapping(value = "/selectAllStockPlaceInfo") 
	public void selectAllStockPlaceInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	JSONArray json=service.searchAllStockPlaceInfo();
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("data",json);
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
	
	@RequestMapping(value = "/selectAllStockInfo") 
	public void selectAllStockInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	JSONArray json=service.searchAllStockInfo();
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("data",json);
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
	
	@RequestMapping(value = "/selectAllInfo") 
	public void selectAllInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	JSONObject json=service.searchAllInfo();
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("data",json);
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
	
	@RequestMapping(value = "/searchStockPlaceInfo") 
	public void searchStockPlaceInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String stockplaceNumber=request.getParameter("stockplaceNumber");
	    	String json=service.searchStockPlaceInfo(stockplaceNumber);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("data",json);
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
	
	@RequestMapping(value = "/searchQCJCX") 
	public void searchQCJCX(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String mateNumber=request.getParameter("mateNumber");
	    	String json=service.searchQCJCSInfo(mateNumber);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("data",json);
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
