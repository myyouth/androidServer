package com.zjKingdee.androidServer.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.service.PDARequisitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PDARequisitionController {
    private static final Logger LOG = LoggerFactory.getLogger(PDAJobWorkingController.class);
    @Autowired
    private PDARequisitionService Service;

    @RequestMapping(value = "/submitRequisition")
    public void submitPurchaseReceipt(HttpServletRequest request, HttpServletResponse response, @RequestParam("baseData") String baseData, @RequestParam("materialList") String materialList) throws Exception {
        try {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            Service.submitRequisition(baseData, materialList);
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

