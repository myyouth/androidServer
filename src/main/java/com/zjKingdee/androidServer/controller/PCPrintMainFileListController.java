package com.zjKingdee.androidServer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.service.PDAMainFileListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class PCPrintMainFileListController {
    private static final Logger LOG = LoggerFactory.getLogger(PCPrintMainFileListController.class);
    @Autowired
    private PDAMainFileListService service;
    @RequestMapping(value = "/selectMainFileList")
    public void selectAllMainFileList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            String query=request.getParameter("query");
            JSONObject paramObj=JSONObject.parseObject(query);
            Map<String,Object> map=service.searchBarcodeNumber(paramObj);
            JSONArray json= JSONArray.parseArray(JSON.toJSONString(map.get("data")));
            JSONObject obj=new JSONObject();
            obj.put("success", "true");
            obj.put("msg",json);
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
}
