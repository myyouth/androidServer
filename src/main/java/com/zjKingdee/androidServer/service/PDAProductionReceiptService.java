package com.zjKingdee.androidServer.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.ProductionReceiptMapper;
import com.zjKingdee.androidServer.dao.primary.ProductionReportMapper;
import com.zjKingdee.androidServer.entity.MaterialInfoEntity;
import com.zjKingdee.androidServer.entity.ProductionReportEntity;
import com.zjKingdee.androidServer.entity.PurchaseReceiptEntity;
import com.zjKingdee.androidServer.entity.QCJCXEntity;
import com.zjKingdee.androidServer.entity.QCPurchaseReceiptEntity;
import com.zjKingdee.androidServer.utils.CompressionUtil;

import sun.misc.BASE64Decoder;

@Service
public class PDAProductionReceiptService {
    @Autowired
    private ProductionReportMapper reportMapper;

    @Autowired
    private ProductionReceiptMapper receipMapper;
    /**
     * 获取生产汇报单数据
     */
    public JSONObject selectReceInfo(String billNo,String type) {
        List<ProductionReportEntity> entitys=new ArrayList<ProductionReportEntity>();
    	if(type==null) {
    		entitys=reportMapper.selectProductionReportInfo(billNo);
    	}else {
    		entitys=reportMapper.selectQCProductionReportInfo(billNo);
    	}
        if(entitys.size()==0) {
            throw new RuntimeErrorException(null, "生产汇报单："+billNo+"不存在或不符合条件！");
        }

        ProductionReportEntity entity=entitys.get(0);
        JSONObject obj=JSONObject.parseObject(JSONObject.toJSONString(entity));
        JSONArray array=JSONArray.parseArray(JSONArray.toJSONString(entitys));
        obj.put("materialList", array);
        return obj;
    }

    public void submitPurchaseReceipt(String baseData,String materialList,String barcodeList) {
        ProductionReportEntity ProductionReportEntity=JSONObject.parseObject(baseData, ProductionReportEntity.class);
        List<ProductionReportEntity> list=JSONArray.parseArray(materialList, ProductionReportEntity.class);
        for(ProductionReportEntity entity:list) {
            //ProductionReportEntity.setStockPlace(ProductionReportEntity.getStockPlace() == null || ProductionReportEntity.getStockPlace().equals("null") ? "0" : ProductionReportEntity.getStockPlace());
            entity.setStockPlaceId(entity.getStockPlaceId() == null || entity.getStockPlaceId().equals("null") ? "0" : entity.getStockPlaceId());
            entity.setFBatchNo(entity.getFBatchNo() == null || entity.getFBatchNo().equals("null") ? " " : entity.getFBatchNo());
        }
        if("1".equals(ProductionReportEntity.getDocTypeId())) {
        	receipMapper.insertProductionReceipt(list,JSONArray.parseArray(barcodeList, MaterialInfoEntity.class), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), ProductionReportEntity);
        }else if("-1".equals(ProductionReportEntity.getDocTypeId())) {
        	receipMapper.insertProductionReceiptH(list,JSONArray.parseArray(barcodeList, MaterialInfoEntity.class), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), ProductionReportEntity);
        }
    }
    
    public void submitQC(String baseData,String materialList,String QCBaseData,String content) throws Exception {
    	QCPurchaseReceiptEntity PurchaseReceiptEntity=JSONObject.parseObject(baseData, QCPurchaseReceiptEntity.class);
    	if(PurchaseReceiptEntity.getSafeKeepingId()==null||PurchaseReceiptEntity.getShipperId()==null) {
    		throw new Exception("请填写必填项！");
    	}
        List<PurchaseReceiptEntity> list=JSONArray.parseArray(materialList, PurchaseReceiptEntity.class);
        for(PurchaseReceiptEntity entity:list) {
        	entity.setIsOk(entity.getIsOk().equals("合格")?"286":"287");
        	System.out.println(entity.getIsOk());
        }
        List<QCJCXEntity> qcList=JSONArray.parseArray(QCBaseData, QCJCXEntity.class);
        for(QCJCXEntity entity:qcList) {
        	entity.setIsOk(entity.getIsOk().equals("合格")?"286":"287");
        }
        JSONArray array=getQCTPBytes(content);
        receipMapper.insertQC(list, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), PurchaseReceiptEntity,qcList,array);
        
    }
    
    public JSONArray getQCTPBytes(String content) throws Exception {
    	JSONArray array=JSONArray.parseArray(content);
    	for(int i=0;i<array.size();i++) {
    		JSONObject obj=array.getJSONObject(i);
    		String base64="";
    		if(obj.getString("content").contains("data:image/jpeg;base64,")) {
    			base64=obj.getString("content").replace("data:image/jpeg;base64,", "");
    		}else if(obj.getString("content").contains("data:image/png;base64,")){
    			base64=obj.getString("content").replace("data:image/png;base64,", "");
    		}
    		if(base64.length()==0) {
    			throw new Exception("图片格式异常，请联系管理员!");
    		}
    		BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    		byte[] data = decoder.decodeBuffer(base64);
		    byte[] buff=CompressionUtil.compress(data, CompressionUtil.Level.BEST_COMPRESSION);
		    obj.put("content", buff);
		    obj.put("fullFileName", new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(new Date()).replace("-", "").replace(":", "").trim()+"-"+obj.getString("fileName"));
		    
    	}
    	return array;
    }
}
