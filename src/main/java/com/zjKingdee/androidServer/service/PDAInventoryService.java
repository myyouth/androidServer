package com.zjKingdee.androidServer.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.InventoryMapper;
import com.zjKingdee.androidServer.entity.InventoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;

@Service
public class PDAInventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;

    public JSONObject selectInventory(String materialNumber) {
        List<InventoryEntity> entitys=inventoryMapper.selectInventory(materialNumber);
        if(entitys.size()==0) {
            throw new RuntimeErrorException(null, "物料编码："+materialNumber+"不存在！");
        }

        InventoryEntity entity=entitys.get(0);
        JSONObject obj=JSONObject.parseObject(JSONObject.toJSONString(entity));
        JSONArray array=JSONArray.parseArray(JSONArray.toJSONString(entitys));
        obj.put("materialList", array);
        return obj;
    }
}
