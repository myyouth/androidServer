package com.zjKingdee.androidServer.entity;

import java.math.BigDecimal;

/**
 * 收料通知单--->采购入库单
 */

public class PurchaseReceiptEntity {
    private String FEntryId;
    private String FInterID;
    private String FBillNo;
    private String FPOStyle; //采购方式id
    private String FSupplyID;
    private String fsupplyname; //供应商
    private String materialNumber;
    private String materialName;
    private String materialUnitId;
    private String materialUnit;
    private String FQty;
    private String materialActualSum;
    private String FPrice;
    private String TotalPrice;
    private String fstockName; //仓库
    private String stockNameId;
    private String fstockNumber;//仓位编码
    private String stockPlace;//仓位
    private String stockPlaceId;
    private String FSourceBillNo;
    private String FSourceInterId;
    private String FEmpID;
    private String fempName;
    private String FDeptID;
    private String fdeptName;
    private String fposname; //采购方式
    private String FBatchNo;  //批次
    private String FItemID;  //物料内码
    private String safeKeepingId;//保管
    private String safeKeeping;
    private String shipper;//验收
    private String shipperId;
    private String NbarcodeNumber;
    private String barcodeNumber;
    private String FtranType;
    private String fGroupUnitID; //物料默认计量单位
    private String FSourceEntryID;
    private String FBillId;//制单人
    private String zbm;//自编码
    private String isOk;

    public String getIsOk() {
		return isOk;
	}

	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}

	public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public String getFSourceEntryID() {
        return FSourceEntryID;
    }

    public void setFSourceEntryID(String FSourceEntryID) {
        this.FSourceEntryID = FSourceEntryID;
    }

    public String getfGroupUnitID() {
        return fGroupUnitID;
    }

    public void setfGroupUnitID(String fGroupUnitID) {
        this.fGroupUnitID = fGroupUnitID;
    }

    public String getFtranType() {
        return FtranType;
    }

    public void setFtranType(String ftranType) {
        FtranType = ftranType;
    }

    public String getFSourceInterId() {
        return FSourceInterId;
    }

    public void setFSourceInterId(String FSourceInterId) {
        this.FSourceInterId = FSourceInterId;
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

    public String getMaterialActualSum() {
        return materialActualSum;
    }

    public void setMaterialActualSum(String materialActualSum) {
        this.materialActualSum = materialActualSum;
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

    public String getStockNameId() {
        return stockNameId;
    }

    public void setStockNameId(String stockNameId) {
        this.stockNameId = stockNameId;
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

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getFBatchNo() {
        return FBatchNo;
    }

    public void setFBatchNo(String FBatchNo) {
        this.FBatchNo = FBatchNo;
    }

    public String getFItemID() {
        return FItemID;
    }

    public void setFItemID(String FItemID) {
        this.FItemID = FItemID;
    }

    public String getFposname() {
        return fposname;
    }

    public void setFposname(String fposname) {
        this.fposname = fposname;
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

    public String getFPOStyle() {
        return FPOStyle;
    }

    public void setFPOStyle(String FPOStyle) {
        this.FPOStyle = FPOStyle;
    }

    public String getFSupplyID() {
        return FSupplyID;
    }

    public void setFSupplyID(String FSupplyID) {
        this.FSupplyID = FSupplyID;
    }

    public String getFsupplyname() {
        return fsupplyname;
    }

    public void setFsupplyname(String fsupplyname) {
        this.fsupplyname = fsupplyname;
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

    public String getFQty() {
        return FQty;
    }

    public void setFQty(String FQty) {
        this.FQty = new BigDecimal(FQty).stripTrailingZeros().toPlainString();
    }

    public String getFPrice() {
        return FPrice;
    }

    public void setFPrice(String FPrice) {
        this.FPrice = FPrice;
    }

    public String getFstockName() {
        return fstockName;
    }

    public void setFstockName(String fstockName) {
        this.fstockName = fstockName;
    }

    public String getFSourceBillNo() {
        return FSourceBillNo;
    }

    public void setFSourceBillNo(String FSourceBillNo) {
        this.FSourceBillNo = FSourceBillNo;
    }

    public String getFEmpID() {
        return FEmpID;
    }

    public void setFEmpID(String FEmpID) {
        this.FEmpID = FEmpID;
    }

    public String getFempName() {
        return fempName;
    }

    public void setFempName(String fempName) {
        this.fempName = fempName;
    }

    public String getFDeptID() {
        return FDeptID;
    }

    public void setFDeptID(String FDeptID) {
        this.FDeptID = FDeptID;
    }

    public String getFdeptName() {
        return fdeptName;
    }

    public void setFdeptName(String fdeptName) {
        this.fdeptName = fdeptName;
    }

	public String getFBillId() {
		return FBillId;
	}

	public void setFBillId(String fBillId) {
		this.FBillId = fBillId;
	}

	public String getZbm() {
		return zbm;
	}

	public void setZbm(String zbm) {
		this.zbm = zbm;
	}
    
    
}


