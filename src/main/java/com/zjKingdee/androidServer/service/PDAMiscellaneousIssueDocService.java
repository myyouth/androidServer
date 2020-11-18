package com.zjKingdee.androidServer.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.MiscellaneousIssueDocMapper;
import com.zjKingdee.androidServer.entity.MiscellaneousIssueDocEntity;

/**
 * 其他出库单
 * @author Yinqi				
 * @date 2020年3月25日
 */
@Service
public class PDAMiscellaneousIssueDocService {
	@Autowired
	private MiscellaneousIssueDocMapper mapper;
	public void submitMiscellaneousIssueDoc(String baseData,String materialList) {
		MiscellaneousIssueDocEntity issueEntity=JSONObject.parseObject(baseData, MiscellaneousIssueDocEntity.class);
		List<MiscellaneousIssueDocEntity> list=JSONArray.parseArray(materialList, MiscellaneousIssueDocEntity.class);
		for(MiscellaneousIssueDocEntity entity:list) {
			entity.setPositionId(entity.getPositionId().equals("null")?"0":entity.getPositionId());
		}
		mapper.insertForIssue(list, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), issueEntity);
	}
}
