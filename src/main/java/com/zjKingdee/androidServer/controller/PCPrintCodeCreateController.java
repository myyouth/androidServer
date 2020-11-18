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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.entity.MaterialInfoEntity;
import com.zjKingdee.androidServer.service.PCPrintCodeCreateService;
@Controller
public class PCPrintCodeCreateController {
	private static final Logger LOG = LoggerFactory.getLogger(PCPrintCodeCreateController.class);
	@Autowired
	private PCPrintCodeCreateService service;
	
	@RequestMapping(value = "/DYKR") 
	public ModelAndView redirectToDYKR(HttpServletRequest request,HttpServletResponse response) throws Exception {
		return new ModelAndView(new RedirectView("/index.html"));
	} 
	
	@RequestMapping(value = "/selectAllDoc") 
	public void selectAllDoc(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String typeDoc=request.getParameter("typeDoc");
	    	String query=request.getParameter("query");
	    	JSONObject paramObj=JSONObject.parseObject(query);
			Map<String,Object> map=service.getAllDocInfo(typeDoc,paramObj);
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
	@RequestMapping(value = "/selectAllMaterial") 
	public void selectAllMaterial(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	String typeDoc=request.getParameter("typeDoc");
	    	String mNumber=request.getParameter("mNumber");
	    	List<MaterialInfoEntity> list=service.getAllMaterialInfo(typeDoc,mNumber);
	    	JSONArray json= JSONArray.parseArray(JSON.toJSONString(list));
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
	
	@RequestMapping(value = "/createBarcode")
	public void createBarcode(HttpServletRequest request,HttpServletResponse response,@RequestParam("jsonData") String jsonData) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	List<MaterialInfoEntity> list=JSONArray.parseArray(jsonData, MaterialInfoEntity.class);
	    	Map<String,Object> map=service.createAllBarcode(list);
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
	
	@RequestMapping(value = "/getPrintMachineList")
	public void getPrintMachineList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	JSONArray json= service.getPrintMachineList();
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("data",json.toJSONString());
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
	
	@RequestMapping(value = "/printBarcode")
	public void printBarcode(HttpServletRequest request,HttpServletResponse response,@RequestParam("jsonData")String jsonData) throws Exception {
		try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	    	JSONArray array=JSONArray.parseArray(jsonData);
	    	String printMachine=request.getParameter("printMachine");
	    	String labelName=request.getParameter("labelName");
	    	String log=service.printBarcode(printMachine, labelName, array);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
            obj.put("msg", log);
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
