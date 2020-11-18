package com.zjKingdee.androidServer.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.BarcodeNumberMapper;
import com.zjKingdee.androidServer.dao.primary.MiscellaneousReceiptMapper;
import com.zjKingdee.androidServer.entity.MiscellaneousReceiptEntity;
/**
 * 其他入库单
 * @author Yinqi				
 * @date 2020年4月8日
 */
@Service
public class PDAMiscellaneousReceiptService {
	@Autowired
	private MiscellaneousReceiptMapper mapper;
	@Autowired
	private BarcodeNumberMapper barMapper;
	public void submitMiscellaneousReceipt(String baseData,String materialList,String barcodeList) {
		MiscellaneousReceiptEntity issueEntity=JSONObject.parseObject(baseData, MiscellaneousReceiptEntity.class);
		List<MiscellaneousReceiptEntity> list=JSONArray.parseArray(materialList, MiscellaneousReceiptEntity.class);
		for(MiscellaneousReceiptEntity entity:list) {
			entity.setPositionId(entity.getPositionId().equals("null")?"0":entity.getPositionId());
		}
		mapper.insertForReceipt(list, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), issueEntity);
		barMapper.updateBarcodeNumberStock(JSONArray.parseArray(barcodeList, MiscellaneousReceiptEntity.class));
	}
}
