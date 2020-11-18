package com.zjKingdee.androidServer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.service.PDABoxingService;

@Controller
public class PDABoxingController {
	private static final Logger LOG = LoggerFactory.getLogger(PDABoxingController.class);
	@Autowired
	private PDABoxingService service;
	
	@RequestMapping(value = "/createBoxBarcode")
	public void createBoxBarcode(HttpServletRequest request,HttpServletResponse response,@RequestParam("mateBarcodeList")String mateBarcodeList) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String barcodeNumber=service.createBoxBarcode(mateBarcodeList);
	    	JSONObject obj=new JSONObject();
    		obj.put("success", "true");
	    	obj.put("msg",barcodeNumber);
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
	
	@RequestMapping(value = "/modifiyBoxBarcode")
	public void modifiyBoxBarcode(HttpServletRequest request,HttpServletResponse response,@RequestParam("mateBarcodeList")String mateBarcodeList) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String boxBarcodeNumber=request.getParameter("boxBarcodeNumber");
	    	service.modifyBoxBarcode(mateBarcodeList,boxBarcodeNumber);
	    	JSONObject obj=new JSONObject();
    		obj.put("success", "true");
	    	obj.put("msg","拆箱成功！");
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
