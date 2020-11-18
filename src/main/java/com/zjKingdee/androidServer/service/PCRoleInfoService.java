package com.zjKingdee.androidServer.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.RoleInfoMapper;
import com.zjKingdee.androidServer.entity.PermissionInfoEntity;
import com.zjKingdee.androidServer.entity.RoleInfoEntity;
import com.zjKingdee.androidServer.entity.UseInfoEntity;
@Service
public class PCRoleInfoService {
	@Autowired
	private RoleInfoMapper mapper;
	public void createRoleInfo(JSONObject paramObj) throws Exception {
		RoleInfoEntity entity=JSONObject.toJavaObject(paramObj, RoleInfoEntity.class);
		if(mapper.searchRoleInfo(entity.getRoleName())!=0){
			throw new Exception("当角色已存在!");
		}
		entity.setRoleTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		mapper.createRoleInfo(entity);
	}
	public void deleteRoleInfo(String roleInfo) {
		List<RoleInfoEntity> list=JSONArray.parseArray(roleInfo,RoleInfoEntity.class);
		mapper.delectRoleInfo(list);
	}
	public Map<String,Object> getAllRoleInfo(JSONObject paramObj){
		Map<String,Object> map=new HashMap<String,Object>();
		List<RoleInfoEntity> list=new ArrayList<RoleInfoEntity>();
		String pageSize=paramObj.getString("pageSize");
		Integer pageIndex=(Integer.valueOf(paramObj.getString("pageIndex"))-1)*10;
		String paramValue2=paramObj.getString("param").equals("name")?"%"+paramObj.getString("value")+"%":"%%";
		list=mapper.searchAllRoleInfo(String.valueOf(pageIndex),pageSize, paramValue2);
		int pageTatal=mapper.searchRoleSum(paramValue2);
		map.put("data",list);
		map.put("pageTotal",pageTatal);
		return map;
	}
	public JSONArray getAllRoleInfoForUser(){
		List<RoleInfoEntity> list=mapper.searchAllRoleInfoForUser();
		return JSONArray.parseArray(JSON.toJSONString(list));
	}
	/**
     * ---------------------------------------------------------------------------------------------------------------------------------------------------------
     */
	public JSONArray getAllUserInfoForRole(){
		List<UseInfoEntity> list=mapper.searchAllUserInfoForRole();
		return JSONArray.parseArray(JSON.toJSONString(list));
	}
	public void createUsersInRole(String roleId,String users) throws Exception {
		List<String> userIds=JSONArray.parseArray(users,String.class);
		for(String userId:userIds) {
			int sum=mapper.checkRole(roleId);
			if(sum==0) {
				throw new Exception("当前角色已不存在！");
			}
			String userName=mapper.searchUserInfoInRole(roleId, userId);
			if(userName!=null) {
				throw new Exception(userName+"已存在！");
			}
		}
		mapper.createUsersInRole(roleId, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), userIds);
	}
	public Map<String,Object> getAllUseInfoInRole(JSONObject paramObj){
		Map<String,Object> map=new HashMap<String,Object>();
		List<UseInfoEntity> list=new ArrayList<UseInfoEntity>();
		String pageSize=paramObj.getString("pageSize");
		String roleId=paramObj.getString("roleId");
		Integer pageIndex=(Integer.valueOf(paramObj.getString("pageIndex"))-1)*10;
		String paramValue=paramObj.getString("param").equals("number")?"%"+paramObj.getString("value")+"%":"%%";
		String paramValue2=paramObj.getString("param").equals("name")?"%"+paramObj.getString("value")+"%":"%%";
		list=mapper.searchAllUsersInRole(String.valueOf(pageIndex),pageSize,roleId,paramValue2,paramValue);
		int pageTatal=mapper.AllUsersInRoleSum(roleId,paramValue2,paramValue);
		map.put("data",list);
		map.put("pageTotal",pageTatal);
		return map;
	}
	public void deleteUsersInfoInRole(String userInfo,String roleId) {
		List<UseInfoEntity> list=JSONArray.parseArray(userInfo,UseInfoEntity.class);
		mapper.delectUsersInfoInRole(list, roleId);
	}
	/**
     * ---------------------------------------------------------------------------------------------------------------------------------------------------------
     */
	public JSONArray getAllPermissionInfoForRole(){
		List<PermissionInfoEntity> list=mapper.searchAllPermissionInfoForRole();
		return JSONArray.parseArray(JSON.toJSONString(list));
	}
	public void createPermissionsInRole(String roleId,String permissions) throws Exception {
		List<String> permissionIds=JSONArray.parseArray(permissions,String.class);
		for(String permissionId:permissionIds) {
			int sum=mapper.checkRole(roleId);
			if(sum==0) {
				throw new Exception("当前角色已不存在！");
			}
			String permissionName=mapper.searchPermissionsInfoInRole(roleId, permissionId);
			if(permissionName!=null) {
				throw new Exception(permissionName+"已存在！");
			}
		}
		mapper.createPermissionsInRole(roleId, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), permissionIds);
	}
	public Map<String,Object> getAllPermissionInfoInRole(JSONObject paramObj){
		Map<String,Object> map=new HashMap<String,Object>();
		List<PermissionInfoEntity> list=new ArrayList<PermissionInfoEntity>();
		String pageSize=paramObj.getString("pageSize");
		String roleId=paramObj.getString("roleId");
		Integer pageIndex=(Integer.valueOf(paramObj.getString("pageIndex"))-1)*10;
		String paramValue2=paramObj.getString("param").equals("name")?"%"+paramObj.getString("value")+"%":"%%";
		list=mapper.searchAllPermissionsInRole(String.valueOf(pageIndex),pageSize,roleId,paramValue2);
		int pageTatal=mapper.AllPermissionsInRoleSum(roleId,paramValue2);
		map.put("data",list);
		map.put("pageTotal",pageTatal);
		return map;
	}
	public void deletepermissionsInfoInRole(String permissionInfo,String roleId) {
		List<PermissionInfoEntity> list=JSONArray.parseArray(permissionInfo,PermissionInfoEntity.class);
		mapper.delectPermissionsInfoInRole(list, roleId);
	}
}
