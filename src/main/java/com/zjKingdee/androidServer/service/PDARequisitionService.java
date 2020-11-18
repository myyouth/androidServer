package com.zjKingdee.androidServer.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.RequisitionMapper;
import com.zjKingdee.androidServer.entity.MiscellaneousReceiptEntity;
import com.zjKingdee.androidServer.entity.RequisitionEnity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 调拨单
 */

@Service
public class PDARequisitionService {
    private static final Logger LOG = LoggerFactory.getLogger(PDAJobWorkingService.class);
    @Autowired
    private RequisitionMapper Mapper;

    public void submitRequisition(String baseData,String materialList) {
        RequisitionEnity RequisitionEnity= JSONObject.parseObject(baseData, RequisitionEnity.class);
        List<RequisitionEnity> list= JSONArray.parseArray(materialList, RequisitionEnity.class);
        for(RequisitionEnity entity:list) {
            entity.setPositionId(entity.getPositionId().equals("null")?"0":entity.getPositionId());
        }
        Mapper.insertForRequisition(list, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), RequisitionEnity);
    }
}
