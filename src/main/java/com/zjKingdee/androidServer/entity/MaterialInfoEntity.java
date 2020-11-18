package com.zjKingdee.androidServer.entity;

import java.math.BigDecimal;

public class MaterialInfoEntity {
	private String id;
	private String barcodeNumber;//条码
	private String mId;//物料内码
	private String mNumber;//物料编码
	private String mName;//物料名称
	private String lSum;//生成数
	private String mSum;//数量
	private String minSum;//最小包装数
	private String unitId;//单位内码
	private String unitName;//单位
	private String type;//单据类型
	private String typeNumber;//单据编号
	private String batch;//批号
	private String supplierId;//供应商内码
	private String supplier;//供应商
	private String warehouseId;//仓库编码
	private String warehouse;//仓库
	private String positionId;//仓位编码
	private String position;//仓位
	private String workShop;//部门
	private String workShopId;//部门ID
	private String ZBM;//自编码
	public MaterialInfoEntity(String id, String barcodeNumber, String mId, String mNumber, String mName, String lSum,
			String mSum, String minSum, String unitId, String unitName, String type, String typeNumber, String batch,
			String supplierId, String supplier, String warehouseId, String warehouse, String positionId,
			String position, String workShop, String workShopId,String zbm) {
		super();
		this.id = id;
		this.barcodeNumber = barcodeNumber;
		this.mId = mId;
		this.mNumber = mNumber;
		this.mName = mName;
		this.lSum = lSum;
		this.mSum = mSum;
		this.minSum = minSum;
		this.unitId = unitId;
		this.unitName = unitName;
		this.type = type;
		this.typeNumber = typeNumber;
		this.batch = batch;
		this.supplierId = supplierId;
		this.supplier = supplier;
		this.warehouseId = warehouseId;
		this.warehouse = warehouse;
		this.positionId = positionId;
		this.position = position;
		this.workShop = workShop;
		this.workShopId = workShopId;
		this.ZBM=zbm;
	}
	
	public MaterialInfoEntity(Integer mId, String mNumber, String mName, BigDecimal mSum, Integer unitId, String unitName,
			String batch) {
		super();
		this.mId = String.valueOf(mId);
		this.mNumber = mNumber;
		this.mName = mName;
		this.mSum = mSum.toPlainString();
		this.unitId = String.valueOf(unitId);
		this.unitName = unitName;
		this.batch = batch;
	}

	public MaterialInfoEntity(String mNumber, String mName, String batch, String warehouse, String position,BigDecimal mSum,String unitName,Integer positionId,String zbm,String supply,String typeNumber) {
		super();
		this.mNumber = mNumber;
		this.mName = mName;
		this.batch = batch;
		this.warehouse = warehouse;
		this.position = position;
		this.mSum=mSum.toPlainString();
		this.unitName=unitName;
		this.positionId=String.valueOf(positionId);
		this.ZBM=zbm;
		this.typeNumber=typeNumber;
		this.supplier=supply;
	}
	public MaterialInfoEntity(Integer id, Integer mId, String mNumber, String mName, BigDecimal mSum, Integer unitId,
			String unitName, String batch, Integer supplierId, String supplier, String warehouseId, String warehouse,
			String positionId, String position,String ZBM) {
		super();
		this.id = String.valueOf(id);
		this.mId = String.valueOf(mId);
		this.mNumber = mNumber;
		this.mName = mName;
		this.mSum = mSum.stripTrailingZeros().toPlainString();
		this.unitId = String.valueOf(unitId);
		this.unitName = unitName;
		this.batch = batch;
		this.supplierId = String.valueOf(supplierId);
		this.supplier = supplier;
		this.warehouseId = String.valueOf(warehouseId);
		this.warehouse = warehouse;
		this.positionId = String.valueOf(positionId);
		this.position = position;
		this.ZBM=ZBM;
	}
	public MaterialInfoEntity(Integer mId, String mNumber, String mName, BigDecimal mSum, Integer unitId, String unitName,
			String batch, Integer workShopID, String workShop) {
		super();
		this.id="1";
		this.mId = String.valueOf(mId);
		this.mNumber = mNumber;
		this.mName = mName;
		this.mSum = mSum.stripTrailingZeros().toPlainString();
		this.unitId = String.valueOf(unitId);
		this.unitName = unitName;
		this.batch = batch;
		this.workShop = workShop;
		this.workShopId = String.valueOf(workShopID);
	}
	public MaterialInfoEntity(Integer mId, String mNumber, String mName, Integer unitId, String unitName) {
		super();
		this.mId = String.valueOf(mId);
		this.mNumber = mNumber;
		this.mName = mName;
		this.unitId = String.valueOf(unitId);
		this.unitName = unitName;
	}
	public MaterialInfoEntity(String id, String mId, String mNumber, String mName, String lSum, String mSum,
			String minSum, String unitId, String unitName, String type, String typeNumber, String batch,
			String supplierId, String supplier, String warehouseId, String warehouse, String positionId,
			String position, String workShop, String workShopId) {
		super();
		this.id = id;
		this.mId = mId;
		this.mNumber = mNumber;
		this.mName = mName;
		this.lSum = lSum;
		this.mSum = mSum;
		this.minSum = minSum;
		this.unitId = unitId;
		this.unitName = unitName;
		this.type = type;
		this.typeNumber = typeNumber;
		this.batch = batch;
		this.supplierId = supplierId;
		this.supplier = supplier;
		this.warehouseId = warehouseId;
		this.warehouse = warehouse;
		this.positionId = positionId;
		this.position = position;
		this.workShop = workShop;
		this.workShopId = workShopId;
	}
	
	public MaterialInfoEntity(String barcodeNumber, Integer mId, String mNumber, String mName, String batch,
			Integer unitId, String unitName, Integer warehouseId, String warehouse, Integer positionId,
			String position, BigDecimal mSum) {
		super();
		this.barcodeNumber = barcodeNumber;
		this.mId = String.valueOf(mId);
		this.mNumber = mNumber;
		this.mName = mName;
		this.mSum = mSum.toPlainString();
		this.unitId = String.valueOf(unitId);
		this.unitName = unitName;
		this.batch = batch;
		this.warehouseId = String.valueOf(warehouseId);
		this.warehouse = warehouse;
		this.positionId = String.valueOf(positionId);
		this.position = position;
	}
	public String getBarcodeNumber() {
		return barcodeNumber;
	}

	public void setBarcodeNumber(String barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmNumber() {
		return mNumber;
	}
	public void setmNumber(String mNumber) {
		this.mNumber = mNumber;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getlSum() {
		return lSum;
	}
	public void setlSum(String lSum) {
		this.lSum = lSum;
	}
	public String getmSum() {
		return mSum;
	}
	public void setmSum(String mSum) {
		this.mSum = mSum;
	}
	public String getMinSum() {
		return minSum;
	}
	public void setMinSum(String minSum) {
		this.minSum = minSum;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeNumber() {
		return typeNumber;
	}
	public void setTypeNumber(String typeNumber) {
		this.typeNumber = typeNumber;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getWorkShop() {
		return workShop;
	}
	public void setWorkShop(String workShop) {
		this.workShop = workShop;
	}

	public String getWorkShopId() {
		return workShopId;
	}

	public void setWorkShopId(String workShopId) {
		this.workShopId = workShopId;
	}

	public String getZBM() {
		return ZBM;
	}

	public void setZBM(String zBM) {
		ZBM = zBM;
	}
	
}
