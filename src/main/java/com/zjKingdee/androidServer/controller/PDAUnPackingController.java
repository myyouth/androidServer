package com.zjKingdee.androidServer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.service.PDAUnPackingService;
@Controller
public class PDAUnPackingController {
	private static final Logger LOG = LoggerFactory.getLogger(PDAUnPackingController.class);
	@Autowired
	private PDAUnPackingService service;
	
	@RequestMapping(value = "/updateBarcode")
	public void updateBarcode(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	        String mSum=request.getParameter("mSum");
	        String BarcodeNumber=request.getParameter("BarcodeNumber");
	    	service.updateBarcode(BarcodeNumber,mSum);
	    	JSONObject obj=new JSONObject();
    		obj.put("success", "true");
	    	obj.put("msg","条码更新成功");
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
