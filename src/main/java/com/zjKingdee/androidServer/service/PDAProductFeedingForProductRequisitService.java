package com.zjKingdee.androidServer.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.BarcodeNumberMapper;
import com.zjKingdee.androidServer.dao.primary.ProductFeedingMapper;
import com.zjKingdee.androidServer.dao.primary.ProductRequisitMapper;
import com.zjKingdee.androidServer.entity.BarcodeInfoEntity;
import com.zjKingdee.androidServer.entity.MaterialInfoEntity;
import com.zjKingdee.androidServer.entity.ProductFeedingForRequisitEntity;
import com.zjKingdee.androidServer.entity.PurchaseReceiptEntity;
/**
 * 生产投料单--->生产领料单
 * @author Yinqi				
 * @date 2020年3月9日
 */
@Service
public class PDAProductFeedingForProductRequisitService {
	@Autowired
	private ProductFeedingMapper feedMapper;
	@Autowired
	private ProductRequisitMapper requisitMapper;
	@Autowired
	private BarcodeNumberMapper bnMapper;
	/**
	 * 获取生产投料单
	 * @param feedNumber
	 * @return
	 */
	public JSONArray searchAllProFeed(String feedNumber){
		List<String> list = feedMapper.searchAllProFeed("%"+feedNumber+"%");
		JSONArray json=JSONArray.parseArray(JSON.toJSONString(list));
		return json;
	}
	
	/**
	 * 获取投料单信息
	 * @param feedNumber
	 */
	public Map<String,Object> searchProFeedInfo(String feedNumber) {
		List<ProductFeedingForRequisitEntity> entitys=feedMapper.searchProFeedInfo(feedNumber);
		Map<String,Object> map=null;
		if(entitys !=null&&entitys.size()>0) {
			ProductFeedingForRequisitEntity entity=entitys.get(0);
			map=new HashMap<String, Object>();
			map.put("data", entitys);
			map.put("dept",entity.getDept());
			map.put("deptId", entity.getDeptId());
			if(entity.getType().contentEquals("1054")) {
				map.put("type", entity.getDeptId());
				map.put("typeName", "一般领料");
			}
		}
		return map;
	}
	
	/**
	 * 获取所有科目
	 * @return
	 */
	public List<Map<String,String>> searchAllAccount(){
		List<Map<String,String>> list=requisitMapper.selectAllAccount();
		return list;
	}
	
	public void submitProductRequisit(String baseData,String materialList,String barcodeList) throws Exception {
		JSONObject obj=JSONObject.parseObject(baseData);
		List<ProductFeedingForRequisitEntity> list=JSONArray.parseArray(materialList, ProductFeedingForRequisitEntity.class);
		if(list.size()==0) {
			throw new Exception("领料单不得为空！");
		}
		for(ProductFeedingForRequisitEntity entity:list) {
			String sum=checkProductRequisit(entity.getMaterialId(), entity.getIcmoNo());
//			if(new BigDecimal(sum).add(new BigDecimal(entity.getMaterialActualSum())).compareTo(new BigDecimal(entity.getMaterialSum()))>0) {
//				throw new Exception("申请领料超出数量！");
//			}
			entity.getMaterialActualSum();
			entity.getMaterialSum();
			entity.setAuxmaterialActualSum(new BigDecimal(entity.getMaterialActualSum()).multiply(new BigDecimal(entity.getMual())).toPlainString());
			entity.setAuxmaterialSum(new BigDecimal(entity.getMaterialSum()).multiply(new BigDecimal(entity.getMual())).toPlainString());
			if(entity.getStockPlaceID()==null||entity.getStockPlaceID().equals("null")) {
				entity.setStockPlaceID("0");
			}
		}
		List<MaterialInfoEntity> barcodes=JSONArray.parseArray(barcodeList, MaterialInfoEntity.class);
		if("1".equals(obj.getString("docTypeId"))) {
			requisitMapper.insertForPR(list,barcodes, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), obj.getString("deptId"), 
					obj.getString("typeId")=="254"?"12000":"12000", obj.getString("subjectId"), obj.getString("purpose"),obj.getString("shipperId"),obj.getString("safeKeepingId"),obj.getString("creatorId"),obj.getString("docTypeId"));	
			for(MaterialInfoEntity entity:barcodes) {
				if(entity.getmNumber().startsWith("1.01")) {
					BarcodeInfoEntity info=new BarcodeInfoEntity();
					info.setBarcodNumber(entity.getBarcodeNumber());
					info.setTypeName("生产任务单");
					info.setTypeNumber(list.get(0).getIcmoNo());
					info.setMaterialQty(entity.getmSum());
					List<BarcodeInfoEntity> data=new ArrayList<BarcodeInfoEntity>();
					data.add(info);
					requisitMapper.insertBarcodeNumber(data,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),"2",list.get(0).getIcmoNo());
				}
			}
		}else if("-1".equals(obj.getString("docTypeId"))) {
			requisitMapper.insertForPRH(list,barcodes, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), obj.getString("deptId"), 
					obj.getString("typeId")=="254"?"12000":"12000", obj.getString("subjectId"), obj.getString("purpose"),obj.getString("shipperId"),obj.getString("safeKeepingId"),obj.getString("creatorId"),obj.getString("docTypeId"));	
		}
	}
	
	/**
	 * 领料单提交校验
	 * @param feedingNumber
	 */
	public String checkProductRequisit(String mateID,String feedingNumber) {
		String sum=requisitMapper.selectSumMaterial(mateID, feedingNumber);
		if(sum==null) {
			sum="0";
		}
		return sum;
	}
}
