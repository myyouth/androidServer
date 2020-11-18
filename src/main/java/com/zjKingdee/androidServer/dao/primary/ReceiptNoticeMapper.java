package com.zjKingdee.androidServer.dao.primary;

import com.zjKingdee.androidServer.entity.PurchaseReceiptEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 收料通知单
 */
public interface ReceiptNoticeMapper {
	
    @Select("select a.FEntryId FSourceEntryID,a.FInterID,b.FBillNo,b.FPOStyle,b.FSupplyID,b.FtranType,c.fname fsupplyname,d.FNumber materialNumber,d.FOrderUnitID fGroupUnitID," +
            "d.fname materialName,e.FItemId materialUnitId,e.FName materialUnit,a.FQtyPass FQty,a.FPrice,f.FName fstockName,a.FStockID stockNameId," +
            "a.FSourceInterId,a.FSourceBillNo,b.FEmpID,g.FName fempName,b.FDeptID,h.FName fdeptName,i.FName fposname,b.FFManagerID,b.FSManagerID,a.FBatchNo,a.FItemID,a.FEntrySelfP0384 zbm from POInStockEntry a " +
            "inner join POInStock b on a.finterid=b.finterid " +
            "left join t_Supplier c on c.FItemID=b.FSupplyID " +
            "left join t_ICItem d on d.FItemID=a.FItemID " +
            "left join t_MeasureUnit e on e.FItemID=a.FUnitID " +
            "left join t_Stock f on f.FItemID=a.FStockID " +
            "left join t_Emp g on g.FItemID=b.FEmpID " +
            "left join t_Department h on h.FItemID=b.FDeptID " +
            "left join t_SubMessage i on i.FInterID=b.FPOStyle "+
            "where b.FBillNo=#{billNo}")
    List<PurchaseReceiptEntity> selectNoteInfo(String billNo);
    
    @Select("select a.FEntryId FSourceEntryID,a.FInterID,b.FBillNo,b.FPOStyle,b.FSupplyID,b.FtranType,c.fname fsupplyname,d.FNumber materialNumber,d.FOrderUnitID fGroupUnitID," +
            "d.fname materialName,e.FItemId materialUnitId,e.FName materialUnit,a.FQty,a.FPrice,f.FName fstockName,a.FStockID stockNameId," +
            "a.FSourceInterId,a.FSourceBillNo,b.FEmpID,g.FName fempName,b.FDeptID,h.FName fdeptName,i.FName fposname,b.FFManagerID,b.FSManagerID,a.FBatchNo,a.FItemID,a.FEntrySelfP0384 zbm from POInStockEntry a " +
            "inner join POInStock b on a.finterid=b.finterid " +
            "left join t_Supplier c on c.FItemID=b.FSupplyID " +
            "left join t_ICItem d on d.FItemID=a.FItemID " +
            "left join t_MeasureUnit e on e.FItemID=a.FUnitID " +
            "left join t_Stock f on f.FItemID=a.FStockID " +
            "left join t_Emp g on g.FItemID=b.FEmpID " +
            "left join t_Department h on h.FItemID=b.FDeptID " +
            "left join t_SubMessage i on i.FInterID=b.FPOStyle "+
            "where b.FBillNo=#{billNo} and (select COUNT(*) from QMSourceInfo where FBillNo_SRC=#{billNo})=0")
    List<PurchaseReceiptEntity> selectNoteInfoForQC(String billNo);
}
