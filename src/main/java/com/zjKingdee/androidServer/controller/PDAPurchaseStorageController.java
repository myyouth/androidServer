package com.zjKingdee.androidServer.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.service.PDAJobWorkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PDAPurchaseStorageController {
    private static final Logger LOG = LoggerFactory.getLogger(PDAJobWorkingController.class);
    @Autowired
    private PDAJobWorkingService jbService;
    @RequestMapping(value = "/redirectToPurchaseStorage")
    public ModelAndView redirectToJJobBooking() {
        return new ModelAndView(new RedirectView("/#/purchaseStorage"));
    }
    @RequestMapping(value = "/selectAllDocument")
    public void selectAllDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
}
