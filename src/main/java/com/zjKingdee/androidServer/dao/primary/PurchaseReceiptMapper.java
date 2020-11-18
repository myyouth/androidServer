package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.alibaba.fastjson.JSONArray;
import com.zjKingdee.androidServer.entity.PurchaseReceiptEntity;
import com.zjKingdee.androidServer.entity.QCJCXEntity;
import com.zjKingdee.androidServer.entity.QCPurchaseReceiptEntity;

/**
 * 生成采购入库单
 * 生成来料检验单
 */
public interface PurchaseReceiptMapper {
    @Insert({"<script>",
            "DECLARE @No VARCHAR(50);",
            "exec p_BM_GetBillNo 1,@No output;",
            "DECLARE @test VARCHAR(50);",
            "exec GetICBillNo 1,1,@test output;",
            "DECLARE @Num INT;",
            "exec GetICMaxNum 'icstockbill',@Num output;",
            "insert into ICStockBill (FBrNo,FInterID,FTranType,FDate,FBillNo,FDeptID,FEmpID,FSupplyID,FStatus,FBillerID,FFManagerID,FSManagerID,FPOStyle,FSelTranType,FCussentAcctID)",
            "values(0,(@Num),1,#{date},(@No),#{entity.FDeptID},#{entity.FEmpID},#{entity.FSupplyID},1,#{entity.FBillId},#{entity.shipperId},#{entity.safeKeepingId},252,72,1290);",
            "<foreach collection='data' item='item' index='index' separator=';'>",
            "insert into ICStockBillEntry (FBrNo,FInterID,FEntryID,FItemID,FBatchNo,FUnitID,fqtymust,FAuxQty,FQty,FAuxQtyMust,FAuxPrice,FPrice,FAmount,FDCStockID,FDCSPID,FSourceTranType,FSourceInterId,FSourceBillNo," +
            "FOrderInterID,FOrderBillNo,FOrderEntryID,FSourceEntryID,FEntrySelfA0171)",
            "values(0,	@Num,#{index},#{item.FItemID},#{item.FBatchNo},#{item.materialUnitId},#{item.FQty},",
            "((#{item.materialActualSum})*(select FCoefficient from t_MeasureUnit a inner join t_UnitGroup b on a.FUnitGroupID=b.FUnitGroupID where a.FMeasureUnitID=#{item.fGroupUnitID} and a.FName=#{item.materialUnit}))," +
            "#{item.materialActualSum},((#{item.FQty})*(select FCoefficient from t_MeasureUnit a inner join t_UnitGroup b on a.FUnitGroupID=b.FUnitGroupID where a.FMeasureUnitID=#{item.fGroupUnitID} and a.FName=#{item.materialUnit})),",
            "#{item.FPrice},#{item.FPrice},#{item.totalPrice},#{item.stockNameId},(select FSPID from t_StockPlace where FNumber=#{item.fstockNumber}),#{item.FtranType},#{item.FInterID},#{item.FBillNo},#{item.FSourceInterId},#{item.FSourceBillNo}," +
            "#{item.FSourceEntryID},#{item.FSourceEntryID},#{item.zbm});",
            "</foreach>",
            "<foreach collection='barcodes' item='item' index='index' separator=';'>",
            "update t_BarcodeInfo set stockPlaceName=(select FName from t_StockPlace where FNumber=#{item.fstockNumber})," +
                    "stockPlaceID=(select FSPId from t_StockPlace where FNumber=#{item.fstockNumber}),status=1 where barcodNumber=#{item.barcodeNumber}",
            "insert into t_BarcodeCZInfo (barcodNumber,czId,czType,czTypeName,createTime) values (#{item.barcodeNumber},1,@No,'外购入库单',#{date})",        
            "</foreach>",
            "</script>"})
    void insertPurchaseReceiptt(@Param("data") List<PurchaseReceiptEntity> entitys, @Param("date")String date, @Param("entity")PurchaseReceiptEntity entity,@Param("barcodes") List<PurchaseReceiptEntity> barcodes);


    @Update({"<script>",
            "<foreach collection='data' item='item' index='index' separator=';'>",
            "update t_BarcodeInfo set stockPlaceName=(select FName from t_StockPlace where FNumber=#{item.fstockNumber})," +
                    "stockPlaceID=#{select FSPId from t_StockPlace where FNumber=#{item.fstockNumber}},status=1 where barcodNumber=#{item.barcodeNumber}",
            "</foreach>",
            "</script>"})
    void updateBarcodeNumberPurchaseReceip(@Param("data") List<PurchaseReceiptEntity> entitys);
    
    @Insert({"<script>",
        "DECLARE @No VARCHAR(50);",
        "exec p_BM_GetBillNo 1001000,@No output;",
        "DECLARE @test VARCHAR(50);",
        "exec GetICBillNo 1,1001000,@test output;",
        "DECLARE @Num INT;",
        "exec GetICMaxNum 'ICQCBill',@Num output;",
        "insert into ICQCBill (FInterID,FTranType,FType,FDate,FBillNo,FSCBillInterID,FSupplyID,FItemID,FUnitID,FBatchNo,FCheckMethod,FSendUpQty,FCheckQty,FPassQty,FStatus,FBillDate,FNotPassQty,FPlanMode,FBizType,FSCBillNo,FCheckTimes,FFManagerID,FSManagerID,FBillerID,FResult,FSampleQty,FSampleBadQty)",
        "values (@Num,1001000,390,#{date},@No,(select FInterID from ICQCScheme i1 left join t_ICItem i2 on i2.FInspectionProject=i1.FInterID where i2.FNumber=#{entity.fmateNum}),#{entity.FSupplyID},(select FItemId from t_ICItem where FNumber=#{entity.fmateNum}),(select FUnitID from t_ICItem where FNumber=#{entity.fmateNum}),#{entity.fBatchNo},351,#{entity.fcheckSum},#{entity.fcheckSum},#{entity.passSum},0,#{date},#{entity.ngSum},14036,12510,(select i1.FBillNo from ICQCScheme i1 left join t_ICItem i2 on i2.FInspectionProject=i1.FInterID where i2.FNumber=#{entity.fmateNum}),1,#{entity.safeKeepingId},#{entity.shipperId},#{entity.fBillId},0,0,0);",
        "DECLARE @Ent VARCHAR(50);",
        "DECLARE @OrderBill VARCHAR(50);",
        "DECLARE @OrderEnt VARCHAR(50);",
        "DECLARE @unitID VARCHAR(50);",
        "DECLARE @OrderID VARCHAR(50);",
        "DECLARE @itemID VARCHAR(50);",
        "select @Ent=p1.FEntryID,@OrderBill=p4.FBillNo,@OrderEnt=p5.FEntryID,@unitID=p3.FUnitID,@OrderID=p4.FInterID,@itemID=p3.FItemID from POInStockEntry p1 left join POInStock p2 on p2.FInterID=p1.FInterID left join t_ICItem p3 on p3.FItemID=p1.FItemID left join POOrder p4 on p4.FInterID=p1.FSourceInterId left join POOrderEntry p5 on p5.FItemID=p3.FItemID where p2.FBillNo=#{entity.fBillNo} and p3.FNumber=#{entity.fmateNum};",
        "insert into QMSourceInfo (FInterID,FEntryID,FClassID_SRC,FBillNo_SRC,FID_SRC,FEntryID_SRC,FQCReqTranType,FQCReqBillNo,FQCReqEntryID,FQCReqInterID,FOrderTranType,FOrderBillNo,FOrderEntryID,FOrderInterID,FSourItemID,FSourUnitID,FSourSendUpQty,FSourCheckQty,FSourPassQty,FSourNotPassQty) ",
        "values (@Num,1,-72,#{entity.fBillNo},(select FInterID from POInStock where FBillNo=#{entity.fBillNo}),(@Ent),72,#{entity.fBillNo},(@Ent),(select FInterID from POInStock where FBillNo=#{entity.fBillNo}),71,@OrderBill,@OrderEnt,@OrderID,@itemID,@unitID,#{entity.fcheckSum},#{entity.fcheckSum},#{entity.passSum},#{entity.ngSum});",
        "DECLARE @v1 VARCHAR(50);",
        "DECLARE @v2 VARCHAR(50);",
        "DECLARE @v3 VARCHAR(50);",
        "DECLARE @v4 VARCHAR(50);",
        "<foreach collection='jcxs' item='item' index='index' separator=';'>",
        "select @v1=i1.FQCUnit,@v2=i1.FEmphasesCheck,@v3=i1.FQCItemID,@v4=FAnalysisMethodID from ICQCSchemeEntry i1 left join ICQCScheme i2 on i2.FInterID=i1.FInterID left join t_ICItem i3 on i3.FInspectionProject=i2.FInterID where i1.FEntryID=#{item.qcEntry} and i3.FNumber=#{entity.fmateNum};",
        "insert into ICQCBillEntry (FInterID,FEntryID,FQCUnit,FEmphasesCheck,FResult,FAnalysisMethodID,FQCItemID)",
        "values(@Num,#{item.qcEntry},@v1,@v2,#{item.isOk},@v4,@v3)",
        "</foreach>",
        "<foreach collection='barcodes' item='item' index='index' separator=';'>",
        "update t_BarcodeInfo set qc=#{item.isOk} where barcodNumber=#{item.NbarcodeNumber}",   
        "</foreach>",
        "<foreach collection='tpArray' item='item' index='index' separator=';'>",
        "insert t_accessory(FTypeid,FItemID,FPage,FEntryID,FDesc,FFileName,FFilesize,FUploader,FISPic,FVersion,FSaveMode,FShowFileName,FData)",
        "values(1001000,@Num,0,0,'',#{item.fullFileName},#{item.size},#{entity.fBillId},0,'2.0',0,#{item.fileName},#{item.content})",
        "</foreach>",
        "</script>"})
    void insertQC(@Param("barcodes") List<PurchaseReceiptEntity> entitys, @Param("date")String date, @Param("entity")QCPurchaseReceiptEntity entity,@Param("jcxs") List<QCJCXEntity> jcxs,@Param("tpArray")JSONArray tpArray);
    
    @Insert("insert t_accessory(FTypeid,FItemID,FPage,FEntryID,FDesc,FFileName,FFilesize,FUploader,FISPic,FVersion,FSaveMode,FShowFileName,FData) " + 
    		"values(1001000,@No,0,0,'','20200924133318_barcode.png',18889,16394,0,'2.0',0,'barcode.png',#{fData})")
    int insertQCTP(@Param("fData")byte[] fData);
}


