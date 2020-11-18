package com.zjKingdee.androidServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjKingdee.androidServer.dao.primary.BarcodeNumberMapper;

@Service
public class PDAUnPackingService {
	@Autowired
	private BarcodeNumberMapper mapper;
	
	public void updateBarcode(String barcodNumber,String mSum) {
		mapper.updateBarcodeNumber(barcodNumber, mSum);
	}
}
