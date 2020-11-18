package com.zjKingdee.androidServer.dao.primary;

import com.zjKingdee.androidServer.entity.BarcodeInfoEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface MainFileListMapper {
    /**
     * 获取单据总数
     * @param BarcodeNumber
     * @return
     */
    @Select({"<script>",
            "select COUNT(barcodNumber) from t_BarcodeInfo where barcodNumber like #{BarcodeNumber} ",
            "</script>"})
    String getMailFileCount(@Param("BarcodeNumber")String BarcodeNumber);

    /**
     * 获取条码主档信息
     * @param BarcodeNumber
     * @return
     */
        @Select({"<script>",
                "select top ${size} rownumber,barcodNumber,materielNumber,materialName,materialQty,materialUnitID,materialUnitName,materialBatch,stockID,stockName,stockPlaceID,stockPlaceName,supplyID,supplyName,departmentID,departmentName,typeName,typeNumber,ZBM from (select row_number() over(order by id) as rownumber,* from t_BarcodeInfo) a " +
                        "where rownumber>#{index} and barcodNumber like #{BarcodeNumber}",
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
    List<BarcodeInfoEntity> searchBarcodeNumber(@Param("index")String index,@Param("size")String size,@Param("BarcodeNumber")String BarcodeNumber);

}
