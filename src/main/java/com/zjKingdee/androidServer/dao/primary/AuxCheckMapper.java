package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zjKingdee.androidServer.entity.AuxCheckEntity;

public interface AuxCheckMapper {
	
	@Select("select FProcessID from ICStockCheckProcess where FStatus=0 and FProcessID like #{processNo}")
	List<String> getAllAuxCheckList(String processNo);
	
	@Select("select i.FStockID as stockID,i.FStockPlaceID as stockPlaceID,i.FItemID as materialID,i.FBatchNo as batchNumber,"+
			"i.FQty as qtyAct,i.FAuxCheckQty as auxCheckQty,t1.FName as stockName,t2.FName as stockPlaceName,t3.FName as materialName,"+
			"t3.FNumber as materialCode,t5.FName as unitName,t5.FItemID as unitId from ICInvBackup i " + 
			"left join t_Stock t1 on i.FStockID = t1.FItemID " + 
			"left join t_StockPlace t2 on i.FStockPlaceID = t2.FSPID " + 
			"left join ICStockCheckProcess t4 on t4.FId = i.FInterID " +
			"left join t_icitem t3 on t3.FItemID = i.FItemID " +
			"left join t_MeasureUnit t5 on t5.FItemID=i.FUnitID " +
			"where t4.FProcessID=#{billNo}")
	@Results({
		@Result(property = "stockID",  column = "stockID"),
		@Result(property = "stockPlaceID",  column = "stockPlaceID"),
		@Result(property = "materialID",  column = "materialID"),
		@Result(property = "batchNumber",  column = "batchNumber"),
		@Result(property = "qtyAct",  column = "qtyAct"),
		@Result(property = "auxCheckQty",  column = "auxCheckQty"),
		@Result(property = "stockName",  column = "stockName"),
		@Result(property = "stockPlaceName",  column = "stockPlaceName"),
		@Result(property = "materialName",  column = "materialName"),
		@Result(property = "materialCode",  column = "materialCode"),
		@Result(property = "unitId",  column = "unitId"),
		@Result(property = "unitName",  column = "unitName")
	})
	List<AuxCheckEntity> getAuxCheckItem(String billNo);
	
	@Update({"<script>",
			"<foreach collection='data' item='item' index='index' separator=';'>",
			"update ICInvBackup set FAuxCheckQty=#{item.auxCheckQty},FAuxQtyAct=#{item.auxCheckQty},FCheckQty=#{item.auxCheckQty},FQtyAct=#{item.auxCheckQty},FMinus=#{item.minus} ",
			"where FInterID=(select t1.FID from ICStockCheckProcess t1 where t1.FProcessID=#{item.billNo}) and FItemID=#{item.materialID} and FBatchNo=#{item.batchNumber}",
			"</foreach>",
			"</script>"})
	void updateAuxCheck(@Param("data")List<AuxCheckEntity> data);
	
	@Insert({"<script>",
		"<foreach collection='data' item='item' index='index' separator=';'>",
		"insert into ICInvBackup (FBrNo,FSelect,FAdj,FDateBackup,FItemID,FBatchNo,FUnitID,FStockID,FStockPlaceID,FQty,FAuxQty,FAuxCheckQty,FAuxQtyAct,FCheckQty,FQtyAct,FMinus,FInterID,FMaxBillInterID) values ('',0,0,(select  top 1 FDateBackup from ICInvBackup where FInterID=(select t3.FID from ICStockCheckProcess t3 where t3.FProcessID=#{item.billNo})),#{item.materialID},#{item.batchNumber},#{item.unitId},",
		"#{item.stockID},#{item.stockPlaceID},0,0,#{item.auxCheckQty},#{item.auxCheckQty},#{item.auxCheckQty},#{item.auxCheckQty},#{item.minus},(select t1.FID from ICStockCheckProcess t1 where t1.FProcessID=#{item.billNo}),(select t2.FMaxBillInterID from ICStockCheckProcess t2 where t2.FProcessID=#{item.billNo}))",
		"</foreach>",
		"</script>"})
	void insertAuxCheck(@Param("data")List<AuxCheckEntity> data);
}
