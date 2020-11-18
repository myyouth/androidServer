package com.zjKingdee.androidServer.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.BasicDataMapper;
import com.zjKingdee.androidServer.dao.primary.PurchaseReceiptMapper;
import com.zjKingdee.androidServer.dao.primary.ReceiptNoticeMapper;
import com.zjKingdee.androidServer.entity.PurchaseReceiptEntity;
import com.zjKingdee.androidServer.entity.QCJCXEntity;
import com.zjKingdee.androidServer.entity.QCPurchaseReceiptEntity;
import com.zjKingdee.androidServer.utils.CompressionUtil;

import sun.misc.BASE64Decoder;

@Service
public class PDAPurchaseReceiptService {
    @Autowired
    private ReceiptNoticeMapper receMapper;

    @Autowired
    private PurchaseReceiptMapper purMapper;
    
    @Autowired
    private BasicDataMapper basicMapper;
    /**
     * 获取收料通知单数据
     */
    public JSONObject selectReceInfo(String billNo,String type) {
    	List<PurchaseReceiptEntity> entitys=new ArrayList<PurchaseReceiptEntity>();
    	if(type==null) {
    		entitys=receMapper.selectNoteInfo(billNo);
    	}else {
    		entitys=receMapper.selectNoteInfoForQC(billNo);
    	}
        if(entitys.size()==0) {
            throw new RuntimeErrorException(null, "收料通知单："+billNo+"不符合要求！");
        }

        PurchaseReceiptEntity entity=entitys.get(0);
        JSONObject obj=JSONObject.parseObject(JSONObject.toJSONString(entity));
        JSONArray array=JSONArray.parseArray(JSONArray.toJSONString(entitys));
        obj.put("materialList", array);
        return obj;
    }

    public void submitPurchaseReceipt(String baseData,String materialList,String barcodeList) {
        PurchaseReceiptEntity PurchaseReceiptEntity=JSONObject.parseObject(baseData, PurchaseReceiptEntity.class);
        List<PurchaseReceiptEntity> list=JSONArray.parseArray(materialList, PurchaseReceiptEntity.class);
        for(PurchaseReceiptEntity entity:list) {
            String totalPrice=new BigDecimal(entity.getFPrice()).multiply(new BigDecimal(entity.getFQty())).toPlainString();
            entity.setTotalPrice(totalPrice);
        }
        
        purMapper.insertPurchaseReceiptt(list, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), PurchaseReceiptEntity,JSONArray.parseArray(barcodeList, PurchaseReceiptEntity.class));
        //basicMapper.updateInventory();
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
        purMapper.insertQC(list, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), PurchaseReceiptEntity,qcList,array);
        
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