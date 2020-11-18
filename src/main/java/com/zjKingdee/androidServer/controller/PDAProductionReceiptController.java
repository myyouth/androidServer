package com.zjKingdee.androidServer.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.service.PDAProductionReceiptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生产汇报单--->生产入库单
 */
@Controller
public class PDAProductionReceiptController {
    public static final Logger LOG = LoggerFactory.getLogger(PDAConsignmentNoteForSaleIssueController.class);
    @Autowired
    private PDAProductionReceiptService Sevice;
    @RequestMapping(value = "/selectProductionReport")
    public void selectProductionReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

    @RequestMapping(value = "/submitProductionReceipt")
    public void submitProductionReceipt(HttpServletRequest request, HttpServletResponse response, @RequestParam("baseData") String baseData, @RequestParam("materialList") String materialList, @RequestParam("barcodeList") String barcodeList) throws Exception {
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
    
    @RequestMapping(value = "/submitQCCP")
    public void submitQCCP(HttpServletRequest request, HttpServletResponse response, @RequestParam("baseData") String baseData, @RequestParam("materialList") String materialList,@RequestParam("QCBaseData") String QCBaseData,@RequestParam("content")String content) throws Exception {
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
