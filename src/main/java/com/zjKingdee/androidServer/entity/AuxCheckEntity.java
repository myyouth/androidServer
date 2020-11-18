package com.zjKingdee.androidServer.entity;

import java.math.BigDecimal;

/**
 * 盘点
 * @author Yinqi				
 * @date 2020年3月3日
 */
public class AuxCheckEntity {
	private String stockID;//仓库
	private String stockName;//仓库名称
	private String stockPlaceID;//仓位
	private String stockPlaceName;//仓位名称
	private String qtyAct;//账存数量
	private String auxCheckQty;//盘点数量
	private String materialID;//物料ID
	private String materialCode;//物料代码
	private String materialName;//物料名称
	private String batchNumber;//批次
	private String billNo;//盘点方案编码
	private String minus;//盘盈盘亏
	private String unitId;//单位
	private String unitName;
	
	public AuxCheckEntity() {
		super();
	}
	public AuxCheckEntity(String stockID, String stockPlaceID, String materialID, String batchNumber, String qtyAct,
			String auxCheckQty, String stockName, String stockPlaceName, String materialName, String materialCode,String unit) {
		super();
		this.stockID = stockID;
		this.stockName = stockName;
		this.stockPlaceID = stockPlaceID;
		this.stockPlaceName = stockPlaceName;
		this.qtyAct = qtyAct;
		this.auxCheckQty = auxCheckQty;
		this.materialID = materialID;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.batchNumber = batchNumber;
		this.unitId=unit;
	}
	public AuxCheckEntity(Integer materialID, String materialCode, String materialName, String batchNumber, String stockID, String stockName,
			String stockPlaceID, String stockPlaceName,String auxCheckQty) {
		super();
		this.stockID = stockID;
		this.stockName = stockName;
		this.stockPlaceID = stockPlaceID;
		this.stockPlaceName = stockPlaceName;
		this.auxCheckQty = auxCheckQty;
		this.materialID = String.valueOf(materialID);
		this.materialName = materialName;
		this.materialCode = materialCode;
		this.batchNumber = batchNumber;
	}
	public String getStockID() {
		return stockID;
	}
	public void setStockID(String stockID) {
		this.stockID = stockID;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockPlaceID() {
		return stockPlaceID;
	}
	public void setStockPlaceID(String stockPlaceID) {
		this.stockPlaceID = stockPlaceID;
	}
	public String getStockPlaceName() {
		return stockPlaceName;
	}
	public void setStockPlaceName(String stockPlaceName) {
		this.stockPlaceName = stockPlaceName;
	}
	public String getQtyAct() {
		return qtyAct;
	}
	public void setQtyAct(String qtyAct) {
		this.qtyAct = new BigDecimal(qtyAct).stripTrailingZeros().toPlainString();
	}
	public String getAuxCheckQty() {
		return auxCheckQty;
	}
	public void setAuxCheckQty(String auxCheckQty) {
		this.auxCheckQty = new BigDecimal(auxCheckQty).stripTrailingZeros().toPlainString();
	}
	public String getMaterialID() {
		return materialID;
	}
	public void setMaterialID(String materialID) {
		this.materialID = materialID;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getMinus() {
		return minus;
	}
	public void setMinus(String minus) {
		this.minus = minus;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AuxCheckEntity) {
			AuxCheckEntity entity=(AuxCheckEntity)obj;
			if(entity.getMaterialID().equals(this.materialID)&&entity.getBatchNumber().equals(this.batchNumber)
					&&entity.getStockID().equals(this.stockID)&&entity.getStockPlaceID().equals(this.stockPlaceID)) {
				return true;
			}
			return false;
		}else {
			return false;
		}
	}
	
}
