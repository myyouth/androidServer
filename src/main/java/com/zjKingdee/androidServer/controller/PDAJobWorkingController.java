package com.zjKingdee.androidServer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.service.PDAJobWorkingService;

@Controller
public class PDAJobWorkingController {
	private static final Logger LOG = LoggerFactory.getLogger(PDAJobWorkingController.class);
	@Autowired
	private PDAJobWorkingService jbService;
	@RequestMapping(value = "/redirectToJJobBooking")
	public ModelAndView redirectToJJobBooking() {
		return new ModelAndView(new RedirectView("/#/jobBooking"));
	}
	@RequestMapping(value = "/selectAllBadDes") 
	public void selectAllBadDes(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	        JSONArray json=jbService.selectAllBadDes();
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg",json);
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
	
	@RequestMapping(value = "/selectProcessBooking")
	public void selectProcessBooking(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	        String number=request.getParameter("Number");
	        JSONObject json=jbService.selectAllProcessBook(number);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg",json);
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
	
	@RequestMapping(value = "/submitProcessBooking")
	public void submitProcessBooking(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    try {
	    	response.setHeader("Content-Type", "application/json;charset=utf-8");
	        String submitData=request.getParameter("submitData");
	        jbService.updateProcessBook(submitData);
	    	JSONObject obj=new JSONObject();
            obj.put("success", "true");
	    	obj.put("msg","报工成功");
	    	response.getWriter().write(obj.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject obj=new JSONObject();
            obj.put("success", "false");
	    	obj.put("msg",e.getMessage());
	    	response.getWriter().write(obj.toJSONString());
			LOG.error(e.getLocalizedMessage());
		}
	}
}
