package com.zjKingdee.androidServer.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.service.PDAPurchaseReceiptService;

import sun.misc.BASE64Decoder;

/**
 * 收料通知单--->采购入库单
 * 收料通知单--->检验单
 */
@Controller
public class PDAPurchaseReceiptController {
    public static final Logger LOG = LoggerFactory.getLogger(PDAConsignmentNoteForSaleIssueController.class);
    @Autowired
    private PDAPurchaseReceiptService Sevice;
    @RequestMapping(value = "/selectReceiptNotice")
    public void selectNoteInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            String billNo=request.getParameter("billNo");
            String type=request.getParameter("type");
            JSONObject json=Sevice.selectReceInfo(billNo,type);
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

    @RequestMapping(value = "/submitPurchaseReceipt")
    public void submitPurchaseReceipt(HttpServletRequest request, HttpServletResponse response, @RequestParam("baseData") String baseData, @RequestParam("materialList") String materialList,@RequestParam("barcodeList") String barcodeList) throws Exception {
        try {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            Sevice.submitPurchaseReceipt(baseData, materialList,barcodeList);
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
    
    @RequestMapping(value = "/submitQCLL")
    public void submitQCLL(HttpServletRequest request, HttpServletResponse response, @RequestParam("baseData") String baseData, @RequestParam("materialList") String materialList,@RequestParam("QCBaseData") String QCBaseData,@RequestParam("content")String content) throws Exception {
        try {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            Sevice.submitQC(baseData, materialList,QCBaseData,content);
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
