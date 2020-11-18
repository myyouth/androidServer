package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.zjKingdee.androidServer.entity.ProductFeedingForRequisitEntity;
/**
 * 生产投料单
 * @author Yinqi				
 * @date 2020年3月11日
 */
public interface ProductFeedingMapper {
	/**
	 * 获取所有的生产投料单
	 * @param feedNumber
	 * @return
	 */
	@Select("select FBillNo from PPBOM where FStatus=1 and FBillNo like #{feedNumber} and FICMOInterID in (select FInterID from ICMO where FStatus=1)")
	List<String> searchAllProFeed(String feedNumber);
	
	@Select("select t1.FEntryID,t6.FType,t6.FWorkSHop,t7.FName FWSName,t2.FNumber,t1.FItemID,t2.FName FMName,t1.FBatchNo,"+
			"t1.FUnitID,t5.FName FUName,t1.FStockID,t3.FName FSName,t1.FSPID,t4.FName FSPName,t1.FAuxQtyPick-t1.FAuxStockQty FSum,"+
			"t1.FQtyScrap/t1.FAuxQtyScrap FMual,t6.FItemID FProductId,t8.FName FProductName,t6.FICMOInterID,t9.FBillNo,t10.FNumber icmoNum from PPBOMEntry t1 " + 
			"left join t_ICItem t2 on t2.FItemID=t1.FItemID " + 
			"left join t_Stock t3 on t3.FItemID=t1.FStockID " + 
			"left join t_StockPlace t4 on t4.FSPID=t1.FSPID " + 
			"left join t_MeasureUnit t5 on t5.FItemID=t1.FUnitID " + 
			"left join PPBOM t6 on t6.FInterID=t1.FInterID " + 
			"left join T_Department t7 on t7.FItemID=t6.FWorkSHop " + 
			"left join t_ICItem t8 on t8.FItemID=t6.FItemID "+
			"left join ICMO t9 on t9.FInterID=t6.FICMOInterID "+
			"left join t_ICItem t10 on t9.FItemID=t10.FItemID "+
			"where t6.FBillNo=#{feedNumber} and (t2.FNumber like '1.01%' or t2.FNumber like '1.02%')")
	@Results({
		@Result(property = "id",  column = "FEntryID"),
		@Result(property = "type",  column = "FType"),
		@Result(property = "deptId",  column = "FWorkSHop"),
		@Result(property = "dept",  column = "FWSName"),
		@Result(property = "materialNumber",  column = "FNumber"),
		@Result(property = "materialId",  column = "FItemID"),
		@Result(property = "materialName",  column = "FMName"),
		@Result(property = "materialBatch",  column = "FBatchNo"),
		@Result(property = "unitId",  column = "FUnitID"),
		@Result(property = "unitName",  column = "FUName"),
		@Result(property = "stockId",  column = "FStockID"),
		@Result(property = "stockName",  column = "FSName"),
		@Result(property = "stockPlaceID",  column = "FSPID"),
		@Result(property = "stockPlace",  column = "FSPName"),
		@Result(property = "materialSum",  column = "FSum"),
		@Result(property = "mual",  column = "FMual"),
		@Result(property = "productId",  column = "FProductId"),
		@Result(property = "productName",  column = "FProductName"),
		@Result(property = "icmoId",  column = "FICMOInterID"),
		@Result(property = "icmoNo",  column = "FBillNo"),
		@Result(property = "icmoNum",  column = "icmoNum")
	})
	List<ProductFeedingForRequisitEntity> searchProFeedInfo(String feedNumber);
}
