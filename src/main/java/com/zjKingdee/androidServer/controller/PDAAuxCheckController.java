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
import com.zjKingdee.androidServer.service.PDAAuxCheckService;


@Controller
public class PDAAuxCheckController {
	private static final Logger LOG = LoggerFactory.getLogger(PDAAuxCheckController.class);
	@Autowired
	private PDAAuxCheckService service;
	@RequestMapping(value = "/auxCheck/getAuxCheckList")
	public void getAuxCheckList(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "text/xml;charset=utf-8");
	    	String processNo=request.getParameter("processNo");
	    	JSONArray array=service.getAllAuxList(processNo);
			JSONObject obj=new JSONObject();
	    	obj.put("success", "true");
	    	obj.put("msg",array);
			response.getWriter().write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getLocalizedMessage());
			JSONObject obj=new JSONObject();
			obj.put("success", "false");
			obj.put("msg", "获取盘点方案失败！");
			response.getWriter().write(obj.toString());
		}
	}
	
	@RequestMapping(value = "/auxCheck/getAuxCheckData")
	public void getAuxCheckData(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "text/xml;charset=utf-8");
	    	String auxCheckNo=request.getParameter("auxCheckNo");
	    	JSONArray msg=service.getAuxCheckItem(auxCheckNo);
			JSONObject obj=new JSONObject(); 
	    	obj.put("success", "true");
	    	obj.put("data",msg);
			response.getWriter().write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getLocalizedMessage());
			JSONObject obj=new JSONObject();
			obj.put("success", "false");
			obj.put("msg", "获取盘点方案失败！");
			response.getWriter().write(obj.toString());
		}
	}
	
	@RequestMapping(value = "/auxCheck/updateAuxCheckData")
	public void updateAuxCheckData(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "text/xml;charset=utf-8");
	    	String billNo=request.getParameter("auxCheckNo");
	    	String updateJson=request.getParameter("materialList");
	    	String loggerJson=request.getParameter("notInMaterialList");
	    	JSONObject json = new JSONObject();
	    	json.put("billNo", billNo);
	    	json.put("updateJson", updateJson);
	    	json.put("loggerJson", loggerJson);
			service.updateAuxCheck(json);
			JSONObject obj=new JSONObject(); 
			obj.put("success", "true");
		    obj.put("msg","盘点报告更新成功！");
			response.getWriter().write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getLocalizedMessage());
			JSONObject obj=new JSONObject();
			obj.put("success", "false");
			obj.put("msg", "盘点报告更新失败！");
			response.getWriter().write(obj.toString());
		}
	}
}
