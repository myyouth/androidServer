package com.zjKingdee.androidServer.dao.primary;

import com.zjKingdee.androidServer.entity.RequisitionEnity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RequisitionMapper {
    /**
     * 创建调拨单
     */
    @Insert({"<script>",
            "DECLARE @No VARCHAR(50);",
            "exec p_EC_GetICBillNo 1,41,@No output;",
            "DECLARE @test VARCHAR(50);",
            "exec GetICBillNo 1,41,@test output;",
            "DECLARE @Num INT;",
            "exec GetICMaxNum 'icstockbill',@Num output;",
            "insert into ICStockBill (FBrNo,FInterID,FTranType,FDate,FBillNo,FDeptID,FSupplyID,FStatus,FBillerID,FRefType,FSourceType,FMarketingStyle)",
            "values(0,(@Num),41,#{date},(@No),#{entity.deptId},#{entity.customerId},1,16394,12561,37521,12530);",
            "<foreach collection='data' item='item' index='index' separator=';'>",
            "insert into ICStockBillEntry (FBrNo,FInterID,FEntryID,FItemID,FBatchNo,FUnitID,FQty,FAuxQty,FSCStockID,FSCSPID,FDCStockID,FDCSPID)",
            "values(0,	@Num,#{index},(select FItemID from t_ICItem where FNumber=#{item.mNumber}),#{item.batch},#{item.unitId},#{item.mSum},#{item.mSum},",
            "#{item.inStockName},#{item.inStockPlace},#{item.warehouseId},#{item.positionId});",
            "</foreach>",
            "</script>"})
    void insertForRequisition(@Param("data") List<RequisitionEnity> entitys, @Param("date")String date, @Param("entity")RequisitionEnity entity);
}
