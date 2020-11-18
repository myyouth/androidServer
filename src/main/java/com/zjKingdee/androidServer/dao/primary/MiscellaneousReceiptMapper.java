package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.zjKingdee.androidServer.entity.MiscellaneousReceiptEntity;

/**
 * 其他入库单
 * @author Yinqi				
 * @date 2020年4月8日
 */
public interface MiscellaneousReceiptMapper {
	@Insert({"<script>",
		"DECLARE @No VARCHAR(50);",
		"exec p_EC_GetICBillNo 1,10,@No output;",
		"DECLARE @test VARCHAR(50);",
		"exec GetICBillNo 1,10,@test output;",
		"DECLARE @Num INT;",
		"exec GetICMaxNum 'icstockbill',@Num output;",
		"insert into ICStockBill (FBrNo,FInterID,FTranType,FDate,FBillNo,FDeptID,FSupplyID,FStatus,FBillerID,FFManagerID,FSManagerID,FExplanation)",
		"values(0,(@Num),10,#{date},(@No),#{entity.deptId},#{entity.customerId},0,16394,#{entity.requesterId},#{entity.consignorId},#{entity.purpose});",
		"<foreach collection='data' item='item' index='index' separator=';'>",
		"insert into ICStockBillEntry (FBrNo,FInterID,FEntryID,FItemID,FBatchNo,FUnitID,FQty,FAuxQty,FAuxPrice,FPrice,FAmount,FDCStockID,FDCSPID)",
		"values(0,@Num,#{index},#{item.mId},#{item.batch},#{item.unitId},#{item.mSum},#{item.mSum},",
		"#{item.price},#{item.price},#{item.totalPrice},#{item.warehouseId},#{item.positionId});",
		"</foreach>",
	 "</script>"})
	void insertForReceipt(@Param("data")List<MiscellaneousReceiptEntity> entitys,@Param("date")String date,@Param("entity")MiscellaneousReceiptEntity entity);

}
