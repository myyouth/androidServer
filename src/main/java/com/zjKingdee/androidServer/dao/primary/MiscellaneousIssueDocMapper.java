package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.zjKingdee.androidServer.entity.MiscellaneousIssueDocEntity;

/**
 * 其他出库单
 * @author Yinqi				
 * @date 2020年3月25日
 */
public interface MiscellaneousIssueDocMapper {
	@Insert({"<script>",
		"DECLARE @No VARCHAR(50);",
		"exec p_EC_GetICBillNo 1,29,@No output;",
		"DECLARE @test VARCHAR(50);",
		"exec GetICBillNo 1,29,@test output;",
		"DECLARE @Num INT;",
		"exec GetICMaxNum 'icstockbill',@Num output;",
		"insert into ICStockBill (FBrNo,FInterID,FTranType,FDate,FBillNo,FDeptID,FSupplyID,FStatus,FBillerID,FFManagerID,FSManagerID,FUse)",
		"values(0,(@Num),29,#{date},(@No),#{entity.deptId},#{entity.customerId},0,16394,#{entity.requesterId},#{entity.consignorId},#{entity.purpose});",
		"<foreach collection='data' item='item' index='index' separator=';'>",
		"insert into ICStockBillEntry (FBrNo,FInterID,FEntryID,FItemID,FBatchNo,FUnitID,FQty,FAuxQty,FAuxPrice,FPrice,FAmount,FDCStockID,FDCSPID)",
		"values(0,	@Num,#{index},#{item.mId},#{item.batch},#{item.unitId},#{item.mSum},#{item.mSum},",
		"#{item.price},#{item.price},#{item.totalPrice},#{item.warehouseId},#{item.positionId});",
		"</foreach>",
	 "</script>"})
	void insertForIssue(@Param("data")List<MiscellaneousIssueDocEntity> entitys,@Param("date")String date,@Param("entity")MiscellaneousIssueDocEntity entity);
}
