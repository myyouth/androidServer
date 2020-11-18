package com.zjKingdee.androidServer.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.service.PDAInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PDAInventoryController {
    public static final Logger LOG = LoggerFactory.getLogger(PDAConsignmentNoteForSaleIssueController.class);
    @Autowired
    private PDAInventoryService Sevice;
    @RequestMapping(value = "/selectInventory")
    public void selectInventory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            String materialNumber=request.getParameter("materialNumber");
            JSONObject json=Sevice.selectInventory(materialNumber);
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
}
