package com.zjKingdee.androidServer.entity;

import java.math.BigDecimal;

public class ProductionReportEntity {
    /**
     * 生产汇报单--->生产入库单
     */
    private String FEntryId;
    private String FInterID;
    private String FBillNo; //汇报单号
    private String materialNumber;
    private String materialName;
    private String materialUnitId;
    private String materialUnit;
    private String FItemID;  //物料内码
    private String FAuxQtyFinish; //应收数量
    private String materialActualSum;//实收数量
    private String FSourceBillNo;
    private String safeKeepingId;//保管
    private String safeKeeping;
    private String shipper;//验收
    private String shipperId;
    private String fstockName; //仓库
    private String stockNameId;
    private String stockPlace;//仓位
    private String stockPlaceId;
    private String fstockNumber; //仓位编码
    private String FBatchNo;
    private String FWorkShop; //交货单位
    private String FWorkShopId; //交货单位
    private String NbarcodeNumber;
    private String rwBillNo; //任务单号
    private String FSourceInterId;
    private String FSelTranType;
    private String FICMOInterID;
    private String FSourceEntryID;
    private String fGroupUnitID; //物料默认计量单位
    private String creatorId;//创建者
    private String docTypeId;//红蓝字

    public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getfGroupUnitID() {
        return fGroupUnitID;
    }

    public void setfGroupUnitID(String fGroupUnitID) {
        this.fGroupUnitID = fGroupUnitID;
    }

    public String getFSourceEntryID() {
        return FSourceEntryID;
    }

    public void setFSourceEntryID(String FSourceEntryID) {
        this.FSourceEntryID = FSourceEntryID;
    }

    public String getFICMOInterID() {
        return FICMOInterID;
    }

    public void setFICMOInterID(String FICMOInterID) {
        this.FICMOInterID = FICMOInterID;
    }

    public String getFSelTranType() {
        return FSelTranType;
    }

    public void setFSelTranType(String FSelTranType) {
        this.FSelTranType = FSelTranType;
    }

    public String getFSourceInterId() {
        return FSourceInterId;
    }

    public void setFSourceInterId(String FSourceInterId) {
        this.FSourceInterId = FSourceInterId;
    }

    public String getRwBillNo() {
        return rwBillNo;
    }

    public void setRwBillNo(String rwBillNo) {
        this.rwBillNo = rwBillNo;
    }

    public String getFWorkShop() {
        return FWorkShop;
    }

    public void setFWorkShop(String FWorkShop) {
        this.FWorkShop = FWorkShop;
    }

    public String getFstockNumber() {
        return fstockNumber;
    }

    public void setFstockNumber(String fstockNumber) {
        this.fstockNumber = fstockNumber;
    }

    public String getNbarcodeNumber() {
        return NbarcodeNumber;
    }

    public void setNbarcodeNumber(String nbarcodeNumber) {
        NbarcodeNumber = nbarcodeNumber;
    }

    public String getFBatchNo() {
        return FBatchNo;
    }

    public void setFBatchNo(String FBatchNo) {
        this.FBatchNo = FBatchNo;
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

    public String getFstockName() {
        return fstockName;
    }

    public void setFstockName(String fstockName) {
        this.fstockName = fstockName;
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

    public String getMaterialActualSum() {
        return materialActualSum;
    }

    public void setMaterialActualSum(String materialActualSum) {
        this.materialActualSum = materialActualSum;
    }

    public String getFEntryId() {
        return FEntryId;
    }

    public void setFEntryId(String FEntryId) {
        this.FEntryId = FEntryId;
    }

    public String getFInterID() {
        return FInterID;
    }

    public void setFInterID(String FInterID) {
        this.FInterID = FInterID;
    }

    public String getFBillNo() {
        return FBillNo;
    }

    public void setFBillNo(String FBillNo) {
        this.FBillNo = FBillNo;
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

    public String getMaterialUnitId() {
        return materialUnitId;
    }

    public void setMaterialUnitId(String materialUnitId) {
        this.materialUnitId = materialUnitId;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }

    public String getFItemID() {
        return FItemID;
    }

    public void setFItemID(String FItemID) {
        this.FItemID = FItemID;
    }

    public String getFAuxQtyFinish() {
        return FAuxQtyFinish;
    }

    public void setFAuxQtyFinish(String FAuxQtyFinish) {
        this.FAuxQtyFinish = new BigDecimal(FAuxQtyFinish).stripTrailingZeros().toPlainString();
    }

    public String getFSourceBillNo() {
        return FSourceBillNo;
    }

    public void setFSourceBillNo(String FSourceBillNo) {
        this.FSourceBillNo = FSourceBillNo;
    }

	public String getFWorkShopId() {
		return FWorkShopId;
	}

	public void setFWorkShopId(String fWorkShopId) {
		FWorkShopId = fWorkShopId;
	}

	public String getDocTypeId() {
		return docTypeId;
	}

	public void setDocTypeId(String docTypeId) {
		this.docTypeId = docTypeId;
	}
	
}

