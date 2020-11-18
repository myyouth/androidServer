package com.zjKingdee.androidServer.controller;

import java.util.List;
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
import com.zjKingdee.androidServer.service.PDAProductFeedingForProductRequisitService;
/**
 * 生产投料单--->生产领料单
 * @author Yinqi				
 * @date 2020年3月23日
 */
@Controller
public class PDAProductFeedingForProductRequisitController {
	private static final Logger LOG = LoggerFactory.getLogger(PDAProductFeedingForProductRequisitController.class);
	
	@Autowired
	private PDAProductFeedingForProductRequisitService service;
	@RequestMapping(value = "/searchAllProFeed")
	public void searchAllProFeed(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String feedNumber=request.getParameter("feedNumber");
	    	JSONArray array=service.searchAllProFeed(feedNumber);
	    	JSONObject obj=new JSONObject();
	    	obj.put("success", "true");
	    	obj.put("msg",array);
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
	
	@RequestMapping(value = "/searchProFeedInfo")
	public void searchProFeedInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String feedNumber=request.getParameter("feedNumber");
	    	Map<String,Object> map=service.searchProFeedInfo(feedNumber);
	    	if(map==null) {
	    		throw new Exception("生产投料单异常！");
	    	}
	    	JSONArray json= JSONArray.parseArray(JSON.toJSONString(map.get("data")));
	    	JSONObject obj=new JSONObject();
	    	obj.put("success", "true");
	    	obj.put("data",json);
	    	obj.put("dept",map.get("dept"));
	    	obj.put("deptId",map.get("deptId"));
	    	obj.put("type",map.get("type"));
	    	obj.put("typeName",map.get("typeName"));
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
	
	@RequestMapping(value = "/searchAllAccount")
	public void searchAllAccount(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	List<Map<String,String>> map=service.searchAllAccount();
	    	if(map==null) {
	    		throw new Exception("获取科目异常！");
	    	}
	    	JSONArray array=JSONArray.parseArray(JSON.toJSONString(map));
	    	JSONObject obj=new JSONObject();
	    	obj.put("success", "true");
	    	obj.put("subjects",array);
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
	
	@RequestMapping(value = "/submitProductRequisit")
	public void submitProductRequisit(HttpServletRequest request,HttpServletResponse response,@RequestParam("baseData") String baseData,@RequestParam("materialList") String materialList,@RequestParam("barcodeList") String barcodeList) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	        service.submitProductRequisit(baseData, materialList,barcodeList);
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
