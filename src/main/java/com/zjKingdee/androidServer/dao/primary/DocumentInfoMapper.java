package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;

import com.zjKingdee.androidServer.entity.DocumentInfoEntity;
import com.zjKingdee.androidServer.entity.MaterialInfoEntity;

public interface DocumentInfoMapper {
	/**
	 * 获取单据总数
	 * @return
	 */
	@Select({"<script>",
			"select COUNT(FBillNo) from ${TName} where FBillNo like #{billNo} ",
			"<when test='flag'>",
			"and FTranType=#{type}",
			"</when>",
			"</script>"})
	String getDocCount(@Param("TName")String TName,@Param("billNo")String billNo,@Param("flag")boolean flag,@Param("type")String type);
	
	/**
	 * 获取仓库总数
	 * @return
	 */
	@Select({"<script>",
			"select COUNT(FNumber) from ${TName} where FNumber like #{FNumber} and FName like #{FName}",
			"</script>"})
	String getStockCount(@Param("FNumber")String FNumber,@Param("FName")String FName,@Param("TName")String TName);
	
	/**
	 * 获取仓位总数
	 * @return
	 */
	@Select({"<script>",
			"select COUNT(FNumber) from ${TName} where FNumber like #{FNumber} and FName like #{FName} ",
			"and FSPGroupID = (select FSPGroupID from t_Stock where FNumber=#{stockId})",
			"</script>"})
	String getStockPlaceCount(@Param("FNumber")String FNumber,@Param("FName")String FName,@Param("TName")String TName,@Param("stockId")String stockId);
	
	/**
	 * 获取所有单据
	 * @param index
	 * @param size
	 * @param billNo
	 * @param TName
	 * @param flag
	 * @return
	 */
	@Select({"<script>",
			"select top ${size} FBillNo,rownumber from (select row_number() over(order by FBillNo) as rownumber,* from ${TName} ",
			"where FBillNo like #{billNo} ",
			"<when test='flag'>",
			"and FTranType=#{type}",
			"</when>",
			")A where rownumber>#{index}",
			"</script>"})
	@Results({
			@Result(property = "mNumber",  column = "FBillNo"),
			@Result(property = "id",  column = "rownumber")
	})
	List<DocumentInfoEntity> getAllDocInfo(@Param("index")String index,@Param("size")String size,@Param("billNo")String billNo,@Param("TName")String TName,@Param("flag")boolean flag,@Param("type")String type);
	
	/**
	 * 获取所有仓库
	 * @param index
	 * @param size
	 * @param FNumber
	 * @param FName
	 * @return
	 */
	@Options(flushCache = FlushCachePolicy.TRUE)
	@Select({"<script>",
		"select top ${size} FNumber,FName,FItemId from (select row_number() over(order by FNumber) as rownumber,* from ${TName} where FNumber like #{FNumber} and FName like #{FName})A where rownumber>#{index}",
		"</script>"})
	@Results({
		@Result(property = "mName",  column = "FName"),
		@Result(property = "mNumber",  column = "FNumber"),
		@Result(property = "id",  column = "FItemId")
	})
	List<DocumentInfoEntity> getAllStockInfo(@Param("index")String index,@Param("size")String size,@Param("FNumber")String FNumber,@Param("FName")String FName,@Param("TName")String TName);
	
	/**
	 * 获取所有供应商
	 * @param index
	 * @param size
	 * @param FNumber
	 * @param FName
	 * @param TName
	 * @return
	 */
	@Options(flushCache = FlushCachePolicy.TRUE)
	@Select({"<script>",
		"select top ${size} FNumber,FName,FItemId from (select row_number() over(order by FNumber) as rownumber,* from ${TName} where FNumber like #{FNumber} and FName like #{FName})A where rownumber>#{index}",
		"</script>"})
	@Results({
		@Result(property = "mName",  column = "FName"),
		@Result(property = "mNumber",  column = "FNumber"),
		@Result(property = "id",  column = "FItemId")
	})
	List<DocumentInfoEntity> getAllSupplyInfo(@Param("index")String index,@Param("size")String size,@Param("FNumber")String FNumber,@Param("FName")String FName,@Param("TName")String TName);
	
	/**
	 * 获取所有物料
	 * @param index
	 * @param size
	 * @param FNumber
	 * @param FName
	 * @param TName
	 * @return
	 */
	@Select({"<script>",
		"select top ${size} FNumber,FName,FItemId from (select row_number() over(order by FNumber) as rownumber,* from ${TName} where FNumber like #{FNumber} and FName like #{FName} and (FNumber like '1.02%' or FNumber like '1.01%' or FNumber like '8.02%'))A where rownumber>#{index}",
		"</script>"})
	@Results({
		@Result(property = "mName",  column = "FName"),
		@Result(property = "mNumber",  column = "FNumber"),
		@Result(property = "id",  column = "FItemId")
	})
	List<DocumentInfoEntity> getAllMateInfo(@Param("index")String index,@Param("size")String size,@Param("FNumber")String FNumber,@Param("FName")String FName,@Param("TName")String TName);
	
	/**
	 * 获取物料数目
	 * @param FNumber
	 * @param FName
	 * @param TName
	 * @return
	 */
	@Select({"<script>",
		"select COUNT(FNumber) from ${TName} where FNumber like #{FNumber} and FName like #{FName} and (FNumber like '1.02%' or FNumber like '1.01%')",
		"</script>"})
	String getMateCount(@Param("FNumber")String FNumber,@Param("FName")String FName,@Param("TName")String TName);
	
	/**
	 * 获取所有仓位
	 * @param index
	 * @param size
	 * @param FNumber
	 * @param FName
	 * @return
	 */
	@Select({"<script>",
		"select top ${size} FNumber,FName,FSPID from (select row_number() over(order by FNumber) as rownumber,* from ${TName} where FNumber like #{FNumber} and FName like #{FName} and FSPGroupID = ",
	    "(select FSPGroupID from t_Stock where FNumber=#{stockId}))A where rownumber>#{index}",
		"</script>"})
	@Results({
		@Result(property = "mName",  column = "FName"),
		@Result(property = "mNumber",  column = "FNumber"),
		@Result(property = "id",  column = "FSPID")
	})
	List<DocumentInfoEntity> getAllStockPlaceInfo(@Param("index")String index,@Param("size")String size,@Param("FNumber")String FNumber,@Param("FName")String FName,@Param("TName")String TName,@Param("stockId")String stockId);
	
	/**
	 * 获取收料通知单物料
	 * @param mNumber
	 * @return
	 */
	@Select("select t1.FEntryID,t1.FItemID,t3.FNumber FMNumber,t3.FName FMName,t1.FQty,t1.FUnitID,t4.FName FUName,t1.FBatchNo,t2.FSupplyID,t5.FName FSPName,t6.FNumber FStockID,t6.FName FSTName,t7.FNumber FDCSPID,t7.FName FDSName,t1.FEntrySelfP0384 from POInStockEntry t1 " + 
			"left join POInStock t2 on t1.FInterID=t2.FInterID " + 
			"left join t_icitem t3 on t3.FItemID=t1.FItemID " + 
			"left join t_MeasureUnit t4 on t4.FItemID=t1.FUnitID " + 
			"left join t_Supplier t5 on t5.FItemID=t2.FSupplyID " + 
			"left join t_Stock t6 on t6.FItemID=t1.FStockID " + 
			"left join t_StockPlace t7 on t7.FSPID=t1.FDCSPID " + 
			"where t2.FBillNo=#{mNumber} and (t3.FNumber like '1.02%' or t3.FNumber like '1.01%')")
	@Results({
		@Result(property = "id",  column = "FEntryID"),
		@Result(property = "mId",  column = "FItemID"),
		@Result(property = "mNumber",  column = "FMNumber"),
		@Result(property = "mName",  column = "FMName"),
		@Result(property = "mSum",  column = "FQty"),
		@Result(property = "unitId",  column = "FUnitID"),
		@Result(property = "unitName",  column = "FUName"),
		@Result(property = "batch",  column = "FBatchNo"),
		@Result(property = "supplierId",  column = "FSupplyID"),
		@Result(property = "supplier",  column = "FSPName"),
		@Result(property = "warehouseId",  column = "FStockID"),
		@Result(property = "warehouse",  column = "FSTName"),
		@Result(property = "positionId",  column = "FDCSPID"),
		@Result(property = "position",  column = "FDSName"),
		@Result(property = "ZBM",  column = "FEntrySelfP0384")
	})
	List<MaterialInfoEntity> getAllSLTZDMaterialInfo(String mNumber);
	
	/**
	 * 获取生产任务单物料
	 * @param mNumber
	 * @return
	 */
	@Select("select t1.FItemID,t3.FNumber FMNumber,t3.FName FMName,t1.FAuxQty,t1.FUnitID,t4.FName FUName,t1.FGMPBatchNo,t1.FWorkShop,t5.FName FWSName from ICMO t1 " + 
			"left join t_icitem t3 on t3.FItemID=t1.FItemID " + 
			"left join t_MeasureUnit t4 on t4.FItemID=t1.FUnitID " + 
			"left join t_Department t5 on t5.FItemID=t1.FWorkShop " + 
			"where t1.FBillNo=#{mNumber} and t3.FNumber like '8.02%'")
	@Results({
		@Result(property = "mId",  column = "FItemID"),
		@Result(property = "mNumber",  column = "FMNumber"),
		@Result(property = "mName",  column = "FMName"),
		@Result(property = "mSum",  column = "FAuxQty"),
		@Result(property = "unitId",  column = "FUnitID"),
		@Result(property = "unitName",  column = "FUName"),
		@Result(property = "batch",  column = "FGMPBatchNo"),
		@Result(property = "workShopId",  column = "FWorkShop"),
		@Result(property = "workShop",  column = "FWSName")
	})
	List<MaterialInfoEntity> getAllSCRWDMaterialInfo(@Param("mNumber")String mNumber);
	
	/**
	 * 获取生产汇报单物料
	 * @param mNumber
	 * @return
	 */
	@Select("select t1.FItemID,t3.FNumber FMNumber,t3.FName FMName,t1.FAuxQtyFinish,t1.FUnitID,t4.FName FUName,t1.FGMPBatchNo from ICMORptEntry t1 " +
			"left join ICMORpt t2 on t2.FInterID=t1.FInterID " +
			"left join t_icitem t3 on t3.FItemID=t1.FItemID " +
			"left join t_MeasureUnit t4 on t4.FItemID=t1.FUnitID " +
			"where t2.FBillNo=#{mNumber}")
	@Results({
		@Result(property = "mId",  column = "FItemID"),
		@Result(property = "mNumber",  column = "FMNumber"),
		@Result(property = "mName",  column = "FMName"),
		@Result(property = "mSum",  column = "FAuxQtyFinish"),
		@Result(property = "unitId",  column = "FUnitID"),
		@Result(property = "unitName",  column = "FUName"),
		@Result(property = "batch",  column = "FGMPBatchNo"),
		@Result(property = "workShop",  column = "FWSName")
	})
	List<MaterialInfoEntity> getAllSCHBDMaterialInfo(@Param("mNumber")String mNumber);
	
	/**
	 * 获取入库单物料
	 * @param mNumber
	 * @return
	 */
	@Select("select t1.FEntryID,t1.FItemID,t3.FNumber FMNumber,t3.FName FMName,t1.FQty,t1.FUnitID,t4.FName FUName,t1.FBatchNo,t2.FSupplyID,t5.FName FSPName,t6.FNumber FStockID,t6.FName FSTName,t7.FNumber FDCSPID,t7.FName FDSName from ICStockBillEntry t1 " + 
			"left join ICStockBill t2 on t1.FInterID=t2.FInterID " + 
			"left join t_icitem t3 on t3.FItemID=t1.FItemID " + 
			"left join t_MeasureUnit t4 on t4.FItemID=t1.FUnitID " + 
			"left join t_Item t5 on t5.FItemID=t2.FSupplyID " + 
			"left join t_Stock t6 on t6.FItemID=t1.FDCStockID " + 
			"left join t_StockPlace t7 on t7.FSPID=t1.FDCSPID " + 
			"where t2.FBillNo=#{mNumber} and t2.FTranType=#{type}")
	@Results({
		@Result(property = "id",  column = "FEntryID"),
		@Result(property = "mId",  column = "FItemID"),
		@Result(property = "mNumber",  column = "FMNumber"),
		@Result(property = "mName",  column = "FMName"),
		@Result(property = "mSum",  column = "FQty"),
		@Result(property = "unitId",  column = "FUnitID"),
		@Result(property = "unitName",  column = "FUName"),
		@Result(property = "batch",  column = "FBatchNo"),
		@Result(property = "supplierId",  column = "FSupplyID"),
		@Result(property = "supplier",  column = "FSPName"),
		@Result(property = "warehouseId",  column = "FStockID"),
		@Result(property = "warehouse",  column = "FSTName"),
		@Result(property = "positionId",  column = "FDCSPID"),
		@Result(property = "position",  column = "FDSName")
	})
	List<MaterialInfoEntity> getAllQTRKDMaterialInfo(@Param("mNumber")String mNumber,@Param("type")String type);
	
	/**
	 * 获取物料数据
	 * @param data
	 * @return
	 */
	@Select({"<script>",
			"select t1.FItemID,t1.FNumber,t1.FName,t1.FUnitID,t2.FName FUName from t_ICItem t1 left join t_MeasureUnit t2 on t2.FItemID=t1.FUnitID where ",
			"t1.FNumber in ('',",
			"<foreach collection='data' item='item' index='index' separator=','>",
			"#{item.mNumber}",
			"</foreach>",
			")",
			"</script>"})
	@Results({
		@Result(property = "mId",  column = "FItemID"),
		@Result(property = "mNumber",  column = "FNumber"),
		@Result(property = "mName",  column = "FName"),
		@Result(property = "unitId",  column = "FUnitID"),
		@Result(property = "unitName",  column = "FUName")
	})
	List<MaterialInfoEntity> getAllMaterialInfo(@Param("data")List<DocumentInfoEntity> data);
	
	
}
