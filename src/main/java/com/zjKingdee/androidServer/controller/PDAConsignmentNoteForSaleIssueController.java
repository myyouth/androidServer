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
import com.zjKingdee.androidServer.service.PDAConsignmentNoteForSaleIssueService;

/**
 * 发货通知单--->销售出库单
 * @author Yinqi				
 * @date 2020年3月23日
 */
@Controller
public class PDAConsignmentNoteForSaleIssueController {
	public static final Logger LOG = LoggerFactory.getLogger(PDAConsignmentNoteForSaleIssueController.class);
	@Autowired
	private PDAConsignmentNoteForSaleIssueService noteSevice;
	@RequestMapping(value = "/selectNoteInfo")
	public void selectNoteInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String billNo=request.getParameter("billNo");
	    	JSONObject json=noteSevice.selectNoteInfo(billNo);
	    	JSONObject obj=new JSONObject();
    		obj.put("success", "true");
	    	obj.put("msg",json.toJSONString());
			response.getWriter().write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getLocalizedMessage());
			JSONObject obj=new JSONObject();
			obj.put("success", "false");
			obj.put("msg", e.getMessage().length()==0?"数据获取异常！":e.getMessage());
			response.getWriter().write(obj.toString());
		}
	} 
	
	@RequestMapping(value = "/submitSaleIssue")
	public void submitProductRequisit(HttpServletRequest request,HttpServletResponse response,@RequestParam("baseData") String baseData,@RequestParam("materialList") String materialList,@RequestParam("barcodeList") String barcodeList) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	noteSevice.submitSaleIssue(baseData, materialList,barcodeList);
	    	JSONObject obj=new JSONObject();
	    	obj.put("success", "true");
	    	obj.put("msg","");
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
}
