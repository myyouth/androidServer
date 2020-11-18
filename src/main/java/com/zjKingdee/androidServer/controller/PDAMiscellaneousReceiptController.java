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
import com.zjKingdee.androidServer.service.PDAMiscellaneousReceiptService;

/**
 * 其他入库单
 * @author Yinqi				
 * @date 2020年4月8日
 */
@Controller
public class PDAMiscellaneousReceiptController {
	private static final Logger LOG=LoggerFactory.getLogger(PDAMiscellaneousReceiptController.class);
	@Autowired
	private PDAMiscellaneousReceiptService service;
	@RequestMapping(value = "/submitMiscellaneousReceipt")
	public void submitMiscellaneousReceipt(HttpServletRequest request,HttpServletResponse response,@RequestParam("baseData") String baseData,@RequestParam("materialList") String materialList,@RequestParam("barcodeList") String barcodeList) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	service.submitMiscellaneousReceipt(baseData, materialList,barcodeList);
	    	JSONObject obj=new JSONObject();
	    	obj.put("success", "true");
	    	obj.put("msg","");
			response.getWriter().write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			JSONObject obj=new JSONObject();
			obj.put("success", "false");
			obj.put("msg", e.getMessage());
			response.getWriter().write(obj.toString());
		}
	}
}
