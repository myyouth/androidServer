package com.zjKingdee.androidServer.dao.primary;

import com.zjKingdee.androidServer.entity.InventoryEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 即使库存
 */
public interface InventoryMapper {
    @Select("select b.FNumber materialNumber,b.FName materialName,c.FName fstockName,a.FBatchNo,a.FQty,d.FName stockPlace from  icinventory a " +
            "inner join t_ICItem b on a.FItemID=b.FItemID " +
            "inner join t_Stock c on c.FItemID=a.FStockID " +
            "inner join t_StockPlace d on d.FSPID=a.FStockPlaceID "+
            "where b.FNumber=#{materialNumber}")
    List<InventoryEntity> selectInventory(String materialNumber);
}
