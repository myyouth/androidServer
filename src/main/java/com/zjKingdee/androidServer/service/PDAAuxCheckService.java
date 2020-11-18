package com.zjKingdee.androidServer.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.AuxCheckMapper;
import com.zjKingdee.androidServer.entity.AuxCheckEntity;

@Service
public class PDAAuxCheckService {
	private static final Logger LOG = LoggerFactory.getLogger(PDAAuxCheckService.class);
	@Autowired
	private AuxCheckMapper mapper;
	
	public JSONArray getAllAuxList(String processNo) {
		List<String> list=mapper.getAllAuxCheckList("%"+processNo+"%");
		JSONArray json=JSONArray.parseArray(JSON.toJSONString(list));
		return json;
	}
	
	public JSONArray getAuxCheckItem(String billNo){
		List<AuxCheckEntity> list= mapper.getAuxCheckItem(billNo);
		for(AuxCheckEntity entity:list) {
			entity.setAuxCheckQty("0");
		}
		JSONArray msg=JSONArray.parseArray(JSON.toJSONString(list));
		return msg;
	}
	
	public void updateAuxCheck(JSONObject json) {
		String billNo=json.getString("billNo");
		String updateJson="";
		String loggerJson="";
		try {
			updateJson = URLDecoder.decode(json.getString("updateJson"),"utf-8");
			loggerJson=URLDecoder.decode(json.getString("loggerJson"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			LOG.error(e.getLocalizedMessage());
		}
		JSONArray updateArray=JSONArray.parseArray(updateJson);
		JSONArray loggerArray=JSONArray.parseArray(loggerJson);
		List<AuxCheckEntity> updateList=parseJsonToList(updateArray,billNo);
		List<AuxCheckEntity> loggerList=parseJsonToList(loggerArray,billNo);
		mapper.updateAuxCheck(updateList);
		mapper.insertAuxCheck(loggerList);
//		if(loggerList.size()>0)
//			loggerNewData(loggerList,billNo);
	}
	
	/**
	 * 将盘点多出的数据提示输出
	 * @param logger
	 */
	private void loggerNewData(List<AuxCheckEntity> logger,String auxCheckName) {
		LOG.info("请将以下物料新增至盘点报告："+auxCheckName+"\n");
		for(AuxCheckEntity entity:logger) {
			LOG.info("物料："+entity.getMaterialCode()+"-"+entity.getBatchNumber()+"-"+entity.getStockName()+"-"+entity.getStockPlaceName()+"-"+"盘点数量："+entity.getAuxCheckQty()+"\n");
		}
	}
	
	/**
	 * 解析JsonArray
	 * @param array
	 * @return
	 */
	private List<AuxCheckEntity> parseJsonToList(JSONArray array,String billNo) {
		List<AuxCheckEntity> arrayList=new ArrayList<AuxCheckEntity>();
		for(int i=0;i<array.size();i++) {
			JSONObject json=(JSONObject) array.get(i);
			AuxCheckEntity entity=new AuxCheckEntity();
			entity.setMaterialID(json.getString("materialID"));
			entity.setMaterialCode(json.getString("materialCode"));
			entity.setMaterialName(json.getString("materialName"));
			entity.setBatchNumber(json.getString("batchNumber"));
			entity.setUnitId(json.getString("unitId"));
			entity.setStockID(json.getString("stockID"));
			entity.setStockName(json.getString("stockName"));
			entity.setStockPlaceID(json.getString("stockPlaceID"));
			entity.setStockPlaceName(json.getString("stockPlaceName"));
			entity.setQtyAct(json.getString("qtyAct"));
			entity.setAuxCheckQty(json.getString("auxCheckQty"));
			String auxCheckQty=entity.getAuxCheckQty();
			String qty=entity.getQtyAct();
			BigDecimal p1 = new BigDecimal(auxCheckQty);
	        BigDecimal p2 = new BigDecimal(qty);
	        String minus=String.valueOf(p1.subtract(p2).doubleValue());
	        entity.setMinus(minus);
	        entity.setBillNo(billNo);
			arrayList.add(entity);
		}
		return arrayList;
	}
}
