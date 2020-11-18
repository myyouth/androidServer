package com.zjKingdee.androidServer.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.UserInfoMapper;
import com.zjKingdee.androidServer.entity.UseInfoEntity;
import com.zjKingdee.androidServer.utils.EncryptionAlgorithmUtil;

@Service
public class PCUserInfoService {
	@Autowired
	private UserInfoMapper mapper;
	public Map<String,Object> getAllUseInfo(JSONObject paramObj){
		Map<String,Object> map=new HashMap<String,Object>();
		List<UseInfoEntity> list=new ArrayList<UseInfoEntity>();
		String pageSize=paramObj.getString("pageSize");
		Integer pageIndex=(Integer.valueOf(paramObj.getString("pageIndex"))-1)*10;
		String paramValue=paramObj.getString("param").equals("number")?"%"+paramObj.getString("value")+"%":"%%";
		String paramValue2=paramObj.getString("param").equals("name")?"%"+paramObj.getString("value")+"%":"%%";
		list=mapper.searchAllUserInfo(String.valueOf(pageIndex),pageSize,paramValue2,paramValue);
		int pageTatal=mapper.searchUserSum(paramValue2,paramValue);
		map.put("data",list);
		map.put("pageTotal",pageTatal);
		return map;
	}
	public void createUserInfo(JSONObject paramObj) throws Exception {
		UseInfoEntity entity=JSONObject.toJavaObject(paramObj, UseInfoEntity.class);
		if(mapper.searchUseInfo(entity.getUserId())!=0){
			throw new Exception("当前用户已存在!");
		}
		entity.setUserTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		entity.setUserPassword(EncryptionAlgorithmUtil.encryptionAlgorithm(entity.getUserPassword()));
		mapper.createUserInfo(entity);
	}
	
	public void deleteUserInfo(String useInfo) throws Exception {
		List<UseInfoEntity> list=JSONArray.parseArray(useInfo,UseInfoEntity.class);
		for(UseInfoEntity entity:list) {
			if("administrator".equals(entity.getUserName())) {
				throw new Exception("administrator用户不允许删除！");
			}
		}
		mapper.delectUserInfo(list);
	}
	
	public void ReviseUserInfo(JSONObject paramObj) throws Exception {
		UseInfoEntity entity=new UseInfoEntity();
		entity.setUserId(paramObj.getString("userId"));
		entity.setUserPassword(EncryptionAlgorithmUtil.encryptionAlgorithm(paramObj.getString("checkPass")));
		mapper.reviseUseInfo(entity);
	}
}
