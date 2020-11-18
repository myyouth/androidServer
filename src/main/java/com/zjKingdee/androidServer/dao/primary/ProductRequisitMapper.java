package com.zjKingdee.androidServer.dao.primary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zjKingdee.androidServer.entity.BarcodeInfoEntity;
import com.zjKingdee.androidServer.entity.MaterialInfoEntity;
import com.zjKingdee.androidServer.entity.ProductFeedingForRequisitEntity;

/**
 * 生产领料单
 * @author Yinqi				
 * @date 2020年3月11日
 */
public interface ProductRequisitMapper {
	/**
	 * 获取所有科目
	 * @return
	 */
	@Select("select FAccountID id,FName text from t_Account order by FNumber")
	List<Map<String,String>> selectAllAccount();
	
	@Insert({"<script>",
				"DECLARE @No VARCHAR(50);",
				"exec p_BM_GetBillNo 24,@No output;",
				"DECLARE @test VARCHAR(50);",
				"exec GetICBillNo 1,24,@test output;",
				"DECLARE @Num INT;",
				"exec GetICMaxNum 'icstockbill',@Num output;",
				"insert into ICStockBill (FBrNo,FInterID,FTranType,FDate,FBillNo,FDeptID,FStatus,FPurposeID,FAcctID,FUse,FSelTranType,FBillerID,FFManagerID,FSManagerID,FROB)",
				"values(0,(@Num),24,#{date},(@No),#{deptId},0,#{type},#{subjectId},#{purpose},85,#{creatorId},#{shipperId},#{safeKeepingId},#{docTypeId});",
				"<foreach collection='data' item='item' index='index' separator=';'>",
				"insert into ICStockBillEntry (FBrNo,FInterID,FEntryID,FItemID,FBatchNo,FUnitID,FQtyMust,FQty,FAuxQtyMust,", 
				"FAuxQty,FSourceEntryID,FSourceTranType,FSourceInterId,FSourceBillNo,FICMOBillNo,FICMOInterID,FPPBomEntryID,", 
				"FSCStockID,FDCSPID,FCostOBJID,FReProduceType)",
				"values(0,@Num,#{index}+1,#{item.materialId},#{item.materialBatch},#{item.unitId},#{item.AuxmaterialSum},#{item.AuxmaterialActualSum},",
				"#{item.materialSum},#{item.materialActualSum},#{item.id},85,#{item.icmoId},#{item.icmoNo},#{item.icmoNo},#{item.icmoId},#{item.id},#{item.stockId},#{item.stockPlaceID},(select FCostObjID from ICMO where FBillNo=#{item.icmoNo}),1059);",
				"</foreach>",
				"<foreach collection='barcodes' item='item' index='index' separator=';'>",
	            "update t_BarcodeInfo set status=2,isUse=0 where barcodNumber=#{item.barcodeNumber}",
	            "insert into t_BarcodeCZInfo (barcodNumber,czId,czType,czTypeName,createTime) values (#{item.barcodeNumber},2,@No,'生产领料单',#{date})",        
	            "</foreach>",
			 "</script>"})
	void insertForPR(@Param("data")List<ProductFeedingForRequisitEntity> entitys,@Param("barcodes")List<MaterialInfoEntity> barcodes,@Param("date")String date,@Param("deptId")String deptId,@Param("type")String type,
			@Param("subjectId")String subjectId,@Param("purpose")String purpose,@Param("shipperId")String shipperId,@Param("safeKeepingId")String safeKeepingId,@Param("creatorId")String creatorId,@Param("docTypeId")String docTypeId);
	
	@Insert({"<script>",
		"DECLARE @No VARCHAR(50);",
		"exec p_BM_GetBillNo 24,@No output;",
		"DECLARE @test VARCHAR(50);",
		"exec GetICBillNo 1,24,@test output;",
		"DECLARE @Num INT;",
		"exec GetICMaxNum 'icstockbill',@Num output;",
		"insert into ICStockBill (FBrNo,FInterID,FTranType,FDate,FBillNo,FDeptID,FStatus,FPurposeID,FAcctID,FUse,FSelTranType,FBillerID,FFManagerID,FSManagerID,FROB)",
		"values(0,(@Num),24,#{date},(@No),#{deptId},0,#{type},#{subjectId},#{purpose},85,#{creatorId},#{shipperId},#{safeKeepingId},#{docTypeId});",
		"<foreach collection='data' item='item' index='index' separator=';'>",
		"insert into ICStockBillEntry (FBrNo,FInterID,FEntryID,FItemID,FBatchNo,FUnitID,FQtyMust,FQty,FAuxQtyMust,", 
		"FAuxQty,FSourceEntryID,FSourceTranType,FSourceInterId,FSourceBillNo,FICMOBillNo,FICMOInterID,FPPBomEntryID,", 
		"FSCStockID,FDCSPID,FCostOBJID,FReProduceType)",
		"values(0,@Num,#{index}+1,#{item.materialId},#{item.materialBatch},#{item.unitId},#{item.AuxmaterialSum},#{item.AuxmaterialActualSum},",
		"#{item.materialSum},#{item.materialActualSum},#{item.id},85,#{item.icmoId},#{item.icmoNo},#{item.icmoNo},#{item.icmoId},#{item.id},#{item.stockId},#{item.stockPlaceID},(select FCostObjID from ICMO where FBillNo=#{item.icmoNo}),1059);",
		"</foreach>",
		"<foreach collection='barcodes' item='item' index='index' separator=';'>",
		"delete from t_BarcodeInfo where barcodNumber=#{item.barcodeNumber} and typeName='生产任务单'",
        "update t_BarcodeInfo set status=1,isUse=1 where barcodNumber=#{item.barcodeNumber} and typeName='收料通知单'",
        "insert into t_BarcodeCZInfo (barcodNumber,czId,czType,czTypeName,createTime) values (#{item.barcodeNumber},1,@No,'生产领料单',#{date})",        
        "</foreach>",
	 "</script>"})
void insertForPRH(@Param("data")List<ProductFeedingForRequisitEntity> entitys,@Param("barcodes")List<MaterialInfoEntity> barcodes,@Param("date")String date,@Param("deptId")String deptId,@Param("type")String type,
	@Param("subjectId")String subjectId,@Param("purpose")String purpose,@Param("shipperId")String shipperId,@Param("safeKeepingId")String safeKeepingId,@Param("creatorId")String creatorId,@Param("docTypeId")String docTypeId);

	/**
	 * 获取已投料数量
	 * @param mateID
	 * @return
	 */
	@Select("select sum(FAuxQty) from ICStockBillEntry where FSourceBillNo=#{sourceNumber} and FItemID=#{mateID}")
	String selectSumMaterial(@Param("mateID")String mateID,@Param("sourceNumber")String sourceNumber);
	
	@Insert({"<script>",
		"DECLARE @mId VARCHAR(50);", 
		"DECLARE @materielNumber VARCHAR(50);",
		"DECLARE @materialName VARCHAR(50);", 
		"DECLARE @materialUnitID VARCHAR(50);", 
		"DECLARE @materialUnitName VARCHAR(50);",  
		"DECLARE @materialBatch VARCHAR(50);", 
		"DECLARE @departmentID VARCHAR(50);", 
		"DECLARE @departmentName VARCHAR(50);",
		"select @mId=t2.FItemID,@materialName=t2.FName,@materielNumber=t2.FNumber,@materialBatch=t1.FGMPBatchNo,@materialUnitID=t1.FUnitID,@materialUnitName=t3.FName,@departmentID=t4.FItemID,@departmentName=t4.FName from ICMO t1 ",
		"left join t_ICItem t2 on t2.FItemID=t1.FItemID ",  
		"left join t_MeasureUnit t3 on t3.FItemID=t1.FUnitID ",  
		"left join t_Department t4 on t4.FItemID=t1.FWorkShop ",  
		"where t1.FBillNo=#{icmoNo};",
		"Insert into t_BarcodeInfo (barcodNumber,materialID,materielNumber,materialName,materialQty,materialUnitID,materialUnitName,materialBatch,",
		"stockID,stockName,stockPlaceID,stockPlaceName,supplyID,supplyName,departmentID,departmentName,typeName,typeNumber,createTime,status,ZBM,isUse) values ",
		"<foreach collection='data' item='item' index='index' separator=','>",
		"(#{item.barcodNumber},@mId,@materielNumber,@materialName,#{item.materialQty},@materialUnitID,@materialUnitName,@materialBatch,",
		"#{item.stockID},#{item.stockName},#{item.stockPlaceID},#{item.stockPlaceName},#{item.supplyID},#{item.supplyName},@departmentID,",
		"@departmentName,#{item.typeName},#{item.typeNumber},#{dateTime},#{status},#{item.zbm},1)",
		"</foreach>",
		"</script>"})
	void insertBarcodeNumber(@Param("data")List<BarcodeInfoEntity> data,@Param("dateTime")String dateTime,@Param("status")String status,@Param("icmoNo")String icmoNo);
}
