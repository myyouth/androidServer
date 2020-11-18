package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.zjKingdee.androidServer.entity.ConsignmentNoteForSaleIssueEntity;

/**
 * 发货通知单
 * @author Yinqi				
 * @date 2020年3月19日
 */
public interface ConsignmentNoteMapper {
	@Select("select t1.FEntryId id,t2.FInterID docId,t2.FBillNo docNumber,t2.FSalType salesWayId,t7.FName salesWay,t2.FCustID purchasingUnitId,t6.FName purchasingUnit,t2.FDeptID deptId,t4.FName dept," +
			"t2.FManagerID directorId,t5.FName director,t2.FEmpID salesManId,t3.FName salesMan,t1.FItemID materialId,t8.FNumber materialNumber,t8.FName materialName,t9.FItemId materialUnitId,t9.FName materialUnit,"+
			"t1.FBatchNo materialBatch,t1.FSourceInterId sourceId,t1.FSourceBillNo sourceNumber,t1.FMapNumber mapperNumber,t1.FMapName mapperName," +
			"t10.FItemID stockNameId,t10.FName stockName,t1.FQty materialSum,t1.FPrice materialPrice from SEOutStockEntry t1 " +
			"left join SEOutStock t2 on t2.FInterID=t1.FInterID " +
			"left join t_Emp t3 on t3.FItemID=t2.FEmpID " +
			"left join t_Department t4 on t4.FItemID=t2.FDeptID " +
			"left join t_Emp t5 on t5.FItemID=t2.FManagerID " +
			"left join t_Organization t6 on t6.FItemID=t2.FCustID " +
			"left join t_SubMessage t7 on t7.FInterId=t2.FSalType " +
			"left join t_ICItem t8 on t8.FItemID=t1.FItemID " +
			"left join t_MeasureUnit t9 on t9.FItemID=t1.FUnitID " +
			"left join t_Stock t10 on t10.FItemID=t1.FStockID " +
			"where t2.FBillNo=#{billNo} and (t8.FNumber like '8.02%')")
	List<ConsignmentNoteForSaleIssueEntity> selectNoteInfo(String billNo);
}
