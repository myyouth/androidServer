package com.zjKingdee.androidServer.dao.primary;

import com.alibaba.fastjson.JSONArray;
import com.zjKingdee.androidServer.entity.MaterialInfoEntity;
import com.zjKingdee.androidServer.entity.ProductionReportEntity;
import com.zjKingdee.androidServer.entity.PurchaseReceiptEntity;
import com.zjKingdee.androidServer.entity.QCJCXEntity;
import com.zjKingdee.androidServer.entity.QCPurchaseReceiptEntity;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 生成生产入库单
 */
public interface ProductionReceiptMapper {
    @Insert({"<script>",
            "DECLARE @No VARCHAR(50);",
            "exec p_BM_GetBillNo 2,@No output;",
            "DECLARE @test VARCHAR(50);",
            "exec GetICBillNo 1,2,@test output;",
            "DECLARE @Num INT;",
            "exec GetICMaxNum 'icstockbill',@Num output;",
            "insert into ICStockBill (FBrNo,FInterID,FTranType,FDate,FBillNo,FDeptID,FStatus,FBillerID,FFManagerID,FSManagerID,FROB)",
            "values(0,(@Num),2,#{date},(@No),#{entity.FWorkShopId},1,#{entity.creatorId},#{entity.shipperId},#{entity.safeKeepingId},#{entity.docTypeId});",
            "<foreach collection='data' item='item' index='index' separator=';'>",
            "insert into ICStockBillEntry (FBrNo,FInterID,FEntryID,FItemID,FBatchNo,FUnitID,fqtymust,FAuxQty,FQty,FAuxQtyMust,FDCStockID,FDCSPID,FSourceTranType,FSourceInterId,FSourceBillNo,FSourceEntryID,FICMOBillNo,FICMOInterID)",
            "values(0,(@Num),#{index},#{item.FItemID},#{item.FBatchNo},#{item.materialUnitId},#{item.FAuxQtyFinish}," +
            "((#{item.materialActualSum})*(select FCoefficient from t_MeasureUnit a inner join t_UnitGroup b on a.FUnitGroupID=b.FUnitGroupID where a.FMeasureUnitID=#{item.fGroupUnitID} and a.FName=#{item.materialUnit}))," +
            "#{item.materialActualSum},((#{item.FAuxQtyFinish})*(select FCoefficient from t_MeasureUnit a inner join t_UnitGroup b on a.FUnitGroupID=b.FUnitGroupID where a.FMeasureUnitID=#{item.fGroupUnitID} and a.FName=#{item.materialUnit})),",
            "#{item.stockNameId},#{item.stockPlaceId},551,(select finterid from ICMORpt where FBillNo=#{item.FBillNo}),#{item.FBillNo},#{item.FSourceEntryID},#{item.rwBillNo},#{item.FICMOInterID});",
            "</foreach>",
            "<foreach collection='barcodes' item='item' index='index' separator=';'>",
            "update t_BarcodeInfo set stockID=#{item.warehouseId},stockName=#{item.warehouse},stockPlaceName=#{item.position},",
            "stockPlaceID=#{item.positionId},status=1 where barcodNumber=#{item.barcodeNumber} and typeName='生产任务单'",
            "insert into t_BarcodeCZInfo (barcodNumber,czId,czType,czTypeName,createTime) values (#{item.barcodeNumber},1,@No,'产品入库单',#{date})",        
            "</foreach>",
            "</script>"})
    void insertProductionReceipt(@Param("data") List<ProductionReportEntity> entitys,@Param("barcodes")List<MaterialInfoEntity> barcodes, @Param("date")String date, @Param("entity")ProductionReportEntity entity);
    
    @Insert({"<script>",
        "DECLARE @No VARCHAR(50);",
        "exec p_BM_GetBillNo 2,@No output;",
        "DECLARE @test VARCHAR(50);",
        "exec GetICBillNo 1,2,@test output;",
        "DECLARE @Num INT;",
        "exec GetICMaxNum 'icstockbill',@Num output;",
        "insert into ICStockBill (FBrNo,FInterID,FTranType,FDate,FBillNo,FDeptID,FStatus,FBillerID,FFManagerID,FSManagerID,FROB)",
        "values(0,(@Num),2,#{date},(@No),#{entity.FWorkShopId},1,#{entity.creatorId},#{entity.shipperId},#{entity.safeKeepingId},#{entity.docTypeId});",
        "<foreach collection='data' item='item' index='index' separator=';'>",
        "insert into ICStockBillEntry (FBrNo,FInterID,FEntryID,FItemID,FBatchNo,FUnitID,fqtymust,FAuxQty,FQty,FAuxQtyMust,FDCStockID,FDCSPID,FSourceTranType,FSourceInterId,FSourceBillNo,FSourceEntryID,FICMOBillNo,FICMOInterID)",
        "values(0,(@Num),#{index},#{item.FItemID},#{item.FBatchNo},#{item.materialUnitId},#{item.FAuxQtyFinish}," +
        "((#{item.materialActualSum})*(select FCoefficient from t_MeasureUnit a inner join t_UnitGroup b on a.FUnitGroupID=b.FUnitGroupID where a.FMeasureUnitID=#{item.fGroupUnitID} and a.FName=#{item.materialUnit}))," +
        "#{item.materialActualSum},((#{item.FAuxQtyFinish})*(select FCoefficient from t_MeasureUnit a inner join t_UnitGroup b on a.FUnitGroupID=b.FUnitGroupID where a.FMeasureUnitID=#{item.fGroupUnitID} and a.FName=#{item.materialUnit})),",
        "#{item.stockNameId},#{item.stockPlaceId},551,(select finterid from ICMORpt where FBillNo=#{item.FBillNo}),#{item.FBillNo},#{item.FSourceEntryID},#{item.rwBillNo},#{item.FICMOInterID});",
        "</foreach>",
        "<foreach collection='barcodes' item='item' index='index' separator=';'>",
        "update t_BarcodeInfo set stockPlaceName=#{item.position},",
        "stockPlaceID=#{item.positionId},status=2 where barcodNumber=#{item.barcodeNumber} and typeName='生产任务单'",
        "insert into t_BarcodeCZInfo (barcodNumber,czId,czType,czTypeName,createTime) values (#{item.barcodeNumber},2,@No,'产品入库单',#{date})",        
        "</foreach>",
        "</script>"})
void insertProductionReceiptH(@Param("data") List<ProductionReportEntity> entitys,@Param("barcodes")List<MaterialInfoEntity> barcodes, @Param("date")String date, @Param("entity")ProductionReportEntity entity);
    
    @Insert({"<script>",
        "DECLARE @No VARCHAR(50);",
        "exec p_BM_GetBillNo 1001001,@No output;",
        "DECLARE @test VARCHAR(50);",
        "exec GetICBillNo 1,1001001,@test output;",
        "DECLARE @Num INT;",
        "exec GetICMaxNum 'ICQCBill',@Num output;",
        "insert into ICQCBill (FInterID,FTranType,FType,FDate,FBillNo,FSCBillInterID,FSupplyID,FItemID,FUnitID,FBatchNo,FCheckMethod,FSendUpQty,FCheckQty,FPassQty,FStatus,FBillDate,FNotPassQty,FPlanMode,FBizType,FSCBillNo,FCheckTimes,FFManagerID,FSManagerID,FBillerID,FResult,FSampleQty,FSampleBadQty)",
        "values (@Num,1001001,392,#{date},@No,(select FInterID from ICQCScheme i1 left join t_ICItem i2 on i2.FInspectionProject=i1.FInterID where i2.FNumber=#{entity.fmateNum}),#{entity.FSupplyID},(select FItemId from t_ICItem where FNumber=#{entity.fmateNum}),(select FUnitID from t_ICItem where FNumber=#{entity.fmateNum}),#{entity.fBatchNo},351,#{entity.fcheckSum},#{entity.fcheckSum},#{entity.passSum},0,#{date},#{entity.ngSum},14036,0,(select i1.FBillNo from ICQCScheme i1 left join t_ICItem i2 on i2.FInspectionProject=i1.FInterID where i2.FNumber=#{entity.fmateNum}),1,#{entity.safeKeepingId},#{entity.shipperId},#{entity.fBillId},0,0,0);",
        "DECLARE @Ent VARCHAR(50);",
        "DECLARE @OrderBill VARCHAR(50);",
        "DECLARE @unitID VARCHAR(50);",
        "DECLARE @OrderID VARCHAR(50);",
        "DECLARE @itemID VARCHAR(50);",
        "select @Ent=p1.FEntryID,@OrderBill=p4.FBillNo,@unitID=p3.FUnitID,@OrderID=p4.FInterID,@itemID=p3.FItemID from ICMORptEntry p1 left join ICMORpt p2 on p2.FInterID=p1.FInterID left join t_ICItem p3 on p3.FItemID=p1.FItemID left join ICMO p4 on p4.FInterID=p1.FSourceInterId where p2.FBillNo=#{entity.fBillNo} and p3.FNumber=#{entity.fmateNum};",
        "insert into QMSourceInfo (FInterID,FEntryID,FClassID_SRC,FBillNo_SRC,FID_SRC,FEntryID_SRC,FQCReqTranType,FQCReqBillNo,FQCReqEntryID,FQCReqInterID,FOrderTranType,FOrderBillNo,FOrderEntryID,FOrderInterID,FSourItemID,FSourUnitID,FSourSendUpQty,FSourCheckQty,FSourPassQty,FSourNotPassQty) ",
        "values (@Num,1,-551,#{entity.fBillNo},(select FInterID from ICMORpt where FBillNo=#{entity.fBillNo}),(@Ent),551,#{entity.fBillNo},(@Ent),(select FInterID from ICMORpt where FBillNo=#{entity.fBillNo}),85,@OrderBill,0,@OrderID,@itemID,@unitID,#{entity.fcheckSum},#{entity.fcheckSum},#{entity.passSum},#{entity.ngSum});",
        "DECLARE @v1 VARCHAR(50);",
        "DECLARE @v2 VARCHAR(50);",
        "DECLARE @v3 VARCHAR(50);",
        "DECLARE @v4 VARCHAR(50);",
        "DECLARE @v5 VARCHAR(50);",
        "DECLARE @v6 VARCHAR(50);",
        "DECLARE @v7 VARCHAR(50);",
        "<foreach collection='jcxs' item='item' index='index' separator=';'>",
        "select @v1=i1.FQCUnit,@v2=i1.FEmphasesCheck,@v3=i1.FQCItemID,@v4=i1.FAnalysisMethodID,@v5=i3.FQtyDecimal,@v6=i1.FQCMethodID,@v7=i4.FQMCheckItemTypeID from ICQCSchemeEntry i1 left join ICQCScheme i2 on i2.FInterID=i1.FInterID left join t_ICItem i3 on i3.FInspectionProject=i2.FInterID left join QMCheckItem i4 on i4.FID=i1.FQCItemID where i1.FEntryID=#{item.qcEntry} and i3.FNumber=#{entity.fmateNum};",
        "insert into ICQCBillEntry (FInterID,FEntryID,FQCUnit,FEmphasesCheck,FResult,FAnalysisMethodID,FQCItemID,FQCItemDecimal,FQCMethodID,FQCItemTypeID)",
        "values(@Num,#{item.qcEntry},@v1,@v2,#{item.isOk},@v4,@v3,@v5,@v6,@v7)",
        "</foreach>",
        "<foreach collection='barcodes' item='item' index='index' separator=';'>",
        "update t_BarcodeInfo set qc=#{item.isOk} where barcodNumber=#{item.NbarcodeNumber}",   
        "</foreach>",
        "<foreach collection='tpArray' item='item' index='index' separator=';'>",
        "insert t_accessory(FTypeid,FItemID,FPage,FEntryID,FDesc,FFileName,FFilesize,FUploader,FISPic,FVersion,FSaveMode,FShowFileName,FData)",
        "values(1001001,@Num,0,0,'',#{item.fullFileName},#{item.size},#{entity.fBillId},0,'2.0',0,#{item.fileName},#{item.content})",
        "</foreach>",
        "</script>"})
    void insertQC(@Param("barcodes") List<PurchaseReceiptEntity> entitys, @Param("date")String date, @Param("entity")QCPurchaseReceiptEntity entity,@Param("jcxs") List<QCJCXEntity> jcxs,@Param("tpArray")JSONArray tpArray);
}
