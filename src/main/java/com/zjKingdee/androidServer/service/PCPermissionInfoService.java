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
import com.zjKingdee.androidServer.dao.primary.PermissionMapper;
import com.zjKingdee.androidServer.entity.PermissionInfoEntity;
@Service
public class PCPermissionInfoService {
	@Autowired
	private PermissionMapper mapper;
	public Map<String,Object> getAllPermissionInfo(JSONObject paramObj){
		Map<String,Object> map=new HashMap<String,Object>();
		List<PermissionInfoEntity> list=new ArrayList<PermissionInfoEntity>();
		String pageSize=paramObj.getString("pageSize");
		Integer pageIndex=(Integer.valueOf(paramObj.getString("pageIndex"))-1)*10;
		String paramValue2=paramObj.getString("param").equals("name")?"%"+paramObj.getString("value")+"%":"%%";
		list=mapper.searchAllPermissionInfo(String.valueOf(pageIndex),pageSize, paramValue2);
		int pageTatal=mapper.searchPermissionSum(paramValue2);
		map.put("data",list);
		map.put("pageTotal",pageTatal);
		return map;
	}
	public void createPermissionInfo(JSONObject paramObj) throws Exception {
		PermissionInfoEntity entity=JSONObject.toJavaObject(paramObj, PermissionInfoEntity.class);
		if(mapper.searchPermissionInfo(entity.getPermissionName())!=0){
			throw new Exception("当前权限已存在!");
		}
		entity.setPermissionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		mapper.createPermissionInfo(entity);
	}
	
	public void deletePermissionInfo(String roleInfo) {
		List<PermissionInfoEntity> list=JSONArray.parseArray(roleInfo,PermissionInfoEntity.class);
		mapper.delectPermissionInfo(list);
	}
}
