package com.zjKingdee.androidServer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.BarcodeNumberMapper;
import com.zjKingdee.androidServer.entity.AuxCheckEntity;
import com.zjKingdee.androidServer.entity.MaterialInfoEntity;
@Service
public class PDABarcodeNumberService {
	@Autowired
	private BarcodeNumberMapper mapper;
	public JSONObject searchBarcodeNumber(String BarcodeNumber) {
		AuxCheckEntity entity=mapper.searchBarcodeNumber(BarcodeNumber);
		if(entity==null) {
			throw new RuntimeException("条码不存在！");
		}else {
			JSONObject obj=(JSONObject) JSONObject.toJSON(entity);
			return obj;
		}
	}
	
	public String searchMaterial(String barcodeNumber,int id,String type) throws Exception {
		JSONArray array=new JSONArray();
		if(barcodeNumber.indexOf("BZX")>0) {
			List<String> list = mapper.searchBoxBarcodeNumber(barcodeNumber);
			for(String mateBarcodeNumber:list) {
				boolean flag=checkInOrOut(mateBarcodeNumber, id);
				if(flag) {
					throw new Exception(id==2?"当前条码不存在或已入库！":"当前条码不存在或已出库！");
				}
				MaterialInfoEntity entity=mapper.searchMaterialInfo(mateBarcodeNumber,type==null?"%%":"%"+type+"%");
				if(entity==null) {
					continue;
				}
				entity.setBarcodeNumber(mateBarcodeNumber);
				array.add(entity);
			}
		}else {
			boolean flag=checkInOrOut(barcodeNumber, id);
			if(flag) {
				throw new Exception(id==2?"当前条码不存在或已入库！":"当前条码不存在或已出库！");
			}
			MaterialInfoEntity entity=mapper.searchMaterialInfo(barcodeNumber,type==null?"%%":"%"+type+"%");
			if(entity==null) {
				return "";
			}
			entity.setBarcodeNumber(barcodeNumber);
			array.add(entity);
		}
		return array.toJSONString();
	}
	
	public String searchBarcodeInfo(String barcodeNumber,String type) throws Exception {
		JSONArray array=new JSONArray();
		if(barcodeNumber.indexOf("BZX")>0) {
			List<String> list = mapper.searchBoxBarcodeNumber(barcodeNumber);
			for(String mateBarcodeNumber:list) {
				boolean flag=checkInOrOut(mateBarcodeNumber, 2);
				if(flag) {
					throw new Exception("当前条码不存在或已入库！");
				}
				MaterialInfoEntity entity=mapper.searchBarcodeInfo(mateBarcodeNumber,type==null?"%%":"%"+type+"%");
				if(entity==null) {
					continue;
				}
				entity.setBarcodeNumber(mateBarcodeNumber);
				array.add(entity);
			}
		}else {
			boolean flag=checkInOrOut(barcodeNumber, 2);
			if(flag) {
				throw new Exception("当前条码不存在或已入库！");
			}
			MaterialInfoEntity entity=mapper.searchBarcodeInfo(barcodeNumber,type==null?"%%":"%"+type+"%");
			if(entity==null) {
				return "";
			}
			entity.setBarcodeNumber(barcodeNumber);
			array.add(entity);
		}
		return array.toJSONString();
	}
	
	/**
	 * 校验条码是否出库/入库
	 * @param barcodeNumber
	 * @return
	 */
	public boolean checkInOrOut(String barcodeNumber,int id) {
		int i=mapper.checkInOrOut(barcodeNumber, id);
		if(i>0) {
			return false;
		}
		return true;
	}
}
