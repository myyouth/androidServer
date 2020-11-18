package com.zjKingdee.androidServer.entity;

import java.math.BigDecimal;

/**
 * 即使库存
 */
public class InventoryEntity {
    private String materialNumber;
    private String materialName;
    private String FItemID;  //物料内码
    private String FQty;
    private String fstockName; //仓库
    private String stockPlace;//仓位
    private String FBatchNo;

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

    public String getFItemID() {
        return FItemID;
    }

    public void setFItemID(String FItemID) {
        this.FItemID = FItemID;
    }

    public String getFQty() {
        return FQty;
    }

    public void setFQty(String FQty) {
        this.FQty = new BigDecimal(FQty).stripTrailingZeros().toPlainString();
    }

    public String getFstockName() {
        return fstockName;
    }

    public void setFstockName(String fstockName) {
        this.fstockName = fstockName;
    }

    public String getStockPlace() {
        return stockPlace;
    }

    public void setStockPlace(String stockPlace) {
        this.stockPlace = stockPlace;
    }

    public String getFBatchNo() {
        return FBatchNo;
    }

    public void setFBatchNo(String FBatchNo) {
        this.FBatchNo = FBatchNo;
    }
}
