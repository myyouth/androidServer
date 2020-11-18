package com.zjKingdee.androidServer.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.zjKingdee.androidServer.dao.primary.BarcodeNumberMapper;

@Service
public class PDABoxingService {
	@Autowired
	private BarcodeNumberMapper mapper;
	
	public String createBoxBarcode(String jsonData) {
		List<String> data=JSONArray.parseArray(jsonData, String.class);
		String barcodeNumber=mapper.getBarcodeNumber("ZJFCBZX");
		mapper.insertBoxBarcodeNumber(barcodeNumber, data, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return barcodeNumber;
	}
	
	public void modifyBoxBarcode(String jsonData,String boxBarcodeNumber) {
		List<String> data=JSONArray.parseArray(jsonData, String.class);
		mapper.delectFromBoxBarcodeNumber(data,boxBarcodeNumber);
	}
}
