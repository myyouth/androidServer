package com.zjKingdee.androidServer.service;

import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.MainFileListMapper;
import com.zjKingdee.androidServer.entity.BarcodeInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PDAMainFileListService {
    @Autowired
    private MainFileListMapper mapper;
    public Map<String, Object> searchBarcodeNumber(JSONObject paramObj) {
        Map<String,Object> map=new HashMap<String,Object>();
        List<BarcodeInfoEntity> list=new ArrayList<BarcodeInfoEntity>();
        String pageSize=paramObj.getString("pageSize");
        Integer pageIndex=(Integer.valueOf(paramObj.getString("pageIndex"))-1)*10;
        String paramValue=paramObj.getString("param").equals("BarcodeNumber")?"%"+paramObj.getString("value")+"%":"%%";
        list=mapper.searchBarcodeNumber(String.valueOf(pageIndex),pageSize,paramValue);
        String pageTatal=mapper.getMailFileCount(paramValue);
        map.put("data",list);
        map.put("pageTotal",pageTatal);
        return map;
    }
}
