package com.zjKingdee.androidServer.dao.primary;

import com.zjKingdee.androidServer.entity.ProductionReportEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 生产汇报单
 */
public interface ProductionReportMapper {
    @Select("select a.FEntryId,a.FInterID,a.FEntryID FSourceEntryID,b.FBillNo,a.FItemID,d.FNumber materialNumber,d.fname materialName,d.FOrderUnitID fGroupUnitID," +
            "e.FItemId materialUnitId,e.FName materialUnit,a.FAuxQtyPass FAuxQtyFinish,f.FName fstockName,f.FItemID stockNameId," +
            "a.FSourceBillNo rwBillNo,g.FWorkShop FWorkShopId,t5.FName FWorkShop,g.finterid FICMOInterID,b.FSelTranType FSelTranType,g.FGMPBatchNo fBatchNo from ICMORptEntry a " +
            "inner join ICMORpt b on a.finterid=b.finterid " +
            "inner join t_ICItem d on d.FItemID=a.FItemID " +
            "inner join t_MeasureUnit e on e.FItemID=a.FUnitID "+
            "left join t_Stock f on f.FItemID=d.FDefaultLoc "+
            "left join ICMO g on g.fbillno=a.FSourceBillNo "+
            "left join t_Department t5 on t5.FItemID=g.FWorkShop "+
            "where b.FBillNo=#{billNo}")
    List<ProductionReportEntity> selectProductionReportInfo(String billNo);
    
    @Select("select a.FEntryId,a.FInterID,a.FEntryID FSourceEntryID,b.FBillNo,a.FItemID,d.FNumber materialNumber,d.fname materialName,d.FOrderUnitID fGroupUnitID," +
            "e.FItemId materialUnitId,e.FName materialUnit,a.FAuxQtyFinish FAuxQtyFinish,f.FName fstockName,f.FItemID stockNameId," +
            "a.FSourceBillNo rwBillNo,g.FWorkShop FWorkShopId,t5.FName FWorkShop,g.finterid FICMOInterID,b.FSelTranType FSelTranType,g.FGMPBatchNo fBatchNo from ICMORptEntry a " +
            "inner join ICMORpt b on a.finterid=b.finterid " +
            "inner join t_ICItem d on d.FItemID=a.FItemID " +
            "inner join t_MeasureUnit e on e.FItemID=a.FUnitID "+
            "left join t_Stock f on f.FItemID=d.FDefaultLoc "+
            "left join ICMO g on g.fbillno=a.FSourceBillNo "+
            "left join t_Department t5 on t5.FItemID=g.FWorkShop "+
            "where b.FBillNo=#{billNo} and (select COUNT(*) from QMSourceInfo where FBillNo_SRC=#{billNo})=0")
    List<ProductionReportEntity> selectQCProductionReportInfo(String billNo);
}
