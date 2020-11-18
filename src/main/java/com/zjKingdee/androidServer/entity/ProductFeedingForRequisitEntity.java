package com.zjKingdee.androidServer.entity;

import java.math.BigDecimal;

/**
 * 生产投料单
 * @author Yinqi				
 * @date 2020年3月10日
 */
public class ProductFeedingForRequisitEntity {
	private String id;
	private String type;//生产类型
	private String dept;//部门
	private String deptId;
	private String materialNumber;//物料
	private String materialId;
	private String materialName;
	private String materialBatch;//批号
	private String materialSum;//申请数量
	private String materialActualSum;//实发数量
	private String AuxmaterialSum;//基本申请数量
	private String AuxmaterialActualSum;//基本实发数量
	private String stockName;//仓库
	private String stockId;
	private String stockPlace;//仓位
	private String stockPlaceID;
	private String unitId;//单位
	private String unitName;
	private String mual;//基本单位用量/单位用量
	private String productId;//产品
	private String productName;
	private String icmoId;//生产任务单
	private String icmoNo;
	private String icmoNum;//成品物料编码呈现自编码
	
	public ProductFeedingForRequisitEntity(String id, String type, String dept, String deptId, String materialNumber,
			String materialId, String materialName, String materialBatch, String materialSum, String materialActualSum,
			String auxmaterialSum, String auxmaterialActualSum, String stockName, String stockId, String stockPlace,
			String stockPlaceID, String unitId, String unitName, String mual, String productId, String productName,
			String icmoId, String icmoNo) {
		super();
		this.id = id;
		this.type = type;
		this.dept = dept;
		this.deptId = deptId;
		this.materialNumber = materialNumber;
		this.materialId = materialId;
		this.materialName = materialName;
		this.materialBatch = materialBatch;
		this.materialSum = materialSum;
		this.materialActualSum = materialActualSum;
		this.AuxmaterialSum = auxmaterialSum;
		this.AuxmaterialActualSum = auxmaterialActualSum;
		this.stockName = stockName;
		this.stockId = stockId;
		this.stockPlace = stockPlace;
		this.stockPlaceID = stockPlaceID;
		this.unitId = unitId;
		this.unitName = unitName;
		this.mual = mual;
		this.productId = productId;
		this.productName = productName;
		this.icmoId = icmoId;
		this.icmoNo = icmoNo;
	}

	public ProductFeedingForRequisitEntity(Integer id,Integer type, Integer deptId, String dept, String materialNumber,
			Integer materialId, String materialName, String materialBatch, Integer unitId, String unitName,
			Integer stockId, String stockName, Integer stockPlaceID, String stockPlace, BigDecimal materialSum,
			BigDecimal mual,Integer productId, String productName,Integer icmoId, String icmoNo,String icmoNum) {
		super();
		this.id = String.valueOf(id);
		this.type = String.valueOf(type);
		this.dept = dept;
		this.deptId = String.valueOf(deptId);
		this.materialNumber = materialNumber;
		this.materialId = String.valueOf(materialId);
		this.materialName = materialName;
		this.materialBatch = materialBatch;
		this.stockName = stockName;
		this.stockId = String.valueOf(stockId);
		this.stockPlace = stockPlace;
		this.stockPlaceID = String.valueOf(stockPlaceID);
		this.unitId = String.valueOf(unitId);
		this.unitName = unitName;
//		if(materialSum.compareTo(new BigDecimal("0"))==-1) {
//			this.materialSum="0";
//		}else {
//			this.materialSum=materialSum.stripTrailingZeros().toPlainString();
//		}
		this.materialSum=materialSum.stripTrailingZeros().toPlainString();
		this.materialActualSum="0";
		this.mual=mual.stripTrailingZeros().toPlainString();
		this.productId=String.valueOf(productId);
		this.productName=productName;
		this.icmoId=String.valueOf(icmoId);
		this.icmoNo=icmoNo;
		this.icmoNum=icmoNum;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialBatch() {
		return materialBatch;
	}
	public void setMaterialBatch(String materialBatch) {
		this.materialBatch = materialBatch;
	}
	public String getMaterialSum() {
		return materialSum;
	}
	public void setMaterialSum(String materialSum) {
		if(new BigDecimal(materialSum).compareTo(new BigDecimal("0"))==-1) {
			this.materialSum="0";
		}else {
			this.materialSum = new BigDecimal(materialSum).stripTrailingZeros().toPlainString();
		}
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getStockPlace() {
		return stockPlace;
	}
	public void setStockPlace(String stockPlace) {
		this.stockPlace = stockPlace;
	}
	public String getStockPlaceID() {
		return stockPlaceID;
	}
	public void setStockPlaceID(String stockPlaceID) {
		this.stockPlaceID = stockPlaceID;
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

	public String getMaterialActualSum() {
		return materialActualSum;
	}

	public void setMaterialActualSum(String materialActualSum) {
		this.materialActualSum = materialActualSum;
	}

	public String getMual() {
		return mual;
	}

	public void setMual(String mual) {
		this.mual = mual;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getIcmoId() {
		return icmoId;
	}

	public void setIcmoId(String icmoId) {
		this.icmoId = icmoId;
	}

	public String getIcmoNo() {
		return icmoNo;
	}

	public void setIcmoNo(String icmoNo) {
		this.icmoNo = icmoNo;
	}

	public String getAuxmaterialSum() {
		return AuxmaterialSum;
	}

	public void setAuxmaterialSum(String auxmaterialSum) {
		AuxmaterialSum = auxmaterialSum;
	}

	public String getAuxmaterialActualSum() {
		return AuxmaterialActualSum;
	}

	public void setAuxmaterialActualSum(String auxmaterialActualSum) {
		AuxmaterialActualSum = auxmaterialActualSum;
	}

	public String getIcmoNum() {
		return icmoNum;
	}

	public void setIcmoNum(String icmoNum) {
		this.icmoNum = icmoNum.substring(icmoNum.lastIndexOf(".")+1);;
	}
	
}
