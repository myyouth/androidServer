package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.zjKingdee.androidServer.entity.ConsignmentNoteForSaleIssueEntity;
import com.zjKingdee.androidServer.entity.MaterialInfoEntity;

/**
 * 销售出库单
 * @author Yinqi				
 * @date 2020年3月23日
 */
public interface SaleIssueMapper {
	@Insert({"<script>",
		"DECLARE @No VARCHAR(50);",
		"exec p_BM_GetBillNo 21,@No output;",
		"DECLARE @test VARCHAR(50);",
		"exec GetICBillNo 1,21,@test output;",
		"DECLARE @Num INT;",
		"exec GetICMaxNum 'icstockbill',@Num output;",
		"insert into ICStockBill (FBrNo,FInterID,FTranType,FDate,FBillNo,FMarketingStyle,FSaleStyle,FDeptID,FEmpID,FManagerID,FSupplyID,FStatus,FBillerID,FFManagerID,FSManagerID)",
		"values(0,(@Num),21,#{date},(@No),#{entity.typeId},#{entity.salesWayId},#{entity.deptId},#{entity.salesManId},#{entity.directorId},#{entity.purchasingUnitId},0,16394,#{entity.shipperId},#{entity.safeKeepingId});",
		"<foreach collection='data' item='item' index='index' separator=';'>",
		"insert into ICStockBillEntry (FBrNo,FInterID,FEntryID,FItemID,FBatchNo,FUnitID,FQtyMust,FQty,FAuxQty,",
		"FAuxQtyMust,FConsignPrice,FConsignAmount,FDCStockID,FDCSPID,FMapNumber,FMapName,FSourceTranType,FSourceInterId,FSourceBillNo,FOrderInterID,FOrderBillNo)",
		"values(0,@Num,#{index},#{item.materialId},#{item.materialBatch},#{item.materialUnitId},#{item.materialSum},#{item.materialActualSum},",
		"#{item.materialActualSum},#{item.materialSum},#{item.materialPrice},#{item.materialTotalPrice},#{item.stockNameId},#{item.stockPlaceId},#{item.mapperNumber},",
		"#{item.mapperName},83,#{item.docId},#{item.docNumber},#{item.sourceId},#{item.sourceNumber});",
		"</foreach>",
		"<foreach collection='barcodes' item='item' index='index' separator=';'>",
        "update t_BarcodeInfo set status=2 where barcodNumber=#{item.barcodeNumber}",
        "insert into t_BarcodeCZInfo (barcodNumber,czId,czType,czTypeName,createTime) values (#{item.barcodeNumber},2,@No,'销售出库单',#{date})",        
        "</foreach>",
	 "</script>"})
	void insertForIssue(@Param("data")List<ConsignmentNoteForSaleIssueEntity> entitys,@Param("barcodes")List<MaterialInfoEntity> barcodes,@Param("date")String date,@Param("entity")ConsignmentNoteForSaleIssueEntity entity);
}
