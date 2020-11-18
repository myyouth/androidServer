package com.zjKingdee.androidServer.entity;

import java.math.BigDecimal;

/**
 * 发货通知单--->销售出库单
 * @author Yinqi				
 * @date 2020年3月23日
 */
public class ConsignmentNoteForSaleIssueEntity {
	private String id;
	private String sourceId;//订单内码
	private String sourceNumber;//订单编号
	private String docId;//发货通知单内码
	private String docNumber;//发货通知单编号
	private String type;//销售业务类型
	private String typeId;
	private String salesWay;//销售方式
	private String salesWayId;
	private String purchasingUnit;//购货单位
	private String purchasingUnitId;
	private String dept;//单位
	private String deptId;
	private String director;//主管
	private String directorId;
	private String salesMan;//业务员
	private String salesManId;
	private String materialId;//物料内码
	private String materialNumber;//物料编码
	private String materialName;//物料名称
	private String materialBatch;//批号
	private String stockName;//仓库
	private String stockNameId;
	private String stockPlace;//仓位
	private String stockPlaceId;
	private String materialSum;//应发数量
	private String materialActualSum;//实发数量
	private String materialUnit;//单位
	private String materialUnitId;
	private String materialPrice;//价格
	private String materialTotalPrice;//总价
	private String shipper;//发货
	private String shipperId;
	private String mapperNumber;
	private String mapperName;
	private String safeKeepingId;//保管
	private String safeKeeping;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getSourceNumber() {
		return sourceNumber;
	}
	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getSalesWay() {
		return salesWay;
	}
	public void setSalesWay(String salesWay) {
		this.salesWay = salesWay;
	}
	public String getSalesWayId() {
		return salesWayId;
	}
	public void setSalesWayId(String salesWayId) {
		this.salesWayId = salesWayId;
	}
	public String getPurchasingUnit() {
		return purchasingUnit;
	}
	public void setPurchasingUnit(String purchasingUnit) {
		this.purchasingUnit = purchasingUnit;
	}
	public String getPurchasingUnitId() {
		return purchasingUnitId;
	}
	public void setPurchasingUnitId(String purchasingUnitId) {
		this.purchasingUnitId = purchasingUnitId;
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
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDirectorId() {
		return directorId;
	}
	public void setDirectorId(String directorId) {
		this.directorId = directorId;
	}
	public String getSalesMan() {
		return salesMan;
	}
	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}
	public String getSalesManId() {
		return salesManId;
	}
	public void setSalesManId(String salesManId) {
		this.salesManId = salesManId;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
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
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockNameId() {
		return stockNameId;
	}
	public void setStockNameId(String stockNameId) {
		this.stockNameId = stockNameId;
	}
	public String getStockPlace() {
		return stockPlace;
	}
	public void setStockPlace(String stockPlace) {
		this.stockPlace = stockPlace;
	}
	public String getStockPlaceId() {
		return stockPlaceId;
	}
	public void setStockPlaceId(String stockPlaceId) {
		this.stockPlaceId = stockPlaceId;
	}
	public String getMaterialSum() {
		return materialSum;
	}
	public void setMaterialSum(String materialSum) {
		this.materialSum = new BigDecimal(materialSum).stripTrailingZeros().toPlainString();
	}
	public String getMaterialUnit() {
		return materialUnit;
	}
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	public String getMaterialUnitId() {
		return materialUnitId;
	}
	public void setMaterialUnitId(String materialUnitId) {
		this.materialUnitId = materialUnitId;
	}
	public String getMaterialPrice() {
		return materialPrice;
	}
	public void setMaterialPrice(String materialPrice) {
		this.materialPrice = materialPrice;
	}
	public String getMaterialTotalPrice() {
		return materialTotalPrice;
	}
	public void setMaterialTotalPrice(String materialTotalPrice) {
		this.materialTotalPrice = materialTotalPrice;
	}
	public String getMaterialActualSum() {
		return materialActualSum;
	}
	public void setMaterialActualSum(String materialActualSum) {
		this.materialActualSum = materialActualSum;
	}
	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public String getShipperId() {
		return shipperId;
	}
	public void setShipperId(String shipperId) {
		this.shipperId = shipperId;
	}
	public String getMapperNumber() {
		return mapperNumber;
	}
	public void setMapperNumber(String mapperNumber) {
		this.mapperNumber = mapperNumber;
	}
	public String getMapperName() {
		return mapperName;
	}
	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}
	public String getSafeKeepingId() {
		return safeKeepingId;
	}
	public void setSafeKeepingId(String safeKeepingId) {
		this.safeKeepingId = safeKeepingId;
	}
	public String getSafeKeeping() {
		return safeKeeping;
	}
	public void setSafeKeeping(String safeKeeping) {
		this.safeKeeping = safeKeeping;
	}
	
}
