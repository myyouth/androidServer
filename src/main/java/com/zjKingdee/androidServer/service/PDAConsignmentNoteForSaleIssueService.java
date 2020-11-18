package com.zjKingdee.androidServer.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.ConsignmentNoteMapper;
import com.zjKingdee.androidServer.dao.primary.SaleIssueMapper;
import com.zjKingdee.androidServer.entity.ConsignmentNoteForSaleIssueEntity;
import com.zjKingdee.androidServer.entity.MaterialInfoEntity;

@Service
public class PDAConsignmentNoteForSaleIssueService {
	@Autowired
	private ConsignmentNoteMapper noteMapper;
	@Autowired
	private SaleIssueMapper issueMapper;
	/**
	 * 获取发货通知单数据
	 */
	public JSONObject selectNoteInfo(String billNo) {
		List<ConsignmentNoteForSaleIssueEntity> entitys=noteMapper.selectNoteInfo(billNo);
		if(entitys.size()==0) {
			throw new RuntimeErrorException(null, "发货通知单："+billNo+"不符合条件！");
		}
		for(ConsignmentNoteForSaleIssueEntity entity:entitys) {
			entity.setType("销售出库类型");
			entity.setTypeId("12530");
		}
		ConsignmentNoteForSaleIssueEntity entity=entitys.get(0);
		JSONObject obj=JSONObject.parseObject(JSONObject.toJSONString(entity));
		JSONArray array=JSONArray.parseArray(JSONArray.toJSONString(entitys));
		obj.put("materialList", array);
		return obj;
	}
	
	public void submitSaleIssue(String baseData,String materialList,String barcodeList) {
		ConsignmentNoteForSaleIssueEntity issueEntity=JSONObject.parseObject(baseData, ConsignmentNoteForSaleIssueEntity.class);
		List<ConsignmentNoteForSaleIssueEntity> list=JSONArray.parseArray(materialList, ConsignmentNoteForSaleIssueEntity.class);
		for(ConsignmentNoteForSaleIssueEntity entity:list) {
			if(entity.getStockPlaceId()==null||entity.getStockPlaceId().equals("null")) {
				entity.setStockPlaceId("0");
			}
			String totalPrice=new BigDecimal(entity.getMaterialPrice()).multiply(new BigDecimal(entity.getMaterialActualSum())).toPlainString();
			entity.setMaterialTotalPrice(totalPrice);
		}
		issueMapper.insertForIssue(list,JSONArray.parseArray(barcodeList, MaterialInfoEntity.class), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), issueEntity);
	}
}
