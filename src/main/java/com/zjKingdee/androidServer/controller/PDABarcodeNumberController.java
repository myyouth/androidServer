package com.zjKingdee.androidServer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.service.PDABarcodeNumberService;
@Controller
public class PDABarcodeNumberController {
	private static final Logger LOG = LoggerFactory.getLogger(PDABarcodeNumberController.class);
	@Autowired
	private PDABarcodeNumberService service;
	@RequestMapping(value = "/searchAuxBarcodeNumber")
	public void searchBarcodeNumber(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "text/xml;charset=utf-8");
	    	String barcodeNumber=request.getParameter("BarcodeNumber");
	    	JSONObject json=service.searchBarcodeNumber(barcodeNumber);
	    	JSONObject obj=new JSONObject();
	    	obj.put("success", "true");
	    	obj.put("msg",json);
			response.getWriter().write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getLocalizedMessage());
			JSONObject obj=new JSONObject();
			obj.put("success", "false");
			obj.put("msg", e.getLocalizedMessage());
			response.getWriter().write(obj.toString());
		}
	}
	
	@RequestMapping(value = "/searchBarcodeNumber")
	public void searchMaterial(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String barcodeNumber=request.getParameter("BarcodeNumber");
	    	String id=request.getParameter("id");
	    	String type=request.getParameter("type");
	    	String json=service.searchMaterial(barcodeNumber,Integer.valueOf(id),type);
	    	JSONObject obj=new JSONObject();
	    	if(json.length()==0) {
	    		obj.put("success", "false");
		    	obj.put("msg","当前条码不存在！");
	    	} else {
	    		obj.put("success", "true");
		    	obj.put("msg",json);
	    	}
			response.getWriter().write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getLocalizedMessage());
			JSONObject obj=new JSONObject();
			obj.put("success", "false");
			obj.put("msg", e.getLocalizedMessage());
			response.getWriter().write(obj.toString());
		}
	}
	
	/**
	 * 查询条码数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchBarcodeInfo")
	public void searchBarcodeNumberforMiscellane(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String barcodeNumber=request.getParameter("BarcodeNumber");
	    	String type=request.getParameter("type");
	    	String json=service.searchBarcodeInfo(barcodeNumber,type);
	    	JSONObject obj=new JSONObject();
	    	if(json.length()==0) {
	    		obj.put("success", "false");
		    	obj.put("msg","当前条码不存在！");
	    	} else {
	    		obj.put("success", "true");
		    	obj.put("msg",json);
	    	}
			response.getWriter().write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getLocalizedMessage());
			JSONObject obj=new JSONObject();
			obj.put("success", "false");
			obj.put("msg", "获取数据失败！");
			response.getWriter().write(obj.toString());
		}
	}
}
