package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import com.zjKingdee.androidServer.entity.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;

/**
 * 条码查询
 * @author Yinqi				
 * @date 2020年3月25日
 */
public interface BarcodeNumberMapper {
	/**
	 * 查询数据用于盘点
	 * @param BarcodeNumber
	 * @return
	 */
	@Select("select t2.FItemID,t1.materielNumber,t1.materialName,t1.materialBatch,t3.FItemID stockID,t1.stockName,"
			+ "t4.FSPID stockPlaceID,t1.stockPlaceName,t1.materialQty,t1.materialUnitID,t1.materialUnitName from t_BarcodeInfo t1 "
			+ "left join t_ICItem t2 on t1.materielNumber=t2.FNumber left join t_Stock t3 on t3.FNumber=t1.stockID left join t_StockPlace t4 on t4.FNumber=t1.stockPlaceID "
			+ "where t1.barcodNumber=#{BarcodeNumber}")
	@Results({
		@Result(property = "materialID",  column = "FItemID"),
		@Result(property = "materialCode", column = "materielNumber"),
		@Result(property = "materialName", column = "materialName"),
		@Result(property = "batchNumber",  column = "materialBatch"),
		@Result(property = "stockID",  column = "stockID"),
		@Result(property = "stockName", column = "stockName"),
		@Result(property = "stockPlaceID",  column = "stockPlaceID"),
		@Result(property = "stockPlaceName",  column = "stockPlaceName"),
		@Result(property = "auxCheckQty",  column = "materialQty"),
		@Result(property = "unitId",  column = "materialUnitID"),
		@Result(property = "unitName",  column = "materialUnitName")
	})
	AuxCheckEntity searchBarcodeNumber(String BarcodeNumber);
	
	@Select("select t1.materielNumber as mNumber,t1.materialName as mName,t1.materialBatch as batch,t1.stockName as warehouse,t1.stockPlaceName as position,"
			+ "t1.materialQty,t1.materialUnitName,t2.FSPId as positionId,t1.zbm,t1.supplyName,t1.typeNumber "
			+ "from t_BarcodeInfo t1 left join t_StockPlace t2 on t2.FSPId = t1.stockPlaceID where barcodNumber=#{BarcodeNumber} and t1.typeName like #{type}")
	@Results({
		@Result(property = "mNumber",  column = "mNumber"),
		@Result(property = "mName", column = "mName"),
		@Result(property = "batch", column = "batch"),
		@Result(property = "warehouse",  column = "warehouse"),
		@Result(property = "position",  column = "position"),
		@Result(property = "mSum",  column = "materialQty"),
		@Result(property = "unitName",  column = "materialUnitName"),
		@Result(property = "positionId",  column = "positionId"),
		@Result(property = "ZBM",  column = "zbm"),
		@Result(property = "supplier",  column = "supplyName"),
		@Result(property = "typeNumber",  column = "typeNumber")
	})
	MaterialInfoEntity searchMaterialInfo(@Param("BarcodeNumber")String BarcodeNumber,@Param("type")String type);
	
	@Select("select t1.barcodNumber,t2.FItemID mId,t1.materielNumber mNumber,t1.materialName mName,t1.materialBatch batch,t1.materialUnitID unitId,t1.materialUnitName unitName,"+
			"t3.FItemID warehouseId,t1.stockName warehouse,t4.FSPID positionId,t1.stockPlaceName position,t1.materialQty mSum from t_BarcodeInfo t1 " + 
			"left join t_ICItem t2 on t2.FNumber=t1.materielNumber left join t_Stock t3 on t3.FNumber=t1.stockID left join t_StockPlace t4 on t4.FNumber=t1.stockPlaceID " + 
			"where t1.barcodNumber=#{BarcodeNumber} and t1.typeName like #{type}")
	MaterialInfoEntity searchBarcodeInfo(@Param("BarcodeNumber")String BarcodeNumber,@Param("type")String type);
	
	/**
	 * 获取条码编码
	 * @param codeRule
	 * @return
	 */
	@Options(flushCache = FlushCachePolicy.TRUE)
	@Select({"<script>",
			"select #{codeRule}+right('00000000'+convert(varchar(50),serialNumber),8) as barcodeNumber from t_BarcodeRules where ruleName=#{codeRule}",
			"update t_BarcodeRules set serialNumber=serialNumber+1 where ruleName=#{codeRule}",
			"</script>"})
	String getBarcodeNumber(String codeRule);
	
	/**
	 * 插入物料条码数据
	 * @param data
	 */
	@Insert({"<script>",
			"Insert into t_BarcodeInfo (barcodNumber,materialID,materielNumber,materialName,materialQty,materialUnitID,materialUnitName,materialBatch,",
			"stockID,stockName,stockPlaceID,stockPlaceName,supplyID,supplyName,departmentID,departmentName,typeName,typeNumber,createTime,status,ZBM,isUse) values ",
			"<foreach collection='data' item='item' index='index' separator=','>",
			"(#{item.barcodNumber},#{item.mId},#{item.materielNumber},#{item.materialName},#{item.materialQty},#{item.materialUnitID},#{item.materialUnitName},#{item.materialBatch},",
			"#{item.stockID},#{item.stockName},#{item.stockPlaceID},#{item.stockPlaceName},#{item.supplyID},#{item.supplyName},#{item.departmentID},",
			"#{item.departmentName},#{item.typeName},#{item.typeNumber},#{dateTime},#{status},#{item.zbm},1)",
			"</foreach>",
			"</script>"})
	void insertBarcodeNumber(@Param("data")List<BarcodeInfoEntity> data,@Param("dateTime")String dateTime,@Param("status")String status);
	
	/**
	 * 校验是否存在自编码
	 * @param ZBMs
	 * @return
	 */
	@Select("select count(*) from t_BarcodeInfo where ZBM in #{ZBMs}")
	int checkBarcodeInfo(String ZBMs);
	
	/**
	 * 校验条码状态
	 * @param barcodeNumber
	 * @param id
	 * @return
	 */
	@Select("select count(*) from t_BarcodeInfo where barcodNumber=#{barcodeNumber} and status=#{id}")
	int checkInOrOut(@Param("barcodeNumber")String barcodeNumber,@Param("id")int id);
	
	/**
	 * 插入包装箱条码
	 * @param barcodeNumber
	 * @param data
	 * @param dateTime
	 */
	@Insert({"<script>",
		"Insert into t_BoxBarcodeInfo (boxBarcodNumber,mateBarcodNumber,createTime) values ",
		"<foreach collection='data' item='item' index='index' separator=','>",
		"(#{barcodeNumber},#{item},#{dateTime})",
		"</foreach>",
		"</script>"})
	void insertBoxBarcodeNumber(@Param("barcodeNumber")String barcodeNumber,@Param("data")List<String> data,@Param("dateTime")String dateTime);
	
	@Delete({"<script>",
			"delete from t_BoxBarcodeInfo where boxBarcodNumber=#{boxBarcodeNumber} and mateBarcodNumber in (",
			"<foreach collection='data' item='item' index='index' separator=','>",
			"#{item}",
			"</foreach>",
			")",
			"</script>"})
	void delectFromBoxBarcodeNumber(@Param("data")List<String> data,@Param("boxBarcodeNumber")String boxBarcodeNumber);
	
	@Update("update t_BarcodeInfo set materialQty=#{mSum} where barcodNumber=#{barcodNumber}")
	void updateBarcodeNumber(@Param("barcodNumber")String barcodNumber,@Param("mSum")String mSum);


	@Update("update t_BarcodeInfo set stockPlaceName=(select FFullName from t_StockPlace where FNumber=#{stockPlace}) where barcodNumber=#{barcodNumber}")
	void updateNumberAndStackplace(@Param("barcodNumber")String barcodNumber,@Param("stockPlace")String stockPlace);
	/**
	 * 包装箱明细
	 * @param barcodeNumber
	 * @return
	 */
	@Select("select mateBarcodNumber from t_BoxBarcodeInfo where boxBarcodNumber=#{barcodeNumber}")
	List<String> searchBoxBarcodeNumber(String barcodeNumber);
	
	@Update({"<script>",
			"<foreach collection='data' item='item' index='index' separator=';'>",
			"update t_BarcodeInfo set stockPlaceID=(select FNumber from t_StockPlace where FSPID=#{item.positionId}),stockPlaceName=#{item.position} ",
			"where barcodNumber=#{item.barcodeNumber}",
			"</foreach>",
			"</script>"})
	void updateBarcodeNumberStock(@Param("data")List<MiscellaneousReceiptEntity> entitys);
	
	 /**
     * 获取条码总数
     * @param BarcodeNumber
     * @return
     */
    @Select({"<script>",
            "select COUNT(*) from t_BarcodeInfo where barcodNumber like #{BarcodeNumber} and status='1'",
            "</script>"})
    String getMailFileCount(@Param("BarcodeNumber")String BarcodeNumber);

    /**
     * 获取条码主档信息
     * @param BarcodeNumber
     * @return
     */
        @Select({"<script>",
                "select top ${size} rownumber,barcodNumber,materielNumber,materialName,materialQty,materialUnitID,materialUnitName,materialBatch,stockID,stockName,stockPlaceID,stockPlaceName,supplyID,supplyName,departmentID,departmentName,typeName,typeNumber,ZBM from (select row_number() over(order by id) as rownumber,* from t_BarcodeInfo where barcodNumber like #{BarcodeNumber} and status='1') a " +
                        "where rownumber>#{index}",
                "</script>"})
    @Results({
            @Result(property = "id",  column = "rownumber"),
            @Result(property = "barcodNumber",  column = "barcodNumber"),
            @Result(property = "materielNumber",  column = "materielNumber"),
            @Result(property = "materialName",  column = "materialName"),
            @Result(property = "materialQty",  column = "materialQty"),
            @Result(property = "materialUnitID",  column = "materialUnitID"),
            @Result(property = "materialUnitName",  column = "materialUnitName"),
            @Result(property = "materialBatch",  column = "materialBatch"),
            @Result(property = "stockID",  column = "stockID"),
            @Result(property = "stockName",  column = "stockName"),
            @Result(property = "stockPlaceID",  column = "stockPlaceID"),
            @Result(property = "stockPlaceName",  column = "stockPlaceName"),
            @Result(property = "supplyID",  column = "supplyID"),
            @Result(property = "supplyName",  column = "supplyName"),
            @Result(property = "departmentID",  column = "departmentID"),
            @Result(property = "departmentName",  column = "departmentName"),
            @Result(property = "typeName",  column = "typeName"),
            @Result(property = "typeNumber",  column = "typeNumber"),
            @Result(property = "zbm",  column = "ZBM")
    })
    List<BarcodeInfoEntity> searchBarcodeListInfo(@Param("index")String index,@Param("size")String size,@Param("BarcodeNumber")String BarcodeNumber);
}
