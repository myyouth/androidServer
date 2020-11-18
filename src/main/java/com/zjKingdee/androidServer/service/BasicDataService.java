package com.zjKingdee.androidServer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 基础资料
 * @author Yinqi				
 * @date 2020年3月24日
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.BasicDataMapper;
import com.zjKingdee.androidServer.entity.BasicDataEntity;
import com.zjKingdee.androidServer.entity.QCJCXEntity;
@Service
public class BasicDataService {
	@Autowired
	private BasicDataMapper mapper;
	
	public JSONArray searchAllEmpInfo() {
		List<BasicDataEntity> entitys=mapper.searchAllEmpInfo();
		JSONArray array=JSONArray.parseArray(JSONArray.toJSONString(entitys));
		return array;
	}
	
	public JSONArray searchAllUserInfo() {
		List<BasicDataEntity> entitys=mapper.searchAllUserInfo();
		JSONArray array=JSONArray.parseArray(JSONArray.toJSONString(entitys));
		return array;
	}
	
	public JSONArray searchAllCustomerInfo() {
		List<BasicDataEntity> entitys=mapper.searchAllCustomerInfo();
		JSONArray array=JSONArray.parseArray(JSONArray.toJSONString(entitys));
		return array;
	}
	
	public JSONArray searchAllSupplierInfo() {
		List<BasicDataEntity> entitys=mapper.searchAllSupplierInfo();
		JSONArray array=JSONArray.parseArray(JSONArray.toJSONString(entitys));
		return array;
	}
	
	public JSONArray searchAllDeptInfo() {
		List<BasicDataEntity> entitys=mapper.searchAllDeptInfo();
		JSONArray array=JSONArray.parseArray(JSONArray.toJSONString(entitys));
		return array;
	}
	
	public JSONObject searchAllInfo() {
		JSONObject obj=new JSONObject();
		obj.put("empInfo",searchAllEmpInfo());
		obj.put("customerInfo",searchAllCustomerInfo());
		obj.put("supplierInfo",searchAllSupplierInfo());
		obj.put("deptInfo",searchAllDeptInfo());
		obj.put("stockInfo",searchAllStockInfo());
		obj.put("stockPlaceInfo",searchAllStockPlaceInfo());
		return obj;
	}
	
	public JSONArray searchAllStockInfo() {
		List<BasicDataEntity> entitys=mapper.searchAllStockInfo();
		JSONArray array=JSONArray.parseArray(JSONArray.toJSONString(entitys));
		return array;
	}
	
	public JSONArray searchAllStockPlaceInfo() {
		List<BasicDataEntity> entitys=mapper.searchAllStockPlaceInfo();
		JSONArray array=JSONArray.parseArray(JSONArray.toJSONString(entitys));
		return array;
	}
	
	public String searchStockPlaceInfo(String stockplaceNumber) throws Exception {
		BasicDataEntity entity=mapper.searchStockPlaceInfo(stockplaceNumber);
		if(entity==null) {
			throw new Exception("当前仓位不存在");
		}
		return JSONObject.toJSONString(entity);
	}
	
	public String searchQCJCSInfo(String mateNumber) throws Exception {
		List<QCJCXEntity> entity=mapper.searchQCJCXForFA(mateNumber);
		if(entity==null||entity.size()==0) {
			throw new Exception("当前物料不存在检测方案");
		}
		return JSONArray.parseArray(JSONArray.toJSONString(entity)).toJSONString();
	}
}
