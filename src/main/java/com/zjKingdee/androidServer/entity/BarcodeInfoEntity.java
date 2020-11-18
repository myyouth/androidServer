package com.zjKingdee.androidServer.entity;

public class BarcodeInfoEntity {
	private String id;
	private String mId;//物料内码
	private String materielNumber;//物料编码
	private String materialName;//物料名称
	private String barcodNumber;//条码编码
	private String materialQty;//数量
	private String materialUnitID;//单位内码
	private String materialUnitName;//单位
	private String typeName;//单据类型
	private String typeNumber;//单据编号
	private String materialBatch;//批号
	private String supplyID;//供应商内码
	private String supplyName;//供应商
	private String stockID;//仓库编码
	private String stockName;//仓库
	private String stockPlaceID;//仓位编码
	private String stockPlaceName;//仓位
	private String departmentID;//部门
	private String departmentName;//部门ID
	private String zbm;//自编码
	public String getId() {
		return id;
	}
	public void setId(String id) {
		if(id==null||id.equals("null")){
			id="0";
		}
		this.id = id;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		if(mId==null||mId.equals("null")){
			mId="0";
		}
		this.mId = mId;
	}
	public String getMaterielNumber() {
		return materielNumber;
	}
	public void setMaterielNumber(String materielNumber) {
		if(materielNumber==null||materielNumber.equals("null")){
			materielNumber="";
		}
		this.materielNumber = materielNumber;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		if(materialName==null||materialName.equals("null")){
			materialName="";
		}
		this.materialName = materialName;
	}
	public String getBarcodNumber() {
		return barcodNumber;
	}
	public void setBarcodNumber(String barcodNumber) {
		if(barcodNumber==null||barcodNumber.equals("null")){
			barcodNumber="";
		}
		this.barcodNumber = barcodNumber;
	}
	public String getMaterialQty() {
		return materialQty;
	}
	public void setMaterialQty(String materialQty) {
		if(materialQty==null||materialQty.equals("null")){
			materialQty="0";
		}
		this.materialQty = materialQty;
	}
	public String getMaterialUnitID() {
		return materialUnitID;
	}
	public void setMaterialUnitID(String materialUnitID) {
		if(materialUnitID==null||materialUnitID.equals("null")){
			materialUnitID="0";
		}
		this.materialUnitID = materialUnitID;
	}
	public String getMaterialUnitName() {
		return materialUnitName;
	}
	public void setMaterialUnitName(String materialUnitName) {
		if(materialUnitName==null||materialUnitName.equals("null")){
			materialUnitName="";
		}
		this.materialUnitName = materialUnitName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		if(typeName==null||typeName.equals("null")){
			typeName="";
		}
		this.typeName = typeName;
	}
	public String getTypeNumber() {
		return typeNumber;
	}
	public void setTypeNumber(String typeNumber) {
		if(typeNumber==null||typeNumber.equals("null")){
			typeNumber="";
		}
		this.typeNumber = typeNumber;
	}
	public String getMaterialBatch() {
		return materialBatch;
	}
	public void setMaterialBatch(String materialBatch) {
		if(materialBatch==null||materialBatch.equals("null")){
			materialBatch="";
		}
		this.materialBatch = materialBatch;
	}
	public String getSupplyID() {
		return supplyID;
	}
	public void setSupplyID(String supplyID) {
		if(supplyID==null||supplyID.equals("null")){
			supplyID="0";
		}
		this.supplyID = supplyID;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		if(supplyName==null||supplyName.equals("null")){
			supplyName="";
		}
		this.supplyName = supplyName;
	}
	public String getStockID() {
		return stockID;
	}
	public void setStockID(String stockID) {
		if(stockID==null||stockID.equals("null")||stockID.equals("*")){
			stockID="0";
		}
		this.stockID = stockID;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		if(stockName==null||stockName.equals("null")){
			stockName="";
		}
		this.stockName = stockName;
	}
	public String getStockPlaceID() {
		return stockPlaceID;
	}
	public void setStockPlaceID(String stockPlaceID) {
		if(stockPlaceID==null||stockPlaceID.equals("null")||stockPlaceID.equals("*")){
			stockPlaceID="0";
		}
		this.stockPlaceID = stockPlaceID;
	}
	public String getStockPlaceName() {
		return stockPlaceName;
	}
	public void setStockPlaceName(String stockPlaceName) {
		if(stockPlaceName==null||stockPlaceName.equals("null")){
			stockPlaceName="";
		}
		this.stockPlaceName = stockPlaceName;
	}
	public String getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(String departmentID) {
		if(departmentID==null||departmentID.equals("null")){
			departmentID="0";
		}
		this.departmentID = departmentID;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		if(departmentName==null||departmentName.equals("null")){
			departmentName="";
		}
		this.departmentName = departmentName;
	}
	public String getZbm() {
		return zbm;
	}
	public void setZbm(String zbm) {
		this.zbm = zbm;
	}
	
}
